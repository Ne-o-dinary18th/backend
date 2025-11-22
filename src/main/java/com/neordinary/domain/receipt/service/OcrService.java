package com.neordinary.domain.receipt.service;

import com.neordinary.global.apiPayload.code.status.ErrorStatus;
import com.neordinary.global.apiPayload.exception.GeneralException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class OcrService {

    // 날짜 패턴 (yyyy-mm-dd, yyyy.mm.dd, yyyy/mm/dd)
    private static final Pattern DATE_PATTERN =
            Pattern.compile("(\\d{4}[./-]\\d{1,2}[./-]\\d{1,2})");

    // 금액 패턴 (2,000원 / 2000 원 / 2000)
    private static final Pattern AMOUNT_PATTERN =
            Pattern.compile("([0-9]{1,3}(?:,[0-9]{3})*|[0-9]+)\\s*원?");

    public List<String> regularizate(String ocrText) {
        String normalized = normalize(ocrText);

        String store = findStoreText(normalized);
        Integer totalAmount = findTotalAmount(normalized);
        String dateTime = findDateTime(normalized);

        String key = "찾을 수 없음.";
        if (Objects.equals(store, key) || Objects.equals(dateTime, key)) {
            throw new GeneralException(ErrorStatus.RECEIPT_INVALID_INPUT);
        } else if (Objects.equals(totalAmount, 0)) {
            throw new GeneralException(ErrorStatus.RECEIPT_INVALID_INPUT);
        }

        return List.of(store, totalAmount.toString(), dateTime);
    }

    /**
     * 줄바꿈/탭/연속 공백을 모두 한 칸 공백으로 평탄화
     */
    private String normalize(String text) {
        if (text == null) return "";
        return text
                .replaceAll("[\\t\\r\\n]+", " ")   // 줄바꿈/탭 -> 공백
                .replaceAll(" +", " ")            // 연속 공백 -> 1칸
                .trim();
    }

    /**
     * 상호명 찾기:
     * 1) 키워드가 포함된 구간을 정규식으로 추출 (평탄화 대응)
     * 2) 그래도 없으면 기존 줄단위 fallback
     */
    public String findStoreText(String text) {

        // (주)OOO / 주식회사 OOO / 상호명 OOO / 상호 OOO 형태
        Pattern storePattern = Pattern.compile(
                "(상호명|상호|\\(주\\)|주식회사)\\s*[:]?\\s*([가-힣A-Za-z0-9()\\-_.]+)"
        );
        Matcher m = storePattern.matcher(text);
        if (m.find()) {
            // 키워드 + 실제 상호명만 깔끔하게 반환
            return (m.group(1) + " " + m.group(2)).trim();
        }

        // fallback: 혹시 \n 살아있는 경우 대비
        List<String> lines = Arrays.asList(text.split("\n"));
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.startsWith("상호명") ||
                    trimmed.startsWith("상호") ||
                    trimmed.startsWith("(주)") ||
                    trimmed.startsWith("주식회사")) {
                return trimmed;
            }
        }

        return "찾을 수 없음.";
    }

    /**
     * 총액 찾기:
     * 키워드가 있는 위치 뒤쪽에서 금액 패턴 추출
     */
    public Integer findTotalAmount(String text) {

        // 금액 키워드들
        String[] amountKeywords = {
                "이용금액", "합계 금액", "총액", "신용카드",
                "결제금액", "카드결제", "카 드 결 제", "결제 금액"
        };

        for (String key : amountKeywords) {
            int idx = text.indexOf(key);
            if (idx != -1) {
                // 키워드부터 뒤쪽 40자만 잘라서 금액 찾기 (너무 멀리 가면 오탐)
                String window = text.substring(idx, Math.min(text.length(), idx + 40));
                Integer amount = extractAmountAsInt(window);
                if (amount != null) return amount;
            }
        }

        // fallback: 라인 기반
        List<String> lines = Arrays.asList(text.split("\n"));
        for (String line : lines) {
            String trimmed = line.trim();
            for (String key : amountKeywords) {
                if (trimmed.startsWith(key)) {
                    Integer amount = extractAmountAsInt(trimmed);
                    return amount != null ? amount : 0;
                }
            }
        }

        return 0;
    }

    private Integer extractAmountAsInt(String textLine) {
        Matcher matcher = AMOUNT_PATTERN.matcher(textLine);
        if (matcher.find()) {
            String raw = matcher.group(1).replace(",", "");
            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 날짜 찾기:
     * 키워드 뒤쪽에서 DATE_PATTERN 찾기
     */
    public String findDateTime(String text) {

        String[] dateKeywords = {"이용일시", "거래일시", "일자", "승인일시"};

        for (String key : dateKeywords) {
            int idx = text.indexOf(key);
            if (idx != -1) {
                String window = text.substring(idx, Math.min(text.length(), idx + 40));
                Matcher matcher = DATE_PATTERN.matcher(window);
                if (matcher.find()) {
                    String date = matcher.group(1);
                    if (!isValidDate(date)) {
                        throw new GeneralException(ErrorStatus.RECEIPT_INVALID_INPUT);
                    }
                    return date;
                }
            }
        }

        // fallback: 라인 기반
        List<String> lines = Arrays.asList(text.split("\n"));
        for (String line : lines) {
            String trimmed = line.trim();
            for (String key : dateKeywords) {
                if (trimmed.startsWith(key)) {
                    Matcher matcher = DATE_PATTERN.matcher(trimmed);
                    if (matcher.find()) {
                        String date = matcher.group(1);
                        if (!isValidDate(date)) {
                            throw new GeneralException(ErrorStatus.RECEIPT_INVALID_INPUT);
                        }
                        return date;
                    }
                }
            }
        }

        return "찾을 수 없음.";
    }

    private boolean isValidDate(String dateStr) {
        try {
            String normalized = dateStr.replace(".", "-").replace("/", "-");
            LocalDate.parse(normalized, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

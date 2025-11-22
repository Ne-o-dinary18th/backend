package com.neordinary.domain.receipt.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class OcrService {

    // 정규식
    // 상호명, 총액, 일자
    public String regularizate(String ocrText) {
        String store = findStoreText(ocrText);
        Integer totalAmount = findTotalAmount(ocrText);
        String dateTime = findDateTime(ocrText);

        return store + totalAmount + dateTime;

    }

    public String findStoreText(String text) {
        // 상호명, 상호, (주)로 시작하는 행 추출
        // OCR 결과를 줄 단위로 분리
        List<String> lines = Arrays.asList(text.split("\n"));

        for (String line : lines) {
            String trimmed = line.trim();

            // 상호명, 상호, (주) 등으로 시작하는 라인 찾기
            if (trimmed.startsWith("상호명") ||
                    trimmed.startsWith("상호") ||
                    trimmed.startsWith("(주)") ||
                    trimmed.startsWith("주식회사")) {

                return trimmed;   // 조건을 만족하면 바로 반환
            }
        }

        return "찾을 수 없음."; // 없으면 null

    }

    public Integer findTotalAmount(String text) {
        List<String> lines = Arrays.asList(text.split("\n"));

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.startsWith("이용금액") ||
                    trimmed.startsWith("합계 금액") ||
                    trimmed.startsWith("총액") ||
                    trimmed.startsWith("결제금액")) {

                Integer amount = extractAmountAsInt(trimmed);

                return amount != null ? amount : 0;
            }
        }

        return 0; // 못 찾으면 null
    }

    private Integer extractAmountAsInt(String textLine) {
        // 숫자 + 쉼표 + 선택적으로 '원'
        Pattern pattern = Pattern.compile("([0-9,]+)원?");
        Matcher matcher = pattern.matcher(textLine);

        if (matcher.find()) {
            String raw = matcher.group(1); // ex: "2,000"
            raw = raw.replace(",", "");    // → "2000"

            try {
                return Integer.parseInt(raw); // 정수 변환
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return null;
    }


    public String findDateTime(String text) {
        // 이용일시, 거래일시, 승인일시, 일자
        List<String> lines = Arrays.asList(text.split("\n"));

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.startsWith("이용일시") ||
                    trimmed.startsWith("거래일시") ||
                    trimmed.startsWith("일자") ||
                    trimmed.startsWith("승인일시")) {

                return trimmed;   // 조건을 만족하면 바로 반환
            }
        }
        return "찾을 수 없음.";

    }
}

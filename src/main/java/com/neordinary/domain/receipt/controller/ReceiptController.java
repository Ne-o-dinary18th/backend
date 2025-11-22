package com.neordinary.domain.receipt.controller;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.dto.req.ReceiptRequest;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;
import com.neordinary.domain.receipt.service.OcrService;
import com.neordinary.domain.receipt.service.command.ReceiptCommandService;
import com.neordinary.domain.receipt.service.query.ReceiptQueryService;
import com.neordinary.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("/api")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptCommandService receiptCommandService;
    private final ReceiptQueryService receiptQueryService;
    private final OcrService ocrService;

    /**
     * /api/ocr
     * OCR 파싱 테스트
     * */
    @PostMapping("/ocr")
    public ApiResponse<List<String>> ocr(@RequestBody String exampleText) {
        return ApiResponse.onSuccess(ocrService.regularizate(exampleText));
    }

    /**
     * /api/receipts/upload
     * 영수증 생성 API
     *
     */
    @PostMapping(
            value = "/receipts/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Operation(summary = "영수증 생성", description = "새로운 영수증(지출 내역) 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "영수증 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 입력")
    })
    public ApiResponse<ReceiptResponse.UploadDTO> uploadReceipt(
            @RequestParam Long tagId,

            // ocrText를 multipart 안에서 텍스트 파트로 받기
            @RequestPart("ocrText") String ocrText,

            // 이미지 파일 파트
            @RequestPart("image") MultipartFile image
    ){
        return ApiResponse.onSuccess(receiptCommandService.uploadReceipt(tagId, ocrText, image));
    }

    /**
     * /api/receipts/{receiptId}
     * 영수증 상세페이지 조회(리스트에서 클릭시)
     *
     */
    @GetMapping("/receipts/{receiptId}")
    @Operation(summary = "영수증 상세 내용 조회", description = "단일 영수증의 상세 내용을 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "영수증 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "존재하지 않는 영수증입니다.")
    })
    public ApiResponse<ReceiptResponse.ReceiptResponseDTO> getReceipt(
            @PathVariable Long receiptId
    ){
       return ApiResponse.onSuccess(receiptQueryService.getReceipt(receiptId));
    }

    /**
     * /api/receipts/all
     * 영수증 총액 조회
     * 단일/복수 둘 다 가능
     *
     */
    @GetMapping("/receipts/all")
    @Operation(summary = "영수증 총 지출 조회", description = "선택한 영수증의 총 지출을 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "선택한 영수증 총 지출 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "존재하지 않는 영수증입니다.")
    })
    public ApiResponse<Long> getTotalAmount(
            @RequestParam List<Long> receiptIds
    ){
        return ApiResponse.onSuccess(receiptQueryService.getTotalAmount(receiptIds));
    }

    /**
     * /api/receipts/{receiptId}
     * 영수증 삭제
     *
     */
    @DeleteMapping("/receipts/{receiptId}")
    @Operation(summary = "영수증 삭제", description = "영수증(지출 내역) 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "영수증 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "존재하지 않는 영수증입니다.")
    })
    public ApiResponse<Long> deleteReceipt(
            @PathVariable Long receiptId
    ){
        return ApiResponse.onSuccess(receiptCommandService.deleteReceipt(receiptId));
    }
}

package com.neordinary.domain.receipt.service.command;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.converter.ReceiptConverter;
import com.neordinary.domain.receipt.dto.req.ReceiptRequest;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;
import com.neordinary.domain.receipt.repository.ReceiptRepository;
import com.neordinary.domain.receipt.service.OcrService;
import com.neordinary.domain.tag.Tag;
import com.neordinary.domain.tag.repository.TagRepository;
import com.neordinary.global.apiPayload.code.status.ErrorStatus;
import com.neordinary.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiptCommandServiceImpl implements ReceiptCommandService{

    private final ReceiptRepository receiptRepository;
    private final TagRepository tagRepository;
    private final OcrService ocrService;

    /** 영수증 업로드 메소드
     * OCR 문자열 파싱 및 예외 처리
     * */
    @Override
    public ReceiptResponse.UploadDTO uploadReceipt(Long tagId, String ocrText){
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TAG_NOT_FOUND));

        // OCR 문자열 파싱
        List<String> result = ocrService.regularizate(ocrText);

        String store = result.get(0);
        Integer totalPrice = Integer.valueOf(result.get(1));
        String purchaseDate = result.get(2);

        // 성공하면 영수증 생성
        Receipt entity = ReceiptConverter.toReceipt(store, purchaseDate, totalPrice, tag);
        receiptRepository.save(entity);

        return ReceiptConverter.toUploadDTO(entity);
    }

    /** 영수증 삭제 메소드
     * 영수증 삭제 및 예외 처리
     * */
    @Override
    public Long deleteReceipt(Long receiptId) {
        receiptRepository.findById(receiptId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RECEIPT_NOT_FOUND));

        receiptRepository.deleteById(receiptId);
        return receiptId;
    }
}
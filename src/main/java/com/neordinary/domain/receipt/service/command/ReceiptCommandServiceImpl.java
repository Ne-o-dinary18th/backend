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

    @Override
    public ReceiptResponse.UploadDTO uploadReceipt(Long tagId, String ocrText){
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TAG_NOT_FOUND));

        // convert ocr
        String store = ocrService.findStoreText(ocrText);
        String purchaseDate = ocrService.findDateTime(ocrText);
        Integer totalPrice = ocrService.findTotalAmount(ocrText);

        // entity save
        Receipt entity = ReceiptConverter.toReceipt(store, purchaseDate, totalPrice, tag);
        receiptRepository.save(entity);

        return ReceiptConverter.toUploadDTO(entity);
    }

    @Override
    public Long deleteReceipt(Long receiptId) {
        receiptRepository.deleteById(receiptId);
        return receiptId;
    }


}

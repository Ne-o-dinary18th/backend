package com.neordinary.domain.receipt.service.command;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.converter.ReceiptConverter;
import com.neordinary.domain.receipt.dto.req.ReceiptRequest;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;
import com.neordinary.domain.receipt.repository.ReceiptRepository;
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

    /** 영수증 업로드 메소드*/
    @Override
    public ReceiptResponse.UploadDTO uploadReceipt(ReceiptRequest.UploadDTO dto){

        try {
            Tag tag = tagRepository.findById(dto.tagId)
                    .orElseThrow(() -> new GeneralException(ErrorStatus.TAG_NOT_FOUND));
            Receipt entity = ReceiptConverter.toReceipt(dto, tag);

            validateUploadDto(dto);

            receiptRepository.save(entity);

            return ReceiptConverter.toUploadDTO(entity);
        } catch (GeneralException e){
            throw e; // 의도된 예외는 그대로
        } catch (Exception e) {
            throw new GeneralException(ErrorStatus.INTERNAL_ERROR);
        }
    }

    /** 예외 검사 메소드*/
    private void validateUploadDto(ReceiptRequest.UploadDTO dto) {
        validate(dto != null);
        validate(dto.getTagId() != null && dto.getTagId() > 0);
        validate(dto.getStoreName() != null && !dto.getStoreName().isBlank());
        validate(dto.getPurchaseDate() != null);
        validate(dto.getTotalAmount() != null && dto.getTotalAmount() > 0);
    }

    private void validate(boolean condition) {
        if (!condition) throw new GeneralException(ErrorStatus.INVALID_INPUT);
    }

    /** 영수증 삭제 메소드*/
    @Override
    public Long deleteReceipt(Long receiptId) {
        receiptRepository.deleteById(receiptId);
        return receiptId;
    }


}

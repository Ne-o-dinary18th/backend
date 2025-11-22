package com.neordinary.domain.receipt.service.command;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.converter.ReceiptConverter;
import com.neordinary.domain.receipt.dto.req.ReceiptRequest;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;
import com.neordinary.domain.receipt.repository.ReceiptRepository;
import com.neordinary.domain.tag.Tag;
import com.neordinary.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiptCommandServiceImpl implements ReceiptCommandService{

    private static ReceiptRepository receiptRepository;
    private static TagRepository tagRepository;

    @Override
    public ReceiptResponse.UploadDTO uploadReceipt(ReceiptRequest.UploadDTO dto){
        Tag tag = tagRepository.findById(dto.tagId).orElseThrow();
        Receipt entity = ReceiptConverter.toReceipt(dto, tag);

        receiptRepository.save(entity);

        return ReceiptConverter.toUploadDTO(entity);
    }

    @Override
    public Long deleteReceipt(Long receiptId) {
        receiptRepository.deleteById(receiptId);
        return receiptId;
    }


}

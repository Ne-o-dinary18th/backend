package com.neordinary.domain.tag.service;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.repository.ReceiptRepository;
import com.neordinary.domain.tag.Tag;
import com.neordinary.domain.tag.User;
import com.neordinary.domain.tag.dto.TagResponseDto;
import com.neordinary.domain.tag.dto.TagUserResponseDto;
import com.neordinary.domain.tag.repository.TagRepository;
import com.neordinary.domain.tag.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TagQueryService {
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final ReceiptRepository receiptRepository;

    public TagResponseDto.TagListDto retrieveAllTags() {
        // find tags
        List<Tag> tags = tagRepository.findAll();

        // convert to TagDto
        List<TagResponseDto.TagDto> tagDtos = tags.stream()
                .map(tag -> TagResponseDto.TagDto.convertToTagDto(
                        tag.getTagId(),
                        tag.getTitle()
                ))
                .toList();

        // wrap list
        return TagResponseDto.TagListDto.builder()
                .tags(tagDtos)
                .build();
    }

    public TagUserResponseDto.TagUserListDto getTagUsers(Long tagId) {
        // find tag
        // Todo: 에러처리
        var tag = tagRepository.findById(tagId).orElseThrow();

        // find user
        List<User> users = userRepository.findUsersByTag_TagId(tagId);
        List<TagUserResponseDto.TagUserDto> tagUserDtos = users.stream()
                .map(user -> TagUserResponseDto.TagUserDto.convertToTagUserDto(
                        user.getUserId(),
                        user.getName()
                ))
                .toList();


        // wrap list
        return TagUserResponseDto.TagUserListDto.builder()
                .tagId(tag.getTagId())
                .tagName(tag.getTitle())
                .tagUsers(tagUserDtos)
                .build();
    }

    public TagResponseDto.TagDetailDto getTagReceipts(Long tagId) {
        // find tag, users
        var tag = tagRepository.findById(tagId).orElseThrow();
        List<User> users = userRepository.findUsersByTag_TagId(tagId);
        var userAmount = users.size();

        // Todo: 에러 핸들러
        // find receipts
        var receipts =  receiptRepository.findReceiptsByTag_TagId(tagId);

        // calculate totalAmount
        Integer receiptAmount = Math.toIntExact(receipts.stream()
                .mapToLong(Receipt::getTotalAmount)
                .sum());


        // wrap list
        List<TagResponseDto.ReceiptDto> receiptDtos = receipts.stream()
                .map(receipt -> TagResponseDto.ReceiptDto.convertToTagDetail(
                        receipt.getReceiptId(),
                        receipt.getStoreName(),
                        receipt.getPurchaseDate(),
                        receipt.getTotalAmount()
                )).toList();

        TagResponseDto.TagDetailDto tagDetailDto = TagResponseDto.TagDetailDto.builder()
                .tagId(tag.getTagId())
                .tagName(tag.getTitle())
                .totalAmount(receiptAmount)
                .totalUsers(userAmount)
                .receipts(receiptDtos)
                .build();
        return tagDetailDto;

    }
}

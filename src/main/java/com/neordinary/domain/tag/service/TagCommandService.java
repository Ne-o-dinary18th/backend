package com.neordinary.domain.tag.service;

import com.neordinary.domain.tag.Tag;
import com.neordinary.domain.tag.User;
import com.neordinary.domain.tag.repository.TagRepository;
import com.neordinary.domain.tag.repository.UserRepository;
import com.neordinary.global.apiPayload.code.status.ErrorStatus;
import com.neordinary.global.apiPayload.exception.GeneralException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagCommandService {
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public Tag createTag(String tagName) {
        if (tagName == null || tagName.isBlank()) {
            throw new GeneralException(ErrorStatus.TAG_NAME_REQUIRED);
        }

        // 태그명 중복 검사
        if (tagRepository.existsByTitle(tagName)) {
            throw new GeneralException(ErrorStatus.TAG_NAME_ALREADY_EXISTS);
        }

        var tag = Tag.builder().title(tagName).build();
        tagRepository.save(tag);
        return tag;
    }

    public User createUser(Long tagId, String userName) {
        // find tag
        var tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TAG_NOT_FOUND));
        var user = User
                .builder()
                .name(userName)
                .tag(tag)
                .build();
        userRepository.save(user);
        return user;
    }

    // 태그 삭제 api
    public void deleteTag(Long tagId) {
        var tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TAG_NOT_FOUND));

        tagRepository.deleteById(tagId);
    }

    public Tag updateTag(Long tagId, String tagName) {
        var tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TAG_NOT_FOUND));
        if (tagName == null || tagName.isBlank()) {
            throw new GeneralException(ErrorStatus.TAG_NAME_REQUIRED);
        }

        if (tagRepository.existsByTitle(tagName)) {
            throw new GeneralException(ErrorStatus.TAG_NAME_ALREADY_EXISTS);
        }

        tag.setTitle(tagName);
        tagRepository.save(tag);
        return tag;

    }
}

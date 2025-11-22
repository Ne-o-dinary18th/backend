package com.neordinary.domain.tag.service;

import com.neordinary.domain.tag.dto.TagResponseDto;
import com.neordinary.domain.tag.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagQueryService {
    private final TagRepository tagRepository;

    public TagResponseDto.TagListDto retrieveAllTags() {
        return null;
    }

    public Object getTagUsers(String tagId) {
        return null;
    }
}

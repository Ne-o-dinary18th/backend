package com.neordinary.domain.tag.dto;

import com.neordinary.domain.tag.Tag;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TagResponseDto {

    @Builder
    public static class TagDto {
        Long tagId;
        String tagName;
        // @ExistCategoryColors

        public static TagResponseDto.TagDto convertToTagDto(Long tagId, String tagName) {
            return TagResponseDto.TagDto.builder()
                    .tagId(tagId)
                    .tagName(tagName)
                    .build();

        }
    }

    @Builder
    public static class TagListDto {
        List<TagDto> tags;
    }

    /**
     * 모든 태그 조회 dto
     *
     * */


}

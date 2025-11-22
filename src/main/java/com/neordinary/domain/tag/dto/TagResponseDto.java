package com.neordinary.domain.tag.dto;

import lombok.*;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TagResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TagListDto {
        List<TagDto> tags;
    }


}

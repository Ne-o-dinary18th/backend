package com.neordinary.domain.tag.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TagUserResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TagUserDto {
        Long userId;
        String username;
        // @ExistCategoryColors

        public static TagUserResponseDto.TagUserDto convertToTagUserDto(Long userId, String username) {
            return TagUserDto.builder()
                    .userId(userId)
                    .username(username)
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TagUserListDto {
        Long tagId;
        String tagName;
        List<TagUserResponseDto.TagUserDto> tagUsers;
    }
}

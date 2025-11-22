package com.neordinary.domain.tag.controller;

import com.neordinary.domain.tag.dto.TagResponseDto;
import com.neordinary.domain.tag.dto.TagUserResponseDto;
import com.neordinary.domain.tag.service.TagCommandService;
import com.neordinary.domain.tag.service.TagQueryService;
import com.neordinary.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
public class TagController {

    final public TagQueryService tagQueryService;
    final public TagCommandService tagCommandService;

    @Operation(summary = "태그 생성 API", description = "태그 생성 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG4001", description = "태그 생성에 실패했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG4003", description = "태그명은 필수입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG4004", description = "해당 태그명은 이미 존재합니다."),

    })
    @PostMapping("/upload")
    public ApiResponse<?> createTag(
            @RequestParam(defaultValue = "tag1", required = false, name = "tagName") String tagName

    ) {
        return ApiResponse.onSuccess(tagCommandService.createTag(tagName));
    }

    /*
    * /api/tag/all
    * 태그 조회 api
    * */
    @Operation(summary = "태그 조회 API", description = "태그 전체 조회")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @GetMapping("/all")
    public ApiResponse<TagResponseDto.TagListDto> getAllTags() {
         return ApiResponse.onSuccess(tagQueryService.retrieveAllTags());
    }

     /**
     * /api/tag/{tagId}/user
     * 태그의 유저 생성 api
     *
     * */

    @Operation(summary = "태그의 유저 생성 API", description = "태그의 유저 생성")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG4001", description = "태그 생성에 실패했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG4002", description = "해당 태그가 없습니다."),
    })
    @PostMapping("/{tagId}/user")
    public ApiResponse<?> createTagUser(
            @PathVariable Long tagId,
            @RequestParam(defaultValue = "사용자1", required = false, name = "userName") String userName
    ) {
        return ApiResponse.onSuccess(tagCommandService.createUser(tagId, userName));
    }
    /**
     * /api/tag/{tagId}/user
     * 태그의 유저 조회 api
     * */
    @Operation(summary = "태그의 유저 조회 API", description = "태그의 유저 조회")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG4002", description = "해당 태그가 없습니다."),

    })
    @GetMapping("/{tagId}/user")
    public ApiResponse<TagUserResponseDto.TagUserListDto> createTagUser(
            @PathVariable Long tagId
    ) {
        return ApiResponse.onSuccess(tagQueryService.getTagUsers(tagId));
    }

    /*
     * /api/tag/{tagId}/detail
     * 태그 상세페이지 조회 api
     * return: tag detail + receipts
     * */
    @Operation(summary = "태그 상세 조회 API", description = "태그 상세 내용 + 영수증 조회")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG4002", description = "해당 태그가 없습니다."),

    })
    @GetMapping("/{tagId}/detail")
    public ApiResponse<TagResponseDto.TagDetailDto> getDetailTagPage(
            @PathVariable Long tagId
    ) {
        return ApiResponse.onSuccess(tagQueryService.getTagReceipts(tagId));
    }


    /*
     * /api/tag/receipt/all
     * 태그-영수증 리스트 조회 api
     * return: tag  + receipts sets
     * */
    @Operation(summary = "태그-영수증들 조회 API", description = "메인페이지 태그 + 영수증들 조회")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),

    })
    @GetMapping("/receipt/all")
    public ApiResponse<List<TagResponseDto.TagReceiptListDto>> getTagReceiptsList() {
        return ApiResponse.onSuccess(tagQueryService.getAllTagsAndReceipts());
    }

    /*
     * delete /api/tag/{tagId}
     * 태그 삭제 api
     *
     * */
    @Operation(summary = "태그 삭제 API", description = "테그 삭제 api")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG4002", description = "해당 태그가 없습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG4005", description = "해당 태그를 삭제할 수 없습니다."),

    })
    @DeleteMapping("/{tagId}")
    public ApiResponse<?> deleteTag(
            @PathVariable Long tagId
    ) {
        tagCommandService.deleteTag(tagId);
        return ApiResponse.onSuccessWithoutResult();
    }

    /*
     * patch /api/tag/{tagId}
     * 태그 수정 api
     *
     * */
    @Operation(summary = "태그 수정 API", description = "테그 수정 api")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG_CREATE_ERROR", description = "태그 생성에 실패했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG4002", description = "해당 태그가 없습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG4004", description = "해당 태그명은 이미 존재합니다."),

    })
    @PatchMapping("/{tagId}")
    public ApiResponse<?> updateTag(
            @PathVariable Long tagId,
            @RequestParam(defaultValue = "태그999", required = false, name = "tagName") String tagName
    ) {

        return ApiResponse.onSuccess(tagCommandService.updateTag(tagId, tagName));
    }

}

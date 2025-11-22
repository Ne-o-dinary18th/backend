package com.neordinary.global.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3Repository {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;

    // receiptId으로 폴더 만든 후 영수증 원본 파일 저장.
    public String upload(MultipartFile image, Long receiptId) {
        String fileName = getFileName(receiptId, image); // "10.jpg"

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(image.getContentType());
        metadata.setContentLength(image.getSize());

        try {
            amazonS3.putObject(bucketName, fileName, image.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException("S3 업로드 실패", e);
        }

        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    private String getFileName(Long receiptId, MultipartFile file) {
        String extension = "";
        String original = file.getOriginalFilename();

        if (original != null && original.contains(".")) {
            extension = original.substring(original.lastIndexOf("."));
        }

        return receiptId + extension;  // 예: 15.jpg
    }
}

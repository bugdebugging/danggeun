package com.danggeun.market.common.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3FileUploader implements FileUploader {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Override
    public String upload(String fileName, File file) {
        String uploadedFileUrl = putS3(fileName, file);
        removeTmpFile(file);
        return uploadedFileUrl;
    }

    @Override
    public String upload(String fileName, MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());

        if (file.createNewFile()) {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return upload(fileName, file);
    }

    private String putS3(String fileName, File file) {
        amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    private void removeTmpFile(File file) {
        file.delete();
    }
}

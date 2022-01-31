package com.danggeun.market.common.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileUploader {
    String upload(String fileName, File file);

    String upload(String fileName, MultipartFile multipartFile) throws IOException;
}

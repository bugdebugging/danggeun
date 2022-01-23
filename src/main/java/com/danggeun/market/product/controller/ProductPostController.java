package com.danggeun.market.product.controller;

import com.danggeun.market.common.api.ApiResult;
import com.danggeun.market.common.api.ApiUtils;
import com.danggeun.market.common.file.FileUploader;
import com.danggeun.market.product.domain.Money;
import com.danggeun.market.product.domain.ProductImage;
import com.danggeun.market.product.dto.ProductDetailResponse;
import com.danggeun.market.product.dto.ProductPostRequest;
import com.danggeun.market.product.service.ProductPostService;
import com.danggeun.market.product.service.dto.ProductPostCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductPostController {
    private final ProductPostService productPostService;
    private final FileUploader fileUploader;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResult<ProductDetailResponse> postProduct(@RequestPart(value = "product_images", required = false) List<MultipartFile> productImages,
                                                        @RequestPart("product_info") ProductPostRequest productPostRequest) {
        String prefix = "users/" + productPostRequest.getUserId() + "/products/";
        List<ProductImage> uploadedProductImages = IntStream.range(0, productImages.size())
                .mapToObj(idx -> toProductImage(prefix + idx, productImages.get(idx)))
                .collect(Collectors.toList());


        ProductPostCommand productPostCommand = new ProductPostCommand(productPostRequest.getUserId(),
                productPostRequest.getCategoryId(),
                productPostRequest.getName(),
                Money.of(productPostRequest.getPrice()),
                productPostRequest.getDescription(),
                uploadedProductImages);
        return ApiUtils.success(productPostService.postProduct(productPostCommand));
    }

    private ProductImage toProductImage(String fileName, MultipartFile multipartFile) {
        try {
            return ProductImage.of(fileUploader.upload(fileName, multipartFile));
        } catch (IOException exception) {
            throw new IllegalArgumentException("상품 이미지 업로드 실패:" + exception.getMessage());
        }
    }
}

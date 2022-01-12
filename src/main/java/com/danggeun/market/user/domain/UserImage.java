package com.danggeun.market.user.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
public class UserImage {
    @Column(name = "image_url")
    private String imageUrl;

    protected UserImage() {
    }

    private UserImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static UserImage of(String imageUrl) {
        return new UserImage(imageUrl);
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

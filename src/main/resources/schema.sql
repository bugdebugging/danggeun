CREATE TABLE users
(
    id         bigint                             NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email      varchar(30)                        NOT NULL,
    password   varchar(260)                       NOT NULL,
    phone      varchar(20)                        NOT NULL,
    name       varchar(10)                        NOT NULL,
    nickname   varchar(30)                        NOT NULL,
    created_at datetime default CURRENT_TIMESTAMP NOT NULL,
    updated_at datetime default CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE categories
(
    id   bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(30) NOT NULL
);

CREATE TABLE products
(
    id                  bigint                             NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id             bigint                             NOT NULL,
    category_id         bigint                             NOT NULL,
    name                varchar(50)                        NOT NULL,
    price               bigint                             NOT NULL,
    description         varchar(2048)                      NOT NULL,
    status              varchar(20)                        NOT NULL,
    thumbnail_image_url varchar(100)                       NULL,
    created_at          datetime default CURRENT_TIMESTAMP NOT NULL,
    updated_at          datetime default CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE replies
(
    id         bigint                             NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id    bigint                             NOT NULL,
    product_id bigint                             NOT NULL,
    comment    varchar(1024)                      NOT NULL,
    created_at datetime default CURRENT_TIMESTAMP NOT NULL,
    updated_at datetime default CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE interest_product_histories
(
    id         bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id    bigint NOT NULL,
    product_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE user_images
(
    user_id   bigint       NOT NULL,
    image_url varchar(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE product_images
(
    product_id bigint       NOT NULL,
    image_url  varchar(100) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id)
);

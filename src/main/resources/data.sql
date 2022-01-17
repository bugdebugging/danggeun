insert into users(email, password, phone, name, nickname)
values ('myemail@kw.ac.kr', 'sample_pw', '111-1111-111', 'myname', 'mynickname');

insert into users(email, password, phone, name, nickname)
values ('youremail@kw.ac.kr', 'sample_pw2', '222-2222-222', 'yourname', 'yournickname');

insert into users(email, password, phone, name, nickname)
values ('email@company.com', 'pw', '333-3333-333', 'name', 'nickname');

insert into categories(name) values ('디지털기기');
insert into categories(name) values ('생활가전');

-- 1
insert into products(user_id,category_id, name, price, description, status,thumbnail_image_url)
values (1, 1, 'nike sneakers', 58000, '한번만 신은 나이키 신발입니다.', 'SELL','https://s3.ap-northeast-2.amazonaws.com/danggeun/product/1/1');
-- 2
insert into products(user_id,category_id, name, price, description, status,thumbnail_image_url)
values (1, 2, 'LG 냉장고', 1250000, '가성비 냉장고 판매', 'SELL','https://s3.ap-northeast-2.amazonaws.com/danggeun/product/2/1');
-- 3
insert into products(user_id,category_id, name, price, description, status)
values (1, 2, '조명등', 33000, '굉장히 밝은 조명', 'SOLD');

insert into products(user_id,category_id, name, price, description, status)
values (2, 1, 'MAC 노트북', 2500000, 'MAC BOOK 파라염', 'SELL');
insert into products(user_id,category_id, name, price, description, status)
values (2, 1, 'LG 모니터', 100000, '개꿀 모니터', 'SOLD');
insert into products(user_id,category_id, name, price, description, status)
values (2, 1, '마우스', 20000, '얼마 안쓴 마우스입니다', 'SELL');
insert into products(user_id,category_id, name, price, description, status)
values (2, 1, '로지텍 키보드', 55000, '에누리 금지', 'SOLD');


-- 1 댓글
insert into replies(user_id, product_id, comment)
values (2, 1, '물건 좋아보이네요 사고싶습니다.');
insert into replies(user_id, product_id, comment)
values (2, 1, '어디서 거래하실래요?');

-- 2 댓글
insert into replies(user_id, product_id, comment)
values (2, 2, '가격이 조금 비싼데요?');

-- 3 댓글
insert into replies(user_id, product_id, comment)
values (2, 3, '조명등 밝기 샌편인가요?');
insert into replies(user_id, product_id, comment)
values (2, 3, '설치가 간단할가요?');
insert into replies(user_id, product_id, comment)
values (2, 3, '1000원만 깍아주세요');

-- 1 관심
insert into interest_product_histories(user_id, product_id)
values (2, 1);
-- 3 관심
insert into interest_product_histories(user_id, product_id)
values (1, 3);
insert into interest_product_histories(user_id, product_id)
values (2, 3);
insert into interest_product_histories(user_id, product_id)
values (3, 3);

-- 1 이미지
insert into product_images(product_id, image_url)
values (1, 'https://s3.ap-northeast-2.amazonaws.com/danggeun/product/1/1');
insert into product_images(product_id, image_url)
values (1, 'https://s3.ap-northeast-2.amazonaws.com/danggeun/product/1/2');
-- 2 이미지
insert into product_images(product_id, image_url)
values (2, 'https://s3.ap-northeast-2.amazonaws.com/danggeun/product/2/1');
insert into product_images(product_id, image_url)
values (2, 'https://s3.ap-northeast-2.amazonaws.com/danggeun/product/2/2');

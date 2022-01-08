insert into users(email, password, phone, name, nickname)
values ('myemail@kw.ac.kr', 'sample_pw', '111-1111-111', 'myname', 'mynickname');

insert into categories(name) values ('디지털기기');
insert into categories(name) values ('생활가전');

insert into products(user_id,category_id, name, price, description, status)
values (1, 1, 'nike sneakers', 58000, '한번만 신은 나이키 신발입니다.', 'SELL');


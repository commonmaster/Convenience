drop database if exists convenience;
create database convenience;


use convenience;

drop table if exists board;
drop table if exists product;

create table board
(
no int primary key auto_increment,
title varchar(255) not null,
authorId varchar(255) not null,
contents text,
regDate datetime not null,
modifyDate datetime not null,
readCount int not null default 0
);

create table product
(
barcode int primary key auto_increment,
name varchar(128) not null,
type varchar(64) not null,
price int not null,
provider varchar(128) not null,
imgUrl varchar(255) not null,
intro varchar(255)
);

insert into board(title,authorId,contents,regDate,modifyDate) values('first board','user1','welcome to board','2022-01-11 09:13','2022-01-11 09:13');
insert into board(title,authorId,contents,regDate,modifyDate) values('second board','user1','welcome to board222','2022-01-11 11:15','2022-01-11 11:15');
insert into board(title,authorId,contents,regDate,modifyDate) values('i am sam','user1','i am sam!!!!!!!!!!!','2022-01-12 09:13','2022-01-12 09:13');
insert into board(title,authorId,contents,regDate,modifyDate) values('you are sam','user1','you are sam and i am sung','2022-01-12 11:15','2022-01-12 11:15');
insert into board(title,authorId,contents,regDate,modifyDate) values('spring is difficult','user1','spring is very very difficult!!!!','2022-01-12 21:13','2022-01-12 21:13');
insert into board(title,authorId,contents,regDate,modifyDate) values('java is easy','user2','java is programming language for network','2022-01-12 22:17','2022-01-12 22:17');
insert into board(title,authorId,contents,regDate,modifyDate) values('who are you?','user2','i met you but i do not know you','2022-01-13 09:13','2022-01-13 09:13');
insert into board(title,authorId,contents,regDate,modifyDate) values('tomorrow is happy day','user2','i am happy so every day happy','2022-01-13 11:15','2022-01-13 11:15');
insert into board(title,authorId,contents,regDate,modifyDate) values('apple is good','user1','apple notebook is expensive but this design is very good','2022-01-13 15:13','2022-01-13 15:13');
insert into board(title,authorId,contents,regDate,modifyDate) values('git is version management sw','user2','version management is important','2022-01-13 15:15','2022-01-13 15:15');


insert into product(name,type,price,provider,imgUrl,intro) values("pepero", "snack", 1500, 'lotte', 'pepero.png','This snack is very very long, so we eat long time');
insert into product(name,type,price,provider,imgUrl, intro) values("merona", "icecream", 1000, 'binggre', 'merona.png','this icecream dont contain melon, just name is smilar melon, but this taste is very good!');
insert into product(name,type,price,provider,imgUrl, intro) values("sevensaida", "drink", 1000, 'lotte', 'sevensaida.png','seven star is good provider, this provider drink is great lovely');
insert into product(name,type,price,provider,imgUrl, intro) values('honeybagi', "snack", 1500, 'nongsim', 'honeybagi.png','honeybagi is good snack');
insert into product(name,type,price,provider,imgUrl, intro) values("strawberrymilk", "drink", 1300, 'seoul', 'strawberrymilk.png','straw taste milk');
insert into product(name,type,price,provider,imgUrl, intro) values("bananamilk", "drink", 1300, 'seoul', 'bananamilk.png','banana taste milk');
insert into product(name,type,price,provider,imgUrl, intro) values("ricestar", "snack", 1500, 'lotte', 'ricestar.png','ricestar is make of rice');
insert into product(name,type,price,provider,imgUrl, intro) values("ozingoamond", "snack", 1500, 'orion', 'ozingoamond.png','ozinoamond is ozinoamond + amond');

insert into product(name,type,price,provider,imgUrl, intro) values("amatna", "icecream", 1000, 'lotte', 'amatna.png','amatna is lotte food icecream');
insert into product(name,type,price,provider,imgUrl, intro) values("bibibik", "icecream", 1000, 'binggre', 'bibibik.png','bibibik is pat icecream');
insert into product(name,type,price,provider,imgUrl, intro) values("cancho", "snack", 1000, 'lotte', 'cancho.png','canko is choko snack pictured character');
insert into product(name,type,price,provider,imgUrl, intro) values("cokacola", "drink", 1300, 'coka', 'cokacola.png','cokacola is very famous drink, this drink has a fans');
insert into product(name,type,price,provider,imgUrl, intro) values("doublecon", "icecream", 1800, 'lotte', 'doublecon.png','doublecon is lotte icecream and this is expensive');
insert into product(name,type,price,provider,imgUrl, intro) values("geogia", "drink", 1000, 'coka', 'geogia.png','amatna is lotte food icecream');
insert into product(name,type,price,provider,imgUrl, intro) values("goraebab", "snack", 1000, 'orion', 'goraebab.png','goraebab is character food snack');
insert into product(name,type,price,provider,imgUrl, intro) values("kokalcon", "snack", 1500, 'lotte', 'kokalcon.png','kokalcon is lotte snack, this snack is kokal sharped');
insert into product(name,type,price,provider,imgUrl, intro) values("papico", "icecream", 1000, 'lotte', 'papico.png','papico is lotte food icecream');
insert into product(name,type,price,provider,imgUrl, intro) values("pepero_amond", "snack", 1500, 'lotte', 'pepero_amond.png','pepero_amond is pepero amond version');
insert into product(name,type,price,provider,imgUrl, intro) values("pepsi", "drink", 1500, 'pep', 'pepsi.png','pepsi is korean drink');
insert into product(name,type,price,provider,imgUrl, intro) values("scrubar", "icecream", 1000, 'lotte', 'scrubar.png','scrubar is lotte food icecream');
insert into product(name,type,price,provider,imgUrl, intro) values("sprite_zero", "drink", 1500, 'coka', 'sprite_zero.png','sprite is zero drink');
insert into product(name,type,price,provider,imgUrl, intro) values("pokachip_original", "snack", 1500, 'orion', 'pokachip_original.png','pokachip_original is potato slice snack, this taste is salt');
insert into product(name,type,price,provider,imgUrl, intro) values("pokachip_onion", "snack", 1500, 'orion', 'pokachip_onion.png','pokachip_onion is potato slice snack, this taste is onion and sweet');






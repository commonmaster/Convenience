drop database if exists convenience;
create database convenience;

use convenience;

drop table if exists board;
drop table if exists product;
drop table if exists member;

create table member
(
id varchar(64) primary key,
password varchar(64) not null,
name varchar(64) not null,
email varchar(128),
regDate TIMESTAMP NULL DEFAULT current_timestamp,
state int default 1,
no int auto_increment unique
);

insert into member(id,password,name,email,no) values('admin','1111','관리자JU','admin@ju.com',1);

create table board
(
no int primary key auto_increment,
title varchar(255) not null,
authorId varchar(255) not null,
contents text,	
regDate datetime DEFAULT current_timestamp,
modifyDate datetime DEFAULT current_timestamp,
readCount int not null default 0,
isDeleted int default 0
);

create table product
(
barcode int primary key,
name varchar(128) not null,
type int not null,
price int not null,
provider varchar(128) not null,
productImg longblob,
intro varchar(255),
isExcluded int default 0
);
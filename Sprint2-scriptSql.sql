create database first_question;
use first_question;
#drop database first_question;
create table if not exists product (
id int AUTO_INCREMENT PRIMARY KEY,
name varchar(50) not null,
description varchar (70) not null,
quantity int not null,
price decimal not null
);

create database second_question;
use second_question;
#drop table movie;
select * from movie;
create table if not exists movie (
id int AUTO_INCREMENT PRIMARY KEY,
name varchar(70) not null,
description varchar (20) not null,
year integer not null
);
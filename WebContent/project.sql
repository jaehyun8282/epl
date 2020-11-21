create table s_manager(
	manager_id varchar2(15) primary key,
	manager_pass varchar2(15) not null
);
insert into s_manager values ('master', '1234' );
create table s_member(
	id varchar2(15) primary key,
	password varchar2(20) not null,
	name varchar2(20) not null,
	gender varchar2(10) not null,
	birthday varchar2(10) not null,
	tel varchar2(15) not null,
	email varchar2(30) not null,
	post varchar2(10) not null,
	address varchar2(60) not null,
	reg_date date not null
);
alter table s_member add(del char(1) default 'n');
select*from s_member;
-- create sequence s_board_seq;  
create table s_board (
	board_id number primary key,
	id varchar2(15) references s_member(id),
	board_pass varchar2(20) not null,
	subject varchar2(30) not null,
	content varchar2(500) not null,
	board_date date not null,
	readcount number default 0,
	ref number not null,
	re_step number not null,
	re_level number not null,
	reg_date date not null
);
select * from board1;
create sequence s_product_seq;
create table s_product (
	product_id number primary key,
	p_name varchar2(30) not null,
	p_price number(10) not null,
	p_count number(5) not null,
	p_category varchar2(30) not null,
	p_image varchar2(30) default 'nothing.jpg',
	p_rate number(3) not null,
	p_date date not null
);
create table s_bank (
	account varchar2(30) primary key,
	bank varchar2(15) not null,
	account_name varchar2(15) not null
);
insert into s_bank values('1111','�??','김건우');
insert into s_bank values('2222','?�한','박시?�');
insert into s_bank values('3333','?�리','박재??);
create sequence s_cart_seq;
create table s_cart (
	cart_id number(10) primary key,
	id varchar2(15) references s_member(id),
	p_name varchar2(30) not null,
	total_price number(10) not null,
	total_count number(5) not null,
	p_image varchar2(30) default 'nothing.jpg'
);
create sequence s_order_seq;
create table s_order (
	order_id number primary key,
	id varchar2(15) references s_member(id),
	product_id number references s_product(product_id),
	p_name varchar2(30) not null,
	total_price number(10) not null,
	total_count number(5) not null,
	p_image varchar2(30) default 'nothing.jpg',
	order_date date not null,
	account varchar2(30) references s_bank(account),
	delivery_name varchar2(20) not null,
	delivery_addr varchar2(50) not null,
	delivery_tel varchar2(20) not null,
	order_state varchar2(20) not null
);
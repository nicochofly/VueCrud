create table category
(
	id bigint auto_increment
		primary key,
	name varchar(16) not null,
	time varchar(50) not null,
	operator_id int not null,
	operator_name varchar(32) not null,
	note varchar(200) null
)
charset=utf8;


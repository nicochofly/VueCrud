create table deviceinfo
(
	id bigint auto_increment
		primary key,
	name varchar(16) not null,
	alias varchar(32) not null,
	device_id varchar(32) not null,
	device_group varchar(32) not null,
	time varchar(50) not null,
	operator_name varchar(32) not null,
	note varchar(200) null
)
charset=utf8;


create table pushrecord
(
	id int(11) unsigned auto_increment
		primary key,
-- 	username varchar(255) not null,

	device_id int not null,
	device_name varchar(16) not null,
	content_id varchar(32) not null,
	content_name varchar(32) not null,
	date varchar(32) not null,
	operator_id int not null,
	operator_name varchar(32) not null
)
charset=utf8;


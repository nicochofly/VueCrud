create table pushcontent
(
	id int(11) unsigned auto_increment
		primary key,
	name varchar(32) not null,
	content varchar(255) not null,
	type int not null,
	category int not null,
	date varchar(32) not null,
	operator_id int not null,
	operator_name varchar(32) not null
)
charset=utf8;


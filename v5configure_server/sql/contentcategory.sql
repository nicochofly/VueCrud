create table contentcategory
(
	id bigint auto_increment
		primary key,
	name varchar(16) not null,
	type int not null default 0,
	time varchar(50) not null,
	note varchar(200) null
)
charset=utf8;


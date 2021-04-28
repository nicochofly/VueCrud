create table eventinfo
(
	id int(11) unsigned auto_increment
		primary key,
	username varchar(255) not null,
	channelinfo varchar(32) not null,
	type int not null,
	date varchar(32) not null
)
charset=utf8;


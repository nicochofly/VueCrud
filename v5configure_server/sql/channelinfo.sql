create table channelinfo
(
	id bigint auto_increment
		primary key,
	name varchar(16) not null,
	hostname varchar(32) not null,
	appid varchar(32) not null,
	secret varchar(32) not null,
	uuid varchar(50) not null,
	clientid varchar(50) not null,
	sourceid varchar(50) not null,
	note varchar(200) null
)
charset=utf8;


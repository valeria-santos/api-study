create table courses(
id bigint primary key auto_increment,
name varchar(255) not null,
code varchar(10) not null unique,
instructor varchar(255) not null,
description text,
status enum ('ACTIVE', 'INACTIVE') not null,
creation_date datetime not null,
inactivation_date datetime,
CONSTRAINT chk_code CHECK (Code NOT LIKE '%[0-9]%' AND Code NOT LIKE '%[^A-Za-z\-]%' AND Code NOT LIKE '% %')
)
create table users(
id bigint primary key auto_increment,
name varchar(255) not null,
username varchar(20) not null unique,
email varchar(255) not null unique,
password  varchar(255) not null,
role enum('STUDENT', 'INSTRUCTOR', 'ADMIN') not null,
creation_date datetime not null,
CONSTRAINT chk_username CHECK (Username = LOWER(Username) AND Username NOT LIKE '%[0-9]%' AND Username NOT LIKE '% %'),
CONSTRAINT chk_email CHECK (Email LIKE '%_@__%.__%')
)
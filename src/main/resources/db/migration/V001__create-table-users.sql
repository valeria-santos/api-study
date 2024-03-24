create table users(
UserIdInternal bigint not null auto_increment primary key,
UserIdExternal varchar(36) default (UUID()) not null unique,
Name varchar(255) not null,
Username varchar(20) not null unique,
Email varchar(255) not null unique,
Password  varchar(255) not null,
Role enum('STUDENT', 'INSTRUCTOR', 'ADMINISTRATOR') not null,
CreationDate timestamp default current_timestamp,
CONSTRAINT chk_username CHECK (Username = LOWER(Username) AND Username NOT LIKE '%[0-9]%' AND Username NOT LIKE '% %'),
CONSTRAINT chk_email CHECK (Email LIKE '%_@__%.__%')
)
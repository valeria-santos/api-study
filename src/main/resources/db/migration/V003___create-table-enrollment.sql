create table enrollment(
id bigint primary key auto_increment,
user_id bigint,
course_id bigint,
enrollment_date datetime not null,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (course_id) REFERENCES courses(id)
)
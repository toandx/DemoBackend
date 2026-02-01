create table note(id serial primary key,
title varchar(255), content text)

create table users(id SERIAL  PRIMARY KEY,
                   username varchar(255) UNIQUE,
                   password varchar(255));
create table authority(name varchar(255) primary key);
create table user_author(user_id int,
                         author_id varchar(255),
                         primary key (user_id, author_id),
                         foreign key (user_id) references users(id),
                         foreign key (author_id) references authority(name));
INSERT INTO authority values ('USER');
INSERT INTO authority values ('ADMIN');
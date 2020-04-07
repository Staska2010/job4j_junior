create database request_db;

create table rules (
id serial primary key,
read boolean,
write boolean,
open boolean,
execute boolean
);

create table role (
role_name varchar(50) primary key,
rules int references rules(id)
);

create table users (
id serial primary key,
name varchar(80),
position varchar(80),
role varchar(50) references role(role_name)
);

create table category (
id serial primary key,
cat_name varchar(50)
);

create table comment (
id serial primary key,
com_text text
);

create table state (
state varchar(50) primary key
);

create table attachment (
file varchar(80) primary key
);

create table item (
id serial primary key,
users int references users(id),
item_name varchar(50),
date date,
comment int references comment(id),
attachment varchar(80) references attachment(file),
category int references category(id),
state varchar(50) references state(state)
);

insert into rules (read, write, open, execute) values (true, true, true, true);
insert into rules (read, write, open, execute) values (true, false, false, false);
insert into rules (read, write, open, execute) values (true, false, true, false);

insert into role (role_name, rules) values ('super_user', 1);
insert into role (role_name, rules) values ('read', 2);
insert into role (role_name, rules) values ('read_open', 3);

insert into users (name, position, role) values ('John', 'CEO', 'super');
insert into users (name, position, role) values ('Marie', 'storekeeper', 'read_open');
insert into users (name, position, role) values ('Ruben', 'assistant', 'read');













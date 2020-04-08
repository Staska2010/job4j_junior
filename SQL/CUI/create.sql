CREATE DATABASE request_db;

CREATE TABLE rules (
id SERIAL PRIMARY KEY,
read BOOLEAN,
write BOOLEAN,
open BOOLEAN,
execute BOOLEAN
);

CREATE TABLE role (
role_name VARCHAR(50) PRIMARY KEY,
rules INT REFERENCES rules(id)
);

CREATE TABLE users (
id SERIAL PRIMARY KEY,
name VARCHAR(80),
position VARCHAR(80),
role VARCHAR(50) REFERENCES role(role_name)
);

CREATE TABLE category (
id  SERIAL PRIMARY KEY,
cat_name VARCHAR(50)
);

CREATE TABLE comment (
id SERIAL PRIMARY KEY,
com_text TEXT
);

CREATE TABLE state (
state VARCHAR(50) PRIMARY KEY
);

CREATE TABLE attachment (
file VARCHAR(80) PRIMARY KEY
);

CREATE TABLE item (
id PRIMARY KEY PRIMARY KEY,
users int REFERENCES users(id),
item_name VARCHAR(50),
date DATE,
comment INT REFERENCES comment(id),
attachment VARCHAR(80) REFERENCES attachment(file),
category INT REFERENCES category(id),
state VARCHAR(50) REFERENCES state(state)
);

INSERT INTO rules (read, write, open, execute) VALUES (true, true, true, true);
INSERT INTO rules (read, write, open, execute) VALUES (true, false, false, false);
INSERT INTO rules (read, write, open, execute) VALUES (true, false, true, false);

INSERT INTO role (role_name, rules) VALUES ('super_user', 1);
INSERT INTO role (role_name, rules) VALUES ('read', 2);
INSERT INTO role (role_name, rules) VALUES ('read_open', 3);

INSERT INTO users (name, position, role) VALUES ('John', 'CEO', 'super');
INSERT INTO users (name, position, role) VALUES ('Marie', 'storekeeper', 'read_open');
INSERT INTO users (name, position, role) VALUES ('Ruben', 'assistant', 'read');

INSERT INTO comment (com_text) VALUES ('Simple comment, used in text field');

INSERT INTO category (cat_name) VALUES ('Check new project');

INSERT INTO state VALUES ('Ready');

INSERT INTO attachment VALUES ('path_to_attached_file');

INSERT INTO item (users, item_name, date, comment, attachment, category, state)
VALUES (1, 'Check the project', '2020-01-01', 1, 'path_to_attached_file', 1,  'Ready');










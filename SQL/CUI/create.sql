CREATE DATABASE request_db;


--create RULES table
CREATE TABLE rules (
id SERIAL PRIMARY KEY,
read BOOLEAN,
write BOOLEAN,
open BOOLEAN,
execute BOOLEAN
);

--create ROLE table
CREATE TABLE role (
id SERIAL PRIMaRY KEY,
role_name VARCHAR(50) PRIMARY KEY,
);


--create RoleRules table (many-to-many relationships)
--composite primary key of Role and Rules
CREATE TABLE RoleUsers (
role INT NOT NULL REFERENCES role(id),
rules INT NOT NULL REFERENCES rules(id),
PRIMARY KEY (role, rules);
);


--create USERS table
CREATE TABLE users (
id SERIAL PRIMARY KEY,
name VARCHAR(80) NOT NULL,
position VARCHAR(80),
role VARCHAR(50) REFERENCES role(id)
);


--create CATEGORY table
CREATE TABLE category (
id  SERIAL PRIMARY KEY,
cat_name VARCHAR(50)
);


--create COMMENT table
CREATE TABLE comment (
id SERIAL PRIMARY KEY,
com_text TEXT
item INT REFERENCES item(id)
);


--create STATE table
CREATE TABLE state (
id SERIAL PRIMARY KEY,
state_name VARCHAR(50) PRIMARY KEY
);


--create ATTACHMENT table
CREATE TABLE attachment (
id INT PRIMARY KEY,
file VARCHAR(80),
item INT REFERENCES item(id)
);


--create ITEM table
CREATE TABLE item (
id INT PRIMARY KEY,
item_name VARCHAR(50),
date DATE,
users INT REFERENCES users(id),
category INT REFERENCES category(id),
state VARCHAR(50) REFERENCES state(id)
);



--insert data into ROLE table
INSERT INTO role role_name VALUES ('super_user');
INSERT INTO role role_name VALUES ('read');
INSERT INTO role role_name VALUES ('read_open');


-- insert data into RULES table
INSERT INTO rules (read, write, open, execute) VALUES (true, true, true, true);
INSERT INTO rules (read, write, open, execute) VALUES (true, false, false, false);
INSERT INTO rules (read, write, open, execute) VALUES (true, false, true, false);


--insert into RoleRules table (many-to-many relationships)
INSERT INTO RoleRules VALUES (1, 1);
INSERT INTO RoleRules VALUES (2, 2);
INSERT INTO RoleRules VALUES (3, 3);


--insert data into USERS table
INSERT INTO users (name, position, role) VALUES ('John', 'CEO', 1);
INSERT INTO users (name, position, role) VALUES ('Marie', 'storekeeper', 2);
INSERT INTO users (name, position, role) VALUES ('Ruben', 'assistant', 3);

--insert data into USERS table
INSERT INTO category (cat_name) VALUES ('Check new project');

--insert data into COMMENT table
INSERT INTO comment (com_text, item) VALUES ('Simple comment, used in text field', 1);

--insert data into STATE table
INSERT INTO state state_name VALUES ('Ready');

--insert data into ATTACHMENT table
INSERT INTO attachment file, item VALUES ('path_to_attached_file', 1);

--insert data into ITEM table
INSERT INTO item (item_name, date, users, category, state)
VALUES ('Check the project', '2020-01-01', 1, 1, 1);







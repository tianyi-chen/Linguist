-- database setup guide
-- link mysql-connector-java to the project
-- you need a user "root" with an empty password

-- create a databse
CREATE DATABASE DIC;
USE DIC;

-- create tables
-- CREATE TABLE IF NOT EXISTS Dictionaries (
-- 	name varchar(50) NOT NULL
-- 	meaning varchar(200) NOT NULL,
-- 	example varchar(200),
-- 	isLearnt int NOT NULL DEFAULT 0,
-- 	proficiency float(2,2) NOT NULL DEFAULT 0
-- 	);

CREATE TABLE IF NOT EXISTS User_info (
	username varchar(20) NOT NULL,
	password varchar(50) NOT NULL,

	PRIMARY KEY(username)
	);

CREATE TABLE IF NOT EXISTS 01English (
	content varchar(50) CHARACTER SET utf8 NOT NULL,
	wordClass varchar(20) CHARACTER SET utf8 NOT NULL,
	meaning varchar(200) CHARACTER SET utf8 NOT NULL,
	example varchar(200) CHARACTER SET utf8,
	isLearnt int NOT NULL DEFAULT 0,
	proficiency double(4,2) NOT NULL DEFAULT 0,
	correct int NOT NULL DEFAULT 0,
    incorrect int NOT NULL DEFAULT 0,

	PRIMARY KEY(content)
	);

CREATE TABLE IF NOT EXISTS 01French (
	content varchar(50) CHARACTER SET utf8 NOT NULL,
	wordClass varchar(20) CHARACTER SET utf8 NOT NULL,
	meaning varchar(200) CHARACTER SET utf8 NOT NULL,
	example varchar(200) CHARACTER SET utf8,
	isLearnt int NOT NULL DEFAULT 0,
	proficiency double(4,2) NOT NULL DEFAULT 0,
	correct int NOT NULL DEFAULT 0,
    incorrect int NOT NULL DEFAULT 0,

	PRIMARY KEY(content)
	);

CREATE TABLE IF NOT EXISTS 01Chinese (
	content varchar(50) CHARACTER SET utf8 NOT NULL,
	wordClass varchar(20) CHARACTER SET utf8 NOT NULL,
	meaning varchar(200) CHARACTER SET utf8 NOT NULL,
	example varchar(200) CHARACTER SET utf8,
	isLearnt int NOT NULL DEFAULT 0,
	proficiency double(4,2) NOT NULL DEFAULT 0,
	correct int NOT NULL DEFAULT 0,
    incorrect int NOT NULL DEFAULT 0,

	PRIMARY KEY(content)
	);

CREATE TABLE IF NOT EXISTS Dictionary_info (
	username varchar(20) NOT NULL,
	dictionaryName varchar(50) NOT NULL,

	PRIMARY KEY(dictionaryName)
	);

INSERT INTO User_info VALUES ("01","mypassword");
INSERT INTO User_info VALUES ("user02","lalalalala");
INSERT INTO User_info VALUES ("user03","IamI_uniqColouredFirework");
INSERT INTO User_info VALUES ("Julius","liyou");
INSERT INTO User_info VALUES ("Tianyi","DebugPrincess");



INSERT INTO Dictionary_info VALUES ("01","01 English");
INSERT INTO Dictionary_info VALUES ("01","01 French");
INSERT INTO Dictionary_info VALUES ("01","01 Chinese");



INSERT INTO 01English VALUES ("apple","名词","pingguo","I like apples",0,0.0,0,0);
INSERT INTO 01English VALUES ("banana","n","xiangjiao","I like bananas",0,0.0,0,0);
INSERT INTO 01English VALUES ("peach","n","taozi","I like peaches",0,0.0,0,0);
INSERT INTO 01English VALUES ("pear","n","li","I like pears",1,1.0,0,0);

INSERT INTO 01French VALUES ("test","n","test","test",0,0.0,0,0);
INSERT INTO 01French VALUES ("newtest","n","test","test",1,0.0,0,0);

INSERT INTO 01Chinese VALUES ("苹果","n","apple","我爱吃苹果",1,0.0,0,0);
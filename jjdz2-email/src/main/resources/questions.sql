DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS privileges_table ;

CREATE TABLE questions(q_id INT NOT NULL AUTO_INCREMENT,question VARCHAR(100),PRIMARY KEY(q_id));
INSERT INTO questions VALUES (1,"Are you looking for an urgent email?");
INSERT INTO questions VALUES (2,"Are you looking for a business email?");
INSERT INTO questions VALUES (3,"Are you looking for a family related email?");
INSERT INTO questions VALUES (4,"Are you looking for an email about a meeting?");

CREATE TABLE privileges_table(p_id INT NOT NULL AUTO_INCREMENT, rights VARCHAR(40),PRIMARY KEY(p_id));
INSERT INTO privileges_table VALUES (1,"Admin");
INSERT INTO privileges_table VALUES (2,"Local_user");

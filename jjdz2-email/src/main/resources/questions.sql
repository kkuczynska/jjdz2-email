DROP TABLE IF EXISTS questions;
CREATE TABLE questions(q_id INT NOT NULL AUTO_INCREMENT,quesion VARCHAR(100),PRIMARY KEY(q_id));
INSERT INTO questions VALUES (1,"Are you looking for an urgent email?");
INSERT INTO questions VALUES (2,"Are you looking for a business email?");
INSERT INTO questions VALUES (3,"Are you looking for a family related email?");
INSERT INTO questions VALUES (4,"Are you looking for an email about a meeting?");

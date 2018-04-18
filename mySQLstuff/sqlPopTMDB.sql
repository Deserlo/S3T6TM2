CREATE DATABASE TM;

CREATE TABLE User(id INTEGER NOT NULL AUTO_INCREMENT,
                  userName VARCHAR(30) NOT NULL,
                  fname VARCHAR(30) NOT NULL,
                  team VARCHAR(30) NOT NULL,
                  pwd VARCHAR(30) NOT NULL,
                  mgrID INT,
                  primary key(id));
                  

CREATE TABLE Manager(userName VARCHAR(30) NOT NULL,
                    id INTEGER NOT NULL,
                    PRIMARY KEY(id),
                    FOREIGN KEY(id) REFERENCES User(id));


CREATE TABLE Developer(userName VARCHAR(30) NOT NULL,
                      id INT NOT NULL,
                      PRIMARY KEY (id),
                      FOREIGN KEY (id) REFERENCES User(id));

CREATE TABLE Project(projNo INTEGER NOT NULL AUTO_INCREMENT,
					  projName varchar(30) NOT NULL,
                    mgrID INTEGER NOT NULL,
                    dueDate DATE,
                    PRIMARY KEY(projNo),
                    FOREIGN KEY (mgrID) REFERENCES User(id));

CREATE TABLE Works_on(projNo INTEGER NOT NULL,
                      devID INTEGER NOT NULL,
                      PRIMARY KEY(projNo, devID),
                      FOREIGN KEY(projNo) REFERENCES Project(projNo),
                      FOREIGN KEY(DevID) REFERENCES User(id));

CREATE TABLE Task(taskID INTEGER NOT NULL AUTO_INCREMENT,
                  taskName VARCHAR(30) NOT NULL,
                  projNo INTEGER NOT NULL,
                  devID INTEGER NOT NULL,
                  timeBudget DECIMAL(5,2),
                  start DATETIME,
                  end DATETIME,
                  duration DECIMAL (5,2),
                  description VARCHAR(300),
                  PRIMARY KEY(taskID),
                  FOREIGN KEY(projNo) REFERENCES Project(projNo),
                  FOREIGN KEY(devID) REFERENCES User(id));
                  
CREATE TABLE Team(teamID integer not null auto_increment,
				  teamName varchar(30) NOT NULL,
				  mgrID int NOT NULL,
                  primary key(teamID),
                  foreign key(mgrID) references User(id));
                  
INSERT INTO User (userName, fname, team, pwd) VALUES ("email@example.com", "Mike", "A", "1234");
INSERT INTO User (userName, fname, team, pwd, mgrID) VALUES ("email1@example.com", "Alice", "A", "4567", 1);
INSERT INTO User (userName, fname, team, pwd) VALUES ("email2@example.com", "Ben", "B", "7890");
INSERT INTO User (userName, fname, team, pwd, mgrID) VALUES ("email3@example.com", "John", "B", "1122", 3);

INSERT INTO Developer (userName, id) VALUES ("email1@example.com", 2);
INSERT INTO Developer (userName, id) VALUES ("email3@example.com", 4);

INSERT INTO Manager (userName, id) VALUES ("email@example.com", 1);
INSERT INTO Manager (userName, id) VALUES ("email2@example.com", 3);

INSERT INTO Project (projName, mgrID, dueDate) VALUES ("ExProject1", 1, '2018-06-11') ;
INSERT INTO Project (projName, mgrID, dueDate) VALUES ("ExProject2", 3, '2018-06-24'); 
INSERT INTO Project (projName, mgrID, dueDate) VALUES ("ExProject3", 3, '2018-07-18'); 

INSERT INTO Works_on (projNo, devID) VALUES (1, 2);
INSERT INTO Works_on (projNo, devID) VALUES (2, 4);
INSERT INTO Works_on (projNo, devID) VALUES (3, 4);

INSERT INTO Team (teamName, mgrID) VALUES ("A", 1);
INSERT INTO Team (teamName, mgrID) VALUES ("B", 3);

INSERT INTO Task (taskName, projNo, devID, timeBudget, start, end, duration, description) VALUES ("task A", 1, 2, 4.5, '2018-04-12 09:00:00', '2018-04-12 12:00:00', 3.0, "this is task A");
INSERT INTO Task (taskName, projNo, devID, timeBudget, start, end, duration, description) VALUES ("task B", 2, 4, 2.5, '2018-04-12 08:00:00', '2018-04-12 10:00:00', 2.0, "this is task B");
INSERT INTO Task (taskName, projNo, devID, timeBudget, start, end, duration, description) VALUES ("task C", 3, 4, 6.0, '2018-04-12 10:00:00', '2018-04-12 16:00:00', 6.0, "this is task C");

INSERT INTO Team (teamID, teamName, mgrID) VALUES("11", "A","1");
INSERT INTO Team (teamID, teamName, mgrID) VALUES("22", "B","3");








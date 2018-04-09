CREATE DATABASE TM;

CREATE TABLE User(id INTEGER NOT NULL AUTO_INCREMENT primary key,
                  userName VARCHAR(30) NOT NULL,
                  fname VARCHAR(30) NOT NULL,
                  team VARCHAR(30) NOT NULL,
                  pwd VARCHAR(30) NOT NULL,
                  mgrID INT,
                  );

CREATE TABLE Manager(userName VARCHAR(30) NOT NULL,
                    id INTEGER NOT NULL,
                    PRIMARY KEY(id),
                    FOREIGN KEY(id) REFERENCES User(id));

CREATE TABLE Developer(userName VARCHAR(30) NOT NULL,
                      id INT NOT NULL,
                      PRIMARY KEY (id),
                      FOREIGN KEY (id) REFERENCES User(id));

CREATE TABLE Project(projNo INTEGER NOT NULL AUTO_INCREMENT,
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
                  duration DATETIME,
                  description VARCHAR(300),
                  PRIMARY KEY(taskID),
                  FOREIGN KEY(projNo) REFERENCES Project(projNo),
                  FOREIGN KEY(devID) REFERENCES User(id));

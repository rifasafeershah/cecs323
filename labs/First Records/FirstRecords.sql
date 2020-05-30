CREATE TABLE FirstRecords (
  FirstName VARCHAR(20) NOT NULL,
  LastName VARCHAR(20) NOT NULL,
  Email VARCHAR(20) NOT NULL,
  Phone VARCHAR(20),
  Task VARCHAR(20),
  DueDate Date,
  CONSTRAINTS FirstRecords_pk
  PPIMARY KEY (FirstName, LastName, Phone)
  );
  
INSERT INTO FirstRecords
VALUES ('Tim', 'Ross', 'tim.ross@gmail.com', '(123)456-7890', 'Paint a Picture', 2020-30-5);

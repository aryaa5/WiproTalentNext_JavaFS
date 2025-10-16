-- Create the Table
CREATE TABLE User (
    UserID VARCHAR(200) PRIMARY KEY,
    Password VARCHAR(200),
    Name VARCHAR(200),
    IncorrectAttempts NUMBER(2),
    LockStatus NUMBER(2),
    UserType VARCHAR(200)
);

-- Insert initial records
INSERT INTO User (UserID, Password, Name, IncorrectAttempts, LockStatus, UserType) 
VALUES ('AB1001', 'AB1001', 'Hari', 0, 0, 'Admin');

INSERT INTO User (UserID, Password, Name, IncorrectAttempts, LockStatus, UserType) 
VALUES ('TA1002', 'TA1002', 'Prasath', 0, 0, 'Employee');

INSERT INTO User (UserID, Password, Name, IncorrectAttempts, LockStatus, UserType) 
VALUES ('RS1003', 'RS1003', 'Ganesh', 0, 0, 'Employee');

-- COMMIT;

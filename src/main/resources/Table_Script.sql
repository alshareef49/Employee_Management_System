CREATE TABLE employee (
  employee_id INT NOT NULL,
   name VARCHAR(255) NULL,
   email_id VARCHAR(255) NULL,
   dob date NULL,
   CONSTRAINT pk_employee PRIMARY KEY (employee_id)
);

insert into employee values(1001,'rishabh',"rishabh@gmail.com",'2000-01-01');
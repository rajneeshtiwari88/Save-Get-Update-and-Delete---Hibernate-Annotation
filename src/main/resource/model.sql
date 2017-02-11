
    drop table if exists employee;

    create table employee (
       id varchar(255) not null,
        emp_name varchar(25),
        emp_salary decimal(7,2),
        primary key (id)
    );

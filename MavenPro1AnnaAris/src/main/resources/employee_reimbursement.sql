create schema if not exists employee_reimbursement authorization annacarl;
set search_path to employee_reimbursement; 

drop table if exists employees cascade;
drop table if exists departments cascade;
drop table if exists logins cascade;


drop sequence if exists emp_id_seq; 
drop sequence if exists dep_id_seq; 

create table employees (
employee_id SERIAL,
first_name varchar,
last_name varchar,
supervisor_id integer,
department_id integer,
PRIMARY KEY(employee_id)
);

create view supervisors
	as
	select distinct super.*
	from employees super, employees sub
	where sub.supervisor_id=super.employee_id;


create view supervisor_sub_relations
	as
	select supervisors.employee_id sup_id, employees.employee_id sub_id
	from employees
	inner join supervisors
	on supervisors.employee_id=employees.supervisor_id;



create table departments(
department_id SERIAL,
name varchar,
head_employee_id integer,
primary key(department_id)
);

create table logins(
employee_id integer,
username varchar,
password varchar,
foreign key(employee_id)
references employees(employee_id)
on delete cascade
);

create sequence dep_id_seq
increment by 1
start with 200;

create sequence emp_id_seq
increment by 1;


--testdata
--insert into departments values(1,'Music');
--insert into employees values(nextval('emp_id_seq'),'Billy','Joel',null,1);
--insert into employees values(nextval('emp_id_seq'),'Brittney','Spears',1,1);
--insert into employees values(nextval('emp_id_seq'),'Aretha','Franklin',2,1);
--insert into employees values(nextval('emp_id_seq'),'John','Fogerty',2,1);
--insert into logins values(1,'testuser','password');


select * from employees;
----select * from supervisors;
----select * from supervisor_sub_relations where sup_id=2;
--select * from departments;
--
--update employees set department_id =1 where employee_id =7;
--update logins set password='newpass'where employee_id=1;
--select * from employees where employee_id =9;
--select * from logins;
--select employee_id from logins where username='greenonion' and password='strAwberry';
--select employee_id from logins where username='testuser' and password='newpass';
--select employee_id from logins;

--select * from employees 
--where employee_id =(
--	select employee_id 
--	from logins 
--	where username='testuser' and password='password');
--	select supervisors.employee_id sup_id, employee.employee_id sub_id
--	from employee
--	inner join supervisors
--	on supervisors.employee_id=employee.supervisor_id;

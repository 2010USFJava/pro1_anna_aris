create schema if not exists employee_reimbursement_shared authorization annacarl;
set search_path to employee_reimbursement_shared; 


--create schema if not exists employee_reimbursement authorization annacarl;
--set search_path to employee_reimbursement; 

drop table if exists employees cascade;
drop table if exists departments cascade;
drop table if exists logins cascade;
drop table if exists request cascade;
drop table if exists course_type cascade;
drop table if exists course cascade;
drop table if exists grade cascade;
drop table if exists presentation cascade;

drop sequence if exists emp_id_seq; 
drop sequence if exists dep_id_seq; 
drop sequence if exists course_id_seq; 

create table employees (
employee_id SERIAL,
first_name varchar,
last_name varchar,
supervisor_id integer,
department_id integer,
funds DOUBLE PRECISION,
title varchar,
PRIMARY KEY(employee_id)
);

--select * from employees;

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

create sequence course_id_seq
increment by 1;



--course tables
CREATE TABLE course_type(
course_type_id SERIAL,
string name,
reimbursement_percent integer,
primary key(course_type_id)
);


--Hard coding this, since these numbers were set in instructions

insert into course_type values(0,'University Course',80);
insert into course_type values(1,'Seminar',60);
insert into course_type values(2,'Certification Preperation Class',75);
insert into course_type values(3,'Certification',100);
insert into course_type values(4,'Techincal Training',90);
insert into course_type values(5,'Other',30);

CREATE TABLE course(
course_id SERIAL,
employee_id integer,
course_type_id integer,
name varchar,
institution varchar,
pass boolean,
letter_grade varchar(1),
number_grade double precision,
document text,
primary key(course_id)
--foreign key(employee_id)
--references employees(employee_id)
--on delete cascade,
--foreign key(course_type_id)
--references course_type(employee_id)
--on delete cascade
);


insert into course values(nextval('course_id_seq'),1,2,'Hang-Gliding','Arsospace',false);
select * from course;
--"insert into course(nextval('course_id_seq'),?,?,?,?)";

--CREATE TABLE grade(
--course_id integer,
--letter_grade varchar(1),
--number_grade double precision
----foreign key(course_id)
----references course_type(course_id)
----on delete cascade
--);
--
--insert into grade values(1,'a',77);

--create table presentation(
--course_id integer,
--document_file text
----foreign key(course_id)
----references course_type(course_id)
----on delete cascade
--);



--create view course_presentations as
--	select c.*,p.document_file
--	from course as c, presentation as p
--	inner join course
--	on course.course_id=p.course_id;
--
--create view course_grade as
--	select c.*,p.letter_grade, p.number_grade
--	from course as c, grade as p
--	inner join course
--	on course.course_id=p.course_id;



--select * from course_grade where employee_id=1;
--select * from course_presentations;
--select * from course_grade;

--select * from course;
	
--aris's table(s)
CREATE TABLE request (
	id serial,
	employee_id integer,
	date_made timestamp,
	event_date varchar(10),
	event_time varchar(7),
	event_cost numeric,
	event_street text,
	event_city text,
	event_state varchar(20),
	event_zip varchar(6),
	event_type text,
	event_description text,
	sup_status varchar(10),
	head_status varchar(10),
	ben_status varchar(10)
);


--testdata
--insert into departments values(1,'Music');
--insert into employees values(nextval('emp_id_seq'),'Billy','Joel',null,1);
--insert into employees values(nextval('emp_id_seq'),'Brittney','Spears',1,1);
--insert into employees values(nextval('emp_id_seq'),'Aretha','Franklin',2,1);
--insert into employees values(nextval('emp_id_seq'),'John','Fogerty',2,1);


--insert into logins values(1,'testuser','password');
--select sub_id from supervisor_sub_relations;
--select * from logins;

select * from course;

--select * from employees;
--select employee_id from logins where username='tinycat' and password='meow';
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

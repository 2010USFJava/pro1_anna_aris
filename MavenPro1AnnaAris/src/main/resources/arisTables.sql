CREATE TABLE request (
id serial,
employee_id integer,
event_date varchar(10),
event_time varchar(7),
event_location varchar(20),
event_type varchar(30),
event_description text,
event_cost NUMERIC,
sup_status varchar(10),
head_status varchar(10),
ben_status varchar(10)
);
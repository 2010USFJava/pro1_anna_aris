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
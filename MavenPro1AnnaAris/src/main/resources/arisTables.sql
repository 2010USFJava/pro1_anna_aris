CREATE TABLE request (
	id serial,
	employee_id integer,
	date_made timestamp,
	event_date varchar(10),
	event_time varchar(7),
	event_cost integer,
	estimated_award integer,
	event_street text,
	event_city text,
	event_state varchar(20),
	event_zip varchar(6),
	event_type text,
	event_description text,
	sup_status varchar(10),
	head_status varchar(10),
	ben_status varchar(10),
	awarded boolean,
	amount_awarded integer,
	reason text
);

CREATE TABLE employee (
	id serial,
	first_name varchar(25),
	last_name varchar(25),
	title varchar(25),
	username varchar(18),
	password varchar(18),
	yearly_awards integer
);
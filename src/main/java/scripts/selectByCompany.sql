CREATE TABLE company (
id integer NOT NULL,
c_name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person (
id integer NOT NULL,
p_name character varying,
company_id integer references company(id),
CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, c_name) values (1, 'AAA');
insert into company (id, c_name) values (2, 'BBB');
insert into company (id, c_name) values (3, 'CCC');
insert into company (id, c_name) values (4, 'DDD');
insert into company (id, c_name) values (5, 'EEE');

insert into person (id, p_name, company_id) values (1, 'Tom', 1);
insert into person (id, p_name, company_id) values (2, 'Bob', 1);
insert into person (id, p_name, company_id) values (3, 'John', 1);
insert into person (id, p_name, company_id) values (4, 'Arnold', 2);
insert into person (id, p_name, company_id) values (5, 'Xi', 2);
insert into person (id, p_name, company_id) values (6, 'Tony', 2);
insert into person (id, p_name, company_id) values (7, 'Mark', 3);
insert into person (id, p_name, company_id) values (8, 'Ivan', 3);
insert into person (id, p_name, company_id) values (9, 'Muhammad', 4);
insert into person (id, p_name, company_id) values (10, 'Lee', 4);
insert into person (id, p_name, company_id) values (11, 'Liza', 5);
insert into person (id, p_name, company_id) values (12, 'Edward', 5);
insert into person (id, p_name, company_id) values (13, 'Bill', 5);

select p.p_name as СОТРУДНИК, c.c_name as КОМПАНИЯ
from person as p join company as c
on p.company_id=c.id
where c.id!=5;

with max_c as (
select c.c_name as КОМПАНИЯ, count(p.company_id) as СОТРУДНИКИ
from person as p join company as c
on p.company_id=c.id
group by c.c_name)
select * from max_c
where max_c.СОТРУДНИКИ = (select max(СОТРУДНИКИ) from max_c);



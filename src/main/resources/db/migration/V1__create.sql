create table city (id int8 not null, name varchar(255) not null, postcode varchar(255) not null, region varchar(255) not null, primary key (id));
create table event (id int8 not null, datum date, event_type varchar(255) not null, version int8, primary key (id));
create table partaking (id  bigserial not null, version int8, partaking_type varchar(255) not null, event_id int8, person_id uuid not null, primary key (id));
create table person (id  uuid not null, version int8, achternaam varchar(255) not null, birth_date date, gender varchar(255), voornaam varchar(255) not null, city_id int8, primary key (id));
alter table partaking add constraint uk_partaking unique (person_id, event_id);
alter table partaking add constraint fk_partaking_event foreign key (event_id) references event;
alter table partaking add constraint fk_partaking_person foreign key (person_id) references person;
alter table person add constraint fk_person_city foreign key (city_id) references city;
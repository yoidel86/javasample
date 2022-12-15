create table drone (id integer not null AUTO_INCREMENT,battery_capacity integer,model varchar(255),serial_number varchar(100),state varchar(255),weight_limit integer,load_id integer,primary key (id));
create table load (id integer not null AUTO_INCREMENT, drone_id integer, primary key (id));
create table medication (id integer not null AUTO_INCREMENT, code varchar(255), image varchar(255), name varchar(255), weight integer, primary key (id));
create table medication_loaded (medications_id integer not null, loaded_id integer not null);
create sequence hibernate_sequence start with 1 increment by 1;
alter table drone add constraint FKfd1s4ye1okw2o4hx0w20noh49 foreign key (load_id) references load;
alter table load add constraint FK8e61bb9jj7ovkn3w7g8y4oa5u foreign key (drone_id) references drone;
alter table medication_loaded add constraint FK60li9kkcb3e1ne35ttr999kud foreign key (loaded_id) references load;
alter table medication_loaded add constraint FKgo29dhukl6oi50sjqiruyckxx foreign key (medications_id) references medication;

insert into
    DRONE(serial_number, model, weight_limit, battery_capacity, state)
    values('13579', 'HEAVYWEIGHT', 500, 20, 'RETURNING');
insert into
    DRONE(serial_number, model, weight_limit, battery_capacity, state)
    values('2222', 'MIDDLEWEIGHT', 250, 50, 'DELIVERED');
insert into
    DRONE(serial_number, model, weight_limit, battery_capacity, state)
    values('1111', 'LIGHTWEIGHT', 100, 40, 'LOADING');
insert into
    DRONE(serial_number, model, weight_limit, battery_capacity, state)
    values('3333', 'CRUISERWEIGHT', 400, 60, 'IDLE');
insert into
    DRONE(serial_number, model, weight_limit, battery_capacity, state)
    values('555', 'CRUISERWEIGHT', 400, 20, 'IDLE');
insert into
    MEDICATION(name, code, weight, image)
    values('PFIZER1', 'PFZ-625', 28, 'http://localhost:8080/public/PFIZER1.png');
insert into
    MEDICATION(name, code, weight, image)
    values('PFIZER2', 'PFZ-689', 32, 'http://localhost:8080/public/PFIZER2.png');
insert into LOAD(drone_id) values(3);
insert into LOAD(drone_id) values(null);
insert into LOAD_MEDICATIONS(medications_id, loads_id) values(1,1);
insert into LOAD_MEDICATIONS(medications_id,loads_id) values(2,1);
insert into LOAD_MEDICATIONS(medications_id, loads_id) values(1,2);
insert into LOAD_MEDICATIONS(medications_id,loads_id) values(2,2);

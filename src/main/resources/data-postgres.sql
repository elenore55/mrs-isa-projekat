delete from adventure;
-- delete from offer_rules;
-- delete from rule;
-- delete from offer;
-- delete from price_list;
-- delete from fishing_equipment;
-- -- delete from fishing_instructor;

-- delete from ship;
-- delete from ship_owner;
-- delete from my_users;
-- delete from profile_data;
-- delete from address;

insert into address (city,country,street) values ('Novi Sad','Srbija','Puskinova 6'); --nemo ubacivati id pls brt
insert into profile_data (email, name, password, phone_number, surname, address_id) values ('email@gmail.com', 'Pero', 'pass1', '123345', 'Peric', 1);
-- insert into profile_data (email,name,password,phone_number,surname,address_id) values ('email@gmail.com','ImeInstruktora','11223344','06444124214','PrezimeInstruktora',11);
insert into my_users (category,number_of_points,profile_data_id) values ('REGULAR',0,1);
insert into ship_owner (id) values (1);
insert into offer (additional_info, description, name, price_list, address_id) values ('info', 'opis', 'ime', 300, 1);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (5, 5, 10, 3, 100, 'SHIP', 1, 1);
-- insert into fishing_instructor (biography,id) values ('Instruktor pecanja I',5);
-- insert into fishing_equipment (amount, name) values (10,'Fishing Rod');
-- insert into fishing_equipment (amount, name) values (10,'Fishing Line');
-- insert into fishing_equipment (amount, name) values (10,'Hooks');
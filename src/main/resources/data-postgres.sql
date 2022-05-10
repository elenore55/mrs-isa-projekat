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
insert into address (city,country,street) values ('Beograd','Srbija','Marka Pola 10');
insert into address (city,country,street) values ('Novi Sad','Srbija','Bulevar oslobodjenja 105');
insert into address (city,country,street) values ('Pariz','Francuska','Puskinova 50');

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('email@gmail.com', 'Pero', 'pass1', '123345', 'Peric', 1);
-- insert into profile_data (email,name,password,phone_number,surname,address_id) values ('email@gmail.com','ImeInstruktora','11223344','06444124214','PrezimeInstruktora',11);
insert into my_users (category,number_of_points,profile_data_id) values ('REGULAR',0,1);
insert into ship_owner (id) values (1);
insert into offer (additional_info, description, name, price_list, address_id) values ('info', 'opis', 'ime', 300, 1);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (5, 5, 10, 3, 100, 'BOAT', 1, 1);

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('email2@gmail.com', 'Djuro', 'pass2', '1233456', 'Djuric', 1);
insert into my_users (category,number_of_points,profile_data_id) values ('REGULAR',0,2);
insert into cottage_owner (id) values (2);
insert into offer (additional_info, description, name, price_list, address_id) values ('info', 'ovo je vikendica', 'vikendica', 300, 1);
insert into cottage (id, cottage_owner_id) values (2, 2);

insert into offer (additional_info, description, name, price_list, address_id) values ('info2', 'this is a cottage', 'vikendica2', 500, 2);
insert into cottage (id, cottage_owner_id) values (3, 2);

insert into room (number_of_beds, cottage_id) values (5, 3);
insert into room (number_of_beds, cottage_id) values (3, 3);
insert into room (number_of_beds, cottage_id) values (2, 2);
insert into room (number_of_beds, cottage_id) values (3, 2);
insert into room (number_of_beds, cottage_id) values (5, 2);


insert into image (path) values ('images/img_c1.jpeg');
insert into image (path) values ('images/img_c2.jpg');
insert into image (path) values ('images/img_c3.jpg');
insert into image (path) values ('images/img_c4.jpg');
insert into image (path) values ('images/img_c5.jpg');

insert into image (path) values ('images/room1.jpg');
insert into image (path) values ('images/room3.jpg');
insert into image (path) values ('images/room5.jpg');
insert into image (path) values ('images/room6.jpg');
insert into image (path) values ('images/room7.webp');
insert into image (path) values ('images/room9.webp');
insert into image (path) values ('images/room10.jpg');

insert into image (path) values ('images/ship1.jpg');
insert into image (path) values ('images/ship3.jpg');
insert into image (path) values ('images/ship5.jpg');

insert into image (path) values ('images/inside1.jpg');
insert into image (path) values ('images/inside2.jpg');
insert into image (path) values ('images/inside3.jpg');
insert into image (path) values ('images/inside4.jpg');
insert into image (path) values ('images/inside5.jpg');

insert into cottage_images (cottage_id, images_id) values (2, 1);
insert into cottage_images (cottage_id, images_id) values (2, 6);
insert into cottage_images (cottage_id, images_id) values (3, 7);
insert into cottage_images (cottage_id, images_id) values (3, 8);
insert into cottage_images (cottage_id, images_id) values (3, 9);
insert into cottage_images (cottage_id, images_id) values (3, 10);
insert into cottage_images (cottage_id, images_id) values (3, 12);


insert into offer (additional_info, description, name, price_list, address_id) values ('additional info', 'opis mog broda', 'naziv mog broda', 500, 4);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (50, 100, 300, 15, 500, 'SHIP', 4, 1);
-- insert into fishing_instructor (biography,id) values ('Instruktor pecanja I',5);
-- insert into fishing_equipment (amount, name) values (10,'Fishing Rod');
-- insert into fishing_equipment (amount, name) values (10,'Fishing Line');
-- insert into fishing_equipment (amount, name) values (10,'Hooks');


insert into profile_data (email,name,password,phone_number,surname,address_id) values ('instruktor@gmail.com','ImeInstruktora','11223344','06444124214','PrezimeInstruktora',1);
insert into fishing_instructor (biography, id) values ('Instruktor pecanja I',1);
insert into fishing_equipment (amount, name) values (10,'Fishing Rod');
insert into fishing_equipment (amount, name) values (10,'Fishing Line');
insert into fishing_equipment (amount, name) values (10,'Hooks');

insert into offer (additional_info, description, name, price_list, address_id) values ('additional info', 'Avanture za sve, a narocito za najmladje', 'Avantura "Popaj"', 50, 4);
insert into adventure (max_people, id, fishing_instructor_id) values (10, 5, 1);

insert into ship_images (ship_id, images_id) values (1, 14);
insert into ship_images (ship_id, images_id) values (1, 16);
insert into ship_images (ship_id, images_id) values (1, 17);
insert into ship_images (ship_id, images_id) values (1, 18);
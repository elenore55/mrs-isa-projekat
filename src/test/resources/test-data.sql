INSERT INTO role (name) VALUES ('ROLE_CLIENT');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_COTTAGE');
INSERT INTO role (name) VALUES ('ROLE_SHIP');
INSERT INTO role (name) VALUES ('ROLE_ADVENTURE');


insert into address (city,country,street) values ('Novi Sad','Srbija','Puskinova 6');
insert into address (city,country,street) values ('Beograd','Srbija','Marka Pola 10');
insert into address (city,country,street) values ('Novi Sad','Srbija','Bulevar oslobodjenja 105');


insert into profile_data (email, name, password, phone_number, surname, address_id) values ('email@gmail.com', 'Pero', 'pass1', '065-111-5555', 'Peric', 1);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,1,4);
insert into ship_owner (id) values (1);

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('email2@gmail.com', 'Djuro', '$2a$10$eJAhTBNusorL6jl1LxEuOeXwd75E1MV/XX8u67Fb/IO5yxpUmBOoC', '1233456', 'Djuric', 1); -- pass2
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,2,3);
insert into cottage_owner (id) values (2);

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('milica.popovic55@hotmail.com', 'Marko', 'pass3', '066-321-3443', 'Markovic', 3);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,3,1);
insert into client (id) values (3);


insert into offer (additional_info, description, name, price_list, address_id) values ('Pruža mogućnosti za organizovanje poslovnih ručkova, koktela, prezentacija i promocija, kao i za neobavezno druženje. Brod ima profesionalno ozvučenje i prostranu otvorenu terasu na gornjoj palubi.', 'Brod je na dva nivoa', 'Sirena', 150, 1);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (70, 25, 40, 4, 100, 'SHIP', 1, 1);


insert into offer (additional_info, description, name, price_list, address_id) values ('this is info1', 'desc1', 'name1', 300, 1);
insert into cottage (id, cottage_owner_id) values (2, 2);

insert into offer (additional_info, description, name, price_list, address_id) values ('info2', 'desc2.', 'name2', 500, 3);
insert into cottage (id, cottage_owner_id) values (3, 2);

insert into offer (additional_info, description, name, price_list, address_id) values ('info3', 'desc3', 'name3', 60, 2);
insert into cottage (id, cottage_owner_id) values (4, 2);


insert into room (number_of_beds, cottage_id) values (5, 2);
insert into room (number_of_beds, cottage_id) values (3, 3);
insert into room (number_of_beds, cottage_id) values (4, 4);


insert into offer (additional_info, description, name, price_list, address_id) values ('Zbog komfora koji pruža predstavlja idealan izbor za dnevna i noćna krstarenja, kao i sve vrste proslava!', 'Ekskluzivan katamaran za rentiranje', 'Ariel', 300, 2);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (50, 20, 35, 2, 120, 'BOAT', 8, 1);

insert into offer (additional_info, description, name, price_list, address_id) values ('Poseduje kuhinju i toalet.', 'Moj camac za rentiranje', 'Narval', 100, 3);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (7, 10, 35, 2, 70, 'BOAT', 9, 1);


insert into feedback (comment, rating, status) values ('Very pretty boat', 10, 'APPROVED');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (1, 3, 'PENDING', '20220523 10:05:00 AM', '20220528 10:05:00 AM', 1);
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 1);
insert into availability (offer_id, star_date_time, end_date_time) values (1, '20220501 00:00:01 AM', '20220731 11:59:59 PM');

insert into feedback (comment, rating, status) values ('Very pretty cottage', 10, 'APPROVED');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (2, 3, 'PENDING', '20220520 10:10:00 AM', '20220526 10:00:00 PM', 2);
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 2);
insert into availability (offer_id, star_date_time, end_date_time) values (2, '20220401 00:00:01 AM', '20220831 11:59:59 PM');

insert into feedback (comment, rating, status) values ('Very nice cottage', 9, 'APPROVED');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (2, 3, 'PENDING', '20220602 09:10:00 AM', '20220605 04:00:00 PM', 3);
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 3);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (1, 3, 'FINISHED', '20220502 09:10:00 AM', '20220506 04:00:00 PM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 4);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (2, 3, 'CLIENT_NOT_ARRIVED', '20220502 09:10:00 AM', '20220510 04:00:00 PM');
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 5);

insert into fast_reservation (type, action_duration, action_start, duration, reservation_start, max_people, price)
values ('OTH', 5, '20220505 00:00:01 AM', 5, '20220512 11:00:00 AM', 3, 50);
insert into ship_fast_reservations (ship_id, fast_reservations_id) values (1, 1);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (4, 3, 'FINISHED', '20220423 10:05:00 AM', '20220428 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 6);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (3, 3, 'FINISHED', '20220423 10:05:00 AM', '20220428 10:05:00 AM');
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 7);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (5, 3, 'FINISHED', '20220420 10:05:00 AM', '20220430 10:05:00 AM');
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 8);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (6, 3, 'FINISHED', '20220415 10:05:00 AM', '20220430 10:05:00 AM');
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 9);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (1, 3, 'CLIENT_NOT_ARRIVED', '20220514 10:05:00 AM', '20220517 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 10);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (9, 3, 'FINISHED', '20220511 10:05:00 AM', '20220515 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 11);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (8, 3, 'FINISHED', '20220520 10:05:00 AM', '20220525 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 12);

INSERT INTO role (name) VALUES ('ROLE_CLIENT');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_COTTAGE');
INSERT INTO role (name) VALUES ('ROLE_SHIP');
INSERT INTO role (name) VALUES ('ROLE_ADVENTURE');

insert into rule (rule_text) values ('No smoking allowed');
insert into rule (rule_text) values ('No drinking allowed');
insert into rule (rule_text) values ('No pets allowed');
insert into rule (rule_text) values ('No noise after 23h');
insert into rule (rule_text) values ('Furniture must not be damaged');
insert into rule (rule_text) values ('Neighbors must not be disturbed');

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

insert into address (city,country,street) values ('Novi Sad','Srbija','Puskinova 6');
insert into address (city,country,street) values ('Beograd','Srbija','Marka Pola 10');
insert into address (city,country,street) values ('Novi Sad','Srbija','Bulevar oslobodjenja 105');
insert into address (city,country,street) values ('Pariz','Francuska','Puskinova 50');
insert into address (city,country,street) values ('London','Velika Britanija','Baker Street');
insert into address (city,country,street) values ('Rim','Italija','Balzakova 20');
insert into address (city,country,street) values ('Venecija','Italija','Marka Pola 20');
insert into address (city,country,street) values ('Novi Sad','Srbija','Puskinova 6');
insert into address (city,country,street) values ('Beograd','Srbija','Puskinova 69');

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('email1@gmail.com', 'Pero', 'pass1', '065-111-5555', 'Peric', 1);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,1,4);
insert into ship_owner (id) values (1);

insert into offer (additional_info, description, name, price_list, address_id) values ('Pruža mogućnosti za organizovanje poslovnih ručkova, koktela, prezentacija i promocija, kao i za neobavezno druženje. Brod ima profesionalno ozvučenje i prostranu otvorenu terasu na gornjoj palubi.', 'Brod je na dva nivoa', 'Sirena', 150, 1);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (70, 25, 40, 4, 100, 'SHIP', 1, 1);
insert into price_list (amount, start_date) values (130, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (1, 1);
insert into price_list (amount, start_date) values (150, '20220515 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (1, 2);


insert into profile_data (email, name, password, phone_number, surname, address_id) values ('email2@gmail.com', 'Djuro', '$2a$10$eJAhTBNusorL6jl1LxEuOeXwd75E1MV/XX8u67Fb/IO5yxpUmBOoC', '1233456', 'Djuric', 1); -- pass2
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,2,3);
insert into cottage_owner (id) values (2);


insert into offer (additional_info, description, name, price_list, address_id) values ('Smeštaj je pogodan za više ljudi (u isto vremena na splavu može maksimalno boraviti 20 osoba) koji žele da imaju odvojen prostor za spavanje, odvojen prostor za druženje, a i mesto za odmaranje na reci uz sav komoditet splava.', 'Ovo je dosta autentican prostor.', 'Frida River House', 300, 1);
insert into offer_rules (offer_id, rules_id) values (2, 1);
insert into offer_rules (offer_id, rules_id) values (2, 3);
insert into offer_rules (offer_id, rules_id) values (1, 5);
insert into price_list (amount, start_date) values (100, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (2, 3);
insert into cottage (id, cottage_owner_id) values (2, 2);
insert into price_list (amount, start_date) values (150, '20220520 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (2, 4);
insert into price_list (amount, start_date) values (300, '20220530 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (2, 5);

insert into cottage_images (cottage_id, images_id) values (2, 1);
insert into cottage_images (cottage_id, images_id) values (2, 6);
insert into cottage_images (cottage_id, images_id) values (2, 7);
insert into cottage_images (cottage_id, images_id) values (2, 8);
insert into cottage_images (cottage_id, images_id) values (2, 9);
insert into cottage_images (cottage_id, images_id) values (2, 10);
insert into cottage_images (cottage_id, images_id) values (2, 12);

insert into offer (additional_info, description, name, price_list, address_id) values ('Nalazimo se na glavnom regionalnom putu Raška - Kopaonik koji je uvek očišćen', 'Neki opis.', 'Dvori apartmani', 500, 3);
insert into offer_rules (offer_id, rules_id) values (3, 4);
insert into cottage (id, cottage_owner_id) values (3, 2);
insert into cottage_images (cottage_id, images_id) values (3, 2);
insert into price_list (amount, start_date) values (500, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (3, 6);


insert into profile_data (email,name,password,phone_number,surname,address_id) values ('emailInstr@gmail.com','ImeInstruktora','11223344','06444124214','PrezimeInstruktora',1);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,3,5);

insert into profile_data (email,name,password,phone_number,surname,address_id) values ('emailAdmin@gmail.com','Imeadmin','sifra','06444144444','PrezimeAdmin',2);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,4,2);
insert into admin (is_main, id) values (true , 4);

insert into fishing_instructor (biography, id) values ('Instruktor pecanja I',3);
insert into fishing_equipment (amount, name) values (10,'Fishing Rod');
insert into fishing_equipment (amount, name) values (10,'Fishing Line');
insert into fishing_equipment (amount, name) values (10,'Hooks');

insert into offer (additional_info, description, name, price_list, address_id) values ('additional info', 'neki opis', 'PonudaSQL', 500, 1);
insert into adventure (max_people,id,fishing_instructor_id) values (10, 4, 3);
insert into adventure_fishing_equipments(adventure_id,fishing_equipments_id) values(4,2);

insert into offer (additional_info, description, name, price_list, address_id) values ('additional info', 'opis mog broda', 'Maria', 500, 4);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (50, 100, 300, 15, 500, 'SHIP', 5, 1);
insert into price_list (amount, start_date) values (500, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (5, 7);

insert into offer (additional_info, description, name, price_list, address_id) values ('Enterijer apartmana predstvalja spoj planinskog dekora i svih potrebnih savremenih pogodnosti, koje će zadovoljiti krtiterijume i najzahtevnijih turista.', 'Ovo je apartman iz kog ćete najbrže početi da skijate.', 'Apartman Gondola', 60, 7);
insert into cottage (id, cottage_owner_id) values (6, 2);
insert into cottage_images (cottage_id, images_id) values (6, 3);
insert into price_list (amount, start_date) values (60, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (6, 8);

insert into offer (additional_info, description, name, price_list, address_id) values ('Prostrano dvorište objekta krasi veliki bazen dimenzija 12x6m, dubine 1.6m, sa izdvojenim djakuzi delom.', 'Vila Siesta je građena u meksičkom stilu. Idealna je za važe druženje i u hladnijim danima, kuhinju, trpezariju i kupatilo.', 'Vila Siesta', 240, 6);
insert into cottage (id, cottage_owner_id) values (7, 2);
insert into cottage_images (cottage_id, images_id) values (7, 4);
insert into price_list (amount, start_date) values (240, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (7, 9);

insert into offer (additional_info, description, name, price_list, address_id) values ('Kupatilo ima tuš kabinu.', 'Ispred kuće se nalazi bazen sa lepo uređenim dvorištem oko njega.', 'Vikendica Sunset', 150, 1);
insert into cottage (id, cottage_owner_id) values (8, 2);
insert into price_list (amount, start_date) values (150, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (8, 10);

insert into room (number_of_beds, cottage_id) values (6, 6);
insert into room (number_of_beds, cottage_id) values (1, 6);
insert into room (number_of_beds, cottage_id) values (2, 6);
insert into room (number_of_beds, cottage_id) values (3, 6);
insert into room (number_of_beds, cottage_id) values (3, 7);
insert into room (number_of_beds, cottage_id) values (2, 7);
insert into room (number_of_beds, cottage_id) values (4, 8);
insert into room (number_of_beds, cottage_id) values (3, 8);


insert into offer (additional_info, description, name, price_list, address_id) values ('Zbog komfora koji pruža predstavlja idealan izbor za dnevna i noćna krstarenja, kao i sve vrste proslava!', 'Ekskluzivan katamaran za rentiranje', 'Ariel', 300, 7);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (50, 20, 35, 2, 120, 'BOAT', 9, 1);
insert into price_list (amount, start_date) values (300, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (9, 11);

insert into offer (additional_info, description, name, price_list, address_id) values ('Poseduje kuhinju i toalet.', 'Moj camac za rentiranje', 'Narval', 100, 6);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (7, 10, 35, 2, 70, 'BOAT', 10, 1);
insert into price_list (amount, start_date) values (100, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (10, 12);

insert into ship_images (ship_id, images_id) values (1, 14);
insert into ship_images (ship_id, images_id) values (1, 16);
insert into ship_images (ship_id, images_id) values (1, 17);
insert into ship_images (ship_id, images_id) values (1, 18);
insert into ship_images (ship_id, images_id) values (1, 19);
insert into ship_images (ship_id, images_id) values (1, 20);
insert into ship_images (ship_id, images_id) values (5, 13);
insert into ship_images (ship_id, images_id) values (9, 15);

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('m@m', 'Marko', '$2a$10$4BdHmh6h4Y7BMm1ysD7pl.8EQSNKEG0nmJaqebJlGVh4.H.nQy1aa', '066-321-3443', 'Markovic', 3);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0, 5, 1);
insert into client (id) values (5);

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('milica.popovic55+2@hotmail.com', 'Mirko', 'pass3', '1233456', 'Mirkovic', 4);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,6,2);
insert into admin (id) values (6);

insert into feedback (comment, rating, status) values ('Very pretty boat', 10, 'APPROVED');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (1, 5, 'PENDING', '20220523 10:05:00 AM', '20220528 10:05:00 AM', 1);
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 1);
insert into availability (offer_id, star_date_time, end_date_time) values (1, '20220501 00:00:01 AM', '20220731 11:59:59 PM');


insert into feedback (comment, rating, status) values ('Very pretty cottage', 10, 'APPROVED');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (2, 5, 'PENDING', '20220520 10:10:00 AM', '20220526 10:00:00 PM', 2);
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 2);
insert into availability (offer_id, star_date_time, end_date_time) values (2, '20220401 00:00:01 AM', '20220831 11:59:59 PM');

insert into feedback (comment, rating, status) values ('Very nice cottage', 9, 'APPROVED');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (2, 5, 'PENDING', '20220602 09:10:00 AM', '20220605 04:00:00 PM', 3);
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 3);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (1, 5, 'FINISHED', '20220502 09:10:00 AM', '20220506 04:00:00 PM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 4);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (2, 5, 'CLIENT_NOT_ARRIVED', '20220502 09:10:00 AM', '20220510 04:00:00 PM');
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 5);

insert into fast_reservation (type, action_duration, action_start, duration, reservation_start, max_people, price)
    values ('OTH', 5, '20220505 00:00:01 AM', 5, '20220512 11:00:00 AM', 3, 50);
insert into ship_fast_reservations (ship_id, fast_reservations_id) values (1, 1);


insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (5, 5, 'FINISHED', '20220423 10:05:00 AM', '20220428 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 6);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (3, 5, 'FINISHED', '20220423 10:05:00 AM', '20220428 10:05:00 AM');
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 7);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (6, 5, 'FINISHED', '20220420 10:05:00 AM', '20220430 10:05:00 AM');
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 8);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (7, 5, 'FINISHED', '20220415 10:05:00 AM', '20220430 10:05:00 AM');
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 9);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (1, 5, 'CLIENT_NOT_ARRIVED', '20220514 10:05:00 AM', '20220517 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 10);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (10, 5, 'FINISHED', '20220511 10:05:00 AM', '20220515 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 11);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (9, 5, 'FINISHED', '20220520 10:05:00 AM', '20220525 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 12);
---------------------------------------------
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (9, 5, 'PENDING', '20220720 10:05:00 AM', '20220725 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 13);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (9, 5, 'PENDING', '20220720 10:05:00 AM', '20220725 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 14);

insert into sub (user_id, offer_id) values (5, 2);
insert into sub (user_id, offer_id) values (5, 1);

insert into fast_reservation (type, action_duration, action_start, duration, reservation_start, max_people, price)
    values ('OTH', 5, '20220712 00:00:01 AM', 5, '20220712 11:00:00 AM', 3, 20);
insert into ship_fast_reservations (ship_id, fast_reservations_id) values (1, 2);

insert into fast_reservation (type, action_duration, action_start, duration, reservation_start, max_people, price)
    values ('OTH', 7, '20220707 00:00:01 AM', 3, '20220707 11:00:00 AM', 10, 500);
insert into ship_fast_reservations (ship_id, fast_reservations_id) values (1, 3);

insert into fast_reservation (type, action_duration, action_start, duration, reservation_start, max_people, price)
    values ('OTH', 1, '20220715 00:00:01 AM', 2, '20220715 11:00:00 AM', 1, 30);
insert into ship_fast_reservations (ship_id, fast_reservations_id) values (1, 4);









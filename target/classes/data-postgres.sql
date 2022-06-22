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

insert into image (path) values ('images/adv1.jpeg');
insert into image (path) values ('images/adv2.jpeg');
insert into image (path) values ('images/adv3.jpeg');
insert into image (path) values ('images/adv4.jpeg');
insert into image (path) values ('images/adv5.jpeg');
insert into image (path) values ('images/adv6.jpeg');

insert into address (city,country,street) values ('Novi Sad','Srbija','Puskinova 6');
insert into address (city,country,street) values ('Beograd','Srbija','Marka Pola 10');
insert into address (city,country,street) values ('Novi Sad','Srbija','Bulevar oslobodjenja 105');
insert into address (city,country,street) values ('Pariz','Francuska','Puskinova 50');
insert into address (city,country,street) values ('London','Velika Britanija','Baker Street');
insert into address (city,country,street) values ('Rim','Italija','Balzakova 20');
insert into address (city,country,street) values ('Venecija','Italija','Marka Pola 20');
insert into address (city,country,street) values ('Novi Sad','Srbija','Puskinova 6');
insert into address (city,country,street) values ('Beograd','Srbija','Puskinova 69');
insert into address (city,country,street) values ('Beograd','Srbija','Puskinova 55');
insert into address (city,country,street) values ('Beograd','Srbija','Puskinova 70');
insert into address (city,country,street) values ('Beograd','Srbija','Puskinova 71');
insert into address (city,country,street) values ('Beograd','Srbija','Puskinova 72');
insert into address (city,country,street) values ('Rim','Italija','Balzakova 21');
insert into address (city,country,street) values ('Rim','Italija','Balzakova 22');
insert into address (city,country,street) values ('Rim','Italija','Balzakova 23');
insert into address (city,country,street) values ('Rim','Italija','Balzakova 25');
insert into address (city,country,street) values ('Rim','Italija','Balzakova 28');
insert into address (city,country,street) values ('Venecija','Italija','Marka Pola 22');
insert into address (city,country,street) values ('Venecija','Italija','Marka Pola 25');
insert into address (city,country,street) values ('Venecija','Italija','Marka Pola 28');
insert into address (city,country,street) values ('Venecija','Italija','Marka Pola 29');
insert into address (city,country,street) values ('Venecija','Italija','Marka Pola 76');

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('milica.popovic55+5@hotmail.com', 'Pero', '$2a$10$/rU.Ul4ovYWb/BWMKJnWqeC7ye5ZU9XnRuYCE2wdExlmxRwuy0J12', '065-111-5555', 'Peric', 1);  -- pass1
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,1,4);
insert into ship_owner (id) values (1);

insert into offer (additional_info, description, name, price_list, address_id, version) values ('Pruža mogućnosti za organizovanje poslovnih ručkova, koktela, prezentacija i promocija, kao i za neobavezno druženje. Brod ima profesionalno ozvučenje i prostranu otvorenu terasu na gornjoj palubi.', 'Brod je na dva nivoa', 'Sirena', 150, 2, 0);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (70, 25, 40, 4, 100, 'SHIP', 1, 1);
insert into price_list (amount, start_date) values (130, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (1, 1);
insert into price_list (amount, start_date) values (150, '20220515 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (1, 2);


insert into profile_data (email, name, password, phone_number, surname, address_id) values ('milica.popovic55+6@hotmail.com', 'Djuro', '$2a$10$eJAhTBNusorL6jl1LxEuOeXwd75E1MV/XX8u67Fb/IO5yxpUmBOoC', '1233456', 'Djuric', 3); -- pass2
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,2,3);
insert into cottage_owner (id) values (2);


insert into offer (additional_info, description, name, price_list, address_id, version) values ('Smeštaj je pogodan za više ljudi (u isto vremena na splavu može maksimalno boraviti 20 osoba) koji žele da imaju odvojen prostor za spavanje, odvojen prostor za druženje, a i mesto za odmaranje na reci uz sav komoditet splava.', 'Ovo je dosta autentican prostor.', 'Frida River House', 300, 4, 0);
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

insert into offer (additional_info, description, name, price_list, address_id, version) values ('Nalazimo se na glavnom regionalnom putu Raška - Kopaonik koji je uvek očišćen', 'Neki opis.', 'Dvori apartmani', 500, 5, 0);
insert into offer_rules (offer_id, rules_id) values (3, 4);
insert into cottage (id, cottage_owner_id) values (3, 2);
insert into cottage_images (cottage_id, images_id) values (3, 2);
insert into price_list (amount, start_date) values (500, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (3, 6);


insert into profile_data (email,name,password,phone_number,surname,address_id) values ('milica.popovic55+420@hotmail.com','ImeInstruktora','$2a$10$M9e44e0uS7tE34vlkKMGxulqdSGey86502fkLNbUt60A9hc/peUj6','06444124214','PrezimeInstruktora',6);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,3,5);

insert into profile_data (email,name,password,phone_number,surname,address_id) values ('milica.popovic55+69@hotmail.com','Imeadmin','$2a$10$M9e44e0uS7tE34vlkKMGxulqdSGey86502fkLNbUt60A9hc/peUj6','06444144444','PrezimeAdmin',7);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,4,2);
insert into admin (is_main, id) values (true , 4);

insert into fishing_instructor (biography, id) values ('Instruktor pecanja I',3);
insert into fishing_equipment (amount, name) values (10,'Fishing Rod');
insert into fishing_equipment (amount, name) values (10,'Fishing Line');
insert into fishing_equipment (amount, name) values (10,'Hooks');

insert into offer (additional_info, description, name, price_list, address_id, version) values ('additional info', 'neki opis', 'PonudaSQL', 500, 8, 0);
insert into adventure (max_people,id,fishing_instructor_id) values (10, 4, 3);
insert into adventure_fishing_equipments(adventure_id,fishing_equipments_id) values(4,2);
insert into adventure_images (adventure_id, images_id) values (4, 22);
insert into adventure_images (adventure_id, images_id) values (4, 23);
insert into adventure_images (adventure_id, images_id) values (4, 24);
insert into adventure_images (adventure_id, images_id) values (4, 25);
insert into adventure_images (adventure_id, images_id) values (4, 26);

insert into offer (additional_info, description, name, price_list, address_id, version) values ('additional info', 'opis mog broda', 'Maria', 500, 9, 0);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (50, 100, 300, 15, 500, 'SHIP', 5, 1);
insert into price_list (amount, start_date) values (500, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (5, 7);

insert into offer (additional_info, description, name, price_list, address_id, version) values ('Enterijer apartmana predstvalja spoj planinskog dekora i svih potrebnih savremenih pogodnosti, koje će zadovoljiti krtiterijume i najzahtevnijih turista.', 'Ovo je apartman iz kog ćete najbrže početi da skijate.', 'Apartman Gondola', 60, 10, 0);
insert into cottage (id, cottage_owner_id) values (6, 2);
insert into cottage_images (cottage_id, images_id) values (6, 3);
insert into price_list (amount, start_date) values (60, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (6, 8);

insert into offer (additional_info, description, name, price_list, address_id, version) values ('Prostrano dvorište objekta krasi veliki bazen dimenzija 12x6m, dubine 1.6m, sa izdvojenim djakuzi delom.', 'Vila Siesta je građena u meksičkom stilu. Idealna je za važe druženje i u hladnijim danima, kuhinju, trpezariju i kupatilo.', 'Vila Siesta', 240, 11, 0);
insert into cottage (id, cottage_owner_id) values (7, 2);
insert into cottage_images (cottage_id, images_id) values (7, 4);
insert into price_list (amount, start_date) values (240, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (7, 9);

insert into offer (additional_info, description, name, price_list, address_id, version) values ('Kupatilo ima tuš kabinu.', 'Ispred kuće se nalazi bazen sa lepo uređenim dvorištem oko njega.', 'Vikendica Sunset', 150, 12, 0);
insert into cottage (id, cottage_owner_id) values (8, 2);
insert into price_list (amount, start_date) values (150, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (8, 10);

insert into room (number_of_beds, cottage_id) values (6, 6);
insert into room (number_of_beds, cottage_id) values (1, 6);
insert into room (number_of_beds, cottage_id) values (2, 6);
insert into room (number_of_beds, cottage_id) values (3, 6);
insert into room (number_of_beds, cottage_id) values (3, 7);
insert into room (number_of_beds, cottage_id) values (2, 7);
insert into room (number_of_beds, cottage_id) values (4, 2);
insert into room (number_of_beds, cottage_id) values (3, 2);
insert into room (number_of_beds, cottage_id) values (3, 3);
insert into room (number_of_beds, cottage_id) values (3, 3);
insert into room (number_of_beds, cottage_id) values (5, 8);
insert into room (number_of_beds, cottage_id) values (2, 8);


insert into offer (additional_info, description, name, price_list, address_id, version) values ('Zbog komfora koji pruža predstavlja idealan izbor za dnevna i noćna krstarenja, kao i sve vrste proslava!', 'Ekskluzivan katamaran za rentiranje', 'Ariel', 300, 13, 0);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (50, 20, 35, 2, 120, 'BOAT', 9, 1);
insert into price_list (amount, start_date) values (300, '20220501 00:00:01 AM');
insert into offer_price_history (offer_id, price_history_id) values (9, 11);

insert into offer (additional_info, description, name, price_list, address_id, version) values ('Poseduje kuhinju i toalet.', 'Moj camac za rentiranje', 'Narval', 100, 14, 0);
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

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('milica.popovic55@hotmail.com', 'Marko', '$2a$10$4BdHmh6h4Y7BMm1ysD7pl.8EQSNKEG0nmJaqebJlGVh4.H.nQy1aa', '066-321-3443', 'Markovic', 3);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0, 5, 1);
insert into client (id) values (5);

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('milica.popovic55+2@hotmail.com', 'Mirko', 'pass3', '1233456', 'Mirkovic', 16);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,6,2);
insert into admin (id) values (6);



insert into feedback (comment, rating, status) values ('Very pretty boat', 10, 'APPROVED');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (1, 5, 'PENDING', '20220523 10:00:00 AM', '20220523 4:00:00 PM', 1);
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 1);
insert into availability (offer_id, star_date_time, end_date_time) values (1, '20220501 00:00:01 AM', '20220731 11:59:59 PM');


insert into feedback (comment, rating, status) values ('Very pretty cottage', 10, 'APPROVED');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (2, 5, 'PENDING', '20220520 10:10:00 AM', '20220526 10:00:00 PM', 2);
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 2);
insert into availability (offer_id, star_date_time, end_date_time) values (2, '20220401 00:00:01 AM', '20220831 11:59:59 PM');

insert into feedback (comment, rating, status) values ('Very nice cottage', 9, 'APPROVED');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (2, 5, 'PENDING', '20220602 09:10:00 AM', '20220605 04:00:00 PM', 3);
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 3);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (1, 5, 'FINISHED', '20220502 09:10:00 AM', '20220502 03:10:00 PM');
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

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (1, 5, 'CLIENT_NOT_ARRIVED', '20220514 10:00:00 AM', '20220515 07:00:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 10);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (10, 5, 'FINISHED', '20220511 10:05:00 AM', '20220515 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 11);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (9, 5, 'FINISHED', '20220520 10:05:00 AM', '20220525 10:05:00 AM');
insert into ship_owner_reservations (ship_owner_id, reservations_id) values (1, 12);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (2, 5, 'ACTIVE', '20220615 11:00:00 AM', '20220620 11:00:00 AM');
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 13);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (2, 5, 'PENDING', '20220627 11:00:00 AM', '20220630 11:00:00 AM');
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 14);

insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (3, 5, 'PENDING', '20220701 10:05:00 AM', '20220710 10:05:00 AM');
insert into cottage_owner_reservations (cottage_owner_id, reservations_id) values (2, 15);

insert into sub (user_id, offer_id) values (5, 2);

insert into availability (offer_id, star_date_time, end_date_time) values (3, '20220402 00:00:01 AM', '20220830 11:59:59 PM');
insert into availability (offer_id, star_date_time, end_date_time) values (4, '20220202 00:00:01 AM', '20220930 11:59:59 PM');
insert into availability (offer_id, star_date_time, end_date_time) values (5, '20220302 00:00:01 AM', '20221030 11:59:59 PM');
insert into availability (offer_id, star_date_time, end_date_time) values (6, '20220202 00:00:01 AM', '20221130 11:59:59 PM');
insert into availability (offer_id, star_date_time, end_date_time) values (7, '20220402 00:00:01 AM', '20220830 11:59:59 PM');
insert into availability (offer_id, star_date_time, end_date_time) values (8, '20220302 00:00:01 AM', '20220730 11:59:59 PM');
insert into availability (offer_id, star_date_time, end_date_time) values (9, '20220402 00:00:01 AM', '20220830 11:59:59 PM');

-- /////////////////////////////////////
-- /////////////////////////////////////
insert into profile_data (email, name, password, phone_number, surname, address_id) values ('aleksadsimic@gmail.com', 'Aleksa', 'sifra', '064440044', 'Prezimic', 17);
insert into my_users (category,number_of_points,profile_data_id,role_id) values ('REGULAR',0,7,1);
insert into client (id) values (7);


insert into complaint(content,complaint_date_time,status,issued_by_id) values ('zalba na nesto ili nekog','20220520 10:05:00 AM','PENDING',7);
insert into complaint(content,complaint_date_time,status,issued_by_id) values ('vrv email korisnika','20220520 10:05:00 AM','PENDING',5);
insert into complaint(content,complaint_date_time,status,issued_by_id) values ('vrv email korisnika','20220520 10:05:00 AM','PENDING',5);

insert into deletion_request(request_date_time,status,sent_by_id) values ('20220520 10:05:00 AM','PENDING',7);
insert into deletion_request(request_date_time,status,sent_by_id) values ('20220520 10:05:00 AM','PENDING',5);
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (4, 5, 'FINISHED', '20220520 10:05:00 AM', '20220525 10:05:00 AM');
insert into fishing_instructor_reservations (fishing_instructor_id, reservations_id) values (3, 13);
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time) values (4, 7, 'FINISHED', '20220520 10:05:00 AM', '20220525 10:05:00 AM');
insert into fishing_instructor_reservations (fishing_instructor_id, reservations_id) values (3, 14);

insert into client_review(content,complaint_date_time,penalty_requested,client_id,issued_by_id) values ('ovo je content', '20220520 10:05:00 AM', true, 5,3);
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, client_review_id) values (1, 5, 'PENDING', '20220520 10:10:00 AM', '20220526 10:00:00 PM', 1);
insert into client_review(content,complaint_date_time,penalty_requested,client_id,issued_by_id) values ('ovo je content 2', '20220520 10:05:00 AM', false , 5,3);
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, client_review_id) values (4, 7, 'PENDING', '20220520 10:10:00 AM', '20220526 10:00:00 PM', 2);
insert into client_review(content,complaint_date_time,penalty_requested,client_id,issued_by_id) values ('ovo je content', '20220520 10:05:00 AM', true, 5,3);
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, client_review_id) values (5, 5, 'PENDING', '20220520 10:10:00 AM', '20220526 10:00:00 PM', 3);
insert into client_review(content,complaint_date_time,penalty_requested,client_id,issued_by_id) values ('ovo je content', '20220520 10:05:00 AM', false, 5,3);
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, client_review_id) values (4, 7, 'PENDING', '20220520 10:10:00 AM', '20220526 10:00:00 PM', 4);


insert into feedback (comment, rating, status) values ('Very pretty boat', 10, 'PENDING');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (10, 5, 'PENDING', '20220520 10:10:00 AM', '20220526 10:00:00 PM', 4);
insert into feedback (comment, rating, status) values ('Solid', 9, 'PENDING');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (2, 7, 'PENDING', '20220520 10:10:00 AM', '20220526 10:00:00 PM', 5);
insert into feedback (comment, rating, status) values ('Pretty good', 8, 'PENDING');
insert into reservation (offer_id, client_id, reservation_status, start_date_time, end_date_time, feedback_id) values (4, 5, 'PENDING', '20220520 10:10:00 AM', '20220526 10:00:00 PM', 6);

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('milica.popovic55+10@hotmail.com', 'Novak', '$2a$10$M9e44e0uS7tE34vlkKMGxulqdSGey86502fkLNbUt60A9hc/peUj6', '064440044', 'Prezimic', 18);
insert into registration_request (approval_status,date_time,reason,registration_type,profile_data_id) values ('PENDING', '20220520 10:10:00 AM','zelim da budem registrovaaaaan' ,'SHIP_OWNER', 8);
-------- Mislim da ovo mora biti odradjeno kad se potvrdi zahtev za registrovanje --------
insert into profile_data (email, name, password, phone_number, surname, address_id) values ('milica.popovic55+211@hotmail.com', 'Novakkk', '$2a$10$M9e44e0uS7tE34vlkKMGxulqdSGey86502fkLNbUt60A9hc/peUj6', '064440044', 'Prezimic', 19);
insert into registration_request (approval_status,date_time,reason,registration_type,profile_data_id) values ('PENDING', '20220520 10:10:00 AM','zelim da budem registrovaaaaan' ,'FISHING_OWNER', 9);

---------Loyalty program----------------
insert into coefficients(owner_reservation_points,percentage_client_gold,percentage_client_silver,percentage_owner_gold,percentage_owner_silver,required_points_gold,required_points_silver,reservation_percentage,user_reservation_points) values (10,2.2,3.4,4.1,5.3,3,4,6.9,8);

-- ////// IZGLEDA RUZNO ALI SAD ZASAD MORA OSTATI OVA LINIJA ////////// BROJ 11
insert into offer (additional_info, description, name, price_list, address_id) values ('-1', '-1', '-1', 0, 20);
-- ////////////////////////////////////////////////////////////////////

insert into offer (additional_info, description, name, price_list, address_id, version) values ('additional info', 'neki opis', 'NovaPonuda', 500, 21, 0);
insert into adventure (max_people,id,fishing_instructor_id) values (10, 12, 3);
insert into adventure_fishing_equipments(adventure_id,fishing_equipments_id) values(12,2);
insert into adventure_images (adventure_id, images_id) values (12, 21);

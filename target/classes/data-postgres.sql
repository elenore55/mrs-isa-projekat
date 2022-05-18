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
insert into address (city,country,street) values ('London','Velika Britanija','Baker Street');
insert into address (city,country,street) values ('Rim','Italija','Balzakova 20');
insert into address (city,country,street) values ('Venecija','Italija','Marka Pola 20');

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('email@gmail.com', 'Pero', 'pass1', '123345', 'Peric', 1);
-- insert into profile_data (email,name,password,phone_number,surname,address_id) values ('email@gmail.com','ImeInstruktora','11223344','06444124214','PrezimeInstruktora',11);
insert into my_users (category,number_of_points,profile_data_id) values ('REGULAR',0,1);
insert into ship_owner (id) values (1);
insert into offer (additional_info, description, name, price_list, address_id) values ('Pruža mogućnosti za organizovanje poslovnih ručkova, koktela, prezentacija i promocija, kao i za neobavezno druženje. Brod ima profesionalno ozvučenje i prostranu otvorenu terasu na gornjoj palubi.', 'Brod je na dva nivoa', 'Sirena', 150, 1);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (70, 25, 40, 4, 100, 'SHIP', 1, 1);

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('email2@gmail.com', 'Djuro', 'pass2', '1233456', 'Djuric', 1);
insert into my_users (category,number_of_points,profile_data_id) values ('REGULAR',0,2);
insert into cottage_owner (id) values (2);

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

insert into offer (additional_info, description, name, price_list, address_id) values ('Smeštaj je pogodan za više ljudi (u isto vremena na splavu može maksimalno boraviti 20 osoba) koji žele da imaju odvojen prostor za spavanje, odvojen prostor za druženje, a i mesto za odmaranje na reci uz sav komoditet splava.', 'Ovo je dosta autentican prostor.', 'Frida River House', 300, 1);
insert into offer_rules (offer_id, rules_id) values (2, 1);
insert into offer_rules (offer_id, rules_id) values (2, 3);
insert into offer_rules (offer_id, rules_id) values (1, 5);
insert into cottage (id, cottage_owner_id) values (2, 2);

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

insert into room (number_of_beds, cottage_id) values (5, 3);
insert into room (number_of_beds, cottage_id) values (3, 3);
insert into room (number_of_beds, cottage_id) values (2, 2);
insert into room (number_of_beds, cottage_id) values (3, 2);
insert into room (number_of_beds, cottage_id) values (5, 2);

insert into offer (additional_info, description, name, price_list, address_id) values ('additional info', 'opis mog broda', 'Maria', 500, 4);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (50, 100, 300, 15, 500, 'SHIP', 4, 1);


insert into offer (additional_info, description, name, price_list, address_id) values ('Enterijer apartmana predstvalja spoj planinskog dekora i svih potrebnih savremenih pogodnosti, koje će zadovoljiti krtiterijume i najzahtevnijih turista.', 'Ovo je apartman iz kog ćete najbrže početi da skijate.', 'Apartman Gondola', 60, 7);
--insert into offer_rules (offer_id, rules_id) values (5, 1);
--insert into offer_rules (offer_id, rules_id) values (5, 4);
--insert into offer_rules (offer_id, rules_id) values (5, 5);
insert into cottage (id, cottage_owner_id) values (5, 2);
insert into cottage_images (cottage_id, images_id) values (5, 3);

insert into offer (additional_info, description, name, price_list, address_id) values ('Prostrano dvorište objekta krasi veliki bazen dimenzija 12x6m, dubine 1.6m, sa izdvojenim djakuzi delom.', 'Vila Siesta je građena u meksičkom stilu. Idealna je za važe druženje i u hladnijim danima, kuhinju, trpezariju i kupatilo.', 'Vila Siesta', 240, 6);
--insert into offer_rules (offer_id, rules_id) values (6, 1);
--insert into offer_rules (offer_id, rules_id) values (6, 4);
insert into cottage (id, cottage_owner_id) values (6, 2);
insert into cottage_images (cottage_id, images_id) values (6, 4);

insert into offer (additional_info, description, name, price_list, address_id) values ('Kupatilo ima tuš kabinu.', 'Ispred kuće se nalazi bazen sa lepo uređenim dvorištem oko njega.', 'Vikendica Sunset', 150, 1);
--insert into offer_rules (offer_id, rules_id) values (7, 1);
--insert into offer_rules (offer_id, rules_id) values (7, 4);
insert into cottage (id, cottage_owner_id) values (7, 2);
--insert into cottage_images (cottage_id, images_id) values (7, 5);


insert into room (number_of_beds, cottage_id) values (5, 5);
insert into room (number_of_beds, cottage_id) values (1, 5);
insert into room (number_of_beds, cottage_id) values (2, 5);
insert into room (number_of_beds, cottage_id) values (3, 5);
insert into room (number_of_beds, cottage_id) values (3, 6);
insert into room (number_of_beds, cottage_id) values (2, 6);
insert into room (number_of_beds, cottage_id) values (4, 7);
insert into room (number_of_beds, cottage_id) values (3, 7);


insert into offer (additional_info, description, name, price_list, address_id) values ('Zbog komfora koji pruža predstavlja idealan izbor za dnevna i noćna krstarenja, kao i sve vrste proslava!', 'Ekskluzivan katamaran za rentiranje', 'Ariel', 300, 7);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (50, 20, 35, 2, 120, 'BOAT', 8, 1);

insert into offer (additional_info, description, name, price_list, address_id) values ('Poseduje kuhinju i toalet.', 'Moj camac za rentiranje', 'Narval', 100, 6);
insert into ship (capacity, ship_length, max_speed, number_of_engines, power_of_engine, ship_type, id, ship_owner_id) values (7, 10, 35, 2, 70, 'BOAT', 9, 1);

insert into ship_images (ship_id, images_id) values (1, 14);
insert into ship_images (ship_id, images_id) values (1, 16);
insert into ship_images (ship_id, images_id) values (1, 17);
insert into ship_images (ship_id, images_id) values (1, 18);
insert into ship_images (ship_id, images_id) values (1, 19);
insert into ship_images (ship_id, images_id) values (1, 20);
insert into ship_images (ship_id, images_id) values (4, 13);
insert into ship_images (ship_id, images_id) values (8, 15);

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('milica.popovic55@hotmail.com', 'Marko', 'pass3', '1233456', 'Markovic', 3);
insert into my_users (category,number_of_points,profile_data_id) values ('REGULAR',0,3);
insert into client (id) values (3);

insert into sub (user_id, offer_id) values (3, 2);

insert into profile_data (email, name, password, phone_number, surname, address_id) values ('milica.popovic55+2@hotmail.com', 'Mirko', 'pass3', '1233456', 'Mirkovic', 4);
insert into my_users (category,number_of_points,profile_data_id) values ('REGULAR',0,3);
insert into admin (id) values (4);

-- insert into fishing_instructor (biography,id) values ('Instruktor pecanja I',5);
-- insert into fishing_equipment (amount, name) values (10,'Fishing Rod');
-- insert into fishing_equipment (amount, name) values (10,'Fishing Line');
-- insert into fishing_equipment (amount, name) values (10,'Hooks');
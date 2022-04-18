insert into address (id,city,country,street) values (1,'Novi Sad','Srbija','Puskinova 6');
insert into profile_data (id,email,name,password,phone_number,surname,address_id) values (1,'email@gmail.com','ImeInstruktora','11223344','06444124214','PrezimeInstruktora',1);
insert into my_users (id,category,number_of_points,profile_data_id) values (1,'BRONZE',0,1);
insert into fishing_instructor (biography, id) values ('Instruktor pecanja I',1);
insert into fishing_equipment (amount, name) values (10,'stapovi');

--Authorization
INSERT INTO authority (name) values ('ROLE_PATIENT');
INSERT INTO authority (name) values ('ROLE_DOCTOR');
INSERT INTO authority (name) values ('ROLE_NURSE');
INSERT INTO authority (name) values ('ROLE_CLINIC_ADMIN');
INSERT INTO authority (name) values ('ROLE_CC_ADMIN');

--Clinic
insert into clinic (address, city, description, name, stars, num_votes, latitude, longitude)
values ('Dobricina 27', 'Beograd', 'Ocna bolnica', 'Sveti vid', 4, 9, '44.819580','20.462440');
insert into clinic (address, city, description, name, stars, num_votes, latitude, longitude)
values ('Hajduk Veljkova 1', 'Novi Sad', 'Klinicki centar Vojvodine (Novi Sad)', 'Klinicki centar Vojvodine', 5, 10, '45.250330','19.823720');
insert into clinic (address, city, description, name, stars, num_votes, latitude, longitude)
values ('Cvijiceva 42', 'Beograd', 'Bolnicki centar', 'Euromedic', 3, 10, '44.815670', '20.476900');
insert into clinic (address, city, description, name, stars, num_votes, latitude, longitude)
values ('Bulevar oslobodjenja 18', 'Novi Sad', 'Klinicki centar', 'Klinika Svjetlost', 3, 10, '45.261030','19.831350');
insert into clinic (address, city, description, name, stars, num_votes, latitude, longitude)
values ('Laze Nancica 5', 'Novi Sad', 'Specijalisticka bolnica opste hirurgije', 'Global care surgery', 5, 10,'45.247230','19.823830');

--AppointmentTypes
insert into appointment_type (name, clinic_id)
values ('Kardiologija', 1);

insert into appointment_type (name, clinic_id)
values ('Gastrologija', 1);

insert into appointment_type (name, clinic_id)
values ('Ginekologija', 1);

insert into appointment_type (name, clinic_id)
values ('Opsta praksa', 1);

insert into appointment_type (name, clinic_id)
values ('Onkologija', 2);

insert into appointment_type (name, clinic_id)
values ('Opsta praksa', 2);

insert into appointment_type (name, clinic_id)
values ('Pedijatrija', 2);

insert into appointment_type (name, clinic_id)
values ('Dermatologija', 2);

insert into appointment_type (name, clinic_id)
values ('Dermatologija', 3);

insert into appointment_type (name, clinic_id)
values ('Opsta praksa', 3);

insert into appointment_type (name, clinic_id)
values ('Ginekologija', 4);

insert into appointment_type (name, clinic_id)
values ('Kardiologija', 4);

---AppointmentPrices

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (1, 'OPERATION', 3000);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (1, 'EXAMINATION', 30);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (2, 'OPERATION', 500);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (2, 'EXAMINATION', 25);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (3, 'OPERATION', 490);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (3, 'EXAMINATION', 15);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (4, 'OPERATION', 450);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (4, 'EXAMINATION', 10);

--------- Dodati za drugu kliniku

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (5, 'OPERATION', 3500);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (5, 'EXAMINATION', 30);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (6, 'OPERATION', 600);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (6, 'EXAMINATION', 20);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (7, 'OPERATION', 450);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (7, 'EXAMINATION', 15);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (8, 'OPERATION', 350);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (8, 'EXAMINATION', 15);

---------Trecu kliniku


insert into appointment_price (appointment_type_id, appointment_enum, price)
values (9, 'OPERATION', 370);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (9, 'EXAMINATION', 9);


insert into appointment_price (appointment_type_id, appointment_enum, price)
values (10, 'OPERATION', 400);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (10, 'EXAMINATION', 20);

----------Cetvrtu kliniku


insert into appointment_price (appointment_type_id, appointment_enum, price)
values (11, 'OPERATION', 300);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (11, 'EXAMINATION', 5);


insert into appointment_price (appointment_type_id, appointment_enum, price)
values (12, 'OPERATION', 310);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (12, 'EXAMINATION', 11);

-----------------------------------USERS----------------------------------------

--Predefined Clinic Center Admin
insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, user_status)
values ('CC_ADMIN', 'admin@gmail.com', '$2y$12$4zrqOojpixOe/ogFw1xyyuQuIvFqrzbj0IohYtshqqy1P5rS6kdbq', 'Admin', 'Adminović', '0216362999', 'Zeleznicka ulica 69','Ndzamena','Chad', 'ACTIVE');
INSERT INTO account_authority (account_id, authority_id) values  (1, 5);

--Clinic Admin
insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, user_status, clinic_id)
values ('CLINIC_ADMIN', 'cadmin1@gmail.com', '$2y$12$4zrqOojpixOe/ogFw1xyyuQuIvFqrzbj0IohYtshqqy1P5rS6kdbq', 'Marko', 'Marković', '066123456', 'Zeleznicka ulica 69','Ndzamena','Chad', 'ACTIVE', 1);
INSERT INTO account_authority (account_id, authority_id) values  (2, 4);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, user_status, clinic_id)
values ('CLINIC_ADMIN', 'cadmin2@gmail.com', '$2y$12$4zrqOojpixOe/ogFw1xyyuQuIvFqrzbj0IohYtshqqy1P5rS6kdbq', 'Milos', 'Marković', '066123457', 'Zeleznicka ulica 69','Ndzamena','Chad', 'NEVER_LOGGED_IN', 2);
INSERT INTO account_authority (account_id, authority_id) values  (3, 4);


--Doctor
--Password: dok
insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok@gmail.com', '$2y$12$u3nc1wRBsop15oZaI2FqVuSHFsD9ZHeGXcpXKwGeD3on4zv3BRWd6', 'Dok', 'Dokic', '065525404', 'Zeleznicka ulica 69','Ndzamena','Chad', 1, 1, 'ACTIVE', 50, 10, '07:55', '14:00');
INSERT INTO account_authority (account_id, authority_id) values  (4, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok2@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Sima', 'Dokic', '060123456', 'Zeleznicka ulica 69','Ndzamena','Chad', 2, 1, 'ACTIVE', 46, 10, '07:55', '14:00');
INSERT INTO account_authority (account_id, authority_id) values  (5, 2);

--Nurse
insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status)
values ('NURSE', 'sestra@gmail.com', '$2y$12$M6EnOZMRiIFt/znBY/C8r.2sglykYnJ0jj2Zm.mGpB3mdG9hD0BeW', 'Sima', 'Simic', '065256155', 'Zeleznicka ulica 69','Ndzamena','Chad', '1','ACTIVE');
INSERT INTO account_authority (account_id, authority_id) values  (6, 3);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status)
values ('NURSE', 'sestra2@gmail.com', '$2y$12$M6EnOZMRiIFt/znBY/C8r.2sglykYnJ0jj2Zm.mGpB3mdG9hD0BeW', 'Sestra', 'Simic', '065365157', 'Zeleznicka ulica 82','Ndzamena','Chad', '1','NEVER_LOGGED_IN');
INSERT INTO account_authority (account_id, authority_id) values  (7, 3);

--Patient
insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Pata', 'Patic', '065256165', 'Zeleznicka ulica 69','Ndzamena','Chad','901204931212','APPROVED');
INSERT INTO account_authority (account_id, authority_id) values  (8, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent3@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Kongo', 'Kongic', '065259165', 'Heroja Pinkija 69','Kinsasa','DR Kongo','1235256262','APPROVED');
INSERT INTO account_authority (account_id, authority_id) values  (9, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent4@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Sudan', 'Sudic', '065256065', 'Jevrejska 69','Dzuba','Juzni Sudan','124532636362','APPROVED');
INSERT INTO account_authority (account_id, authority_id) values  (10, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent5@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Dragoje', 'Radic', '065256565', 'Kisacka 69','Maseru','Lesoto','14324234243','AWAITING_APPROVAL');
INSERT INTO account_authority (account_id, authority_id) values  (11, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent6@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Milovan', 'Radovic', '0652565445', 'Bulevar Oslobodjenja 69','Maseru','Lesoto','14324234212','APPROVED');
INSERT INTO account_authority (account_id, authority_id) values  (12, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent7@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Kristina', 'Kekic', '0652565889', 'Bulevar Kralja Petra 69','Maseru','Lesoto','14324234223','APPROVED');
INSERT INTO account_authority (account_id, authority_id) values  (13, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent8@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Milica', 'Petrovic', '0652565229', 'Gogoljeva 69','Maseru','Lesoto','14324234256','APPROVED');
INSERT INTO account_authority (account_id, authority_id) values  (14, 1);

--doc2
insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok3@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Mika', 'Dokic', '060321456', 'Zeleznicka ulica 49','Ndzamena','Chad', 5, 2, 'ACTIVE', 46, 10, '07:55', '14:00');
INSERT INTO account_authority (account_id, authority_id) values  (15, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok4@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Mile', 'Dokic', '060123654', 'Zeleznicka ulica 64','Ndzamena','Chad', 6, 2, 'ACTIVE', 46, 10, '07:55', '14:00');
INSERT INTO account_authority (account_id, authority_id) values  (16, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok5@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Jovan', 'Cvijic', '060222654', 'Zeleznicka ulica 65','Ndzamena','Chad', 2, 1, 'ACTIVE', 42, 10, '07:55', '14:00');
INSERT INTO account_authority (account_id, authority_id) values  (17, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok6@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Jova', 'Cvetin', '060222654', 'Zeleznicka ulica 65','Ndzamena','Chad', 7, 2, 'ACTIVE', 42, 10, '07:55', '14:00');
INSERT INTO account_authority (account_id, authority_id) values  (18, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok7@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Jovan', 'Cvijic', '060222654', 'Zeleznicka ulica 65','Ndzamena','Chad', 4, 1, 'ACTIVE', 42, 10, '14:00', '20:00');
INSERT INTO account_authority (account_id, authority_id) values  (19, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok8@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Teodora', 'Cvijic', '060222654', 'Zeleznicka ulica 65','Ndzamena','Chad', 3, 1, 'ACTIVE', 42, 10, '14:00', '20:00');
INSERT INTO account_authority (account_id, authority_id) values  (20, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok9@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Milos', 'Oleg', '060222654', 'Zeleznicka ulica 65','Ndzamena','Chad', 3, 1, 'ACTIVE', 42, 10, '14:00', '20:00');
INSERT INTO account_authority (account_id, authority_id) values  (21, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok10@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Zivojin', 'Misic', '060222654', 'Zeleznicka ulica 65','Ndzamena','Chad', 3, 1, 'ACTIVE', 45, 10, '14:00', '20:00');
INSERT INTO account_authority (account_id, authority_id) values  (22, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok11@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Petar', 'Petrovic', '060222654', 'Zeleznicka ulica 65','Ndzamena','Chad', 10, 3, 'ACTIVE', 43, 10, '14:00', '20:00');
INSERT INTO account_authority (account_id, authority_id) values  (23, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok12@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Jovan', 'Jovanovic', '060222654', 'Zeleznicka ulica 65','Ndzamena','Chad', 11, 4, 'ACTIVE', 42, 10, '14:00', '20:00');
INSERT INTO account_authority (account_id, authority_id) values  (24, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok13@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Jova', 'Prodanov', '060222654', 'Zeleznicka ulica 65','Ndzamena','Chad', 12, 4, 'ACTIVE', 48, 10, '07:55', '14:00');
INSERT INTO account_authority (account_id, authority_id) values  (25, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok14@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Gligorije', 'Prodanov', '060222654', 'Zeleznicka ulica 65','Ndzamena','Chad', 8, 2, 'ACTIVE', 48, 10, '07:55', '14:00');
INSERT INTO account_authority (account_id, authority_id) values  (26, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok15@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Novica', 'Novic', '060224654', 'Zeleznicka ulica 65','Ndzamena','Chad', 1, 1, 'ACTIVE', 48, 10, '07:55', '14:00');
INSERT INTO account_authority (account_id, authority_id) values  (27, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok16@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Aleksa', 'Novkovic', '060122654', 'Zeleznicka ulica 65','Ndzamena','Chad', 1, 1, 'ACTIVE', 48, 10, '07:55', '14:00');
INSERT INTO account_authority (account_id, authority_id) values  (28, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes, work_time_start, work_time_end)
values ('DOCTOR', 'dok17@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Stevan', 'Jovic', '060722654', 'Zeleznicka ulica 65','Ndzamena','Chad', 4, 1, 'ACTIVE', 48, 10, '07:55', '14:00');
INSERT INTO account_authority (account_id, authority_id) values  (29, 2);
------------------------------------------------------------------------------

--Diagnosis
insert into diagnosis (name, description)
values ('Streptococcus pneumoniae', 'Acute bacterial rhinosinusitis');
insert into diagnosis (name, description)
values ('Babesia microti', 'Babesiosis ');
insert into diagnosis (name, description)
values ('Candidiasis', 'Candida infection');
insert into diagnosis (name, description)
values ('Staphylococcus aureus', 'Dacryocytitis');
insert into diagnosis (name, description)
values ('Ebola ', 'Ebola virus (Filovirus)');
insert into diagnosis (name, description)
values ('Clostridium perfringens', 'Gas gangrene');

--Drug
insert into drug (name, ingredient, description)
values ('Tylenol', 'Acetaminophen', 'Acetaminophen is a pain reliever and a fever reducer.');
insert into drug (name, ingredient, description)
values ('Vivlodex', 'Meloxicam', 'Meloxicam is a nonsteroidal anti-inflammatory drug (NSAID)');
insert into drug (name, ingredient, description)
values ('Adoxa Pak', 'Doxycycline', 'Doxycycline is a tetracycline antibiotic that fights bacteria in the body.');
insert into drug (name, ingredient, description)
values ('Sterapred', 'Prednisone', 'Prednisone prevents the release of substances in the body that cause inflammation.');
insert into drug (name, ingredient, description)
values ('Ativan', 'Lorazepam', 'Lorazepam affects chemicals in the brain that may be unbalanced in people with anxiety.');

--Ordination

insert into ordination(type, number, clinic_id)
values('EXAMINATION', '100', '1');
insert into ordination(type, number, clinic_id)
values('EXAMINATION', '101', '1');
insert into ordination(type, number, clinic_id)
values('EXAMINATION', '102', '1');
insert into ordination(type, number, clinic_id)
values('OPERATION', '201', '1');
insert into ordination(type, number, clinic_id)
values('OPERATION', '202', '1');
insert into ordination(type, number, clinic_id)
values('EXAMINATION', '10', '2');
insert into ordination(type, number, clinic_id)
values('EXAMINATION', '11', '2');
insert into ordination(type, number, clinic_id)
values('EXAMINATION', '12', '2');
insert into ordination(type, number, clinic_id)
values('OPERATION', '20', '2');
insert into ordination(type, number, clinic_id)
values('OPERATION', '21', '2');


--Medical record
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'Eggs, gluten', '0', '230', 8, '30');
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'Cats', 'AB', '130', 9, '170');
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'Flowers', 'A', '170', 10, '100');
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'None', 'AB', '180', 12, '70');
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'Flower', 'B', '165', 13, '60');


--Appointments

--This operation is yet to be approved and it's ordination and doctors to be set
insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(2, '02.23.2020 11:00', '02.23.2020 11:30', 'APPROVED', '1', '9', 6, 1, 1, '10');
insert into appointed_doctors (appointment_id, doctor_id) values (1,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(4, '02.13.2020 10:45', '02.13.2020 11:30', 'APPROVED', 3, '9', 6, 1, 1, '10');
insert into appointed_doctors (appointment_id, doctor_id) values (2,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(1, '02.27.2020 10:00', '02.27.2020 13:30', 'PREDEF_BOOKED', '4', 8, 6, 2, 1, '5');
insert into appointed_doctors (appointment_id, doctor_id) values (3,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(1, '02.27.2020 15:00', '02.27.2020 16:00', 'CANCELED', '5', 8, 6, 2, 1, '5');
insert into appointed_doctors (appointment_id, doctor_id) values (4,5);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(2, '02.21.2020 9:00', '02.21.2020 10:30', 'APPROVED', '3', 9, 6, 1, 1);
insert into appointed_doctors (appointment_id, doctor_id) values (5,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(2, '01.27.2020 10:15', '01.27.2020 11:00', 'PREDEF_AVAILABLE', '4', null, 6, 2, 1, '5');
insert into appointed_doctors (appointment_id, doctor_id) values (6,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(2, '02.02.2020 09:30', '02.02.2020 10:15', 'PREDEF_AVAILABLE', '4', null, 6, 2, 1, '5');
insert into appointed_doctors (appointment_id, doctor_id) values (7,4);
insert into appointed_doctors (appointment_id, doctor_id) values (7,5);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(4, '02.23.2020 18:30', '02.23.2020 20:30', 'AWAITING_APPROVAL', '2', 8, 7, 3, 1);

insert into appointment(price_id, start_date_time, end_date_time, status, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(2, '02.24.2020 08:00', '02.24.2020 9:30', 'AWAITING_APPROVAL', 8, 7, 3, 1);

insert into appointment(price_id, start_date_time, end_date_time, status, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(1, '02.25.2020 08:00', '02.25.2020 9:30', 'AWAITING_APPROVAL', 8, 7, 3, 1);

insert into appointment(price_id, start_date_time, end_date_time, status, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(1, '04.25.2020 08:00', '04.25.2020 9:30', 'AWAITING_APPROVAL', 8, 7, 3, 1);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(2, '02.24.2020 9:00', '02.24.2020 11:30', 'APPROVED', '3', 8, 6, 1, 1);
insert into appointed_doctors (appointment_id, doctor_id) values (12,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(2, '03.22.2020 11:00', '03.22.2020 12:30', 'APPROVED', '3', 8, 6, 1, 1);
insert into appointed_doctors (appointment_id, doctor_id) values (13,16);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(2, '04.22.2020 1:00', '04.22.2020 12:30', 'PREDEF_BOOKED', '3', 8, 6, 1, 1);
insert into appointed_doctors (appointment_id, doctor_id) values (14,17);


--AppointmentRequests
insert into appointment_request(end_date_time, patient_id, start_date_time, type, clinic_id, doctor_id)
values ('02.25.2020 11:30', 8, '02.25.2020 11:00', 'EXAMINATION', 1, 4);

insert into appointment_request(end_date_time, patient_id, start_date_time, type, clinic_id, doctor_id)
values ('02.25.2020 11:40', 9, '02.25.2020 11:00', 'EXAMINATION', 1, 4);

insert into appointment_request(end_date_time, patient_id, start_date_time, type, clinic_id, doctor_id)
values ('02.25.2020 11:40', 10, '02.25.2020 10:50', 'EXAMINATION', 1, 4);

insert into appointment_request(end_date_time, patient_id, start_date_time, type, clinic_id, doctor_id)
values ('02.25.2020 11:20', 11, '02.25.2020 10:55', 'EXAMINATION', 1, 4);

--Examination Report
insert into examination_report (comment, time_created, appointment_id, diagnosis_id, doctor_id, medical_record_id)
values ('Lorem ipsum lupus hominus', '01.20.2020 19:15', 1, 1, 4, 1);

insert into examination_report (comment, time_created, appointment_id, diagnosis_id, doctor_id, medical_record_id)
values ('Hahus quius est locus', '01.19.2020 22:10', 2, 1, 5, 2);

--Prescription
insert into prescription(prescription_enum, drug_id, examination_report_id, nurse_id)
values (0, 1, 1, 6);

insert into prescription(prescription_enum, drug_id, examination_report_id, nurse_id)
values (0, 2, 1, 6);

--Appointments

/*insert into appointment(type, start_date_time, end_date_time, price, status, ordination_id, patient_id, nurse_id, medical_record_id, clinic_id, discount)
values('OPERATION', '12.29.2019 10:00', '12.29.2019 12:00', '1000', 'PREDEF_AVAILABLE', '1', '5', '1', '1', '1', '10');
insert into appointed_doctors (appointment_id,doctor_id) values (8,4);

insert into appointment(type, start_date_time, end_date_time, price, status, ordination_id, patient_id, nurse_id, medical_record_id, clinic_id, discount)
values('OPERATION', '12.28.2019 10:00', '12.28.2019 12:00', '900', 'AWAITING_APPROVAL', '1', '3', '1', '1', '1', '10');
insert into appointed_doctors (appointment_id,doctor_id) values (8,4);

insert into appointment(type, start_date_time, end_date_time, price, status, ordination_id, patient_id, nurse_id, medical_record_id, clinic_id, discount)
values('OPERATION', '12.27.2019 10:00', '12.27.2019 12:00', '800', 'CANCELED', '1', '2', '1', '1', '1', '10');
insert into appointed_doctors (appointment_id,doctor_id) values (8,4);

insert into appointment(type, start_date_time, end_date_time, price, status, ordination_id, patient_id, nurse_id, medical_record_id, clinic_id, discount)
values('OPERATION', '12.29.2019 10:00', '12.29.2019 12:00', '1000', 'PREDEF_AVAILABLE', '1', '5', '1', '1', '1', '10');
insert into appointed_doctors (appointment_id,doctor_id) values (9,5);*/

------------------------------------------------------------------------------

--AbsenceRequests

insert into paid_time_off_doctor(commentt, end_date_time, paid_time_off_status,paid_time_off_type, start_date_time,doctor_id)
values ('Komentar3', '03.11.2020', 'REQUESTED', 'ANNUAL_LEAVE', '03.05.2020','4');

insert into paid_time_off_doctor(commentt, end_date_time, paid_time_off_status,paid_time_off_type, start_date_time,doctor_id)
values ('Komentar4', '03.08.2020', 'REQUESTED', 'SICK_LEAVE', '03.03.2020','5');

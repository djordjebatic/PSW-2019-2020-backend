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
values ('Gastologija', 1);

insert into appointment_type (name, clinic_id)
values ('Ginekologija', 2);

insert into appointment_type (name, clinic_id)
values ('Opsta praksa', 2);
---AppointmentPrices

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (1, 'OPERATION', 3000);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (1, 'EXAMINATION', 30);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (2, 'OPERATION', 500);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (2, 'EXAMINATION', 10);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (3, 'OPERATION', 1000);
---------


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
values ('CLINIC_ADMIN', 'cadmin2@gmail.com', '$2y$12$4zrqOojpixOe/ogFw1xyyuQuIvFqrzbj0IohYtshqqy1P5rS6kdbq', 'Milos', 'Marković', '066123457', 'Zeleznicka ulica 69','Ndzamena','Chad', 'NEVER_LOGGED_IN', 1);
INSERT INTO account_authority (account_id, authority_id) values  (3, 4);


--Doctor
--Password: dok
insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes)
values ('DOCTOR', 'dok@gmail.com', '$2y$12$u3nc1wRBsop15oZaI2FqVuSHFsD9ZHeGXcpXKwGeD3on4zv3BRWd6', 'Dok', 'Dokic', '065525404', 'Zeleznicka ulica 69','Ndzamena','Chad', 1, 1, 'ACTIVE', 50, 10);
INSERT INTO account_authority (account_id, authority_id) values  (4, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, specialization_id, clinic_id, user_status, stars, num_votes)
values ('DOCTOR', 'dok2@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Sima', 'Dokic', '060123456', 'Zeleznicka ulica 69','Ndzamena','Chad', 2, 1, 'ACTIVE', 46, 10);
INSERT INTO account_authority (account_id, authority_id) values  (5, 2);

--Nurse
insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status)
values ('NURSE', 'sestra@gmail.com', '$2y$12$M6EnOZMRiIFt/znBY/C8r.2sglykYnJ0jj2Zm.mGpB3mdG9hD0BeW', 'Sima', 'Simic', '065256155', 'Zeleznicka ulica 69','Ndzamena','Chad', '1','ACTIVE');
INSERT INTO account_authority (account_id, authority_id) values  (6, 3);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status)
values ('NURSE', 'sestra2@gmail.com', '$2y$12$M6EnOZMRiIFt/znBY/C8r.2sglykYnJ0jj2Zm.mGpB3mdG9hD0BeW', 'Sestra', 'Simic', '065365157', 'Zeleznicka ulica 82','Ndzamena','Chad', '1','NEVER_LOGGED_IN');
INSERT INTO account_authority (account_id, authority_id) values  (7, 3);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status)
values ('NURSE', 'sestra3@gmail.com', '$2y$12$M6EnOZMRiIFt/znBY/C8r.2sglykYnJ0jj2Zm.mGpB3mdG9hD0BeW', 'Brat', 'Simic', '065365158', 'Zeleznicka ulica 182','Ndzamena','Chad', 2,'ACTIVE');
INSERT INTO account_authority (account_id, authority_id) values  (8, 3);

--Patient
insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Pata', 'Patic', '065256165', 'Zeleznicka ulica 69','Ndzamena','Chad','901204931212','APPROVED');
INSERT INTO account_authority (account_id, authority_id) values  (9, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent3@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Kongo', 'Kongic', '065259165', 'Heroja Pinkija 69','Kinsasa','DR Kongo','1235256262','APPROVED');
INSERT INTO account_authority (account_id, authority_id) values  (10, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent4@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Sudan', 'Sudic', '065256065', 'Jevrejska 69','Dzuba','Juzni Sudan','124532636362','AWAITING_APPROVAL');
INSERT INTO account_authority (account_id, authority_id) values  (11, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent5@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Dragoje', 'Radic', '065256565', 'Kisacka 69','Maseru','Lesoto','14324234243','AWAITING_APPROVAL');
INSERT INTO account_authority (account_id, authority_id) values  (12, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent6@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Milovan', 'Radovic', '0652565445', 'Bulevar Oslobodjenja 69','Maseru','Lesoto','14324234212','APPROVED');
INSERT INTO account_authority (account_id, authority_id) values  (13, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent7@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Kristina', 'Kekic', '0652565889', 'Bulevar Kralja Petra 69','Maseru','Lesoto','14324234223','APPROVED');
INSERT INTO account_authority (account_id, authority_id) values  (14, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent8@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Milica', 'Petrovic', '0652565229', 'Gogoljeva 69','Maseru','Lesoto','14324234256','APPROVED');
INSERT INTO account_authority (account_id, authority_id) values  (15, 1);

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


--Medical record
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'Eggs, gluten', '0', '230', 8, '30');
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'Cats', 'AB', '130', 9, '170');


--Appointments

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(1, PARSEDATETIME('01.23.2020 18:00', 'MM.dd.yyyy HH:mm'), PARSEDATETIME('01.23.2020 19:30', 'MM.dd.yyyy HH:mm'), 'APPROVED', 3, 10, 6, 1, 1, '10');
insert into appointed_doctors (appointment_id, doctor_id) values (1,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(1, PARSEDATETIME('01.19.2020 14:45', 'MM.dd.yyyy HH:mm'), PARSEDATETIME('01.19.2020 23:30', 'MM.dd.yyyy HH:mm'), 'APPROVED', 3, 10, 6, 1, 1, '10');
insert into appointed_doctors (appointment_id, doctor_id) values (2,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(2, PARSEDATETIME('01.27.2020 10:00', 'MM.dd.yyyy HH:mm'), PARSEDATETIME('01.27.2020 13:30','MM.dd.yyyy HH:mm'), 'PREDEF_BOOKED', 4, 11, 6, 2, 1, '5');
insert into appointed_doctors (appointment_id, doctor_id) values (3,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(2, PARSEDATETIME('01.27.2020 15:00', 'MM.dd.yyyy HH:mm'), PARSEDATETIME('01.27.2020 16:00', 'MM.dd.yyyy HH:mm'),'CANCELED', 4, 10, 6, 2, 1, '5');
insert into appointed_doctors (appointment_id, doctor_id) values (4,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(2, PARSEDATETIME('01.27.2020 17:00', 'MM.dd.yyyy HH:mm'), PARSEDATETIME('01.27.2020 18:00', 'MM.dd.yyyy HH:mm'),'APPROVED', 3, 12, 7, 2, 1, '5');
insert into appointed_doctors (appointment_id, doctor_id) values (5,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(2, PARSEDATETIME('01.21.2020 9:00', 'MM.dd.yyyy HH:mm'), PARSEDATETIME('01.21.2020 19:30', 'MM.dd.yyyy HH:mm'),'APPROVED', 1, 10, 6, 1, 1);
insert into appointed_doctors (appointment_id, doctor_id) values (6,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(1, PARSEDATETIME('01.23.2020 18:30', 'MM.dd.yyyy HH:mm'), PARSEDATETIME('01.23.2020 20:30', 'MM.dd.yyyy HH:mm'),'AWAITING_APPROVAL', 2, 10, 7, 3, 1);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(1, PARSEDATETIME('01.24.2020 08:00', 'MM.dd.yyyy HH:mm'), PARSEDATETIME('01.24.2020 9:30', 'MM.dd.yyyy HH:mm'),'AWAITING_APPROVAL', 2, 10, 7, 3, 1);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_id)
values(5, PARSEDATETIME('01.25.2020 08:00', 'MM.dd.yyyy HH:mm'), PARSEDATETIME('01.25.2020 9:30', 'MM.dd.yyyy HH:mm'),'AWAITING_APPROVAL', 6, 10, 8, 2);
--

--Examination Report
insert into examination_report (comment, time_created, appointment_id, diagnosis_id, doctor_id, medical_record_id)
values ('Lorem ipsum lupus hominus', PARSEDATETIME('01.20.2020 19:15', 'MM.dd.yyyy HH:mm'), 1, 1, 4, 1);

insert into examination_report (comment, time_created, appointment_id, diagnosis_id, doctor_id, medical_record_id)
values ('Hahus quius est locus', PARSEDATETIME('01.19.2020 22:10', 'MM.dd.yyyy HH:mm'), 2, 1, 5, 2);

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


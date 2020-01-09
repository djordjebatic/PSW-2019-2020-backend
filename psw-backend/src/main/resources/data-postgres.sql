--Authorization
INSERT INTO authority (name) values ('ROLE_PATIENT');
INSERT INTO authority (name) values ('ROLE_DOCTOR');
INSERT INTO authority (name) values ('ROLE_NURSE');
INSERT INTO authority (name) values ('ROLE_CLINIC_ADMIN');
INSERT INTO authority (name) values ('ROLE_CC_ADMIN');

--Clinic
insert into clinic (address, city, description, name, stars, num_votes)
values ('1300 Kaplara', 'Beograd', 'Ocna bolnica', 'Sveto oko', 4, 9);
insert into clinic (address, city, description, name, stars, num_votes)
values ('Trg slobode', 'Novi Sad', 'Klinicki centar Vojvodine (Novi Sad)', 'Klinicki centar Vojvodine', 5, 10);
insert into clinic (address, city, description, name, stars, num_votes)
values ('Bulevar umetnosti 29', 'Beograd', 'Bolnicki centar', 'Euromedic', 3, 10);
insert into clinic (address, city, description, name, stars, num_votes)
values ('Bulevar oslobodjenja 10', 'Novi Sad', 'Bolnicki centar', 'Consilium', 3, 10);
insert into clinic (address, city, description, name, stars, num_votes)
values ('Fruskogorska 16', 'Novi Sad', 'Specijalisticka bolnica opste hirurgije', 'Global care surgery', 5, 10);

--AppointmentTypes
----
insert into appointment_type (name, clinic_id)
values ('Kardiologija', 1);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (1, 'OPERATION', 3000);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (1, 'EXAMINATION', 30);
---------
insert into appointment_type (name, clinic_id)
values ('Gastologija', 1);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (2, 'OPERATION', 500);

insert into appointment_price (appointment_type_id, appointment_enum, price)
values (2, 'EXAMINATION', 10);
---------
insert into appointment_type (name, clinic_id)
values ('Ginekologija', 2);
insert into appointment_type (name, clinic_id)
values ('Opsta praksa', 2);


-----------------------------------USERS----------------------------------------

--Predefined Clinic Center Admin
insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, user_status)
values ('CC_ADMIN', 'admin@gmail.com', '$2y$12$4zrqOojpixOe/ogFw1xyyuQuIvFqrzbj0IohYtshqqy1P5rS6kdbq', 'Admin', 'Adminović', '0216362999', 'Zeleznicka ulica 69','Ndzamena','Chad', 'NEVER_LOGGED_IN');
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
values ('DOCTOR', 'dok2@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Sima', 'Dokic', '060123456', 'Zeleznicka ulica 69','Ndzamena','Chad', 2, 1, 'NEVER_LOGGED_IN', 46, 10);
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
values ('PATIENT', 'patijent3@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Kongo', 'Kongic', '065259165', 'Heroja Pinkija 69','Kinsasa','DR Kongo','1235256262','AWAITING_APPROVAL');
INSERT INTO account_authority (account_id, authority_id) values  (9, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent4@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Sudan', 'Sudic', '065256065', 'Jevrejska 69','Dzuba','Juzni Sudan','124532636362','AWAITING_APPROVAL');
INSERT INTO account_authority (account_id, authority_id) values  (10, 1);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent5@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Dragoje', 'Radic', '065256565', 'Kisacka 69','Maseru','Lesoto','14324234243','AWAITING_APPROVAL');
INSERT INTO account_authority (account_id, authority_id) values  (11, 1);

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
values('EXAMINATION', '1', '1');
insert into ordination(type, number, clinic_id)
values('EXAMINATION', '101', '1');
insert into ordination(type, number, clinic_id)
values('EXAMINATION', '102', '1');
insert into ordination(type, number, clinic_id)
values('OPERATION', '201', '1');
insert into ordination(type, number, clinic_id)
values('OPERATION', '202', '1');


--Medical record
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'Milk, flowers', 'A', '170', '6', '70');
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'Eggs, gluten', '0', '230', '8', '30');
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'Cats', 'AB', '130', '9', '170');
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'Pollen', 'B', '180', '2', '70');

--Appointments

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(1, '01.03.2020 20:00', '01.03.2020 22:00', 'APPROVED', '4', '9', 6, 1, 1, '10');
insert into appointed_doctors (appointment_id, doctor_id) values (1,4);


insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(1, '01.08.2020 20:00', '01.08.2020 22:00', 'APPROVED', '4', '9', 6, 1, 1, '10');
insert into appointed_doctors (appointment_id, doctor_id) values (2,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(1, '01.08.2020 22:00', '01.08.2020 23:59', 'PREDEF_BOOKED', '4', '9', 6, 1, 1, '10');
insert into appointed_doctors (appointment_id, doctor_id) values (3,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(1, '01.09.2020 12:00', '01.09.2020 20:00', 'APPROVED', '4', '9', 6, 1, 1, '10');
insert into appointed_doctors (appointment_id, doctor_id) values (4,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(1, '01.09.2020 20:01', '01.09.2020 23:59', 'PREDEF_BOOKED', '4', '9', 6, 1, 1, '10');
insert into appointed_doctors (appointment_id, doctor_id) values (5,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(2, '12.27.2019 10:00', '12.27.2019 13:30', 'PREDEF_BOOKED', '4', '8', 6, 2, 1, '5');
insert into appointed_doctors (appointment_id, doctor_id) values (6,4);

insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id, discount)
values(2, '12.27.2019 15:00', '12.27.2019 16:00', 'CANCELED', '4', '8', 6, 2, 1, '5');
insert into appointed_doctors (appointment_id, doctor_id) values (7,4);

--
insert into appointment(price_id, start_date_time, end_date_time, status, ordination_id, patient_id, nurse_id, clinic_admin_id, clinic_id)
values(4, '12.23.2019 08:00', '12.23.2019 9:30', 'PREDEF_AVAILABLE', '2', '4', 7, 3, 1);
insert into appointed_doctors (appointment_id, doctor_id) values (8,5);

--Examination Report
insert into examination_report (comment, time_created, appointment_id, diagnosis_id, doctor_id, medical_record_id)
values ('hahaha', '01.03.2020 11:30', 1, 1, 4, 3);

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
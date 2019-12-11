--Authorization
INSERT INTO authority (name) values ('ROLE_PATIENT');
INSERT INTO authority (name) values ('ROLE_DOCTOR');
INSERT INTO authority (name) values ('ROLE_NURSE');
INSERT INTO authority (name) values ('ROLE_CLINIC_ADMIN');
INSERT INTO authority (name) values ('ROLE_CC_ADMIN');

--Clinic
insert into clinic (address, city, description, name, stars, num_votes)
values ('1300 Kaplara', 'Beograd', 'Ocna bolnica', 'Sveto oko', 30, 9);
insert into clinic (address, city, description, name, stars, num_votes)
values ('Trg slobode', 'Novi Sad', 'Klinicki centar Vojvodine (Novi Sad)', 'Klinicki centar Vojvodine', 47, 10);

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
insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status, stars, num_votes)
values ('DOCTOR', 'dok@gmail.com', '$2y$12$u3nc1wRBsop15oZaI2FqVuSHFsD9ZHeGXcpXKwGeD3on4zv3BRWd6', 'Dok', 'Dokic', '065525404', 'Zeleznicka ulica 69','Ndzamena','Chad', 1, 'ACTIVE', 50, 10);
INSERT INTO account_authority (account_id, authority_id) values  (4, 2);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status, stars, num_votes)
values ('DOCTOR', 'dok2@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Sima', 'Dokic', '060123456', 'Zeleznicka ulica 69','Ndzamena','Chad', 1, 'NEVER_LOGGED_IN', 46, 10);
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
values ('PATIENT', 'patijent2@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Pera', 'Peric', '064123466', 'Veselina Maslese 3','Beograd','Srbija','1234567890123','AWAITING_APPROVAL');
INSERT INTO account_authority (account_id, authority_id) values  (9, 1);

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
values ('Tylenol', 'Acetaminophen', 'Acetaminophen is a pain reliever and a fever reducer');
insert into drug (name, ingredient, description)
values ('Vivlodex', 'Meloxicam', 'Meloxicam is a nonsteroidal anti-inflammatory drug (NSAID)');
insert into drug (name, ingredient, description)
values ('Adoxa Pak', 'Doxycycline', 'Doxycycline is a tetracycline antibiotic that fights bacteria in the body');
insert into drug (name, ingredient, description)
values ('Sterapred', 'Prednisone', 'Prednisone is a corticosteroid. It prevents the release of substances in the body that cause inflammation. It also suppresses the immune system');
insert into drug (name, ingredient, description)
values ('Ativan', 'Lorazepam', 'Lorazepam belongs to a group of drugs called benzodiazepines. It affects chemicals in the brain that may be unbalanced in people with anxiety');

--Ordination
insert into ordination(type, number, clinic_id)
values('EXAMINATION', '1', '1');

--Medical record
insert into medical_record(allergies, blood_type, height, patient_id, weight)
values( 'None', 'A', '170', '6', '70');

--Predefined appointment

insert into appointment(type, date, time, price, duration, status, ordination_id, doctor_id, patient_id, nurse_id, medical_record_id, clinic_admin_id, clinic_id, discount, doctor_rating, clinic_rating)
values('EXAMINATION', '12.04.2019.', '18.30', '1000', '1', 'PREDEF_AVAILABLE', '1', '3', '6', '5', '1', '2', '1', '1', '3', '4');



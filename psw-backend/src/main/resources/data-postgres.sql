insert into clinic (address, city, description, name, stars, num_votes)
values ('1300 Kaplara', 'Beograd', 'Ocna bolnica', 'Sveto oko', 30, 9);
insert into clinic (address, city, description, name, stars, num_votes)
values ('Trg slobode', 'Novi Sad', 'Klinicki centar Vojvodine (Novi Sad)', 'Klinicki centar Vojvodine', 47, 10);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, user_status)
values ('CC_ADMIN', 'admin@gmail.com', '$2y$12$4zrqOojpixOe/ogFw1xyyuQuIvFqrzbj0IohYtshqqy1P5rS6kdbq', 'Admin', 'Adminović', '0216362999', 'Zeleznicka ulica 69','Ndzamena','Chad', 'NEVER_LOGGED_IN');

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, user_status, clinic_id)
values ('CLINIC_ADMIN', 'cadmin1@gmail.com', '$2y$12$4zrqOojpixOe/ogFw1xyyuQuIvFqrzbj0IohYtshqqy1P5rS6kdbq', 'Marko', 'Marković', '066123456', 'Zeleznicka ulica 69','Ndzamena','Chad', 'ACTIVE', 1);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status, stars, num_votes)
values ('DOCTOR', 'dok@gmail.com', '$2y$12$u3nc1wRBsop15oZaI2FqVuSHFsD9ZHeGXcpXKwGeD3on4zv3BRWd6', 'Dok', 'Dokic', '065525404', 'Zeleznicka ulica 69','Ndzamena','Chad', 1, 'ACTIVE', 50, 10);
insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status, stars, num_votes)
values ('DOCTOR', 'dok2@gmail.com', '$2y$12$hjx/YFOIKdJhYhnoabYN5enQVYWQvrOU/h/NyT8jK4/af5LWDoKd2', 'Sima', 'Dokic', '060123456', 'Zeleznicka ulica 69','Ndzamena','Chad', 1, 'NEVER_LOGGED_IN', 46, 10);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status)
values ('NURSE', 'sestra@gmail.com', '$2y$12$M6EnOZMRiIFt/znBY/C8r.2sglykYnJ0jj2Zm.mGpB3mdG9hD0BeW', 'Sima', 'Simic', '065256155', 'Zeleznicka ulica 69','Ndzamena','Chad', '1','ACTIVE');

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Pata', 'Patic', '065256165', 'Zeleznicka ulica 69','Ndzamena','Chad','901204931212','APPROVED');
insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent2@gmail.com', '$2y$12$cfJ7eUBHua9B4mJhHXAx2eN6j/6sfGduRWRyMQy7E/Gci0xUXt8tK', 'Pera', 'Peric', '064123466', 'Veselina Maslese 3','Beograd','Srbija','1234567890123','AWAITING_APPROVAL');

INSERT INTO authority (id, name) values (1, 'PATIENT');
INSERT INTO authority (id, name) values (2, 'DOCTOR');
INSERT INTO authority (id, name) values (3, 'NURSE');
INSERT INTO authority (id, name) values (4, 'CLINIC_ADMIN');
INSERT INTO authority (id, name) values (5, 'CC_ADMIN');

INSERT INTO account_authority (account_id, authority_id) values  (1, 5);
INSERT INTO account_authority (account_id, authority_id) values  (2, 4);
INSERT INTO account_authority (account_id, authority_id) values  (3, 2);
INSERT INTO account_authority (account_id, authority_id) values  (4, 2);
INSERT INTO account_authority (account_id, authority_id) values  (5, 3);
INSERT INTO account_authority (account_id, authority_id) values  (6, 1);
INSERT INTO account_authority (account_id, authority_id) values  (7, 1);



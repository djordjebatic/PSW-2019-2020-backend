insert into clinic (address, city, description, name, stars, num_votes)
values ('1300 Kaplara', 'Beograd', 'Ocna bolnica', 'Sveto oko', 30, 9);
insert into clinic (address, city, description, name, stars, num_votes)
values ('Trg slobode', 'Novi Sad', 'Klinicki centar Vojvodine (Novi Sad)', 'Klinicki centar Vojvodine', 47, 10);

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, user_status)
values ('CC_ADMIN', 'admin@gmail.com', 'admin', 'Admin', 'Adminović', '0216362999', 'Zeleznicka ulica 69','Ndzamena','Chad', 'NEVER_LOGGED_IN');

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, user_status, clinic_id)
values ('CLINIC_ADMIN', 'cadmin1@gmail.com', 'admin', 'Marko', 'Marković', '066123456', 'Zeleznicka ulica 69','Ndzamena','Chad', 'ACTIVE', 1);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status, stars, num_votes)
values ('DOCTOR', 'dok@gmail.com', 'dok', 'Dok', 'Dokic', '065525404', 'Zeleznicka ulica 69','Ndzamena','Chad', 1, 'ACTIVE', 50, 10);
insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status, stars, num_votes)
values ('DOCTOR', 'dok2@gmail.com', 'dok2', 'Sima', 'Dokic', '060123456', 'Zeleznicka ulica 69','Ndzamena','Chad', 1, 'NEVER_LOGGED_IN', 46, 10);

insert into account(account_type, email, password, first_name, last_name, phone_number, address, city, country, clinic_id, user_status)
values ('NURSE', 'sestra@gmail.com', '280510556', 'Sima', 'Simic', '065256155', 'Zeleznicka ulica 69','Ndzamena','Chad', '1','ACTIVE');

insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent@gmail.com', 'patijent', 'Pata', 'Patic', '065256165', 'Zeleznicka ulica 69','Ndzamena','Chad','901204931212','APPROVED');
insert into account (account_type, email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('PATIENT', 'patijent2@gmail.com', 'patijent', 'Pera', 'Peric', '064123466', 'Veselina Maslese 3','Beograd','Srbija','1234567890123','AWAITING_APPROVAL');

INSERT INTO authority (id, name) values (1, 'PATIENT');
INSERT INTO authority (id, name) values (2, 'DOCTOR');
INSERT INTO authority (id, name) values (3, 'NURSE');
INSERT INTO authority (id, name) values (4, 'CLINIC_ADMIN');
INSERT INTO authority (id, name) values (5, 'CC_ADMIN');




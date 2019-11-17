insert into ccadmin (email, password, first_name, last_name, phone_number, status)
values ('admin@gmail.com', 'admin', 'Admin', 'Adminović', '0216362999', 'ACTIVE');

insert into clinic (address, city, description, name, stars, num_votes)
values ('1300 Kaplara', 'Beograd', 'Ocna bolnica', 'Sveto oko', 30, 9);
insert into clinic (address, city, description, name, stars, num_votes)
values ('Trg slobode', 'Novi Sad', 'Klinicki centar Vojvodine (Novi Sad)', 'Klinicki centar Vojvodine', 47, 10);

insert into clinic_admin (email, password, first_name, last_name, phone_number, status, clinic_id)
values ('cadmin1@gmail.com', 'admin', 'Marko', 'Marković', '066123456', 'ACTIVE', 1);
insert into clinic_admin (email, password, first_name, last_name, phone_number, status, clinic_id)
values ('cadmin2@gmail.com', 'admin', 'Mario', 'Tintoreto', '063341403', 'ACTIVE', 2);

/* TODO */
/*insert into appointment*/

/* TODO Add worktime for doctors and nurses in classes and specialization for admin ? */
insert into doctor (email, password, first_name, last_name, phone_number, clinic_id, status, stars, num_votes)
values ('dok@gmail.com', 'dok', 'Dok', 'Dokic', '065525404', 1, 'ACTIVE', 50, 10);
insert into doctor (email, password, first_name, last_name, phone_number, clinic_id, status, stars, num_votes)
values ('dok2@gmail.com', 'dok2', 'Sima', 'Dokic', '060123456', 1, 'DELETED', 46, 10);

insert into nurse (email, password, first_name, last_name, phone_number,clinic_id, status)
values ('sestra@gmail.com', '280510556', 'Sima', 'Simic', '065256155','1','ACTIVE');

insert into patient (email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('patijent@gmail.com', 'patijent', 'Pata', 'Patic', '065256155', 'Zeleznicka ulica 69','Ndzamena','Chad','901204931212','APPROVED');
insert into patient (email, password, first_name, last_name, phone_number, address, city, country, medical_number, status)
values ('patijent2@gmail.com', 'patijent', 'Pera', 'Peric', '064123456', 'Veselina Maslese 3','Beograd','Srbija','1234567890123','AWAITING_APPROVAL');


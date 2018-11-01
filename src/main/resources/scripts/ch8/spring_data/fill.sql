insert into contact (first_name, last_name, birth_date) values ('Chris', 'Schaefer', '1981-05-03');
insert into contact (first_name, last_name, birth_date) values ('Scott', 'Tiger', '1990-11-02');
insert into contact (first_name, last_name, birth_date) values ('John', 'Smith', '1964-02-28');

insert into contact_tel_detail (contact_id, tel_type, tel_number) values(1,  'Mobile', '1234567890');
insert into contact_tel_detail (contact_id, tel_type, tel_number) values (1,  'Home', '1234567890');
insert into contact_tel_detail(contact_id, tel_type, tel_number) values (2,  'Home', '1234567890');

INSERT INTO HOBBY(HOBBY_ID) VALUES ('Swimming');
INSERT INTO HOBBY(HOBBY_ID) VALUES ('Jogging');
INSERT INTO HOBBY(HOBBY_ID) VALUES ('Programming');
INSERT INTO HOBBY(HOBBY_ID) VALUES ('Movies');
INSERT INTO HOBBY(HOBBY_ID) VALUES ('Reading');

INSERT INTO CONTACT_HOBBY_DETAIL(CONTACT_ID, HOBBY_ID) VALUES (1, 'Swimming');
INSERT INTO CONTACT_HOBBY_DETAIL(CONTACT_ID, HOBBY_ID) VALUES (1, 'Movies');
INSERT INTO CONTACT_HOBBY_DETAIL(CONTACT_ID, HOBBY_ID) VALUES (2, 'Swimming');
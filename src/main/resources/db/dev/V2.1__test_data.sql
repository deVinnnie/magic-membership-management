INSERT INTO event(id, version, datum, event_type)
VALUES('10', '1', '2015-01-17', 'SUPERNOVA'),
      ('20', '1', '2015-01-10', 'MAIN_SEQUENCE');

INSERT INTO person(id, version, voornaam, achternaam, gender, city_id) VALUES
      ('10', '1', 'Filius', 'Flitwick', 'M', '140'),
      ('20', '1', 'Remus', 'Lupin', 'M', '140'),
      ('30', '1', 'Sirius', 'Black', 'M', '140'),
      ('40', '1', 'Severus', 'Snape', 'M', '140'),
      ('50', '1', 'Albus', 'Dumbledore', 'M', '140'),
      ('60', '1', 'Hermione', 'Granger', 'F', '140'),
      ('70', '1', 'Giny', 'Weasley', 'F', '140'),
      ('80', '1', 'Harry', 'Potter', 'M', '140'),
      ('90', '1', 'Ron', 'Weasley', 'M', '140');

INSERT INTO partaking(id, version, partaking_type, event_id, person_id) VALUES
      ('10' , '1', 'LEIDING', '10', '40'),
      ('20' , '1', 'DEELNEMER', '10', '60'),
      ('30' , '1', 'DEELNEMER', '10', '70'),
      ('40' , '1', 'DEELNEMER', '10', '80'),
      ('50' , '1', 'DEELNEMER', '10', '90');

--
--INSERT INTO membership(id, version, person_id, issue_date) VALUES
--      ('10' , '1', '10', '2015-01-01');
--INSERT INTO membership(id, version, person_id, issue_date) VALUES
--      ('20' , '1', '10', '2016-01-01');
--

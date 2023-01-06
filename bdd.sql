DROP TABLE Flammes;
DROP TABLE Fires;

DROP TABLE Capteur_Donnees;
DROP TABLE Capteurs ;
DROP TABLE Microcontrollers;
DROP TABLE Capteur_Types;
DROP TABLE Etats;


CREATE TABLE Flammes(
    id SERIAL,
    lat FLOAT NOT NULL,
    long FLOAT NOT NULL,
    intensite INT NOT NULL,
    distance FLOAT NOT NULL,
    id_fire BIGINT
);

CREATE TABLE Fires(
    id SERIAL
);

CREATE TABLE Etats(
    id SERIAL,
    valeur VARCHAR(255)
);

CREATE TABLE Microcontrollers(
    id SERIAL,
    latitude FLOAT,
    longitude FLOAT,
    id_etat BIGINT,
    nom VARCHAR(255)
);

CREATE TABLE Capteurs(
    id SERIAL,
    identifier VARCHAR(255),
    id_microcontroller INT,
    id_capteur_type INT
);

CREATE TABLE Capteur_Donnees(
    id SERIAL,
    identifier VARCHAR(255),
    valeur VARCHAR(255),
	id_capteur INT
);

CREATE TABLE Capteur_Types(
    id SERIAL,
    TYPE VARCHAR(255)
);



ALTER TABLE Flammes 
    ADD PRIMARY KEY (id),
    ADD FOREIGN KEY (id_fire) REFERENCES Flammes(id);

ALTER TABLE Fires 
    ADD PRIMARY KEY (id);



ALTER TABLE Capteur_Types
    ADD PRIMARY KEY (id);

ALTER TABLE Etats
    ADD PRIMARY KEY (id);
	
ALTER TABLE Microcontrollers
    ADD PRIMARY KEY (id),
	ADD FOREIGN KEY (id_etat) REFERENCES Etats(id);
	
ALTER TABLE Capteurs
    ADD PRIMARY KEY (id),
	ADD FOREIGN KEY (id_microcontroller) REFERENCES Microcontrollers(id),
	ADD FOREIGN KEY (id_capteur_type) REFERENCES Capteur_Types(id);
	
ALTER TABLE Capteur_Donnees
    ADD PRIMARY KEY (id),
	ADD FOREIGN KEY (id_capteur) REFERENCES Capteurs(id);
	
INSERT INTO Fires DEFAULT VALUES; 
INSERT INTO Flammes (lat,long,intensite,distance,id_fire) VALUES (2,3,9,2,1); 

INSERT INTO Etats (valeur) VALUES ('En fonctionnement');
INSERT INTO Etats (valeur) VALUES ('Problème partiel');
INSERT INTO Etats (valeur) VALUES ('Ne répond pas');

SELECT * FROM Etats;

INSERT INTO Capteur_Types (type) VALUES ('Incendie');

SELECT * FROM Capteur_Types ;

SELECT * FROM Microcontrollers;
SELECT * FROM Capteurs;

SELECT  Capteur_Donnees.id,Microcontrollers.latitude,Microcontrollers.longitude 
FROM Microcontrollers 
	JOIN Capteurs ON Capteurs.id_microcontroller = Microcontrollers.id
	JOIN  Capteur_Donnees ON  Capteur_Donnees.id = Capteurs.id;


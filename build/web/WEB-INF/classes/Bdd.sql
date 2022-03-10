CREATE DATABASE test;
CREATE ROLE user_test LOGIN PASSWORD 'root';
ALTER DATABASE test OWNER TO user_test;


CREATE TABLE utilisateur(
	username varchar(150),
	mdp varchar(250),
	fonction varchar(50)
);
INSERT INTO utilisateur values('Mino','123456789','enseignant');
INSERT INTO utilisateur values('Fenosoa','123456789','enseignant');



CREATE TABLE etudiant(
    id serial,
    matricule varchar(125),
    nom varchar(125),
    prenom varchar(125),
    dtn date,
    contact varchar(25),
    sexe varchar(10),
    etat boolean,
    primary key(id)
);
INSERT INTO etudiant(matricule,nom,prenom,dtn,contact,sexe,etat) VALUES('ETU1496','Rasamimanana','Diary','11-11-2003','0346362125','fille',true);
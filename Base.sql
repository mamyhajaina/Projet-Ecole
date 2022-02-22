-- Create a new database called 'Ecole'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT name
        FROM sys.databases
        WHERE name = N'Ecole'
)
CREATE DATABASE Ecole
GO

create table personne(
    id int primary key,
    nom varchar(50),
    prenom varchar(50),
    dateDeNaissance date,
    sexe varchar(10),
    adresse varchar(50),
    photo varchar (50)
);

create table classes(
    id integer primary key,
    nom integer
);

create table eleve(
    id_matriculation varchar(20) primary key,
    id_personne integer,
    id_classe int,
    statut varchar(50),
    cree_le date,
FOREIGN KEY(id_personne) REFERENCES personne(id),
FOREIGN KEY(id_classe) REFERENCES classes(id)

);

create table historique_classes(
    id int primary key,
    ideleve varchar(20),
    annedebut date,
    annefin date
);

create table paiement_ecolage(
    ideleve varchar(20),
    idclasse int,
    somme int,
    payer_le date

    FOREIGN KEY(ideleve) REFERENCES eleve(id),
    FOREIGN KEY(idclasse) REFERENCES classes(id)
);

create table historique_anne_ecolage(
   idclasse integer,
   ecolage integer,
   annee_debut date,
   annee_fin date,

   FOREIGN KEY(id_classe) REFERENCES classes(id)
);

create table admin(
 id int primary key,
 mail varchar(20),
 mtps varchar(20)
);


INSERT INTO Personne (id,nom,prenom,dateDeNaissance,sexe,adresse,photo) values (1,"RABESALAMPY", "Jonh",'18/10/2009',"Homme","Vr153Bis","JohnRabealampy.jpg")
INSERT INTO Personne (id,nom,prenom,dateDeNaissance,sexe,adresse,photo) values (2,"RAKOTOZANANY", "Mihary",'14/03/2007',"Homme","Vr104JK","MiharyRakotozanany.jpg")
INSERT INTO Personne (id,nom,prenom,dateDeNaissance,sexe,adresse,photo) values (1,"RABESA", "Anto",'16/11/2009',"Femme","Vr12KV","AntoRabesa.jpg")


INSERT INTO eleve (id_matriculation) values (1) 
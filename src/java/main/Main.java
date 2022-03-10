
package main;

import Ecole.Etudiant;
import Ecole.Utilisateur;
import fonctions.Fonctions;
import connexion.Connexion;

public class Main {
    public static void main(String[] args)throws Exception{
        Utilisateur usr = new Utilisateur();
        Etudiant e = new Etudiant();
        e.ajout("Seq", "cedric", "homme", "0331181491");
        e.modifier(11,"Miova", "cedi", "fille", "0331181491");
        e.renvoyer(2);
        usr.insereUtilisateur("koto","123456789","test");
        System.out.println("test");
    }
    
}

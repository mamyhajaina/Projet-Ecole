
package ecole;

import connexion.Connexion;
import fonctions.Fonctions;
import java.util.Vector;

public class Classe {
    private int id;
    private String nom;

    public Classe(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public Classe(){}

    public Classe[] findAll()throws Exception{
        Fonctions f =new Fonctions();
        Vector<Classe> vect = f.select(new Classe(), "classe", new Connexion("ecole", "eco", "le"));
        int taille = vect.size();
        Classe res[] = new Classe[taille];
        for(int i=0;i<taille;i++){
            res[i] = (Classe)vect.elementAt(i);
        }
        return res;
    }
    
}

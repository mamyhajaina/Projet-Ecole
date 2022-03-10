

package ecole;

import connexion.Connexion;
import fonctions.Fonctions;
import java.util.Vector;

public class Matiere {
    private int id;
    private String nom;
    private int coefficient;

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

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public Matiere(int id, String nom, int coefficient) {
        this.id = id;
        this.nom = nom;
        this.coefficient = coefficient;
    }
    public Matiere(){}

    public Matiere[] findAll()throws Exception{
        Fonctions f =new Fonctions();
        Vector<Matiere> vect = f.select(new Matiere(), "matiere", new Connexion("ecole", "eco", "le"));
        int taille = vect.size();
        Matiere res[] = new Matiere[taille];
        for(int i=0;i<taille;i++){
            res[i] = (Matiere)vect.elementAt(i);
        }
        return res;
    }
    
    
    
}

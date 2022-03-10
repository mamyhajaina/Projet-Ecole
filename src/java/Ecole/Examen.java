
package ecole;

import connexion.Connexion;
import fonctions.Fonctions;
import java.util.Vector;

public class Examen {
    private int id;
    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Examen(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public Examen(){}

    public Examen[] findAll()throws Exception{
        Fonctions f =new Fonctions();
        Vector<Examen> vect = f.select(new Examen(), "examen", new Connexion("ecole", "eco", "le"));
        int taille = vect.size();
        Examen res[] = new Examen[taille];
        for(int i=0;i<taille;i++){
            res[i] = (Examen)vect.elementAt(i);
        }
        return res;
    }
    
    public Examen findById(int id)throws Exception{
        Examen e = new Examen();
        Examen vect[] = findAll();
        int size = vect.length;
        for (int i = 0; i < size; i++) {
            if(vect[i].getId()==id){
                   e = vect[i];
            }
        }
        return e;
    }
    
}

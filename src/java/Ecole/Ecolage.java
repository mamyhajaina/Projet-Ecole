
package ecole;

import connexion.Connexion;
import fonctions.Fonctions;
import java.util.Vector;

public class Ecolage {
    private int id;
    private int idclasse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdclasse() {
        return idclasse;
    }

    public Ecolage(int id, int idclasse) {
        this.id = id;
        this.idclasse = idclasse;
    }

    public void setIdclasse(int idclasse) {
        this.idclasse = idclasse;
    }
    public Ecolage(){}
    public Ecolage[] findAll()throws Exception{
        Fonctions f =new Fonctions();
        Vector<Ecolage> vect = f.select(new Ecolage(), "ecolage", new Connexion("ecole", "eco", "le"));
        int taille = vect.size();
        Ecolage res[] = new Ecolage[taille];
        for(int i=0;i<taille;i++){
            res[i] = (Ecolage)vect.elementAt(i);
        }
        return res;
    }
    public int retId(int idClasse) throws Exception{
        Ecolage all[]= findAll();
        int size = all.length;
        int ret =0;
        for(int i=0;i<size;i++){
            if(all[i].getIdclasse()==idClasse){
                ret = ret + i;
            }
        }
    return ret;    
    }
    
}

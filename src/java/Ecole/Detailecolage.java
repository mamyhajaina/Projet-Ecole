
package ecole;

import connexion.Connexion;
import fonctions.Fonctions;
import java.util.Date;
import java.util.Vector;

public class Detailecolage {
    private int id;
    private int idecolage;

    public Detailecolage(int id, int idecolage, Date datelimite, int total, int ansneescolaire) {
        this.id = id;
        this.idecolage = idecolage;
        this.datelimite = datelimite;
        this.total = total;
        this.anneescolaire = anneescolaire;
    }
    private Date datelimite;
    private int total;
    private int anneescolaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdecolage() {
        return idecolage;
    }

    public void setIdecolage(int idecolage) {
        this.idecolage = idecolage;
    }

    public Date getDatelimite() {
        return datelimite;
    }

    public void setDatelimite(Date datelimite) {
        this.datelimite = datelimite;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAnneescolaire() {
        return anneescolaire;
    }

    public void setAnneescolaire(int anneescolaire) {
        this.anneescolaire = anneescolaire;
    }
    public Detailecolage(){}
    public Detailecolage[] findAll()throws Exception{
        Fonctions f =new Fonctions();
        Vector<Detailecolage> vect = f.select(new Detailecolage(), "detailecolage", new Connexion("ecole", "eco", "le"));
        int taille = vect.size();
        Detailecolage res[] = new Detailecolage[taille];
        for(int i=0;i<taille;i++){
            res[i] = (Detailecolage)vect.elementAt(i);
        }
        return res;
    }
    
    
    public int retId(int idecolage,int annee) throws Exception{
        Detailecolage dt[] = findAll();
        int res = 0;
        for(int i=0;i<dt.length;i++){
            if(dt[i].getIdecolage()==idecolage && dt[i].getAnneescolaire()==annee){
                res = i;
            }
        }
       return res;
    }
    
}

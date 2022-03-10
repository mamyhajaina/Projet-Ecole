
package ecole;

import connexion.Connexion;
import fonctions.Fonctions;
import java.util.Vector;

public class Utilisateur {
    private String username;
    private String mdp;
    private String fonction;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Utilisateur(String username, String mdp, String fonction) {
        this.username = username;
        this.mdp = mdp;
        this.fonction = fonction;
    }

    public Utilisateur() {
    }
    public Utilisateur[] findAll()throws Exception{
        Fonctions f =new Fonctions();
        Vector<Utilisateur> vect = f.select(new Utilisateur(), "utilisateur", new Connexion("ecole", "eco", "le"));
        int taille = vect.size();
        Utilisateur res[] = new Utilisateur[taille];
        for(int i=0;i<taille;i++){
            res[i] = (Utilisateur)vect.elementAt(i);
        }
        return res;
    }
    
    public void insereUtilisateur(String nom,String mdp,String fon) throws Exception{
        Fonctions f = new Fonctions();
        Utilisateur u = new Utilisateur(nom,mdp,fon);
        f.inserer(u, "utilisateur",new Connexion("ecole","eco", "le"));
    }
    
}

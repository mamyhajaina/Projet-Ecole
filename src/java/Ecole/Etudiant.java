
package ecole;

import connexion.Connexion;
import fonctions.Fonctions;
import java.util.Date;
import java.util.Vector;

public class Etudiant {
    private int id;
    private String matricule;
    private String nom;
    private String prenom;
    private Date dtn;
    private String contact;
    private String sexe;
    private boolean etat;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
       this.etat = etat;
    }
    public Etudiant(){}
    
    
    public Etudiant[] findAll()throws Exception{
        Fonctions f =new Fonctions();
        Vector<Etudiant> vect = f.select(new Etudiant(), "etudiant", new Connexion("ecole", "user_ecole", "root"));
        int taille = vect.size();
        Etudiant res[] = new Etudiant[taille];
        for(int i=0;i<taille;i++){
            res[i] = (Etudiant)vect.elementAt(i);
        }
        return res;
    }
    public Etudiant findBymatricule(String matricule)throws Exception{
        Etudiant e = new Etudiant();
        Etudiant vect[] = findAll();
        int size = vect.length;
        for (int i = 0; i < size; i++) {
            if(vect[i].getMatricule().equalsIgnoreCase(matricule)){
                   e = vect[i];
            }
        }
        return e;
    }
    public Etudiant findById(int idet)throws Exception{
        Etudiant e = new Etudiant();
        Etudiant vect[] = findAll();
        int size = vect.length;
        for (int i = 0; i < size; i++) {
            if(vect[i].getId()==idet){
                   e = vect[i];
            }
        }
        return e;
    }
    
    public int retId(String matricule) throws Exception{
        Etudiant e = findBymatricule(matricule);
        return e.getId();
    }
    
    public Etudiant( String nom,String prenom,String sexe, String contact) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.contact = contact;
    }
    public Etudiant(int id, String nom,String prenom,String sexe, String contact) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.contact = contact;
    }

    public void  ajout(String nom,String prenom,String sexe,String contact) throws Exception{
        Etudiant e = new Etudiant(nom,prenom,sexe,contact);
        Fonctions f = new Fonctions();
        f.insererSeq(e,"etudiant","matricule",new Connexion("ecole","eco","le"));
       // System.out.println("Vita");
    } 
    
    public void modifier(int id,String nom,String prenom,String sexe,String contact) throws Exception{
        Etudiant e = new Etudiant(id,nom,prenom,sexe,contact);
        Fonctions f = new Fonctions();
        f.update(e, "etudiant",new Connexion("ecole","eco","le"));
    }
    
    public Etudiant(int id){
        this.id = id;
    }
    
    public void renvoyer(int idEtudiant) throws Exception{
        Etudiant e = new Etudiant(idEtudiant);
        Fonctions f = new Fonctions();
        f.delete(e, "etudiant", new Connexion("ecole","eco","le"));
    }
    
    public int retId(String matricule,String nom,String prenom) throws Exception{
        int e = 0;
        Etudiant vect[] = findAll();
        int size = vect.length;
        for (int i = 0; i < size; i++) {
            if(vect[i].getMatricule().equalsIgnoreCase(matricule) && vect[i].getNom().equalsIgnoreCase(nom) && vect[i].getPrenom().equalsIgnoreCase(prenom)){
                   e = vect[i].getId();
            }
        }
        return e;        
    }
}

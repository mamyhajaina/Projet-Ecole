/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecole;

import connexion.Connexion;
import fonctions.Fonctions;
import java.util.Vector;

public class Detailclasse {
    private int id;
    private int idclasse;
    private int idetudiant;
    private int numero;
    private int anneescolaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Detailclasse(int id, int idclasse, int idetudiant, int numero, int anneescolaire) {
        this.id = id;
        this.idclasse = idclasse;
        this.idetudiant = idetudiant;
        this.numero = numero;
        this.anneescolaire = anneescolaire;
    }

    public int getIdclasse() {
        return idclasse;
    }

    public void setIdclasse(int idclasse) {
        this.idclasse = idclasse;
    }

    public int getIdetudiant() {
        return idetudiant;
    }

    public void setIdetudiant(int idetudiant) {
        this.idetudiant = idetudiant;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAnneescolaire() {
        return anneescolaire;
    }

    public void setAnneescolaire(int anneescolaire) {
        this.anneescolaire = anneescolaire;
    }
    
    public Detailclasse(String matricule,int numero,int annee,int idClasse) throws Exception{
        Etudiant e = new Etudiant();
        this.idclasse = idclasse;
        this.idetudiant = e.retId(matricule);
        this.numero = numero;
        this.anneescolaire = annee;    
    }
    
    public void inscription(String matricule,int numero,int annee,int idClasse) throws Exception{
        Detailclasse dl = new Detailclasse(matricule,numero,annee,idclasse);
        Fonctions f = new Fonctions();
        f.inserer(dl,"detailclasse",new Connexion("ecole","eco","le"));
    }
    
    public void modifier(int id,String matricule,int numero,int annee,int idClasse) throws Exception{
        Detailclasse dl = new Detailclasse(matricule,numero,annee,idclasse);
        Fonctions f = new Fonctions();
        f.update(dl, "detailclasse",new Connexion("ecole","eco","le"));
    }
    
    public Detailclasse(){}
    
    public Detailclasse[] findAll()throws Exception{
        Fonctions f =new Fonctions();
        Vector<Detailclasse> vect = f.select(new Detailclasse(), "etudiant", new Connexion("ecole", "eco", "le"));
        int taille = vect.size();
        Detailclasse res[] = new Detailclasse[taille];
        for(int i=0;i<taille;i++){
            res[i] = (Detailclasse)vect.elementAt(i);
        }
        return res;
    }
    
    public Etudiant findEleve(String matricule,int annee) throws Exception{
        boolean misy = false;
        Etudiant e = new Etudiant();
        Etudiant res = new Etudiant();
        int idet = e.retId(matricule);
        Detailclasse det[] = findAll();
        int sizedet = det.length;
        for(int i=0;i<sizedet;i++){
            if(det[i].getIdetudiant()==idet && det[i].getAnneescolaire()==annee){
                misy = true;
            }
        }
        if(misy){
            res = e.findBymatricule(matricule);
        }else{
            throw new Exception("Tsy misy");
        }
        return res;        
    }
    
    
    public void deletEleve(int idclasse,int idetudiant) throws Exception{
        boolean misy = false;
        Etudiant e = new Etudiant();
        Detailclasse det[] = findAll();
        int sizedet = det.length;
        for(int i=0;i<sizedet;i++){
            if(det[i].getIdclasse()==idclasse && det[i].getIdetudiant()==idetudiant){
                misy = true;
            }
        }
        if(misy){
            e.renvoyer(idetudiant);
        }else{
            throw new Exception("Tsy misy");
        }
    }

    public Etudiant[] findEtudiantparAnneAndClasse(int idclasse,int anneescolaire) throws Exception{
        Detailclasse det[] = findAll();
        Etudiant e = new Etudiant();
        int sizedet = det.length;
        Vector<Etudiant> permanant = new Vector();    
        int idPer = 0;
        for(int i=0;i<sizedet;i++){
            if(det[i].getIdclasse()==idclasse && det[i].getAnneescolaire()==anneescolaire){
                permanant.add(e.findById(det[i].getIdetudiant()));
                idPer +=1;
            }
        }
        Etudiant res[] = new Etudiant[idPer];
        for(int i=0;i<idPer;i++){
            res[i] = permanant.elementAt(i);
        }
        return res;
    }
    
    public int retId(int idclasse) throws Exception{
        int res=0;
        Detailclasse det[] = findAll();
        for(int i=0;i<det.length;i++){
            if(det[i].getIdclasse()==idclasse){
                res=i;
            }
        }
        return res;
    }

    public int retIdForNote(int ideleve,int annee) throws Exception{
        Detailclasse dt[] = findAll();
        int res = 0;
        for(int i=0;i<dt.length;i++){
            if(dt[i].getIdetudiant()==ideleve && dt[i].getAnneescolaire()==annee){
                res =  i;
            }
        }
       return res;
    }

}

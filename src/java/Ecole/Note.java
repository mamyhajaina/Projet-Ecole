
package ecole;

import connexion.Connexion;
import fonctions.Fonctions;
import java.util.Vector;

public class Note {
    private int id;
    private int iddetailclasse;
    private int idmatiere;
    private int idexamen;
    private float  note;
    private boolean validation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIddetailclasse() {
        return iddetailclasse;
    }

    public void setIddetailclasse(int iddetailclasse) {
        this.iddetailclasse = iddetailclasse;
    }

    public int getIdmatiere() {
        return idmatiere;
    }

    public void setIdmatiere(int idmatiere) {
        this.idmatiere = idmatiere;
    }

    public int getIdexamen() {
        return idexamen;
    }

    public void setIdexamen(int idexamen) {
        this.idexamen = idexamen;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }
    
    public Note(int id, int iddetailclasse, int idmatiere, int idexamen, float note, boolean validation) {
        this.id = id;
        this.iddetailclasse = iddetailclasse;
        this.idmatiere = idmatiere;
        this.idexamen = idexamen;
        this.note = note;
        this.validation = validation;
    }
    public Note(){}

    public Note[] findAll()throws Exception{
        Fonctions f =new Fonctions();
        Vector<Note> vect = f.select(new Note(), "note", new Connexion("ecole", "eco", "le"));
        int taille = vect.size();
        Note res[] = new Note[taille];
        for(int i=0;i<taille;i++){
            res[i] = (Note)vect.elementAt(i);
        }
        return res;
    }
    
    public float moyenneClasse(int idclasse) throws Exception{
        Note all[] = findAll();
        float res = 0;
        Detailclasse del= new Detailclasse();
        Vector<Note> noteClasse = new Vector();
        for(int i=0;i<all.length;i++){
            if(all[i].getIddetailclasse() == del.retId(idclasse)){
                noteClasse.add(all[i]);
            }
        }
        float sum = 0;
        for(int i=0;i<noteClasse.size();i++){
            sum = sum +noteClasse.elementAt(i).getNote();
        }
        res = sum/noteClasse.size();
        return res;
    }
    
    public float noteMatiereEleve(int idEleve, int idMatiere,int anneescolaire) throws Exception{
        float res=0;
        Detailclasse det = new Detailclasse();
        int idDetail =  det.retIdForNote(idEleve,anneescolaire);
        Note all[] = findAll();
        
        for(int i=0;i<all.length;i++){
            if(all[i].getIddetailclasse()==idDetail && all[i].getIdmatiere()==idMatiere){
                res = all[i].getNote();
            }
        }
        return res;
    }

    public Note(int iddetailclasse, int idmatiere, int idexamen, float note) {
        this.iddetailclasse = iddetailclasse;
        this.idmatiere = idmatiere;
        this.idexamen = idexamen;
        this.note = note;
    }
    
    public void  insertionNote(String matricule,String nom,String prenom,int anneescolaire, int idExamen, int idMatiere,float note) throws Exception{
        Etudiant e = new Etudiant();
        int ideleve = e.retId(matricule,nom,prenom);
        Detailclasse det = new Detailclasse();
        int idDetail =  det.retIdForNote(ideleve,anneescolaire);
        Note n = new Note(idDetail,idMatiere,idExamen,note);
        Fonctions f = new Fonctions();
        f.inserer(n,"note",new Connexion("ecole","eco","le"));
            
    }
    
    
    
}

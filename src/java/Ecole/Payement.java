
import connexion.Connexion;
import fonctions.Fonctions;
import java.util.Vector;

public class Payement {
    private int id;
    private int iddetailecolage;
    private int idetudiant;
    private boolean paye;

    public Payement(int id, int iddetailecolage, int idetudiant, boolean paye) {
        this.id = id;
        this.iddetailecolage = iddetailecolage;
        this.idetudiant = idetudiant;
        this.paye = paye;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIddetailecolage() {
        return iddetailecolage;
    }

    public void setIddetailecolage(int iddetailecolage) {
        this.iddetailecolage = iddetailecolage;
    }

    public int getIdetudiant() {
        return idetudiant;
    }

    public void setIdetudiant(int idetudiant) {
        this.idetudiant = idetudiant;
    }

    public boolean isPaye() {
        return paye;
    }

    public void setPaye(boolean paye) {
        this.paye = paye;
    }
    public Payement(int iddetailecolage,int idetudiant){
        this.iddetailecolage =iddetailecolage;
        this.idetudiant =idetudiant;

    }
    public void ajout(String matricule,int idclasse,int annee) throws Exception{
        Ecolage ec = new Ecolage();
        Etudiant et = new Etudiant();
        Detailecolage dtec = new Detailecolage();
        int idet = et.retId(matricule);
        int ideco = ec.retId(idclasse);
        int iddet = dtec.retId(ideco,annee);
        Payement p = new Payement(idet,iddet);
        Fonctions f = new Fonctions();
        f.inserer(p,"payement",new Connexion("ecole","eco","le"));
    }
    public Payement(){}
    public Payement[] findAll()throws Exception{
        Fonctions f =new Fonctions();
        Vector<Payement> vect = f.select(new Payement(), "payement", new Connexion("ecole", "eco", "le"));
        int taille = vect.size();
        Payement res[] = new Payement[taille];
        for(int i=0;i<taille;i++){
            res[i] = (Payement)vect.elementAt(i);
        }
        return res;
    }

    public boolean isExist(String matricule,int idclasse,int annee) throws Exception{
        boolean res=false;
        Ecolage ec = new Ecolage();
        Etudiant et = new Etudiant();
        Detailecolage dtec = new Detailecolage();
        int idet = et.retId(matricule);
        int ideco = ec.retId(idclasse);
        int iddet = dtec.retId(ideco,annee);
        Payement p = new Payement(idet,iddet);
        Fonctions f = new Fonctions();
        Payement ps[] = findAll();
        for(int i=0;i<ps.length;i++){
            if(ps[i].getIdetudiant()==idet && ps[i].getIddetailecolage()==iddet){
                res = true;
            }
        }
        return res;
    }
    public int getIdPayement(String matricule,int idclasse,int annee) throws Exception{
        int res=0;
        Ecolage ec = new Ecolage();
        Etudiant et = new Etudiant();
        Detailecolage dtec = new Detailecolage();
        int idet = et.retId(matricule);
        int ideco = ec.retId(idclasse);
        int iddet = dtec.retId(ideco,annee);
        Payement p = new Payement(idet,iddet);
        Fonctions f = new Fonctions();
        Payement ps[] = findAll();
        for(int i=0;i<ps.length;i++){
            if(ps[i].getIdetudiant()==idet && ps[i].getIddetailecolage()==iddet){
                res = i;
            }
        }
        return res;
    }
    

}

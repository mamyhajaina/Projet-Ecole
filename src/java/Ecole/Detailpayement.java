
package ecole;

import connexion.Connexion;
import fonctions.Fonctions;
import java.util.Date;

public class Detailpayement {
    private int id;
    private int idpayement;
    private Date datepayement;
    private double montant;

    public int getId() {
        return id;
    }

    public Detailpayement(int id, int idpayement, Date datepayement, int montant) {
        this.id = id;
        this.idpayement = idpayement;
        this.datepayement = datepayement;
        this.montant = montant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdpayement() {
        return idpayement;
    }

    public void setIdpayement(int idpayement) {
        this.idpayement = idpayement;
    }

    public Date getDatepayement() {
        return datepayement;
    }

    public void setDatepayement(Date datepayement) {
        this.datepayement = datepayement;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Detailpayement(int idpayement,double montant){
        this.idpayement = idpayement;
        this.montant = montant;
    }
    public void ajout(int idpayement,double montant) throws Exception{
        Detailpayement dt = new Detailpayement(idpayement,montant);
        Fonctions f = new Fonctions();
        f.inserer(dt, "detailpayement",new Connexion("ecole","eco","le"));
    }

}


package Produits.Model;

public class Fournisseurs {
    private int id;
    private String nomFournisseur;
    private int idFournisseur;
    private String emailFournisseur;

    public Fournisseurs(){super();}
    public Fournisseurs(int id, String nomFournisseur,int idFournisseur, String emailFournisseur)
    {
        super();
        this.id=id;
        this.nomFournisseur=nomFournisseur;
        this.idFournisseur=idFournisseur;
        this.emailFournisseur=emailFournisseur;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getEmailFournisseur() {
        return emailFournisseur;
    }

    public void setEmailFournisseur(String emailFournisseur) {
        this.emailFournisseur = emailFournisseur;
    }
}

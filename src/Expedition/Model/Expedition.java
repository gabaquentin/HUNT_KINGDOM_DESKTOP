package Expedition.Model;

public class Expedition {

    private int id;
    private String nom;
    private String statut;
    private String dateDebut;
    private String dateFin;
    private String date;
    private String message;
    private String lieux;
    private String type;
    private String utilisateur;

    public Expedition(int id, String nom, String statut, String dateDebut, String dateFin, String date, String message, String lieux, String type, String utilisateur) {
        this.id = id;
        this.nom = nom;
        this.statut = statut;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.date = date;
        this.message = message;
        this.lieux = lieux;
        this.type = type;
        this.utilisateur = utilisateur;
    }

    public Expedition(String nom, String statut, String dateDebut, String dateFin, String date, String message, String lieux, String type) {
        this.nom = nom;
        this.statut = statut;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.date = date;
        this.message = message;
        this.lieux = lieux;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Expedition{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", statut='" + statut + '\'' +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                ", date='" + date + '\'' +
                ", message='" + message + '\'' +
                ", lieux='" + lieux + '\'' +
                ", type='" + type + '\'' +
                ", utilisateur='" + utilisateur + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }
}

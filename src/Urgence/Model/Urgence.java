package Urgence.Model;

import Expedition.Model.Expedition;

public class Urgence {

    private int id;
    private int expedition;
    private String Utilisateur;
    private Double latitude;
    private Double longitude;
    private String addresse;
    private String place_id;
    private String description;
    private String plus;
    private String date;
    private String etat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getExpedition() {
        return expedition;
    }

    public void setExpedition(int expedition) {
        this.expedition = expedition;
    }

    public String getUtilisateur() {
        return Utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        Utilisateur = utilisateur;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Urgence(String utilisateur, Double latitude, Double longitude, String addresse, String place_id, String description, String plus, String date, String etat) {
        Utilisateur = utilisateur;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addresse = addresse;
        this.place_id = place_id;
        this.description = description;
        this.plus = plus;
        this.date = date;
        this.etat = etat;
    }

    public Urgence(int id, int expedition, String utilisateur, Double latitude, Double longitude, String addresse, String place_id, String description, String plus, String date, String etat) {
        this.id = id;
        this.expedition = expedition;
        Utilisateur = utilisateur;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addresse = addresse;
        this.place_id = place_id;
        this.description = description;
        this.plus = plus;
        this.date = date;
        this.etat = etat;
    }

    public Urgence(int expedition, String utilisateur, Double latitude, Double longitude, String addresse, String place_id, String description,String plus, String date, String etat) {
        this.expedition = expedition;
        Utilisateur = utilisateur;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addresse = addresse;
        this.place_id = place_id;
        this.description = description;
        this.plus = plus;
        this.date = date;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Urgence{" +
                "id=" + id +
                ", expedition=" + expedition +
                ", Utilisateur='" + Utilisateur + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", addresse='" + addresse + '\'' +
                ", place_id='" + place_id + '\'' +
                ", description='" + description + '\'' +
                ", plus='" + plus + '\'' +
                ", date='" + date + '\'' +
                ", etat='" + etat + '\'' +
                '}';
    }
}

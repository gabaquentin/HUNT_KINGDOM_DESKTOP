package User.Model;

import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String email;
    private ArrayList<String> roles;
    private String nom;
    private String prenom;
    private String telephone;
    private String num_auth;
    private String penalite;
    private String adresse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNum_auth() {
        return num_auth;
    }

    public void setNum_auth(String num_auth) {
        this.num_auth = num_auth;
    }

    public String getPenalite() {
        return penalite;
    }

    public void setPenalite(String penalite) {
        this.penalite = penalite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


}

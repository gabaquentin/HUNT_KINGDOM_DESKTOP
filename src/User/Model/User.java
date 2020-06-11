package User.Model;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class User {
    @NotNull
    private int id;
    private String username;
    private String email;
    private String roles;
    private String nom;
    private String prenom;
    private String telephone;
    private String num_auth;
    private String penalite;
    private String adresse;
    private String password;

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public User(String utilisateur, String password) {
        this.username = utilisateur;
        this.password = password;
    }

    public User(String nom, String prenom, String email, String telephone, String addresse, String num_auth, String utilisateur, String password, String roles, String penalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = addresse;
        this.num_auth = num_auth;
        this.username = utilisateur;
        this.password = password;
        this.roles = roles;
        this.penalite = penalite;
    }

}

package Model;

public class Produits {
    private int id;
    private String nomProduit;
    private int quantite;
    private String categorie;
    private int prix;
    private String image;
    private int fournisseur;
    private String description;

    public Produits(){
        super();
    }
    public Produits(int id,String nomProduit, int quantite,String categorie,int prix,String image,int fournisseur,String description)
    {
        super();
        this.id=id;
        this.nomProduit=nomProduit;
        this.quantite=quantite;
        this.categorie=categorie;
        this.prix=prix;
        this.image=image;
        this.fournisseur=fournisseur;
        this.description=description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(int fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

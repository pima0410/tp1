package com.sdzee.beans;

public class Client {

    private Long   id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse( String adresse ) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone( String telephone ) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return " Client d'id:" + id + " nom:" + nom + " prenom:" + prenom + " adresse:" + adresse + " telephone:"
                + telephone + " email:" + email;
    }
}

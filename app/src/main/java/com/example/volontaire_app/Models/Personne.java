package com.example.emploiandroid.Models;

import java.io.Serializable;
import java.util.Date;

public class Personne  implements Serializable {

    private  int id;
    private String nom,prenom,adresse, numTelephone,email,roles,password,token,status;

    public Personne(){};

    public Personne(int id, String nom, String prenom, String adresse, String numTelephone, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTelephone = numTelephone;
        this.email = email;
    }

    public Personne(int id, String nom, String prenom, String adresse, String numTelephone, String email, String roles, String password, String token) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTelephone = numTelephone;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.token = token;
    }

    public Personne(int id, String nom, String email,String roles, String token) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.email = roles;
        this.token = token;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }



    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





}

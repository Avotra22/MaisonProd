/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import dao.AccessBase;
import dao.Attribute;
import dao.TableName;

/**
 *
 * @author P14A-Serge
 */
@TableName(table = "Realisateur", view = "9")
public class Realisateur extends AccessBase{
    @Attribute(attrName = "id", attrType = "", idPrimaryKey = "")
    Integer id;
    @Attribute(attrName = "nom", attrType = "", idPrimaryKey = "")
    String nom;
    @Attribute(attrName = "prenom", attrType = "", idPrimaryKey = "")
    String prenom;
    @Attribute(attrName = "email", attrType = "", idPrimaryKey = "")
    String email;
    @Attribute(attrName = "passe", attrType = "", idPrimaryKey = "")
    String passe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasse() {
        return passe;
    }

    public void setPasse(String passe) {
        this.passe = passe;
    }
    
    ////functions 
    
}

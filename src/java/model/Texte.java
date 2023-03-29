/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AccessBase;
import dao.Attribute;
import dao.TableName;
import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
/**
 *
 * @author P14A-Serge
 */
@TableName(table = "Texte", view = "9")
public class Texte extends AccessBase{
    @Attribute(attrName = "id", attrType = "", idPrimaryKey = "yes")
    Integer id;
    @Attribute(attrName = "duree", attrType = "", idPrimaryKey = "")
    Time duree;
    @Attribute(attrName = "texte", attrType = "", idPrimaryKey = "")
    String texte;
    @Attribute(attrName = "idScene", attrType = "", idPrimaryKey = "")
    Integer idScene;
    @Attribute(attrName = "idHumeur", attrType = "", idPrimaryKey = "")
    Integer idHumeur;
    @Attribute(attrName = "idActeur", attrType = "", idPrimaryKey = "")
    Integer idActeur;
    private Humeur humeur;
    private Acteur acteur;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdScene() {
        return idScene;
    }

    public void setIdScene(Integer idScene) {
        this.idScene = idScene;
    }

    public Integer getIdHumeur() {
        return idHumeur;
    }

    public void setIdHumeur(Integer idHumeur) {
        this.idHumeur = idHumeur;
    }

    public Integer getIdActeur() {
        return idActeur;
    }

    public void setIdActeur(Integer idActeur) {
        this.idActeur = idActeur;
    }
    
    
    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }
    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Humeur getHumeur() {
        return humeur;
    }

    public void setHumeur(Humeur humeur) {
        this.humeur = humeur;
    }

    public Acteur getActeur() {
        return acteur;
    }

    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }
    
    //find texteµ
    @Override
    public ArrayList<Texte> find(Connection con) throws Exception{
        ArrayList<Texte> rep=super.find(con);
        Humeur hum=new Humeur();
        Acteur act=new Acteur();
        for(Texte t:rep){
            hum.setId(t.getIdHumeur());
            act.setId(t.getIdActeur());
            t.setActeur((Acteur) act.find(con).get(0));
            t.setHumeur((Humeur)hum.find(con).get(0));
        }
       return rep;  
    } 
    
}

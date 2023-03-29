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
@TableName(table = "FilmActeur", view = "9")
public class FilmActeur extends AccessBase{
    @Attribute(attrName = "idFilm", attrType = "", idPrimaryKey = "")
    Integer idFilm;
    @Attribute(attrName = "idActeur", attrType = "", idPrimaryKey = "")
    Integer idActeur;
    private ArrayList<Acteur> acteur;
    
    public Integer getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Integer idFilm) {
        this.idFilm = idFilm;
    }

    public Integer getIdActeur() {
        return idActeur;
    }

    public void setIdActeur(Integer idActeur) {
        this.idActeur = idActeur;
    }    

    public ArrayList<Acteur> getActeur() {
        return acteur;
    }

    public void setActeur(ArrayList<Acteur> acteur) {
        this.acteur = acteur;
    }
    
    //insertion acteur
    public ArrayList<FilmActeur> find(Connection con) throws Exception{
        ArrayList<FilmActeur> rep=super.find(con);
        Acteur acteur=new Acteur();
        for(FilmActeur act:rep){
            acteur.setId(act.getIdActeur());
            act.setActeur(acteur.find(con));
        }
        return rep;
    }
}

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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


/**
 *
 * @author P14A-Serge
 */
@TableName(table = "Acteur", view = "9") 
public class Acteur extends AccessBase{
   @Attribute(attrName = "id", attrType = "", idPrimaryKey = "yes")
    Integer id;
    @Attribute(attrName = "nom", attrType = "", idPrimaryKey = "")
    String nom;
   @Attribute(attrName = "naissance", attrType = "", idPrimaryKey = "")
    Date naissance;
    @Attribute(attrName = "description", attrType = "", idPrimaryKey = "")
    String description;
  
    ArrayList<IndisponibiliteActeur> indispoAct;

    public ArrayList<IndisponibiliteActeur> getIndispoAct() {
        return indispoAct;
    }

    public void setIndispoAct(ArrayList<IndisponibiliteActeur> indispoAct) {
        this.indispoAct = indispoAct;
    }
    
    
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

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //find Perso
    public ArrayList<Acteur> find(Connection con) throws Exception{
        ArrayList<Acteur> rep=super.find(con);
        Humeur hum=new Humeur();
        IndisponibiliteActeur act=new IndisponibiliteActeur();
        for(Acteur t:rep){
            act.setIdActeur(t.getId());
            t.setIndispoAct(act.find(con));
        }
       return rep;  
    } 
}

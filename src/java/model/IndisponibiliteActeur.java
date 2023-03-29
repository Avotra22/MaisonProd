/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AccessBase;
import dao.Attribute;
import dao.TableName;
import java.sql.Timestamp;
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
@TableName(table = "IndisponibiliteActeur", view = "9")
public class IndisponibiliteActeur extends AccessBase{
    @Attribute(attrName = "id", attrType = "", idPrimaryKey = "yes")
    Integer id;
    @Attribute(attrName = "debut", attrType = "", idPrimaryKey = "")
    Timestamp debut;
    @Attribute(attrName = "fin", attrType = "", idPrimaryKey = "")
    Timestamp fin;
    @Attribute(attrName = "idActeur", attrType = "", idPrimaryKey = "")
    Integer idActeur;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }
    public void setDebut(String debut) {
        this.setDebut(Timestamp.valueOf(debut));
    }
    public void setFin(String debut) {
        this.setFin(Timestamp.valueOf(debut));
    }
    
    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }
    
    public Integer getIdActeur() {
        return idActeur;
    }

    public void setIdActeur(Integer idActeur) {
        this.idActeur = idActeur;
    }
    
    
}

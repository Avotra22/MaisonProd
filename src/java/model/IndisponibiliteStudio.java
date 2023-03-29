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


/**
 *
 * @author P14A-Serge
 */
@TableName(table = "IndisponibiliteStudio", view = "9")
public class IndisponibiliteStudio extends AccessBase{
    @Attribute(attrName = "id", attrType = "", idPrimaryKey = "id")
    Integer id;
    @Attribute(attrName = "debut", attrType = "", idPrimaryKey = "")
    Timestamp debut;
    @Attribute(attrName = "fin", attrType = "", idPrimaryKey = "")
    Timestamp fin;
    @Attribute(attrName = "idStudio", attrType = "", idPrimaryKey = "")
    Integer idStudio;
    
    
    public IndisponibiliteStudio(Timestamp debut, Timestamp fin) {
       this.setDebut(debut);
        this.setFin(fin);
    }
    public IndisponibiliteStudio(){
    }
    

    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

    public Integer getId() {
        return id;
    }
    public void setDebut(String debut) {
        this.setDebut(Timestamp.valueOf(debut));
    }
    public void setFin(String debut) {
        this.setFin(Timestamp.valueOf(debut));
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(Integer idStudio) {
        this.idStudio = idStudio;
    }
    
    
}

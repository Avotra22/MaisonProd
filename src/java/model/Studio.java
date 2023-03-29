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

/**
 *
 * @author P14A-Serge
 */
@TableName(table = "Studio", view = "9")
public class Studio extends AccessBase{
    @Attribute(attrName = "id", attrType = "", idPrimaryKey = "")
    Integer id;
    @Attribute(attrName = "nom", attrType = "", idPrimaryKey = "")
    String nom;
    @Attribute(attrName = "x", attrType = "", idPrimaryKey = "")
    Double x;
    @Attribute(attrName = "y", attrType = "", idPrimaryKey = "")
    Double y;
    ArrayList<IndisponibiliteStudio> indispoStudio;

    public ArrayList<IndisponibiliteStudio> getIndispoStudio() {
        return indispoStudio;
    }

    public void setIndispoStudio(ArrayList<IndisponibiliteStudio> indispoStudio) {
        this.indispoStudio = indispoStudio;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer idStudio) {
        this.id = idStudio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
    
    public ArrayList<Studio> find(Connection con) throws Exception{
        ArrayList<Studio> rep=super.find(con);
        IndisponibiliteStudio act=new IndisponibiliteStudio();
        for(Studio t:rep){
            act.setIdStudio(t.getId());
            t.setIndispoStudio(act.find(con));
        }
       return rep;  
    }
}

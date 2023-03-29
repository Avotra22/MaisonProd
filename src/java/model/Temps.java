/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AccessBase;
import dao.Attribute;
import dao.TableName;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


/**
 *
 * @author P14A-Serge
 */
@TableName(table = "Temps", view = "9")
public class Temps extends AccessBase{
    @Attribute(attrName = "id", attrType = "", idPrimaryKey = "yes")
    Integer id;
    @Attribute(attrName = "nom", attrType = "", idPrimaryKey = "")
    String nom;
    @Attribute(attrName = "debut", attrType = "", idPrimaryKey = "")
    Time debut;
    @Attribute(attrName = "fin", attrType = "", idPrimaryKey = "")
    Time fin;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer idTemps) {
        this.id = idTemps;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Time getDebut() {
        return debut;
    }

    public void setDebut(Time debut) {
        this.debut = debut;
    }

    public Time getFin() {
        return fin;
    }

    public void setFin(Time fin) {
        this.fin = fin;
    }
    //is inside intervalle;
    public boolean isInsideIntervalle(Timestamp debInterv,Timestamp finIntervstamp){
       Time debut=Temps.getTime(debInterv);
       Time fin=Temps.getTime(finIntervstamp);
       
        return isInsideIntervalle(debut,fin);
    }
    public boolean isInsideIntervalle(Time debInterv,Time finInterv){
       Time debut=this.getDebut();
       Time fin=this.getFin();
       
        return Temps.isInsideIntervalle(debut, fin,debInterv)&&Temps.isInsideIntervalle(debut, fin,finInterv);
    }
    public static boolean isInsideIntervalle(Time a,Time b,Time comparant){
     return  a.before(comparant)&&b.after(comparant);
    }
    public static Time getTime(Timestamp t){
     return new Time(t.getMonth(),t.getMinutes(),t.getSeconds());
    }
    public static void main(String[]dd){
        Time debut=new Time(10,00,00);
        Time fin=new Time(12,00,01);
        Temps tps=new Temps();
        tps.setDebut(debut);
        tps.setFin(fin);
        Time debut1=new Time(10,30,00);
        Time fin1=new Time(12,00,00);
        
        boolean b=tps.isInsideIntervalle(debut1, fin1);
        System.out.println(b);
    }
}
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
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 *
 * @author P14A-Serge
 */
@TableName(table = "Scene", view = "9")
public class Scene extends AccessBase{
    @Attribute(attrName = "id", attrType = "", idPrimaryKey = "")
    Integer id;
    @Attribute(attrName = "debutTournage", attrType = "", idPrimaryKey = "")
    Timestamp debutTournage;
    @Attribute(attrName = "idFilm", attrType = "", idPrimaryKey = "")
    Integer idFilm;
    @Attribute(attrName = "idLieu", attrType = "", idPrimaryKey = "")
    Integer idLieu;
    @Attribute(attrName = "idTemps", attrType = "", idPrimaryKey = "")
    Integer IdTemps;
    @Attribute(attrName = "idStudio", attrType = "", idPrimaryKey = "")
    Integer idStudio;
    
    Lieu  lieu;
    
    Temps temps;
    
    Studio studio;
    
    ArrayList<Texte> texte;

    public Integer getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(Integer idStudio) {
        this.idStudio = idStudio;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer idScene) {
        this.id = idScene;
    }

    public Integer getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Integer idFilm) {
        this.idFilm = idFilm;
    }

    public Integer getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(Integer idScene) {
        this.idLieu = idScene;
    }

    public Integer getIdTemps() {
        return IdTemps;
    }

    public void setIdTemps(Integer IdTemps) {
        this.IdTemps = IdTemps;
    }
   
    

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public Temps getTemps() {
        return temps;
    }

    public void setTemps(Temps temps) {
        this.temps = temps;
    }
    
    public Timestamp getDebutTournage() {
        return debutTournage;
    }

    public void setDebutTournage(Timestamp debutTournage) {
        this.debutTournage = debutTournage;
    }
    public void setDebutTournage(String debut) {
        this.setDebutTournage(Timestamp.valueOf(debut));
    }
    
    public static Scene[] getSceneDuJour(Timestamp debut,Timestamp fin){
        return null;
    }

    public ArrayList<Texte> getTexte() {
        return texte;
    }

    public void setTexte(ArrayList<Texte> texte) {
        this.texte = texte;
    }
    public void InsertionIndisponibilite(){
        
    }
    
   //fonction bdd
    public ArrayList<Scene> find(Connection con) throws Exception
    {
        Temps t=new Temps();
        ArrayList<Scene> rep =super.find(con);
        Lieu lieu=new Lieu();
        Texte txt=new Texte();
        Studio studio=new Studio();
        for(Scene re:rep){
            t.setId(re.getIdTemps());
            lieu.setId(re.getIdLieu());
            txt.setIdScene(re.getId());
            studio.setId(re.getIdStudio());
            re.setLieu((Lieu) lieu.find(con).get(0));
            re.setTemps((Temps) t.find(con).get(0));
            re.setStudio((Studio) studio.find(con).get(0));
            re.setTexte(txt.find(con));
        }
        return rep;
    }
    public static ArrayList<Scene> filtrerLocalement(ArrayList<Scene>  scene,String[] checked){
        ArrayList<Scene> rep=new ArrayList<Scene>();
        for(Scene sc:scene){
            for(String check:checked){
                if(sc.getId()==Integer.parseInt(check)){
                    rep.add(sc);
                }
            }
        }
        return rep;
    }
    
}

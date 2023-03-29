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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author P14A-Serge
 */
@TableName(table = "Film", view = "9")
public class Film extends AccessBase{
    @Attribute(attrName = "id", attrType = "", idPrimaryKey = "yes")
    Integer id;
    @Attribute(attrName = "titre", attrType = "", idPrimaryKey = "")
    String titre;
    @Attribute(attrName = "publication", attrType = "", idPrimaryKey = "")
    Date publication;
    @Attribute(attrName = "description", attrType = "", idPrimaryKey = "")
    String description;
    @Attribute(attrName = "idRealisateur", attrType = "", idPrimaryKey = "")
    private Integer idRealisateur;
    
    private Realisateur realisateur;
    
    private FilmActeur filmActeur;
    
    private ArrayList<Scene> scenes;

    public ArrayList<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(ArrayList<Scene> scenes) {
        this.scenes = scenes;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getIdRealisateur() {
        return idRealisateur;
    }

    public void setIdRealisateur(Integer idRealisateur) {
        this.idRealisateur = idRealisateur;
    }
    
    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Realisateur getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Realisateur realisateur) {
        this.realisateur = realisateur;
    }

    public FilmActeur getFilmActeur() {
        return filmActeur;
    }

    public void setFilmActeur(FilmActeur filmActeur) {
        this.filmActeur = filmActeur;
    }
     
   ////private
    @Override
    public ArrayList<Film> find(Connection con) throws Exception{
        ArrayList<Film> rep= super.find(con);
        Realisateur real=new Realisateur();
        FilmActeur fa=new FilmActeur();
        Scene scene=new Scene();
        for(Film film:rep){
            real.setId(film.getIdRealisateur());
            scene.setIdFilm(film.getId());
            fa.setIdFilm(film.getId());
            film.setRealisateur((Realisateur) real.find(con).get(0));
            film.setFilmActeur(fa.find(con).get(0));
            film.setScenes(scene.find(con));
        }
        return rep;
    }
    
}

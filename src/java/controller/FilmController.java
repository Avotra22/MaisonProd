/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ConnectionBase;
import dao.HibernateDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import model.*;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author P14A-Serge
 */
@Controller
public class FilmController {

    @Autowired
    HibernateDao dao;

    @RequestMapping(value = "/ajouterFilm")
    public ModelAndView ajouterFilm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("NewFilm");
        return mv;
    }

    @RequestMapping(value = "/ajouterFilmActeur")
    public ModelAndView ajouterFilmActeur() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("AjoutFilmActeur");
        return mv;
    }

    @RequestMapping(value = "/listerFilm")
    public ModelAndView listerFilm(HttpSession session) throws Exception {

        Connection con=null;
        try{
            con=ConnectionBase.getCon();
            RealisateurController realCont = new RealisateurController();
            Integer idRealisateur = realCont.verificationConnection(session);
            Film f = new Film();
            f.setId(idRealisateur);
            List<Film> film = f.find(con);
            ModelAndView mv = new ModelAndView();
            mv.addObject("allFilm", film);
            mv.setViewName("Accueil");
            return mv;
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(con!=null)con.close();
        }
    }

    @RequestMapping(value = "/ficheFilm")
    public ModelAndView ficheFilm(@RequestParam Integer idFilm,HttpSession session) throws Exception {
        Connection con=null;
        try {
            con=ConnectionBase.getCon();
            Film film =new Film();
            film.setId(idFilm);
            film=film.find(con).get(0);
            ModelAndView mv = new ModelAndView();
            mv.addObject("film", film);
            mv.setViewName("FicheFilm");
            session.setAttribute("idFilm",idFilm);
            return mv;
        } catch (Exception e) {
            throw e;
        } finally {
            if(con!=null)con.close();
        }

    }

    //////////Controller//////////
    @RequestMapping(value = "/ajouterFilmController")
    public String ajouterFilmController(@RequestParam String titre,@RequestParam String description,HttpSession session) throws Exception {
        RealisateurController realCont = new RealisateurController();
        Integer idRealisateur = realCont.verificationConnection(session);
        Film film = new Film();
        
        film.setTitre(titre);
        film.setIdRealisateur(idRealisateur);
        film.setDescription(description);
        film.setPublication(new Date(new java.util.Date().getTime()));
        
        film.insertBase();
        ModelAndView mv = new ModelAndView();
        return  "redirect:listerFilm";

    }

    @RequestMapping(value = "/ajouterFilmActeurController")
    public String ajouterFilmActeurController(@ModelAttribute Integer film, @ModelAttribute Integer acteur) throws Exception {
        FilmActeur filmActeur = new FilmActeur();
        filmActeur.setIdActeur(acteur);
        filmActeur.setIdFilm(film);
        filmActeur.insertBase();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Accueil");
        return  "redirect:listerFilm";
    }
}

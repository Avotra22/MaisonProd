/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ConnectionBase;
import dao.HibernateDao;
import java.sql.Connection;
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
public class ActeurController {

    @Autowired
    HibernateDao dao;

    @RequestMapping(value = "/ajouterActeur")
    public ModelAndView ajouterActeur() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("NewActeur");
        return mv;
    }

    @RequestMapping(value = "/listerActeur")
    public ModelAndView listerActeur() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("");
        return mv;
    }

    @RequestMapping(value = "/ficheActeur")
    public ModelAndView ficheActeur(@RequestParam Integer id) throws Exception {

        Acteur allAct = new Acteur();
        allAct.setId(id);
        Connection con = null;

        try{ 
            con = ConnectionBase.getCon();
            allAct = (Acteur) allAct.find(con).get(0);
            ModelAndView mv = new ModelAndView();
            mv.addObject("acteur", allAct);
            mv.setViewName("FicheActeur");
            return mv;
        } catch (Exception e) {
            throw e;
        } finally {
            if(con!=null)con.close();
        }
        
    }
    //////////acteur controlleur//////////

    @RequestMapping(value = "/ajouterActeurController")
    public ModelAndView ajouterActeurController(@ModelAttribute Acteur acteur) throws Exception {
        acteur.insertBase();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Accueil");
        return mv;
    }

}

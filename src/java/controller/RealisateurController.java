/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.HibernateDao;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class RealisateurController {
    @Autowired
    HibernateDao dao;
    
    public Integer verificationConnection(HttpSession session) throws Exception{
    if(session.getAttribute("realisateur")==null)throw new Exception("connectez vous d'abord");
    else return (Integer)session.getAttribute("realisateur");
    }
    @RequestMapping(value="/login")
    public ModelAndView login(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("Login");
        return mv;
    }
     @RequestMapping(value="/loginController")
    public String loginController(@RequestParam String email,@RequestParam String passe,HttpServletRequest request) throws IOException, Exception{
       Realisateur realisateur=new Realisateur();
       realisateur.setEmail(email);
       realisateur.setPasse(passe);
       List<Realisateur> connected=realisateur.find();
       if(connected.size()>0){
           HttpSession session=request.getSession();
           Realisateur r=connected.get(0);
           session.setAttribute("realisateur",r.getId());
          return  "redirect:listerFilm";
       }
       else{
            return "redirect:login";
       }
       
    }
    @RequestMapping(value="/inscription")
    public ModelAndView inscription(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("Inscription");
        return mv;
    }
    
    
     @RequestMapping(value="/ajouterRealisateur")
    public ModelAndView ajouterRealisateur(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("");
        return mv;
    }
    @RequestMapping(value="/inscriptionController")
    public ModelAndView ajouterActeurController(@ModelAttribute Realisateur acteur) throws Exception{
        acteur.insertBase();
        ModelAndView mv=new ModelAndView();
        mv.setViewName("Accueil");
        return mv;
    }
    
}
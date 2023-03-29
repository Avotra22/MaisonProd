/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.HibernateDao;
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
/**
 *
 * @author P14A-Serge
 */
@Controller
public class TexteController {
    @Autowired
    HibernateDao dao;
    
     @RequestMapping(value="/ajouterTexte")
    public ModelAndView ajouterTexte(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("");
        return mv;
    }
   
    @RequestMapping(value="/listerTexte")
    public ModelAndView listerTexte(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("");
        return mv;
    }
    
    @RequestMapping(value="/ficheTexte")
    public ModelAndView ficheTexte(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("");
        return mv;
    }
}
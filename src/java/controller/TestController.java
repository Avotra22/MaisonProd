 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.HibernateDao;
import java.sql.Date;
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
public class TestController {
    @Autowired
    HibernateDao dao;
    
     @RequestMapping(value="/Test")
    public ModelAndView ajouterTest() throws Exception{
        Integer idRealisateur=2;
        Film f=new Film();
        Realisateur real=new Realisateur();
        real.setId(idRealisateur);
       f.setTitre("test Film test");
       f.setDescription("test Description test");
       //f.setRealisateur(real);
       f.setPublication(new Date(new java.util.Date().getTime()));
       dao.create(f);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("Login");
        return mv;
    }
   
    @RequestMapping(value="/listerTest")
    public ModelAndView listerTest(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("");
        return mv;
    }
    
    @RequestMapping(value="/ficheTest")
    public ModelAndView ficheTest(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("");
        return mv;
    }
    
    
}
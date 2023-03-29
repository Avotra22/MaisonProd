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
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 *
 * @author P14A-Serge
 */
@Controller
public class SceneController {
    @Autowired
    HibernateDao dao;
    public Integer getIdFilm(HttpSession session) throws Exception{
    if(session.getAttribute("idFilm")==null)throw new Exception("connectez vous d'abord");
    else return (Integer)session.getAttribute("idFilm");
    }
     @RequestMapping(value="/ajouterScene")
    public ModelAndView ajouterScene() throws Exception{
        ModelAndView mv=new ModelAndView();
        mv.addObject("lieu",new Lieu().find());
         mv.addObject("temps",new Temps().find());
        mv.setViewName("NewScene");
        return mv;
    }
   
    @RequestMapping(value="/listerScene")
    public ModelAndView listerScene(HttpSession session) throws Exception{
        ModelAndView mv=new ModelAndView();
        Integer idFilm=this.getIdFilm(session);
        Scene scene=new Scene();
        Connection con=null;
        try{
            con=ConnectionBase.getCon();
            scene.setIdFilm(idFilm);
            mv.addObject("scene",scene.find(con));
        }
        catch(Exception e){
         throw e;
        }
        finally{
            if(con!=null)con.close();
        }
        mv.setViewName("Scene");
        return mv;
    }
    
    @RequestMapping(value="/ficheScene")
    public ModelAndView ficheScene(HttpSession session,@ModelAttribute Integer idScene) throws Exception{
        Integer idFilm=this.getIdFilm(session);
        Scene scene=new Scene();
        ModelAndView mv=new ModelAndView();
        Connection con=null;
        try{
            con=ConnectionBase.getCon();
            scene.setId(idScene);
            mv.addObject("scene",scene.find(con));
            mv.setViewName("Scene");
            return mv;
        }
        catch(Exception e){
         throw e;
        }
        finally{
            if(con!=null)con.close();
        }
        
    }
    //////////controller//////////
    @RequestMapping(value="/ajouterSceneController")
    public String ajouterSceneController(@ModelAttribute Integer idFilm,@ModelAttribute Integer idTemps,@ModelAttribute Integer idLieu) throws Exception{
        Scene scene=new Scene();
        scene.setIdFilm(idFilm);
        scene.setIdLieu(idLieu);
        scene.setIdTemps(idTemps);
        scene.insertBase();
        return  "redirect:listerScene";
    }
    ///planification scene////
    @RequestMapping(value="/planificationScene")
    public ModelAndView planificationScen(HttpSession session) throws Exception{
        ModelAndView mv=new ModelAndView();
        Integer idFilm=this.getIdFilm(session);
        Scene scene=new Scene();
        Connection con=null;
        try{
            con=ConnectionBase.getCon();
            scene.setIdFilm(idFilm);
            mv.addObject("scene",scene.find(con));
        }
        catch(Exception e){
         throw e;
        }
        finally{
            if(con!=null)con.close();
        }
        mv.setViewName("planificationscene");
        return mv;
    }
    ///ajoutsceneController/////
    @RequestMapping(value="/planificationController")
    public ModelAndView planifierController(HttpServletRequest request,HttpSession session,@RequestParam String debut,@RequestParam String fin) throws Exception{
        String[] ids=request.getParameterValues("id");
        Integer idFilm=this.getIdFilm(session);
        Scene scene=new Scene();
        Connection con=null;
        ModelAndView mv=new ModelAndView();
        try{
            con=ConnectionBase.getCon();
            scene.setIdFilm(idFilm);
            ArrayList<Scene> scenes=scene.find(con);
            scenes=Scene.filtrerLocalement(scenes,ids);
            scenes=Planifications.planifierTournage(scenes,Date.valueOf(debut),Date.valueOf(fin));
            session.setAttribute("planification",scenes);
            mv.setViewName("confirmationplanification");
            mv.addObject("scene",scenes);
            return mv;
        }
        catch(Exception e){
         throw e;
        }
        finally{
            if(con!=null)con.close();
        }
        
        
    }
    @RequestMapping(value="/confirmationplanificationController")
    public String planifierController(HttpServletRequest request,HttpSession session) throws Exception{
        String[] ids=request.getParameterValues("id");
        ArrayList<Scene> scenes=(ArrayList<Scene>) session.getAttribute("planification");
        scenes=Scene.filtrerLocalement(scenes,ids);
        for(Scene sc:scenes){
            sc.updateBase();
        }
        return  "redirect:listerScene";
    }
}
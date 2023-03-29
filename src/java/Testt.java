
import dao.ConnectionBase;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import model.Acteur;
import model.IndisponibiliteActeur;
import model.IndisponibiliteStudio;
import model.Scene;
import model.Studio;
import model.Texte;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author P14A-Serge
 */
public class Testt {

    public static Boolean acteursIsDisponibleMatin(ArrayList<Texte> texte, Date date) {
        for (int i = 0; i < texte.size(); i++) {
            Acteur act = texte.get(i).getActeur();
            ArrayList<IndisponibiliteActeur> indisp = act.getIndispoAct();
            for (int j = 0; j < indisp.size(); j++) {
                if (indisp.get(j).getDebut().compareTo(new Timestamp(date.getYear(), date.getMonth(), date.getDate(), 9, 0, 0, 0)) <= 0) {
                    if (indisp.get(j).getFin().compareTo(new Timestamp(date.getYear(), date.getMonth(), date.getDate(), 9, 0, 0, 0)) > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static Boolean acteursIsDisponibleMidi(ArrayList<Texte> texte, Date date) {
        for (int i = 0; i < texte.size(); i++) {
            Acteur act = texte.get(i).getActeur();
            ArrayList<IndisponibiliteActeur> indisp = act.getIndispoAct();
            for (int j = 0; j < indisp.size(); j++) {
                if (indisp.get(j).getDebut().compareTo(new Timestamp(date.getYear(), date.getMonth(), date.getDate(), 15, 0, 0, 0)) <= 0) {
                    if (indisp.get(j).getFin().compareTo(new Timestamp(date.getYear(), date.getMonth(), date.getDate(), 15, 0, 0, 0)) > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static Boolean jourFerierOuWeekEnd(Date date) {
        return false;
    }

    public static Boolean studioIsDisponibleMatin(Studio s, Date date) {
        ArrayList<IndisponibiliteStudio> indisp = s.getIndispoStudio();
        for (int j = 0; j < indisp.size(); j++) {
            if (indisp.get(j).getDebut().compareTo(new Timestamp(date.getYear(), date.getMonth(), date.getDate(), 9, 0, 0, 0)) <= 0) {
                if (indisp.get(j).getFin().compareTo(new Timestamp(date.getYear(), date.getMonth(), date.getDate(), 9, 0, 0, 0)) > 0) {
                    return false;
                }
            }

        }
        return true;
    }

    public static Boolean studioIsDisponibleMidi(Studio s, Date date) {
        ArrayList<IndisponibiliteStudio> indisp = s.getIndispoStudio();
        for (int j = 0; j < indisp.size(); j++) {
            if (indisp.get(j).getDebut().compareTo(new Timestamp(date.getYear(), date.getMonth(), date.getDate(), 9, 0, 0, 0)) <= 0) {
                if (indisp.get(j).getFin().compareTo(new Timestamp(date.getYear(), date.getMonth(), date.getDate(), 15, 0, 0, 0)) > 0) {
                    return false;
                }
            }
        }
        return true;

    }

    public ArrayList<Scene> planifierTournage(ArrayList<Scene> scenes, Date debutTournage, Date finTournage) {
        System.out.println(debutTournage.getDate());
        for (int i = debutTournage.getDate(); i <= finTournage.getDate(); i++) {
            //for(int k=0;k<2;k++)
            boolean ok = false;
            for (int j = 0; j < scenes.size(); j++) {
                if (!jourFerierOuWeekEnd(new java.sql.Date(debutTournage.getYear(), debutTournage.getMonth(), i))) {
                    if (scenes.get(j).getDebutTournage() == null) {
                        System.err.println("herer" + j + studioIsDisponibleMatin(scenes.get(j).getStudio(), new Date(debutTournage.getYear(), debutTournage.getMonth(), i)));
                        if (!studioIsDisponibleMatin(scenes.get(j).getStudio(), new Date(debutTournage.getYear(), debutTournage.getMonth(), i))) {
                            ok=true;
                        }
                        if (ok == false && studioIsDisponibleMatin(scenes.get(j).getStudio(), new Date(debutTournage.getYear(), debutTournage.getMonth(), i))) {
                            if (acteursIsDisponibleMatin(scenes.get(j).getTexte(), new Date(debutTournage.getYear(), debutTournage.getMonth(), i))) {
                                scenes.get(j).setDebutTournage(new Timestamp(debutTournage.getYear(), debutTournage.getMonth(), i, 6, 0, 0, 0));
                                ok = true;
                                System.out.println("tato v");
                            }
                        }
                        else if (ok == true && studioIsDisponibleMidi(scenes.get(j).getStudio(), new Date(debutTournage.getYear(), debutTournage.getMonth(), i))) {

                            if (acteursIsDisponibleMidi(scenes.get(j).getTexte(), new Date(debutTournage.getYear(), debutTournage.getMonth(), i))) {
                                scenes.get(j).setDebutTournage(new Timestamp(debutTournage.getYear(), debutTournage.getMonth(), i, 13, 0, 0, 0));
                                break;
                            }
                        }
                    }
                }
            }
        }
        return scenes;
    }

    public static void main(String[] args) throws Exception {
        Scene scene = new Scene();
        scene.setIdFilm(1);
        Connection con = ConnectionBase.getCon();
        ArrayList<Scene> allScene = scene.find(con);
        Testt test = new Testt();
        Timestamp debut = Timestamp.valueOf("2023-03-29 06:00:00");
        Timestamp fin = Timestamp.valueOf("2023-03-30 18:00:00");
        allScene = test.planifierTournage(allScene, debut, fin);
        System.out.println(allScene.get(0).getDebutTournage().toLocaleString());
        System.out.println(allScene.get(1).getDebutTournage().toLocaleString());
    }
}

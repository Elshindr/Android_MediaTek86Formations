package com.example.mediatek86formations.controleur;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.mediatek86formations.modele.AccesDistant;
import com.example.mediatek86formations.modele.AccesLocal;
import com.example.mediatek86formations.modele.Formation;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe du controleur
 */
public class Controle {
    /**
     * Propriété contenant une formation
     */
    private Formation formation;
    /**
     * Propriété contenant la liste de toutes les formations
     */
    private List<Formation> lesFormationsAll = new ArrayList<>();
    /**
     * Propriété contenant la liste des formations en cours d'utilisation
     */
    private List<Formation> lesFormationsChoix = new ArrayList<>();
    /**
     * Propriété contenant la liste des formations marquées comme favorites
     */
    private final List<Formation> lesFormationsFavorites = new ArrayList<>();
    /**
     * Propriété contenant la liste des id des formations marquées comme favorites
     */
    private static List<Integer> lesFavoris = new ArrayList<>();
    /**
     * Propriété d'acces à la base de données Locale
     */
    private static AccesLocal accesLocal;
    /**
     * Propriété d'acces à la base de données distantes
     */
    @SuppressLint("StaticFieldLeak")
    private static AccesDistant accesDistant;
    /**
     * Propriété d'instance du controleur
     */
    private static Controle instance = null;


    /**
     * Propriété contenant le choix de l'ativité : si toutes formation ou favoris
     */
    private static String choix = "";


    /**
     * constructeur privé de la Classe Controle
     */
    private Controle() {
        super();
    }

    /**
     * Methode de récupération de l'instance unique du Controleur
     *
     * @return instance
     */
    public static Controle getInstance(Context context) {
        if (Controle.instance == null) {
            Controle.instance = new Controle();

            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                accesDistant = new AccesDistant();
                accesDistant.envoi("tous", null);
            }, 1000); //1 seconde

            accesLocal = new AccesLocal(context);
            lesFavoris = accesLocal.recup();
        }

        return Controle.instance;
    }

    /**
     * Setter sur la liste lesFavorites
     *
     * @return lesFavoris List<Integer>
     */
    public static List<Integer> getLesFavoris() {
        return lesFavoris;
    }

    /**
     * Getter sur la liste lesFormationsFavorites
     *
     * @return lesFormationsFavorites List<Formation>
     */
    public List<Formation> getLesFormationsFavorites() {
        for (Formation uneFormation : lesFormationsAll) {
            if (lesFavoris.contains(uneFormation.getId()) && !lesFormationsFavorites.contains(uneFormation)) {
                lesFormationsFavorites.add(uneFormation);
            }
        }
        return lesFormationsFavorites;
    }

    /**
     * Methode qui ajoute un objet Formation à la liste des formations favorites: lesFavoris
     *
     * @param favFormation Formation
     */
    public void ajoutFavoris(Formation favFormation) {
        if (!lesFavoris.contains(favFormation.getId())) {
            lesFavoris.add(favFormation.getId());
            accesLocal.ajoutfavoris(favFormation.getId());
            lesFormationsFavorites.add(favFormation);
        }
    }

    /**
     * Methode qui supprime un objet Formation à la liste des formations favorites: lesFavoris
     *
     * @param notfavFormation Formation
     */
    public void removeFav(Formation notfavFormation) {
        accesLocal.removefavoris(notfavFormation.getId());
        lesFavoris.remove((Integer) notfavFormation.getId());
        lesFormationsFavorites.remove(notfavFormation);
    }


    /**
     * Methode qui retourne la liste des formations dont le titre contient le filtre
     *
     * @param filtre String
     * @return lstFiltre List<Formation>
     */
    public List<Formation> getLesFormationFiltre(String filtre) {
        List<Formation> lstFiltre = new ArrayList<>();
        if (lesFormationsChoix != null) {
            for (Formation uneFormation : lesFormationsChoix) {
                if (uneFormation.getTitle().toUpperCase().contains(filtre.toUpperCase())) {
                    lstFiltre.add(uneFormation);
                }
            }
        }
        return lstFiltre;
    }

    /**
     * Getter sur l'objet d'une formation
     *
     * @return formation Formation
     */
    public Formation getFormation() {
        return formation;
    }

    /**
     * Setter sur l'objet Formation
     *
     * @param formation Formation
     */
    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    /**
     * Getter sur l'objet lesFormationsAll
     *
     * @return lesFormations List<Formation>
     */
    public List<Formation> getLesFormationsAll() {
        return lesFormationsAll;
    }


    /**
     * Setter sur la liste lesFormationsAll
     *
     * @param lesFormationsAll List<Formation>
     */
    public void setLesFormationsAll(List<Formation> lesFormationsAll) {
        this.lesFormationsAll = lesFormationsAll;
    }


    /**
     * Setter sur le choix de l'activité
     *
     * @param choix String
     */
    public static void setChoix(String choix) {
        Controle.choix = choix;
    }


    /**
     * Getter sur la liste des formations choisies
     */
    public List<Formation> getLesFormationsChoix() {
        if (choix.equals("favoris")) {
            lesFormationsChoix = getLesFormationsFavorites();
        }
        if (choix.equals("all")) {
            lesFormationsChoix = getLesFormationsAll();
        }
        return lesFormationsChoix;
    }

}


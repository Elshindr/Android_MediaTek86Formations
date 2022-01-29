package com.example.mediatek86formations.controleur;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.mediatek86formations.modele.AccesDistant;
import com.example.mediatek86formations.modele.AccesLocal;
import com.example.mediatek86formations.modele.Formation;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe du controleur.
 */
public class Controle {
    /**
     * Propriété contenant la liste de toutes les formations.
     */
    private static List<Formation> lesFormationsAll = new ArrayList<>();
    /**
     * Propriété contenant la liste des formations marquées comme favorites.
     */
    private final List<Formation> lesFormationsFavorites = new ArrayList<>();
    /**
     * Propriété contenant la liste des formations en cours d'utilisation.
     */
    private List<Formation> lesFormationsChoix = new ArrayList<>();

    /**
     * Propriété contenant la liste des id des formations favorites.
     */
    private static List<Integer> lesFavoris = new ArrayList<>();

    /**
     * Propriété contenant le choix de l'activité.
     */
    private static String choix = "";

    /**
     * Propriété d'acces à la base de données Locale.
     */
    private static AccesLocal accesLocal;
    /**
     * Propriété d'acces à la base de données distantes.
     */
    @SuppressLint("StaticFieldLeak")
    private static AccesDistant accesDistant;
    /**
     * Propriété d'instance du controleur.
     */
    private static Controle instance = null;
    /**
     * Propriété contenant une formation.
     */
    private Formation formation;



    /**
     * Constructeur privé de la Classe Controle.
     */
    private Controle() {
        super();
    }


    /**
     * Methode de récupération de l'instance unique du Controleur.
     *
     * @param context Context
     * @return instance
     */
    public static Controle getInstance(Context context) {
        if (Controle.instance == null) {
            Controle.instance = new Controle();

            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                accesDistant = new AccesDistant();
                accesDistant.envoi("tous", null);
            }, 2000); //2 secondes

            accesLocal = new AccesLocal(context);
            lesFavoris = accesLocal.recup();
        }

        return Controle.instance;
    }

    /**
     * Methode qui permet de nettoyer la base de donnée locale si une formation est supprimée.
     */
    public static void checkLesFavoris() {
        List<Integer> testIdFavoris = new ArrayList<>();
        for (Formation uneFormation : lesFormationsAll) {
            testIdFavoris.add(uneFormation.getId());
        }

        for (int i = 0; i < lesFavoris.size(); i++) {
            if (!testIdFavoris.contains(lesFavoris.get(i))) {
                Log.d("checkFav", "Suppr  favoris: " + lesFavoris.get(i).toString());
                accesLocal.removeFavoris(lesFavoris.get(i));
                lesFavoris.remove(lesFavoris.get(i));
            }
        }
    }


    /**
     * Setter sur la liste lesFavorites.
     *
     * @return lesFavoris List<Integer>
     */
    public static List<Integer> getLesFavoris() {
        return lesFavoris;
    }

    /**
     * Setter sur le choix de l'activité.
     *
     * @param choix String
     */
    public static void setChoix(String choix) {
        Controle.choix = choix;
    }

    /**
     * Getter sur le choix de l'activité.
     * @return
     */
    public static String getChoix() {
        return choix;
    }
    /**
     * Getter sur la liste lesFormationsFavorites.
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
     * Methode qui ajoute un objet Formation à la liste lesFavoris.
     *
     * @param favFormation Formation
     */
    public void ajoutFavoris(Formation favFormation) {
        if (!lesFavoris.contains(favFormation.getId())) {
            lesFavoris.add(favFormation.getId());
            accesLocal.ajoutFavoris(favFormation.getId());
            lesFormationsFavorites.add(favFormation);
        }
    }

    /**
     * Methode qui supprime un objet Formation à la liste lesFavoris.
     *
     * @param notfavFormation Formation
     */
    public void removeFav(Formation notfavFormation) {
        if(lesFavoris.contains(notfavFormation.getId())){
            accesLocal.removeFavoris(notfavFormation.getId());
            lesFavoris.remove((Integer) notfavFormation.getId());
            lesFormationsFavorites.remove(notfavFormation);
        }
    }

    /**
     * Methode qui retourne la liste des formations avec le filtre.
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
     * Getter sur l'objet d'une formation.
     *
     * @return formation Formation
     */
    public Formation getFormation() {
        return formation;
    }

    /**
     * Setter sur l'objet Formation.
     *
     * @param formation Formation
     */
    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    /**
     * Getter sur l'objet lesFormationsAll.
     *
     * @return lesFormations List<Formation>
     */
    public List<Formation> getLesFormationsAll() {
        return lesFormationsAll;
    }

    /**
     * Setter sur la liste lesFormationsAll.
     *
     * @param lesFormationsAll List<Formation>
     */
    public static void setLesFormationsAll(List<Formation> lesFormationsAll) {
        Controle.lesFormationsAll = lesFormationsAll;
    }

    /**
     * Getter sur la liste des formations choisies.
     *
     * @return lesFormationsChoix List<Formation>
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


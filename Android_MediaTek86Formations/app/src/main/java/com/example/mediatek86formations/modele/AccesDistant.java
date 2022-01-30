package com.example.mediatek86formations.modele;

import android.content.Context;
import android.util.Log;

import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.outils.AccesREST;
import com.example.mediatek86formations.outils.AsyncResponse;
import com.example.mediatek86formations.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe d'acces à la base de données distante qui implémente AsyncResponse.
 */
public class AccesDistant implements AsyncResponse {

    /**
     * Propriété contenant la chaine de l'adresse du serveur.
     */
    private static final String SERVERADDR = "https://api-mediatekformations.herokuapp.com/";

    /**
     * Propriété d'instance du contexte de l'activité.
     */
    private Context context;

    /**
     * Constructeur de la classe AccesDistant, valorise la propriété controle.
     */
    public AccesDistant() {
        Controle.getInstance(context);
    }

    /**
     * Methode qui récupére la réponse du serveur distant et valorise la liste des formations vers le controleur.
     *
     * @param output String
     */
    @Override
    public void processFinish(final String output) {
        Log.d("serveur", "************" + output);
        try {
            JSONObject retour = new JSONObject(output);
            String message = retour.getString("message");
            if (!message.equals("OK")) {
                Log.d("erreur", "********* retour api rest :" + message);
            } else {
                JSONArray infos = retour.getJSONArray("result");
                ArrayList<Formation> lesFormations = new ArrayList<>();
                for (int k = 0; k < infos.length(); k++) {
                    JSONObject info = new JSONObject(infos.get(k).toString());
                    int id = Integer.parseInt(info.getString("id"));
                    Date publishedAt = MesOutils.convertStringToDate(info.getString("published_at"), "yyyy-MM-dd HH:mm:ss");
                    String title = info.getString("title");
                    String description = info.getString("description");
                    String miniature = info.getString("miniature");
                    String picture = info.getString("picture");
                    String videoId = info.getString("video_id");
                    Formation formation = new Formation(id, publishedAt, title, description, miniature, picture, videoId);
                    lesFormations.add(formation);
                }
                Controle.setLesFormationsAll(lesFormations);
                Controle.checkLesFavoris();
            }
        } catch (JSONException e) {
            Log.d("processFinish Exception",  e.getMessage());
        }
    }

    /**
     * Methode qui envoi la requete de récupération des données vers le serveur distant.
     *
     * @param operation      String
     * @param lesDonneesJSON JSONObject
     */
    public void envoi(final String operation, final JSONObject lesDonneesJSON) {
        AccesREST accesDonnees = new AccesREST();
        accesDonnees.delegate = this;
        String requesMethod = null;
        if (operation.equals("tous")) {
            requesMethod = "GET";
        }
        if (requesMethod != null) {
            accesDonnees.setRequestMethod(requesMethod);
            accesDonnees.addParam("formation");
            if (lesDonneesJSON != null) {
                accesDonnees.addParam(lesDonneesJSON.toString());
            }
            accesDonnees.execute(SERVERADDR);
        }
    }

}

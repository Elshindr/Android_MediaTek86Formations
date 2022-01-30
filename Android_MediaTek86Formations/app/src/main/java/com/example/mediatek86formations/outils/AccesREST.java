package com.example.mediatek86formations.outils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Classe technique de connexion distante HTTP.
 */
public class AccesREST extends AsyncTask<String, Integer, Long> {

    /**
     * Propriété permettant de mettre en place la gestion du retour asynchrone.
     */
    public AsyncResponse delegate = null;
    /**
     * Propriété contenant la reponse du serveur.
     */
    private String ret = "";
    /**
     * Propriété contenant les paramètres d'envoi au serveur en POST.
     */
    private String parametres = "";
    /**
     * Propriété contenant la methode d'envoi au serveur.
     */
    private String requestMethod = "GET";

    /**
     * Constructeur de la Classe AccesREST.
     */
    public AccesREST() {
        super();
    }

    /**
     * Methode qui ajoute des paramètres à la requete.
     *
     * @param valeur String
     */
    public void addParam(String valeur) {
        try {
            if (parametres.equals("")) {
                // premier paramètre
                parametres = URLEncoder.encode(valeur, "UTF-8");
            } else {
                // paramètres suivants (séparés par /)
                parametres += "/" + URLEncoder.encode(valeur, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            Log.d("addParam Exception",  e.getMessage());
        }
    }

    /**
     * Setter sur la propriété requestMethod.
     *
     * @param requestMethod String
     */
    public void setRequestMethod(final String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * Méthode appelée par la méthode execute qui permet d'envoyer au serveur une liste de paramètres en GET.
     *
     * @param urls contient l'adresse du serveur dans la case 0 de urls
     * @return null
     */
    @Override
    protected Long doInBackground(final String... urls) {

        // pour éliminer certaines erreurs
        System.setProperty("http.keepAlive", "false");
        // objets pour gérer la connexion et la lecture
        BufferedReader reader = null;
        HttpURLConnection connexion;
        try {
            // création de l'url à partir de l'adresse reçu en paramètre, dans urls[0] + les paramètres
            URL url = new URL(urls[0] + parametres);
            // ouverture de la connexion
            connexion = (HttpURLConnection) url.openConnection();
            // choix de la méthode pour l'envoi des paramètres
            connexion.setRequestMethod(requestMethod);
            // Récupération du retour du serveur
            reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            ret = reader.readLine();
        } catch (Exception e) {
            Log.d("doInBackgroundException",  e.getMessage());
        } finally {
            // fermeture du canal de réception
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                Log.d("doInBackgroundException",  e.getMessage());
            }
        }


        return null;
    }

    /**
     * Sur le retour du serveur, envoi l'information retournée à processFinish.
     *
     * @param result Long
     * @deprecated AsyncTask
     */
    @Override
    @Deprecated
    protected void onPostExecute(final Long result) {
        // ret contient l'information récupérée
        delegate.processFinish(this.ret);
    }

}


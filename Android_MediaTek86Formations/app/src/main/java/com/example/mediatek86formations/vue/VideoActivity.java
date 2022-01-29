package com.example.mediatek86formations.vue;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediatek86formations.R;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;

/**
 * Classe visuelle permettant la gestion des vidéos de formations
 */
public class VideoActivity extends AppCompatActivity {

    /**
     * Propriété contenant l'objet graphique affichant la vidéo
     */
    private WebView wbvYoutube;

    /**
     * Methode appellée à la création de l'activité et l'initialise
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        init();
    }

    /**
     * Methode permettant d'afficher la vidéo
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        Controle controle = Controle.getInstance(this);
        Formation formation = controle.getFormation();
        if (formation != null) {
            wbvYoutube = findViewById(R.id.wbvYoutube);
            Log.d("onCreate", "******* Ouverture js");
            wbvYoutube.getSettings().setJavaScriptEnabled(true);
            wbvYoutube.setWebViewClient(new WebViewClient());
            wbvYoutube.loadUrl("https://www.youtube.com/embed/" + formation.getVideoId());
        }
    }

    /**
     * Methode appellée à la fermeture de l'activité
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wbvYoutube.getSettings().getJavaScriptEnabled()) {
            wbvYoutube.getSettings().setJavaScriptEnabled(false);
            Log.d("onDestroy", "******* Fermeture js");
        }
    }
}
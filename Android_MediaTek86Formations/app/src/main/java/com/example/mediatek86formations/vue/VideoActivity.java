package com.example.mediatek86formations.vue;

import android.os.Bundle;
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
    WebView wbvYoutube;

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
    private void init() {
        Controle controle = Controle.getInstance(this);
        Formation formation = controle.getFormation();
        if (formation != null) {
            wbvYoutube = findViewById(R.id.wbvYoutube);
            wbvYoutube.getSettings().setJavaScriptEnabled(true);
            wbvYoutube.setWebViewClient(new WebViewClient());
            wbvYoutube.loadUrl("https://www.youtube.com/embed/" + formation.getVideoId());
        }
    }

}
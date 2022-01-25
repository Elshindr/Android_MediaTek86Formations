package com.example.mediatek86formations.vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediatek86formations.R;
import com.example.mediatek86formations.controleur.Controle;

/**
 * Classe d'activité principale hérite de AppCompatActivity
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Méthode qui gére la création de l'activité
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Méthode qui initialise les composants graphiques de l'activité
     */
    private void init() {
        creerMenu();
        Controle.getInstance(this);
    }

    /**
     * Méthode qui appelle les procédures événementielles gérant le menu
     */
    private void creerMenu() {
        ecouteMenu(findViewById(R.id.btnFormations), "all");
        ecouteMenu(findViewById(R.id.btnFavoris), "favoris");
    }

    /**
     * Méthode événementielle sur le clic d'une image du menu
     *
     * @param btn   ImageButton
     * @param choix String
     */
    private void ecouteMenu(ImageButton btn, String choix) {
        btn.setOnClickListener(v -> {
            Activity activity = MainActivity.this;
            Intent intent = new Intent(activity, FormationsActivity.class);
            activity.startActivity(intent);
            Controle.setChoix(choix);
        });
    }

}
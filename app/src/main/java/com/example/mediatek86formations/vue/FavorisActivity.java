package com.example.mediatek86formations.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.mediatek86formations.R;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;

import java.util.ArrayList;
import java.util.Collections;

public class FavorisActivity extends AppCompatActivity {

    private Controle controle;
    private Button btnFiltrer;
    private EditText txtFiltre;
    private ArrayList<Formation> lesFormationsFavorites;
    private ArrayList<Integer> lesFavoris ;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations);
        init();
    }

    /**
     * initialisations
     */
    private void init(){
        btnFiltrer = (Button) findViewById(R.id.btnFiltrer);
        txtFiltre = (EditText) findViewById(R.id.txtFiltre);

        controle = Controle.getInstance(this);

        lesFormationsFavorites = controle.getLesFormationsFavorites();
        lesFavoris = controle.getLesFavoris();

        creerListe(lesFormationsFavorites);
        ecouteFiltre();
    }

    /**
     * création de la liste adapter
     */
    private void creerListe(ArrayList<Formation> lesFormationsFavorites){
        if(lesFormationsFavorites != null){
            Log.d("creerliste", "FAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAvoris " );
            Collections.sort(lesFormationsFavorites, Collections.<Formation>reverseOrder());
            ListView listView = (ListView)findViewById(R.id.lstFormations);
            FormationListAdapter adapter = new FormationListAdapter(lesFormationsFavorites, lesFavoris,FavorisActivity.this);
            listView.setAdapter(adapter);
        }
    }

    /**
     * Methode événementielle sur le clic du button filtrer. Permet de filtrer les formations sur le titre
     */
    private void ecouteFiltre(){
        txtFiltre = (EditText) findViewById(R.id.txtFiltre);
        btnFiltrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                ArrayList<Formation> lstFormationFiltre = new ArrayList<Formation>(controle.getLesFormationFiltre(txtFiltre.getText().toString(), FavorisActivity.this));
                if(txtFiltre.getText().toString() != ""){
                    creerListe(lstFormationFiltre);
                }
                else {
                    controle.setLesFormations(controle.getLesFormationsFavorites());
                    creerListe(lesFormationsFavorites);
                }
            }
        });
    }
}
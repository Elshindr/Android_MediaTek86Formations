package com.example.mediatek86formations.vue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mediatek86formations.R;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;

import java.util.List;

/**
 * Classe visuelle de gestion des listes adaptables hérite de BaseAdapter
 */
public class FormationListAdapter extends BaseAdapter {
    /**
     * Propriété contenant la liste des formations
     */
    private final List<Formation> lesFormations;
    /**
     * Propriété contenant l'instance du layout graphique
     */
    private final LayoutInflater inflater;
    /**
     * Propriété contenant l'instance du controleur'
     */
    private final Controle controle;
    /**
     * Propriété contenant l'instance du contexte'
     */
    private final Context context;
    /**
     * Propriété contenant la liste des id des formations
     */
    private List<Integer> lesFavoris;

    /**
     * Constructeur de la liste graphique adaptable. Valorise l'ensemble de ses propriétés privées
     *
     * @param lesFormations List<Formation>
     * @param lesFavoris    List<Integer>
     * @param context       Context
     */
    public FormationListAdapter(List<Formation> lesFormations, List<Integer> lesFavoris, Context context) {
        this.lesFormations = lesFormations;
        this.lesFavoris = lesFavoris;
        this.controle = Controle.getInstance(context);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * Getter sur la taille de la liste lesFormations
     *
     * @return nombre de formations
     */
    @Override
    public int getCount() {
        return lesFormations.size();
    }

    /**
     * Getter sur la formation à la position i donnée
     *
     * @param i position de l'item formation
     * @return la formation à cette position
     */
    @Override
    public Object getItem(int i) {
        return lesFormations.get(i);
    }

    /**
     * Getter sur l'id de la formation fournie
     *
     * @param i position de l'item
     * @return id de la formation à cette position
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Methode de construction des lignes de la liste. Appellée pour chaque item
     *
     * @param i         int
     * @param view      View
     * @param viewGroup ViewGroup
     * @return view View
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewProperties viewProperties;
        if (view == null) {
            viewProperties = new ViewProperties();
            view = inflater.inflate(R.layout.layout_liste_formations, null);
            viewProperties.txtListeTitle = view.findViewById(R.id.txtListTitle);
            viewProperties.txtListPublishedAt = view.findViewById(R.id.txtListPublishedAt);
            viewProperties.btnListFavori = view.findViewById(R.id.btnListFavori);
            view.setTag(viewProperties);
        } else {
            viewProperties = (ViewProperties) view.getTag();
        }

        viewProperties.txtListeTitle.setText(lesFormations.get(i).getTitle());
        viewProperties.txtListPublishedAt.setText(lesFormations.get(i).getPublishedAtToString());
        viewProperties.txtListeTitle.setTag(i);
        viewProperties.txtListeTitle.setOnClickListener(this::ouvrirUneFormationActivity);

        viewProperties.txtListPublishedAt.setTag(i);
        viewProperties.txtListPublishedAt.setOnClickListener(this::ouvrirUneFormationActivity);

        viewProperties.txtListPublishedAt.setOnClickListener(this::ouvrirUneFormationActivity);

        viewProperties.btnListFavori.setImageResource(selectFavBtnColor(i));
        viewProperties.btnListFavori.setOnClickListener(v -> {
            if (lesFavoris.contains(lesFormations.get(i).getId())) {
                viewProperties.btnListFavori.setImageResource(R.drawable.coeur_gris);
                controle.removeFav(lesFormations.get(i));
            } else {
                viewProperties.btnListFavori.setImageResource(R.drawable.coeur_rouge);
                controle.ajoutFavoris(lesFormations.get(i));
            }
            lesFavoris = Controle.getLesFavoris();
            notifyDataSetChanged();
        });
        return view;
    }

    /**
     * Methode évenementielle qui permet de gérer si le bouton favoris d'une formation est marquée comme favoris ou non
     *
     * @param id int
     * @return int Ressource
     */
    private int selectFavBtnColor(int id) {
        if (lesFavoris.contains(lesFormations.get(id).getId())) {
            return R.drawable.coeur_rouge;
        } else {
            return R.drawable.coeur_gris;
        }
    }

    /**
     * Methode évenementielle qui ouvre la page du détail de la formation
     *
     * @param v View
     */
    private void ouvrirUneFormationActivity(View v) {
        int indice = (int) v.getTag();
        controle.setFormation(lesFormations.get(indice));
        Intent intent = new Intent(context, UneFormationActivity.class);
        context.startActivity(intent);
    }

    /**
     * Classe privée interne possédant les propriétés des lignes de la liste adapter
     */
    private static class ViewProperties {
        /**
         * Propriété graphique représentant le bouton des favoris
         */
        ImageButton btnListFavori;
        /**
         * Propriété graphique de la zone de text de la date de publication
         */
        TextView txtListPublishedAt;
        /**
         * Propriété graphique de la zone de texte du titre
         */
        TextView txtListeTitle;
    }
}

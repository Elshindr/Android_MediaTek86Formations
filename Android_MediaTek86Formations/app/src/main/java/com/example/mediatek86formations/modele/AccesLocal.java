package com.example.mediatek86formations.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mediatek86formations.outils.MySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'acces à la base de données locale.
 */
public class AccesLocal {
    /**
     * Propriété contenant la chaine du nom de la base de donnée locale.
     */
    private static final String NOMBASE = "bdMediatek.sqlite";
    /**
     * Propriété contenant la valeur de la version de la base de donnée.
     */
    private static final Integer VERSIONBASE = 1;
    /**
     * Propriété contenant l'instance de la classe technique gestion de la base de donnée SQLite.
     */
    private final MySQLiteOpenHelper accesBD;
    /**
     * Propriété contenant l'instance de la base de donnée locale SQLite.
     */
    private SQLiteDatabase bd;

    /**
     * Constructeur public qui valorise la propriété d'accès à la base de donnée locale.
     *
     * @param context Context
     */
    public AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context, NOMBASE, VERSIONBASE);
    }

    /**
     * Methode qui ajoute dans la base de donnée locale la valeur du paramétre fourni.
     *
     * @param id int
     */
    public void ajoutfavoris(int id) {
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idformation", id);
        bd.insert("favoris", null, values);
        bd.close();
    }

    /**
     * Methode qui retourne la liste des id contenu dans la base de données locale.
     *
     * @return favoris List<Integer>
     */
    public List<Integer> recup() {
        List<Integer> favoris = new ArrayList<>();
        bd = accesBD.getReadableDatabase();
        String req = "select * from favoris";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            Integer idformation = curseur.getInt(0);

            favoris.add(idformation);
            curseur.moveToNext();
        }
        Log.d("recupfavoris", "id favoris: " + favoris.toString());

        curseur.close();
        return favoris;
    }

    /**
     * Methode qui supprime l'id fourni de la base de donnée locale.
     *
     * @param notFavId int
     */
    public void removefavoris(int notFavId) {
        bd = accesBD.getWritableDatabase();
        bd.delete("favoris", "idformation=?", new String[]{String.valueOf(notFavId)});
        Log.d("suppr", "id: " + notFavId);
    }
}

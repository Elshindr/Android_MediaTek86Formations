package com.example.mediatek86formations.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe de gestion d'une base de donnée MySQLite qui hérite de SQLiteOpenHelper.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    /**
     * Propriété contenant la requete de creation de la table.
     */
    private static final String CREATION = "create table favoris (idformation INTEGER NOT NULL);";

    /**
     * Construction de l'accès à une base de données locale.
     *
     * @param context Context
     * @param name    String
     * @param version int
     */
    public MySQLiteOpenHelper(final Context context, final String name, final int version) {
        super(context, name, null, version);
    }

    /**
     * Méthode qui redéfinie onCreate. Elle est automatiquement appelée par le constructeur
     * si celui-ci repère que la base n'existe pas encore
     *
     * @param db SQLiteDatabase
     */
    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(CREATION);
    }

    /**
     * méthode qui redéfinie OnUpgrade et est appelée automatiquement s'il y a changement de version de la base.
     *
     * @param db         SQLiteDatabase
     * @param oldVersion int
     * @param newVersion int
     */
    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        // non utilisée
    }

}

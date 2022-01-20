package com.example.mediatek86formations.modele;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mediatek86formations.outils.MesOutils;
import com.example.mediatek86formations.outils.MySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

public class AccesLocal {

    private String nomBase = "bdMediatek.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * constructeur : valorise l'accès à la BDD
     * @param context
     */
    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);
    }
    public void ajoutfavoris(int i){
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idformation", i);
        bd.insert("favoris", null, values);
        bd.close();
    }

    public ArrayList<Integer> recup(){
        ArrayList<Integer> favoris = new ArrayList<Integer>();
        bd = accesBD.getReadableDatabase();
        String req = "select * from favoris";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToFirst();
        int i =0;
        while (!curseur.isAfterLast()){
            Integer idformation = curseur.getInt(0);

            Log.d("testttt","id forma:"+idformation + "i: "+i);
            favoris.add(idformation) ;
            i++;
            curseur.moveToNext();
        }
        Log.d("test","id: " + favoris.toString());

        curseur.close();
        return favoris;
    }


    public void removefavoris(int notFavId){

        bd = accesBD.getWritableDatabase();
        bd.delete("favoris","idformation=?",new String[]{String.valueOf(notFavId)});

        Log.d("suppr","id: " + notFavId);

    }
}

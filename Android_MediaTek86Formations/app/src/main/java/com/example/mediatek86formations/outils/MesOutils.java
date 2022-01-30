package com.example.mediatek86formations.outils;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageButton;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe Interface contenant différentes methodes utilitaires.
 */
public interface MesOutils {

    /**
     * Méthode qui reçoit une date au format String et la convertit au format Date.
     *
     * @param strDate         String
     * @param expectedPattern format de la date
     * @return date  Date
     */
    static Date convertStringToDate(String strDate, String expectedPattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            Log.d("convertStringToDate",  e.getMessage());
        }
        return null;
    }


    /**
     * Methode qui reçoit une date au format Date et la convertit au format String dd/MM/yyyy.
     *
     * @param uneDate au format Date
     * @return date convertie au format String
     */
    static String convertDateToString(Date uneDate) {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(uneDate);
    }

    /**
     * Methode qui charge une image à partir d'une url.
     *
     * @param img ImageButton
     * @param url String
     */
    static void loadMapPreview(ImageButton img, String url) {
        new Thread(() -> {
            try {
                //download the drawable
                final Drawable drawable = Drawable.createFromStream((InputStream) new URL(url).getContent(), "src");
                //edit the view in the UI thread
                img.post(() -> img.setImageDrawable(drawable));
            } catch (IOException e) {
                Log.d("loadMapPreview",  e.getMessage());
            }
        }).start();

    }

}

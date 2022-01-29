package com.example.mediatek86formations.modele;


import junit.framework.TestCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe de tests unitaire pour la classe Formation
 */
public class FormationTest extends TestCase {
    private final String strDate = "2020-08-07 05:59:10";
    private final String format = "dd/MM/yyyy";
    private final SimpleDateFormat formatter = new SimpleDateFormat(format);
    private Date date = new Date();
    private final Formation uneFormation = new Formation(13, date, "titre", "description", "", "", "");

    {
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test unitaire permettant de vérifier le getter de publishedAt
     */
    public void testGetPublishedAt() {
        assertEquals(date, uneFormation.getPublishedAt());
    }

    /**
     * Test unitaire permettant de vérifier la conversion en String de publishedAt
     */
    public void testGetPublishedAtToString() {
        assertEquals(formatter.format(date), uneFormation.getPublishedAtToString());
    }
}
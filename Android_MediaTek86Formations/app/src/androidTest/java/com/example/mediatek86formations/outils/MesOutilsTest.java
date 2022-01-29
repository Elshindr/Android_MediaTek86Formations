package com.example.mediatek86formations.outils;

import com.example.mediatek86formations.modele.Formation;

import junit.framework.TestCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Classe de tests unitaires de la classe MesOutils
 */
public class MesOutilsTest extends TestCase {
    private final String strDate = "2020-08-07 05:59:10";
    private final String format = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat formatter = new SimpleDateFormat(format);
    private Date date = new Date();

    {
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private final Formation uneFormation = new Formation(13, date, "titre", "description", "", "", "");

    /**
     * Test unitaire vérifiant qu'un String est converti en Date au format donné
     */
    public void testConvertStringToDate() {
        String leFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(leFormat);

        assertEquals(date, MesOutils.convertStringToDate(dateFormat.format(date), leFormat));
    }

    /**
     * Test unitaire vérifiant qu'une Date est convertie en String au format donné
     */
    public void testConvertDateToString() {
        String leFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(leFormat);

        assertEquals(dateFormat.format(date), MesOutils.convertDateToString(date));
        assertEquals(dateFormat.format(date), MesOutils.convertDateToString(uneFormation.getPublishedAt()));
    }
}
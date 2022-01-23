package com.example.mediatek86formations.outils;

import com.example.mediatek86formations.modele.Formation;

import junit.framework.TestCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe de tests unitaires de MesOutils
 */
public class MesOutilsTest extends TestCase {
    final String strDate = "2020-08-07 05:59:10";
    final String expectedPattern = "yyyy-MM-dd HH:mm:ss";
    final SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
    Date date = new Date();

    {
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private final Formation uneFormation = new Formation(13, date, "UML : Diagramme de cas d'utilisation", "Présentation du diagramme de cas d'utilisation à travers la construction d'un exemple.", "https://i.ytimg.com/vi/LDTDlXMV1xY/default.jpg", "https://i.ytimg.com/vi/LDTDlXMV1xY/sddefault.jpg", "LDTDlXMV1xY");

    /**
     * Test unitaire vérifiant qu'un String est convertie en Date au format donné
     */
    public void testConvertStringToDate() {
        assertEquals(date, MesOutils.convertStringToDate("2020-08-07 05:59:10", expectedPattern));
    }

    /**
     * Test unitaire vérifiant qu'une Date est convertie en String au format donné
     */
    public void testConvertDateToString() {
        assertEquals("07/08/2020", MesOutils.convertDateToString(uneFormation.getPublishedAt()));
    }


}
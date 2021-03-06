package com.example.mediatek86formations.outils;

/**
 * Created by emds on 07/08/2015.
 * Classe Interface de gestion des reponses asynchrones.
 */
public interface AsyncResponse {
    /**
     * Méthode à réécrire qui lance une opération asynchrone.
     * @param output String
     */
    void processFinish(String output);
}

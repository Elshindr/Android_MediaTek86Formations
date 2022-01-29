package com.example.mediatek86formations.modele;

import com.example.mediatek86formations.outils.MesOutils;

import java.util.Date;

/**
 * Classe métier représentant l'objet Formation qui implémente Comparable.
 */
public class Formation implements Comparable<Formation> {
    /**
     * Propriété contenant id d'une formation.
     */
    private final int id;
    /**
     * Propriété contenat la date de publication d'une formation.
     */
    private final Date publishedAt;
    /**
     * Propriété contenant le titre d'une formation.
     */
    private final String title;
    /**
     * Propriété contenant la description d'une formation.
     */
    private final String description;
    /**
     * Propriété contenant l'adresse internet de la miniature de video d'une formation.
     */
    private final String miniature;
    /**
     * Propriété contenant l'adresse internet de l'image de video d'une formation.
     */
    private final String picture;
    /**
     * Propriété contenant l'id de la vidéo sur Youtube.
     */
    private final String videoId;

    /**
     * Constructeur public de la classe Formation qui valorise l'ensemble de ses propriétés privées.
     *
     * @param id          int
     * @param publishedAt Date
     * @param title       String
     * @param description String
     * @param miniature   String
     * @param picture     String
     * @param videoId     String
     */
    public Formation(int id, Date publishedAt, String title, String description, String miniature, String picture, String videoId) {
        this.id = id;
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.miniature = miniature;
        this.picture = picture;
        this.videoId = videoId;
        this.getPublishedAtToString();
    }

    /**
     * Getter sur la propriété id de la formation.
     *
     * @return id int
     */
    public int getId() {
        return id;
    }

    /**
     * Getter sur la propriété publishedAt de la formation.
     *
     * @return publishedAt Date
     */
    public Date getPublishedAt() {
        return publishedAt;
    }

    /**
     * Methode qui retourne la propriété publishedAt de type Date en String au format dd/MM/yyyy.
     *
     * @return publishedAt String
     */
    public String getPublishedAtToString() {
        return MesOutils.convertDateToString(this.publishedAt);
    }

    /**
     * Getter sur la propriété title de Formation.
     *
     * @return title String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter sur la propriété description de Formation.
     *
     * @return description String
     */
    public String getDescription() {
        return description;
    }


    /**
     * Getter sur la propriété picture de Formation.
     *
     * @return picture String
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Getter sur la propriété videoId de Formation.
     *
     * @return videoId String
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * Methode implémentée par Comparable<Formation>. Compare deux formations entre elles selon la propriété publishedAt.
     *
     * @param uneFormation Formation
     * @return int int
     */
    @Override
    public int compareTo(Formation uneFormation) {
        return publishedAt.compareTo((uneFormation).getPublishedAt());
    }

}

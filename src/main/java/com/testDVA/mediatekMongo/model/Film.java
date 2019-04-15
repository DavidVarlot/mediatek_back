package com.testDVA.mediatekMongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

/**********************************
 *
 * Modèle de donnée d'un Film
 *
 **********************************/

@Document(collection = "Film")
public class Film {
    @Id
    private ObjectId id;
    @Field(value = "Titre")
    private String titre;
    @Field(value = "Synopsis")
    private String synopsis;
    @Field(value = "Genre")
    private Genre genre;

    public Film() {}

    public Film(ObjectId id, String titre, String synopsis, Genre genre) {
        this.id = id;
        this.titre = titre;
        this.synopsis = synopsis;
        this.genre = genre;
    }

    public Film(String titre, String synopsis, Genre genre) {
        this.titre = titre;
        this.synopsis = synopsis;
        this.genre = genre;
    }

    public String getId(){
        if(this.id != null)
            return this.id.toHexString();
        else
            return null;
    }
    public void setId(ObjectId id){
        this.id = id;
    }
    public String getTitre(){
        return this.titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getSynopsis(){
        return this.synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public Genre getGenre(){
        return this.genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}

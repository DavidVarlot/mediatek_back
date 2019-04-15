package com.testDVA.mediatekMongo.model;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**********************************
 *
 * Modèle de donnée d'un genre
 *
 **********************************/

@Document(collection = "Genre")
public class Genre {
    @Id
    public ObjectId id;
    @Field(value = "Nom")
    public String nom;

    public Genre() {}
    public Genre(ObjectId id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public Genre(String nom) {
        this.nom = nom;
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
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}

package com.testDVA.mediatekMongo.model;
import org.bson.types.ObjectId;

import java.util.List;

public interface FilmRepositoryCustom {

    List<Film> findByGenreId(ObjectId genreId);
    long countByGenreId(ObjectId genreId);

}

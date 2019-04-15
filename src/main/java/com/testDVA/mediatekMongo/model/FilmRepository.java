package com.testDVA.mediatekMongo.model;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FilmRepository extends MongoRepository<Film, ObjectId>, FilmRepositoryCustom {
    List<Film> findByGenreId(ObjectId genreId);
    long countByGenreId(ObjectId genreId);
}

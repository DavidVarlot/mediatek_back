package com.testDVA.mediatekMongo.model;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

/**********************************
 *
 * Implémentation des méthodes complèmentaires
 *
 **********************************/

@Repository
public  class FilmRepositoryCustomImpl implements FilmRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Film> findByGenreId(ObjectId genreId) {
        Query query = new Query(Criteria.where("genre.id").is(genreId));
        List<Film> result = mongoTemplate.find(query, Film.class);
        return result;
    }

    public long countByGenreId(ObjectId genreId) {
        Query query = new Query(Criteria.where("genre.id").is(genreId));
        long result = mongoTemplate.count(query, Film.class);
        return result;
    }

}

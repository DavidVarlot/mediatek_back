package com.testDVA.mediatekMongo.web.controller;
import com.testDVA.mediatekMongo.model.Genre;
import com.testDVA.mediatekMongo.model.GenreRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController

/**********************************
 *
 * API de manipulation des Genres
 *
 **********************************/

public class GenreController {
    @Autowired
    private GenreRepository _genreRepository;

    // on récupère tout les genres
    @GetMapping(value = "/Genres")
    public ResponseEntity<List<Genre>> getAllgenre() {
        return ResponseEntity.ok(this._genreRepository.findAll(Sort.by("nom")));
    }

    // on récupère un genre par son ID
    @GetMapping(value = "/Genres/{id}")
    public ResponseEntity<Genre> getGenre(@PathVariable String id) {
        ObjectId oId;
        try {
            oId = new ObjectId(id);
        } catch (Exception e) {
            //objectid incorrecte on revoit un 400
            return ResponseEntity.badRequest().build();
        }
        Optional<Genre> genre = this._genreRepository.findById(oId);
        if (!genre.isPresent()) {
            //genre non trouvé, on renvoit un 404
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genre.get());
    }

    //ajout ou mise ) jour d'un genre
    @PostMapping(value = "/Genres")
    public ResponseEntity<Genre> postGenre(@RequestBody Genre genre) {
        if(genre.getId() == null) {
            // on est en création
            genre = this._genreRepository.insert(genre);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(genre.getId())
                    .toUri();
            return ResponseEntity.created(location).body(genre);
        } else {
            // on est en modification
            ObjectId oId = new ObjectId(genre.getId());
            if(this._genreRepository.existsById(oId)) {
                this._genreRepository.save(genre);
                return ResponseEntity.ok(genre);
            }
            // genre non trouvé
            return ResponseEntity.notFound().build();
        }
    }

    //supression d'un genre
    @DeleteMapping(value = "/Genres")
    public ResponseEntity suprimerGenre(@RequestBody String id) {
        ObjectId oId;
        try {
            oId = new ObjectId(id);
        } catch (Exception e) {
            //objectid incorrecte on revoit un 400
            return ResponseEntity.badRequest().build();
        }
        Optional<Genre> genre = this._genreRepository.findById(oId);
        if (!genre.isPresent()) {
            //genre non trouvé, on renvoit un 404
            return ResponseEntity.notFound().build();
        }
        this._genreRepository.deleteById(new ObjectId(id));
        return ResponseEntity.ok().build();
    }
}

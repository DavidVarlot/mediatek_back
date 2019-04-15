package com.testDVA.mediatekMongo.web.controller;
import com.testDVA.mediatekMongo.model.Film;
import com.testDVA.mediatekMongo.model.FilmRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**********************************
 *
 * API de manipulation des Films
 *
 **********************************/

@CrossOrigin
@RestController
public class FilmController {

    private ArrayList<Film> listFilm = new ArrayList<Film>();
    @Autowired
    private FilmRepository _filmRepository;

    // on récupère tous les films
    @GetMapping(value = "/Films")
    public ResponseEntity<List<Film>> getAllFilm() {
        return ResponseEntity.ok(this._filmRepository.findAll(Sort.by("titre")));
    }

    // on récupère un film par son ID
    @GetMapping(value = "/Films/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable String id) {
        ObjectId oId;
        try {
            oId = new ObjectId(id);
        } catch (Exception e) {
            //objectid incorrecte on revoit un 400
            return ResponseEntity.badRequest().build();
        }
        Optional<Film> film = this._filmRepository.findById(oId);
        if (!film.isPresent()) {
            //film non trouvé, on renvoit un 404
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(film.get());
    }

    // on récupère tous les films du genre passé en paramètre
    @GetMapping(value = "/Films/Genre/{idGenre}")
    public ResponseEntity<List<Film>> getFilmsByGenre(@PathVariable String idGenre) {
        ObjectId GenreOId;
        try {
            GenreOId = new ObjectId(idGenre);
        } catch (Exception e) {
            //objectid incorrecte on revoit un 400
            return ResponseEntity.badRequest().build();
        }
        List<Film> films = this._filmRepository.findByGenreId(GenreOId);
        return ResponseEntity.ok(films);
    }

    // on compte le nombre de film d'un genre passé en paramètre
    @GetMapping(value = "/Films/Genre/{idGenre}/Count")
    public ResponseEntity countFilmsByGenre(@PathVariable String idGenre) {
        ObjectId GenreOId;
        try {
            GenreOId = new ObjectId(idGenre);
        } catch (Exception e) {
            //objectid incorrecte on revoit un 400
            return ResponseEntity.badRequest().build();
        }
       long nbFilms = this._filmRepository.countByGenreId(GenreOId);
        return ResponseEntity.ok(nbFilms);
    }

    // ajout ou mise à jour d'un film
    @PostMapping(value = "/Films")
    public ResponseEntity<Film> postFilm(@RequestBody Film film) {
        if(film.getId() == null) {
            // on est en création
            film = this._filmRepository.insert(film);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(film.getId())
                    .toUri();
            return ResponseEntity.created(location).body(film);
        } else {
            // on est en modification
            ObjectId oId = new ObjectId(film.getId());
            if(this._filmRepository.existsById(oId)) {
                this._filmRepository.save(film);
                return ResponseEntity.ok(film);
            }
            // film non trouvé
            return ResponseEntity.notFound().build();
        }
    }

    //suppression d'un film
    @DeleteMapping(value = "/Films")
    public ResponseEntity suprimerProduit(@RequestBody String id) {
        ObjectId oId;
        try {
            oId = new ObjectId(id);
        } catch (Exception e) {
            //objectid incorrecte on revoit un 400
            return ResponseEntity.badRequest().build();
        }
        Optional<Film> film = this._filmRepository.findById(oId);
        if (!film.isPresent()) {
            //film non trouvé, on renvoit un 404
            return ResponseEntity.notFound().build();
        }
        this._filmRepository.deleteById(new ObjectId(id));
        return ResponseEntity.ok().build();
    }
}

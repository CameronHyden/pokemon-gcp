package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.Integer.TYPE;
import static java.lang.Integer.parseInt;

@RestController
public class PokemonController {

    @Autowired
    PokemonRepository repository;

    @Autowired
    PokemonGetMoveService PokemonService;

    @PostMapping("/pokemon")
    public ResponseEntity<String> createPokemon(@RequestBody Pokemon pokemon) {
        repository.save(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body("pokemon added");
    }

    @GetMapping("/pokemonAll")
    public ResponseEntity<List<Pokemon>> getAllPokemon() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @GetMapping("/pokemon/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable String id) {
        for (Pokemon pokemon : repository.findAll()) {
            if (pokemon.getId() == parseInt(id)) {
                return ResponseEntity.status(HttpStatus.OK).body(pokemon);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/pokemon/{id}")
    public ResponseEntity<String> deleteGreeting(@PathVariable String id) {
        try {
            repository.deleteById(parseInt(id));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("you killed a pokemon :( with the id of" + id);
        } catch (EmptyResultDataAccessException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No pokemon were harmed");
        }
    }

    @GetMapping("/pokemon/moves")
    public ResponseEntity<List<String>> getPokemonMove() {
        List<String> moves = PokemonService.getMoveService((repository.findAll()));
        return ResponseEntity.status(HttpStatus.OK).body(moves);
    }
}
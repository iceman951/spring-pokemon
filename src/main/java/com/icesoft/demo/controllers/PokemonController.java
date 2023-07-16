package com.icesoft.demo.controllers;

import org.springframework.web.bind.annotation.*;

import com.icesoft.demo.models.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
    private List<Pokemon> pokemons = new ArrayList<>();
    private int nextId = 1;

    public PokemonController() {
        pokemons.addAll(List.of(
            new Pokemon(1, "Bulbasaur", 1, "Grass"),
            new Pokemon(2, "Ivysaur", 2, "Grass"),
            new Pokemon(3, "Venusaur", 3, "Grass"),
            new Pokemon(4, "Charmander", 1, "Fire"),
            new Pokemon(5, "Charmeleon", 2, "Fire"),
            new Pokemon(6, "Charizard", 3, "Fire")
        ));
        this.nextId = this.nextId + pokemons.size();
        
    }

    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemons;
    }

    @PostMapping
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        pokemon.setId(nextId++);
        pokemons.add(pokemon);
        return pokemon;
    }

    @GetMapping("/{id}")
    public Pokemon getPokemonById(@PathVariable int id) {

        Optional<Pokemon> optionalPokemon = pokemons.stream()
                .filter(p -> Integer.toString(p.getId()).equals(id))
                .findFirst();
                System.out.println("optionalPokemon: " + optionalPokemon);
        if (optionalPokemon.isPresent()) {
            return optionalPokemon.get();
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public Pokemon updatePokemon(@PathVariable int id, @RequestBody Pokemon updatedPokemon) {
        Optional<Pokemon> optionalPokemon = pokemons.stream()
                .filter(p -> Integer.toString(p.getId()).equals(id))
                .findFirst();
        if (optionalPokemon.isPresent()) {
            Pokemon pokemon = optionalPokemon.get();
            pokemon.setName(updatedPokemon.getName());
            pokemon.setLevel(updatedPokemon.getLevel());
            pokemon.setType(updatedPokemon.getType());
            return pokemon;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deletePokemon(@PathVariable int id) {
        boolean removed = pokemons.removeIf(p -> Integer.toString(p.getId()).equals(id));
        if (removed) {
            return "Pokemon with id " + id + " was removed";
        } else {
            return "Pokemon with id " + id + " was not found";
        }
    }
}

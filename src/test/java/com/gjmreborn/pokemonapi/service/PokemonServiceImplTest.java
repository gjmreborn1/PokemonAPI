package com.gjmreborn.pokemonapi.service;

import com.gjmreborn.pokemonapi.model.AttackPokemon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PokemonServiceImplTest {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonServiceImplTest(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Test
    void getPokemonDamageMultiplier() {
        Optional<Double> opt1 = pokemonService.getPokemonDamageMultiplier(new AttackPokemon("fire", List.of("grass")));
        assertEquals(2.0, opt1.get());

        Optional<Double> opt2 = pokemonService.getPokemonDamageMultiplier(new AttackPokemon("fighting", List.of("ice", "rock")));
        assertEquals(4.0, opt2.get());

        Optional<Double> opt3 = pokemonService.getPokemonDamageMultiplier(new AttackPokemon("psychic", List.of("poison", "dark")));
        assertEquals(0.0, opt3.get());

        Optional<Double> opt4 = pokemonService.getPokemonDamageMultiplier(new AttackPokemon("water", List.of("normal")));
        assertEquals(1.0, opt4.get());

        Optional<Double> opt5 = pokemonService.getPokemonDamageMultiplier(new AttackPokemon("fire", List.of("rock")));
        assertEquals(0.5, opt5.get());
    }

    @Test
    void getPokemonDamageMultiplierNotFound() {
        Optional<Double> opt5 = pokemonService.getPokemonDamageMultiplier(new AttackPokemon("bad", List.of("bad")));

        assertTrue(opt5.isEmpty());
    }
}
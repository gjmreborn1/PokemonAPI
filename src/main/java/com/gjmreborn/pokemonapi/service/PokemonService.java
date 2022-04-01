package com.gjmreborn.pokemonapi.service;

import com.gjmreborn.pokemonapi.model.AttackPokemon;

import java.util.Optional;

public interface PokemonService {
    Optional<Double> getPokemonDamageMultiplier(AttackPokemon attackPokemon);
}

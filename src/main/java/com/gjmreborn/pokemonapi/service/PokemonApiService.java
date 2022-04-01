package com.gjmreborn.pokemonapi.service;

import org.json.JSONObject;

import java.util.Optional;

public interface PokemonApiService {
    Optional<JSONObject> getDamageRelationObjectForAttack(String attack);
}

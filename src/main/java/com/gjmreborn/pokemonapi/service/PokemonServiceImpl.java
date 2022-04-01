package com.gjmreborn.pokemonapi.service;

import com.gjmreborn.pokemonapi.model.AttackPokemon;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PokemonServiceImpl implements PokemonService {
    private static final Map<String, Double> DAMAGE_MULTIPLIERS = Map.of(
            "no_damage_to", 0.0,
            "half_damage_to", 0.5,
            "double_damage_to", 2.0
    );

    private final PokemonApiService pokemonApiService;

    @Autowired
    public PokemonServiceImpl(PokemonApiService pokemonApiService) {
        this.pokemonApiService = pokemonApiService;
    }

    @Override
    public Optional<Double> getPokemonDamageMultiplier(AttackPokemon attackPokemon) {
        String attack = attackPokemon.getAttack();
        List<String> pokemons = attackPokemon.getPokemons();
        Optional<JSONObject> damageRelationsOpt = pokemonApiService.getDamageRelationObjectForAttack(attack);

        if (damageRelationsOpt.isEmpty()) {
            return Optional.empty();
        }

        double damageMultiplierValue = 1;
        JSONObject damageRelations = damageRelationsOpt.get();
        for (String pokemon : pokemons) {
            for (Map.Entry<String, Double> damageMultiplier : DAMAGE_MULTIPLIERS.entrySet()) {
                Optional<Double> damageMultiplierValueOpt =
                        getDamageMultiplierByPokemonType(damageRelations, damageMultiplier.getKey(), pokemon);

                if (damageMultiplierValueOpt.isPresent()) {
                    damageMultiplierValue *= damageMultiplierValueOpt.get();
                }
            }
        }
        return Optional.of(damageMultiplierValue);
    }

    private Optional<Double> getDamageMultiplierByPokemonType(JSONObject damageMultipliers,
                                                              String multiplierType, String pokemon) {
        JSONArray multipliersArray = damageMultipliers.getJSONArray(multiplierType);

        for (int i = 0; i < multipliersArray.length(); i++) {
            JSONObject pokemonObject = multipliersArray.getJSONObject(i);

            if (pokemonObject.getString("name").equals(pokemon)) {
                return Optional.of(DAMAGE_MULTIPLIERS.get(multiplierType));
            }
        }
        return Optional.empty();
    }
}

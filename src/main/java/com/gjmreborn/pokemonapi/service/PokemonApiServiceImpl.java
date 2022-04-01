package com.gjmreborn.pokemonapi.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.Body;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PokemonApiServiceImpl implements PokemonApiService {
    private static final int ATTACK_NOT_FOUND_HTTP_STATUS = 404;

    @Override
    public Optional<JSONObject> getDamageRelationObjectForAttack(String attack) {
        try {
            HttpResponse<String> jsonResponse = Unirest.get("https://pokeapi.co/api/v2/type/{attackType}")
                    .routeParam("attackType", attack)
                    .asString();

            if (jsonResponse.getStatus() == ATTACK_NOT_FOUND_HTTP_STATUS) {
                return Optional.empty();
            }

            return Optional.of(new JSONObject(jsonResponse.getBody())
                    .getJSONObject("damage_relations"));
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}

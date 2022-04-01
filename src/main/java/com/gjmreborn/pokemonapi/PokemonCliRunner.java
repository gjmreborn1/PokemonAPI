package com.gjmreborn.pokemonapi;

import com.gjmreborn.pokemonapi.model.AttackPokemon;
import com.gjmreborn.pokemonapi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;

@Component
@Profile("!test")
public class PokemonCliRunner implements CommandLineRunner {
    private final PokemonService pokemonApiService;

    @Autowired
    public PokemonCliRunner(PokemonService pokemonApiService) {
        this.pokemonApiService = pokemonApiService;
    }

    @Override
    public void run(String... args) throws IOException {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                AttackPokemon attackPokemon = getAttackPokemonFromStream(input);
                Optional<Double> damageMultiplier = pokemonApiService.getPokemonDamageMultiplier(attackPokemon);

                if (damageMultiplier.isPresent()) {
                    System.out.println(damageMultiplier.get() + "x");
                } else {
                    System.out.println("Unknown pokemon or attack type");
                }
            }
        }
    }

    private AttackPokemon getAttackPokemonFromStream(BufferedReader inputStream) throws IOException {
        AttackPokemon attackPokemon = new AttackPokemon();

        String line = inputStream.readLine();
        String[] tokens = line.split("->");

        if(tokens.length != 2) {
            throw new IllegalArgumentException("Bad input format");
        }

        attackPokemon.setAttack(tokens[0].trim());
        attackPokemon.setPokemons(Arrays.stream(tokens[1].trim().split(" ")).toList());

        return attackPokemon;
    }
}

package com.gjmreborn.pokemonapi.model;

import java.util.List;
import java.util.Objects;

public class AttackPokemon {
    private String attack;
    private List<String> pokemons;

    public AttackPokemon() {
    }

    public AttackPokemon(String attack, List<String> pokemons) {
        this.attack = attack;
        this.pokemons = pokemons;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public List<String> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<String> pokemons) {
        this.pokemons = pokemons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttackPokemon that = (AttackPokemon) o;
        return attack.equals(that.attack) && pokemons.equals(that.pokemons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attack, pokemons);
    }

    @Override
    public String toString() {
        return "AttackPokemon{" +
                "attack='" + attack + '\'' +
                ", pokemons=" + pokemons +
                '}';
    }
}

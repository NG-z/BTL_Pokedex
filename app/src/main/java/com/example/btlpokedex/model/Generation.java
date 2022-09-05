package com.example.btlpokedex.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.btlpokedex.dao.converters.IntegerConverters;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(tableName = "generation")
public class Generation implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @Ignore
    @SerializedName("pokemon_species")
    private List<PokemonSpecies> pokemon_species;

    @TypeConverters(IntegerConverters.class)
    private List<Integer> listPokeID;

    // Convert pokemon_species to list id
    public List<Integer> convertToListID(List<PokemonSpecies> pokemon_species) {
        List<Integer> list=new ArrayList<>();
        for(PokemonSpecies ps:pokemon_species)
            list.add(Integer.parseInt(ps.getUrl()
                    .replace("https://pokeapi.co/api/v2/pokemon-species/", "")
                    .replace("/","")));
        Collections.sort(list);
        return list;
    }

    public Generation() {
    }

    public Generation(int id, String name, List<Integer> listPokeID) {
        this.id = id;
        this.name = name;
        this.listPokeID = listPokeID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonSpecies> getPokemon_species() {
        return pokemon_species;
    }

    public void setPokemon_species(List<PokemonSpecies> pokemon_species) {
        this.pokemon_species = pokemon_species;
    }

    public List<Integer> getListPokeID() {
        return listPokeID;
    }

    public void setListPokeID(List<Integer> listPokeID) {
        this.listPokeID = listPokeID;
    }
}

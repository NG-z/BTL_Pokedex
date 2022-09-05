package com.example.btlpokedex.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.btlpokedex.model.Pokemon;

import java.util.List;

@Dao
public interface PokemonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPokemon(Pokemon pokemon);

    @Query("SELECT * FROM pokemon WHERE generation= :gen")
    List<Pokemon> getPokemonByGeneration(int gen);

    @Query("SELECT * FROM pokemon WHERE favorite=1")
    List<Pokemon> getPokemonFavorite();

    @Query(("SELECT * FROM pokemon WHERE name LIKE '%' || :name || '%'"))
    List<Pokemon> searchPokemonByName(String name);

    @Query(("SELECT * FROM pokemon WHERE generation= :gen AND name LIKE '%' || :name || '%'"))
    List<Pokemon> searchPokemonByGenByName(int gen, String name);
}

package com.example.btlpokedex.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.btlpokedex.model.Article;
import com.example.btlpokedex.model.Generation;
import com.example.btlpokedex.model.MoveDetail;
import com.example.btlpokedex.model.Pokemon;

@Database(entities = {Generation.class, Pokemon.class, MoveDetail.class, Article.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {
    private static final String DATABASE_NAME = "pokedex.db";
    private static RoomDB instance;

    public static synchronized RoomDB getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();

        return instance;
    }

    public abstract GenerationDAO generationDAO();

    public abstract PokemonDAO pokemonDAO();

    public abstract MoveDetailDAO moveDetailDAO();

    public abstract ArticleDAO articleDAO();
}

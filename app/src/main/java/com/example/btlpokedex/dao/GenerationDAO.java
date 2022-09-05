package com.example.btlpokedex.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.btlpokedex.model.Generation;

@Dao
public interface GenerationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGeneration(Generation generation);
}

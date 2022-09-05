package com.example.btlpokedex.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.btlpokedex.model.MoveDetail;

@Dao
public interface MoveDetailDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMoveDetail(MoveDetail moveDetail);

    @Query("SELECT * FROM move WHERE id= :id")
    MoveDetail getMoveDetail(int id);
}

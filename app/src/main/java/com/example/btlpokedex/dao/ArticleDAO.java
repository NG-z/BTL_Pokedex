package com.example.btlpokedex.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.btlpokedex.model.Article;

import java.util.List;

@Dao
public interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticle(Article article);

    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Query("DELETE FROM article")
    void deleteAll();
}

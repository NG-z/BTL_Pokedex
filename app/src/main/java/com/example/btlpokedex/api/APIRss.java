package com.example.btlpokedex.api;

import com.example.btlpokedex.model.RSSFeed;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

public interface APIRss {
    String BASE_URL = "https://www.pokemon.com/us/pokemon-news/";

    APIRss apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(APIRss.class);

    @GET("rss")
    Call<RSSFeed> getRSSFeed();
}

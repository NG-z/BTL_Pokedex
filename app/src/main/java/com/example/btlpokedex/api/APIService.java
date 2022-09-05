package com.example.btlpokedex.api;

import com.example.btlpokedex.model.Generation;
import com.example.btlpokedex.model.MoveDetail;
import com.example.btlpokedex.model.Pokemon;
import com.example.btlpokedex.model.ResultsMove;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    String BASE_URL = "https://pokeapi.co/api/v2/";

    APIService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService.class);

    @GET("generation/{id}")
    Call<Generation> getGenaration(@Path("id") int id);

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int id);

    @GET("move?limit=1000")
    Call<ResultsMove> getAllMove();

    @GET("move/{id}")
    Call<MoveDetail> getMoveDetail(@Path("id") int id);
}

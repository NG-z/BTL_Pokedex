package com.example.btlpokedex.common;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

public class Common {
    public final static String BASE_URL_IMAGE_SMALL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    public final static String BASE_URL_IMAGE_BIG = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/";

    public static int getColorType(String name) {
        switch (name) {
            case "normal":
                return Color.parseColor("#A8A878");
            case "fire":
                return Color.parseColor("#F7786B");
            case "water":
                return Color.parseColor("#58ABF6");
            case "grass":
                return Color.parseColor("#2CDAB1");
            case "electric":
                return Color.parseColor("#FFCE4B");
            case "ice":
                return Color.parseColor("#98D8D8");
            case "fighting":
                return Color.parseColor("#C03028");
            case "poison":
                return Color.parseColor("#9F5BBA");
            case "ground":
                return Color.parseColor("#E0C068");
            case "flying":
                return Color.parseColor("#A890F0");
            case "psychic":
                return Color.parseColor("#F85888");
            case "bug":
                return Color.parseColor("#A8B820");
            case "rock":
                return Color.parseColor("#B8A038");
            case "ghost":
                return Color.parseColor("#7C538C");
            case "dark":
                return Color.parseColor("#705848");
            case "dragon":
                return Color.parseColor("#6C79DB");
            case "steel":
                return Color.parseColor("#B8B8D0");
            case "fairy":
                return Color.parseColor("#F0B6BC");
        }
        return 0;
    }

    public static int getFavoriteColor(boolean favorite) {
        if(favorite) return Color.parseColor("#FFFFFF");
        return Color.parseColor("#303943");
    }

    public static int sumListInteger(List<Integer> listInteger) {
        int sum = 0;
        for (Integer i : listInteger) sum += i;
        return sum;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }
}

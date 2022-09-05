package com.example.btlpokedex.dao.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class StringConverters {
    @TypeConverter
    public static String listToJson(List<String> strings) {
        return new Gson().toJson(strings);
    }

    @TypeConverter
    public static List<String> jsonToList(String strings) {
        return new Gson().fromJson(strings, new TypeToken<ArrayList<String>>() {
        }.getType());
    }
}

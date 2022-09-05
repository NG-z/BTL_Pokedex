package com.example.btlpokedex.dao.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class IntegerConverters {
    @TypeConverter
    public static String listToJson(List<Integer> integers) {
        return new Gson().toJson(integers);
    }

    @TypeConverter
    public static List<Integer> jsonToList(String integers) {
        return new Gson().fromJson(integers, new TypeToken<ArrayList<Integer>>() {
        }.getType());
    }
}

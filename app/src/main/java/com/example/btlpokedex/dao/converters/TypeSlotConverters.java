package com.example.btlpokedex.dao.converters;

import androidx.room.TypeConverter;

import com.example.btlpokedex.model.TypeSlot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class TypeSlotConverters {
    @TypeConverter
    public static String listToJson(List<TypeSlot> types) {
        return new Gson().toJson(types);
    }

    @TypeConverter
    public static List<TypeSlot> jsonToList(String types) {
        return new Gson().fromJson(types, new TypeToken<ArrayList<TypeSlot>>() {
        }.getType());
    }
}

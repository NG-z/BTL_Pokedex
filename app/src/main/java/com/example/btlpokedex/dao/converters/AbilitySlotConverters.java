package com.example.btlpokedex.dao.converters;

import androidx.room.TypeConverter;

import com.example.btlpokedex.model.AbilitySlot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class AbilitySlotConverters {
    @TypeConverter
    public static String listToJson(List<AbilitySlot> abilities) {
        return new Gson().toJson(abilities);
    }

    @TypeConverter
    public static List<AbilitySlot> jsonToList(String abilities) {
        return new Gson().fromJson(abilities, new TypeToken<ArrayList<AbilitySlot>>() {
        }.getType());
    }
}

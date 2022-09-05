package com.example.btlpokedex.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.btlpokedex.dao.converters.IntegerConverters;
import com.example.btlpokedex.dao.converters.AbilitySlotConverters;
import com.example.btlpokedex.dao.converters.StringConverters;
import com.example.btlpokedex.dao.converters.TypeSlotConverters;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "pokemon")
public class Pokemon implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    private int generation;

    @TypeConverters(TypeSlotConverters.class)
    @SerializedName("types")
    private List<TypeSlot> types;

    @TypeConverters(AbilitySlotConverters.class)
    @SerializedName("abilities")
    private List<AbilitySlot> abilities;

    @Ignore
    @SerializedName("held_items")
    private List<ItemSlot> held_items;

    @TypeConverters(StringConverters.class)
    private List<String> listItem;

    // Convert held items to list string
    public List<String> convertToListString(List<ItemSlot> held_items) {
        List<String> list=new ArrayList<>();
        for(ItemSlot is:held_items)
            list.add(is.getItem().getName().replace("-", " "));
        return list;
    }

    @Ignore
    @SerializedName("stats")
    private List<Stat> stats;

    @TypeConverters(IntegerConverters.class)
    private List<Integer> listStat;

    // Convert stats to list integer
    public List<Integer> convertToListInteger(List<Stat> stats) {
        List<Integer> list=new ArrayList<>();
        for(Stat s:stats)
            list.add(s.getBase_stat());
        return list;
    }

    @Ignore
    @SerializedName("moves")
    private List<MoveSlot> moves;

    @TypeConverters(IntegerConverters.class)
    private List<Integer> listMove;

    // Convert moves to list id
    public List<Integer> convertToListID(List<MoveSlot> moves) {
        List<Integer> list=new ArrayList<>();
        for(MoveSlot ms:moves)
            list.add(Integer.parseInt(ms.getMove().getUrl()
                    .replace("https://pokeapi.co/api/v2/move/", "")
                    .replace("/","")));
        return list;
    }

    @SerializedName("height")
    private float height;

    @SerializedName("weight")
    private float weight;

    @SerializedName("base_experience")
    private int base_experience;

    private boolean favorite;

    public Pokemon() {
    }

    public Pokemon(int id, String name, int generation, List<TypeSlot> types, List<AbilitySlot> abilities, List<String> listItem, List<Integer> listStat, List<Integer> listMove, float height, float weight, int base_experience, boolean favorite) {
        this.id = id;
        this.name = name;
        this.generation = generation;
        this.types = types;
        this.abilities = abilities;
        this.listItem = listItem;
        this.listStat = listStat;
        this.listMove = listMove;
        this.height = height;
        this.weight = weight;
        this.base_experience = base_experience;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public List<TypeSlot> getTypes() {
        return types;
    }

    public void setTypes(List<TypeSlot> types) {
        this.types = types;
    }

    public List<AbilitySlot> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilitySlot> abilities) {
        this.abilities = abilities;
    }

    public List<ItemSlot> getHeld_items() {
        return held_items;
    }

    public void setHeld_items(List<ItemSlot> held_items) {
        this.held_items = held_items;
    }

    public List<String> getListItem() {
        return listItem;
    }

    public void setListItem(List<String> listItem) {
        this.listItem = listItem;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public List<Integer> getListStat() {
        return listStat;
    }

    public void setListStat(List<Integer> listStat) {
        this.listStat = listStat;
    }

    public List<MoveSlot> getMoves() {
        return moves;
    }

    public void setMoves(List<MoveSlot> moves) {
        this.moves = moves;
    }

    public List<Integer> getListMove() {
        return listMove;
    }

    public void setListMove(List<Integer> listMove) {
        this.listMove = listMove;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public class Stat implements Serializable {
        @SerializedName("base_stat")
        private int base_stat;

        public Stat() {
        }

        public Stat(int base_stat) {
            this.base_stat = base_stat;
        }

        public int getBase_stat() {
            return base_stat;
        }

        public void setBase_stat(int base_stat) {
            this.base_stat = base_stat;
        }
    }
}

package com.example.btlpokedex.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "move")
public class MoveDetail implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    private int id;

    private String name;

    @Ignore
    @SerializedName("damage_class")
    private DamageClass damage_class;

    private String category;

    @SerializedName("power")
    private String power;

    @SerializedName("pp")
    private String pp;

    @SerializedName("accuracy")
    private String accuracy;

    public MoveDetail() {

    }

    public MoveDetail(int id, String name, String category, String power, String pp, String accuracy) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.power = power;
        this.pp = pp;
        this.accuracy = accuracy;
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

    public DamageClass getDamage_class() {
        return damage_class;
    }

    public void setDamage_class(DamageClass damage_class) {
        this.damage_class = damage_class;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public class DamageClass implements Serializable {
        @SerializedName("name")
        private String name;

        public DamageClass() {
        }

        public DamageClass(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

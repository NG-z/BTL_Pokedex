package com.example.btlpokedex.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TypeSlot implements Serializable {
    @SerializedName("slot")
    private int slot;
    @SerializedName("type")
    private Type type;

    public TypeSlot() {
    }

    public TypeSlot(int slot, Type type) {
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public class Type implements Serializable {
        @SerializedName("name")
        private String name;

        public Type() {
        }

        public Type(String name) {
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



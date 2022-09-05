package com.example.btlpokedex.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemSlot implements Serializable {
    @SerializedName("item")
    private Item item;

    public ItemSlot() {
    }

    public ItemSlot(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public class Item implements Serializable {
        @SerializedName("name")
        private String name;

        public Item() {
        }

        public Item(String name) {
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

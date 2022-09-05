package com.example.btlpokedex.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MoveSlot implements Serializable {
    @SerializedName("move")
    private Move move;

    public MoveSlot() {
    }

    public MoveSlot(Move move) {
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public class Move implements Serializable {
        @SerializedName("name")
        private String name;

        @SerializedName("url")
        private String url;

        public Move() {
        }

        public Move(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

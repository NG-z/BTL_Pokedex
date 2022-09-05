package com.example.btlpokedex.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsMove {
    @SerializedName("results")
    private List<MoveSlot.Move> results;

    public ResultsMove() {
    }

    public ResultsMove(List<MoveSlot.Move> results) {
        this.results = results;
    }

    public List<MoveSlot.Move> getResult() {
        return results;
    }

    public void setResult(List<MoveSlot.Move> results) {
        this.results = results;
    }
}

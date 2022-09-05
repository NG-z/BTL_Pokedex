package com.example.btlpokedex.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AbilitySlot implements Serializable {
    @SerializedName("is_hidden")
    private boolean hidden;

    @SerializedName("ability")
    private Ability ability;

    public AbilitySlot() {
    }

    public AbilitySlot(boolean hidden, Ability ability) {
        this.hidden = hidden;
        this.ability = ability;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public class Ability implements Serializable {
        @SerializedName("name")
        private String name;

        public Ability() {
        }

        public Ability(String name) {
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

package com.example.btlpokedex.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btlpokedex.fragment.AboutFragment;
import com.example.btlpokedex.fragment.BaseStatFragment;
import com.example.btlpokedex.fragment.MoveFragment;
import com.example.btlpokedex.model.Pokemon;

public class DetailFragmentAdapter extends FragmentStatePagerAdapter {
    private int numPager = 3;
    private Pokemon pokemon;

    public DetailFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AboutFragment(pokemon);
            case 1:
                return new BaseStatFragment(pokemon);
            case 2:
                return new MoveFragment(pokemon);
            default:
                return new AboutFragment(pokemon);
        }
    }

    @Override
    public int getCount() {
        return numPager;
    }
}

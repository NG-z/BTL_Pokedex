package com.example.btlpokedex.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btlpokedex.R;
import com.example.btlpokedex.model.Pokemon;

public class AboutFragment extends Fragment {
    private TextView tvHeight, tvWeight, tvAbility, tvBaseEXP, tvHeldItems;
    private Pokemon pokemon;

    public AboutFragment(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setView();
    }

    private void initView(View view) {
        tvHeight = view.findViewById(R.id.tvHeight);
        tvWeight = view.findViewById(R.id.tvWeight);
        tvAbility = view.findViewById(R.id.tvAbility);
        tvBaseEXP = view.findViewById(R.id.tvBaseEXP);
        tvHeldItems = view.findViewById(R.id.tvHeldItems);
    }

    private void setView() {
        tvHeight.setText(pokemon.getHeight() / 10.0 + " m");

        tvWeight.setText(pokemon.getWeight() / 10.0 + " kg");

        String abilities = "";
        for (int i = 0; i < pokemon.getAbilities().size() - 1; i++)
            if (!pokemon.getAbilities().get(i).isHidden())
                abilities += "• " + pokemon.getAbilities().get(i).getAbility().getName().replace("-", " ") + "\n";
            else
                abilities += "• " + pokemon.getAbilities().get(i).getAbility().getName().replace("-", " ") + " (hidden)\n";
        if (!pokemon.getAbilities().get(pokemon.getAbilities().size() - 1).isHidden())
            abilities += "• " + pokemon.getAbilities().get(pokemon.getAbilities().size() - 1).getAbility().getName().replace("-", " ");
        else
            abilities += "• " + pokemon.getAbilities().get(pokemon.getAbilities().size() - 1).getAbility().getName().replace("-", " ") + " (hidden)";
        tvAbility.setText(abilities);

        tvBaseEXP.setText(pokemon.getBase_experience() + " EXP");

        if (!pokemon.getListItem().isEmpty()) {
            String held_items = "";
            for (int i = 0; i < pokemon.getListItem().size() - 1; i++)
                held_items += pokemon.getListItem().get(i) + "\n";
            held_items += pokemon.getListItem().get(pokemon.getListItem().size() - 1);
            tvAbility.setText(held_items);
        } else tvHeldItems.setText("None");
    }
}

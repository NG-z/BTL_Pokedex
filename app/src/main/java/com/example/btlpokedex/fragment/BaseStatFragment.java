package com.example.btlpokedex.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btlpokedex.R;
import com.example.btlpokedex.common.Common;
import com.example.btlpokedex.model.Pokemon;

public class BaseStatFragment extends Fragment {
    private TextView tvHP, tvAttack, tvDefense, tvSpAtk, tvSpDef, tvSpeed, tvTotal;
    private ProgressBar pbHP, pbAttack, pbDefense, pbSpAtk, pbSpDef, pbSpeed;
    private Pokemon pokemon;

    public BaseStatFragment(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_stat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setView();
    }

    private void initView(View view) {
        tvHP = view.findViewById(R.id.tvHP);
        tvAttack = view.findViewById(R.id.tvAttack);
        tvDefense = view.findViewById(R.id.tvDefense);
        tvSpAtk = view.findViewById(R.id.tvSpAtk);
        tvSpDef = view.findViewById(R.id.tvSpDef);
        tvSpeed = view.findViewById(R.id.tvSpeed);
        tvTotal = view.findViewById(R.id.tvTotal);
        pbHP = view.findViewById(R.id.pbHP);
        pbAttack = view.findViewById(R.id.pbAttack);
        pbDefense = view.findViewById(R.id.pbDefense);
        pbSpAtk = view.findViewById(R.id.pbSpAtk);
        pbSpDef = view.findViewById(R.id.pbSpDef);
        pbSpeed = view.findViewById(R.id.pbSpeed);
    }

    private void setView() {
        tvHP.setText(pokemon.getListStat().get(0) + "");
        tvAttack.setText(pokemon.getListStat().get(1) + "");
        tvDefense.setText(pokemon.getListStat().get(2) + "");
        tvSpAtk.setText(pokemon.getListStat().get(3) + "");
        tvSpDef.setText(pokemon.getListStat().get(4) + "");
        tvSpeed.setText(pokemon.getListStat().get(5) + "");
        tvTotal.setText(Common.sumListInteger(pokemon.getListStat()) + "");
        pbHP.setProgress((int) (pokemon.getListStat().get(0) / 255.0 * 100));
        pbAttack.setProgress((int) (pokemon.getListStat().get(1) / 255.0 * 100));
        pbDefense.setProgress((int) (pokemon.getListStat().get(2) / 255.0 * 100));
        pbSpAtk.setProgress((int) (pokemon.getListStat().get(3) / 255.0 * 100));
        pbSpDef.setProgress((int) (pokemon.getListStat().get(4) / 255.0 * 100));
        pbSpeed.setProgress((int) (pokemon.getListStat().get(5) / 255.0 * 100));
    }
}

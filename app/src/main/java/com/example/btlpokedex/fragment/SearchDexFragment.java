package com.example.btlpokedex.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.btlpokedex.R;
import com.example.btlpokedex.activity.PokedexActivity;
import com.example.btlpokedex.dao.RoomDB;
import com.example.btlpokedex.model.Pokemon;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchDexFragment extends BottomSheetDialogFragment {
    private SearchView searchPokemon;

    @Override
    public int getTheme() {
        return super.getTheme();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getDialog().setCanceledOnTouchOutside(true);

        initView(view);
        setView();
    }

    private void initView(View view) {
        searchPokemon = view.findViewById(R.id.searchPokemon);
    }

    private void setView() {
        GridLayoutManager managerLayoutPokedex = new GridLayoutManager(getContext(), 2);

        searchPokemon.requestFocusFromTouch();
        searchPokemon.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                PokedexActivity pokedexActivity = (PokedexActivity) getActivity();
                List<Pokemon> listPokemon = new ArrayList<>();
                listPokemon = RoomDB.getInstance(getContext()).pokemonDAO().searchPokemonByGenByName(pokedexActivity.getGen(), text);
                pokedexActivity.setListPokemon(listPokemon);
                return true;
            }
        });
    }
}

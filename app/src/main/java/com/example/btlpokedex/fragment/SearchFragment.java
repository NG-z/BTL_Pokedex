package com.example.btlpokedex.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlpokedex.R;
import com.example.btlpokedex.activity.DetailActivity;
import com.example.btlpokedex.adapter.PokedexAdapter;
import com.example.btlpokedex.dao.RoomDB;
import com.example.btlpokedex.model.Pokemon;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BottomSheetDialogFragment implements PokedexAdapter.ItemPokemonListener {
    private SearchView searchPokemon;
    private RecyclerView rvSearch;
    private PokedexAdapter pokedexAdapter;
    private List<Pokemon> listPokemon = new ArrayList<>();

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
        rvSearch = view.findViewById(R.id.rvSearch);
    }

    private void setView() {
        GridLayoutManager managerLayoutPokedex = new GridLayoutManager(getContext(), 2);
        pokedexAdapter = new PokedexAdapter(getContext(), listPokemon);
        rvSearch.setAdapter(pokedexAdapter);
        rvSearch.setLayoutManager(managerLayoutPokedex);
        pokedexAdapter.setItemPokemonListener(this);

        searchPokemon.requestFocusFromTouch();
        searchPokemon.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listPokemon = RoomDB.getInstance(getContext()).pokemonDAO().searchPokemonByName(query);
                if (listPokemon.size() == 0)
                    Toast.makeText(getContext(), "No result...", Toast.LENGTH_SHORT).show();
                pokedexAdapter.setListPokemon(listPokemon);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                return false;
            }
        });
    }

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("pokemon", listPokemon.get(postion));
        startActivity(intent);
    }
}

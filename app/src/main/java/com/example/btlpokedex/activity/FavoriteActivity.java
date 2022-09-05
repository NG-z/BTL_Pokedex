package com.example.btlpokedex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlpokedex.R;
import com.example.btlpokedex.adapter.PokedexAdapter;
import com.example.btlpokedex.common.Common;
import com.example.btlpokedex.dao.RoomDB;
import com.example.btlpokedex.fragment.SearchDexFragment;
import com.example.btlpokedex.model.Pokemon;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity implements PokedexAdapter.ItemPokemonListener {
    private RecyclerView rvPokedex;
    private PokedexAdapter pokedexAdapter;
    private SpeedDialView fabFilter;
    private Button btBack;
    private TextView tvTitle, tvQuantity;
    private List<Pokemon> listPokemon = new ArrayList<>();
    private boolean sortName = true, sortStat = true;

    public void setListPokemon(List<Pokemon> listPokemon) {
        this.listPokemon = listPokemon;
        this.pokedexAdapter.setListPokemon(listPokemon);
        this.tvQuantity.setText("Search result: " + listPokemon.size());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);
        initView();
        setView();
    }

    private void initView() {
        rvPokedex = findViewById(R.id.rvPokedex);
        fabFilter = findViewById(R.id.fabFilter);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvTitle = findViewById(R.id.tvTitle);
        btBack = findViewById(R.id.btBack);
    }

    private void setView() {
        listPokemon = RoomDB.getInstance(this).pokemonDAO().getPokemonFavorite();

        tvTitle.setText("My Favorite");
        tvQuantity.setText(listPokemon.size() + " Pokemon");
        fabFilter.inflate(R.menu.filter_menu);

        GridLayoutManager managerLayoutPokedex = new GridLayoutManager(this, 2);
        pokedexAdapter = new PokedexAdapter(this, listPokemon);
        rvPokedex.setAdapter(pokedexAdapter);
        rvPokedex.setLayoutManager(managerLayoutPokedex);
        pokedexAdapter.setItemPokemonListener(this);

        fabFilter.removeActionItemById(R.id.mSearch);
        fabFilter.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                switch (actionItem.getId()) {
                    case R.id.mRefresh:
                        listPokemon = RoomDB.getInstance(FavoriteActivity.this).pokemonDAO().getPokemonFavorite();
                        pokedexAdapter.setListPokemon(listPokemon);
                        tvQuantity.setText(listPokemon.size() + " Pokemon");
                        break;
                    case R.id.mName:
                        if (sortName) Collections.sort(listPokemon, new Comparator<Pokemon>() {
                            @Override
                            public int compare(Pokemon p1, Pokemon p2) {
                                return p1.getName().compareToIgnoreCase(p2.getName());
                            }
                        });
                        else Collections.sort(listPokemon, new Comparator<Pokemon>() {
                            @Override
                            public int compare(Pokemon p1, Pokemon p2) {
                                return p2.getName().compareToIgnoreCase(p1.getName());
                            }
                        });
                        sortName = !sortName;
                        pokedexAdapter.setListPokemon(listPokemon);
                        break;
                    case R.id.mStat:
                        if (sortStat) Collections.sort(listPokemon, new Comparator<Pokemon>() {
                            @Override
                            public int compare(Pokemon p1, Pokemon p2) {
                                int t1 = Common.sumListInteger(p1.getListStat());
                                int t2 = Common.sumListInteger(p2.getListStat());
                                return t1 < t2 ? -1 : t1 == t2 ? 0 : 1;
                            }
                        });
                        else Collections.sort(listPokemon, new Comparator<Pokemon>() {
                            @Override
                            public int compare(Pokemon p1, Pokemon p2) {
                                int t1 = Common.sumListInteger(p1.getListStat());
                                int t2 = Common.sumListInteger(p2.getListStat());
                                return t2 < t1 ? -1 : t2 == t1 ? 0 : 1;
                            }
                        });
                        sortStat = !sortStat;
                        pokedexAdapter.setListPokemon(listPokemon);
                        break;
                }
                return true;
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listPokemon = RoomDB.getInstance(FavoriteActivity.this).pokemonDAO().getPokemonFavorite();
        pokedexAdapter.setListPokemon(listPokemon);
        tvQuantity.setText(listPokemon.size() + " Pokemon");
    }

    @Override
    public void onItemClick(View view, int postion) {
        fabFilter.close();

        Intent intent = new Intent(FavoriteActivity.this, DetailActivity.class);
        intent.putExtra("pokemon", listPokemon.get(postion));
        startActivity(intent);
    }
}

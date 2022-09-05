package com.example.btlpokedex.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.cache.DiskCache;
import com.example.btlpokedex.R;
import com.example.btlpokedex.adapter.MenuAdapter;
import com.example.btlpokedex.api.APIService;
import com.example.btlpokedex.task.CacheSizeTask;
import com.example.btlpokedex.common.Common;
import com.example.btlpokedex.dao.RoomDB;
import com.example.btlpokedex.fragment.NewsFragment;
import com.example.btlpokedex.fragment.SearchFragment;
import com.example.btlpokedex.model.AbilitySlot;
import com.example.btlpokedex.model.Generation;
import com.example.btlpokedex.model.MoveDetail;
import com.example.btlpokedex.model.MoveSlot;
import com.example.btlpokedex.model.Pokemon;
import com.example.btlpokedex.model.ResultsMove;
import com.example.btlpokedex.model.TypeSlot;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MenuAdapter.ItemMenuListener {
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private RecyclerView recyclerViewMenu;
    private Toolbar toolBar;
    private MenuAdapter menuAdapter;
    private RoomDB roomDB;
    private ProgressBar progressBar;
    private TextView tvViewAll;
    List<Pokemon> listFavorite;
    private int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setView();

        if (Common.isOnline(getApplicationContext())) {
            showDialogFetch();
        } else {
            Toast.makeText(this, "No internet, using local data!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);
        recyclerViewMenu = findViewById(R.id.recyclerViewMenu);
        progressBar = findViewById(R.id.progressBar);
        toolBar = findViewById(R.id.toolBar);
        tvViewAll = findViewById(R.id.tvViewAll);
    }

    private void setView() {
        menuAdapter = new MenuAdapter();
        GridLayoutManager managerLayoutMenu = new GridLayoutManager(this, 2);
        recyclerViewMenu.setAdapter(menuAdapter);
        recyclerViewMenu.setLayoutManager(managerLayoutMenu);
        menuAdapter.setItemMenuListener(this);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mFavorite) {
                    Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.mContact) {
                    Intent email = new Intent(Intent.ACTION_SENDTO);
                    email.setData(Uri.parse("mailto:" + "nguyengiangcv@gmail.com"));
                    startActivity(email);
                }
                if (item.getItemId() == R.id.mCache) {
                    CacheSizeTask cacheSizeTask = new CacheSizeTask(MainActivity.this);
                    cacheSizeTask.execute(new File(getCacheDir(), DiskCache.Factory.DEFAULT_DISK_CACHE_DIR));
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        toolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragment searchFragment = new SearchFragment();
                searchFragment.show(getSupportFragmentManager(), "SearchInMain");
            }
        });

        tvViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsFragment newsFragment = new NewsFragment();
                newsFragment.show(getSupportFragmentManager(), "newsInMain");
            }
        });
    }

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent(MainActivity.this, PokedexActivity.class);
        intent.putExtra("generation", postion + 1);
        startActivity(intent);
    }

    public void showDialogFetch() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Detect internet connection");
        builder.setMessage("Do you want to refresh data from internet?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listFavorite = RoomDB.getInstance(MainActivity.this).pokemonDAO().getPokemonFavorite();
                fetchGeneration();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(getApplicationContext(), "Refeshing data from internet...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Using local data!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void fetchGeneration() {
        for (int i = 1; i <= 8; i++) {
            APIService.apiService.getGenaration(i).enqueue(new Callback<Generation>() {
                @Override
                public void onResponse(Call<Generation> call, Response<Generation> response) {
                    if (response.isSuccessful()) {
                        int id = response.body().getId();
                        String name = response.body().getName();
                        List<Integer> listPokemonID = new ArrayList<>(response.body().convertToListID(response.body().getPokemon_species()));
                        Generation generation = new Generation(id, name, listPokemonID);
                        RoomDB.getInstance(MainActivity.this).generationDAO().insertGeneration(generation);

                        fetchPokemon(generation);
                    }
                }

                @Override
                public void onFailure(Call<Generation> call, Throwable t) {

                }
            });
        }
    }

    private void fetchPokemon(Generation generation) {
        int gen = generation.getId();
        int lastPoke = generation.getListPokeID().get(generation.getListPokeID().size() - 1);
        for (int j : generation.getListPokeID()) {
            APIService.apiService.getPokemon(j).enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    if (response.isSuccessful()) {
                        int id = response.body().getId();
                        String name = response.body().getName().replace("-", " ");
                        List<TypeSlot> types = new ArrayList<>(response.body().getTypes());
                        List<AbilitySlot> abilities = new ArrayList<>(response.body().getAbilities());
                        List<String> listItem = new ArrayList<>(new Pokemon().convertToListString(response.body().getHeld_items()));
                        List<Integer> listStat = new ArrayList<>(new Pokemon().convertToListInteger(response.body().getStats()));
                        List<Integer> listMove = new ArrayList<>(new Pokemon().convertToListID(response.body().getMoves()));
                        float height = response.body().getHeight();
                        float weight = response.body().getWeight();
                        int base_experience = response.body().getBase_experience();

                        Pokemon pokemon = new Pokemon(id, name, gen, types, abilities, listItem, listStat, listMove, height, weight, base_experience, false);
                        RoomDB.getInstance(MainActivity.this).pokemonDAO().insertPokemon(pokemon);

                        // check when fetch pokemon finish
                        if (id == lastPoke) {
                            check++;
                            progressBar.setProgress(10 * check);
                        }
                        if (check == 8) {
                            fetchMove();
                            for (Pokemon p : listFavorite)
                                RoomDB.getInstance(MainActivity.this).pokemonDAO().insertPokemon(p);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {

                }
            });
        }
    }

    public void fetchMove() {
        APIService.apiService.getAllMove().enqueue(new Callback<ResultsMove>() {
            @Override
            public void onResponse(Call<ResultsMove> call, Response<ResultsMove> response) {
                if (response.isSuccessful()) {
                    List<MoveSlot.Move> listMove = new ArrayList<>(response.body().getResult());
                    fetchMoveDetail(listMove);
                }
            }

            @Override
            public void onFailure(Call<ResultsMove> call, Throwable t) {

            }
        });
    }

    public void fetchMoveDetail(List<MoveSlot.Move> listMove) {
        int lastMove = Integer.parseInt(listMove.get(listMove.size() - 1).getUrl().replace("https://pokeapi.co/api/v2/move/", "").replace("/", ""));
        for (MoveSlot.Move m : listMove) {
            int id = Integer.parseInt(m.getUrl().replace("https://pokeapi.co/api/v2/move/", "").replace("/", ""));
            APIService.apiService.getMoveDetail(id).enqueue(new Callback<MoveDetail>() {
                @Override
                public void onResponse(Call<MoveDetail> call, Response<MoveDetail> response) {
                    if (response.isSuccessful()) {
                        int id = response.body().getId();
                        String name = response.body().getName().replace("-", " ");
                        String category = response.body().getDamage_class().getName();
                        String power = response.body().getPower();
                        String pp = response.body().getPp();
                        String accuracy = response.body().getAccuracy();
                        MoveDetail moveDetail = new MoveDetail(id, name, category, power, pp, accuracy);
                        RoomDB.getInstance(MainActivity.this).moveDetailDAO().insertMoveDetail(moveDetail);

                        // check when fetch move finish
                        if (id == lastMove) {
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressBar.setProgress(100);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MoveDetail> call, Throwable t) {

                }
            });
        }
    }
}
package com.example.btlpokedex.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.btlpokedex.R;
import com.example.btlpokedex.adapter.DetailFragmentAdapter;
import com.example.btlpokedex.common.Common;
import com.example.btlpokedex.dao.RoomDB;
import com.example.btlpokedex.model.Pokemon;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DetailFragmentAdapter detailFragmentAdapter;
    private ImageView imgPokemon;
    private TextView tvName, tvID, tvType1, tvType2;
    private Button btBack, btFavorite;
    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        setView();
    }

    private void initView() {
        appBarLayout = findViewById(R.id.appBarLayout);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        imgPokemon = findViewById(R.id.imgPokemon);
        tvName = findViewById(R.id.tvName);
        tvID = findViewById(R.id.tvID);
        tvType1 = findViewById(R.id.tvType1);
        tvType2 = findViewById(R.id.tvType2);
        btBack = findViewById(R.id.btBack);
        btFavorite = findViewById(R.id.btFavorite);
    }

    private void setView() {
        Intent intent = getIntent();
        pokemon = (Pokemon) intent.getSerializableExtra("pokemon");

        tvName.setText(pokemon.getName());
        tvID.setText("#" + pokemon.getId());
        btFavorite.setBackgroundTintList(ColorStateList.valueOf(Common.getFavoriteColor(pokemon.isFavorite())));
        if (pokemon.getTypes().size() == 1) {
            tvType1.setText(pokemon.getTypes().get(0).getType().getName());
            tvType2.setVisibility(View.INVISIBLE);
        } else {
            tvType1.setText(pokemon.getTypes().get(0).getType().getName());
            tvType2.setText(pokemon.getTypes().get(1).getType().getName());
        }
        Glide.with(this).load(Common.BASE_URL_IMAGE_BIG + pokemon.getId() + ".png").into(imgPokemon);
        appBarLayout.setBackgroundColor(Common.getColorType(pokemon.getTypes().get(0).getType().getName()));

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemon.setFavorite(!pokemon.isFavorite());
                RoomDB.getInstance(DetailActivity.this).pokemonDAO().insertPokemon(pokemon);
                btFavorite.setBackgroundTintList(ColorStateList.valueOf(Common.getFavoriteColor(pokemon.isFavorite())));
                if (pokemon.isFavorite())
                    Toast.makeText(DetailActivity.this, "Add " + pokemon.getName() + " to favorite list!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DetailActivity.this, "Remove " + pokemon.getName() + " form favorite list!", Toast.LENGTH_SHORT).show();
            }
        });

        detailFragmentAdapter = new DetailFragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        detailFragmentAdapter.setPokemon(pokemon);
        viewPager.setAdapter(detailFragmentAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("About");
        tabLayout.getTabAt(1).setText("Base Stat");
        tabLayout.getTabAt(2).setText("Move");
    }
}
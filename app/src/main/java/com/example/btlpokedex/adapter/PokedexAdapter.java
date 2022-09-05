package com.example.btlpokedex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btlpokedex.R;
import com.example.btlpokedex.common.Common;
import com.example.btlpokedex.model.Pokemon;

import java.util.List;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder> {
    private Context context;
    private List<Pokemon> listPokemon;
    private ItemPokemonListener itemPokemonListener;

    public PokedexAdapter(Context context, List<Pokemon> listPokemon) {
        this.context = context;
        this.listPokemon = listPokemon;
    }

    public void setListPokemon(List<Pokemon> listPokemon) {
        this.listPokemon = listPokemon;
        notifyDataSetChanged();
    }

    public void setItemPokemonListener(ItemPokemonListener itemPokemonListener) {
        this.itemPokemonListener = itemPokemonListener;
    }

    @NonNull
    @Override
    public PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokedexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexViewHolder holder, int position) {
        Pokemon pokemon = listPokemon.get(position);
        holder.tvID.setText("#" + pokemon.getId());
        holder.tvName.setText(pokemon.getName());
        if (pokemon.getTypes().size() == 1) {
            holder.tvType2.setText(pokemon.getTypes().get(0).getType().getName());
            holder.tvType1.setVisibility(View.INVISIBLE);
        } else {
            holder.tvType2.setText(pokemon.getTypes().get(0).getType().getName());
            holder.tvType1.setText(pokemon.getTypes().get(1).getType().getName());
        }
        Glide.with(context).load(Common.BASE_URL_IMAGE_SMALL + pokemon.getId() + ".png").into(holder.imgPokemon);
        holder.cardItemPokemon.setCardBackgroundColor(Common.getColorType(pokemon.getTypes().get(0).getType().getName()));
    }

    @Override
    public int getItemCount() {
        return listPokemon.size();
    }

    public class PokedexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName, tvID, tvType1, tvType2;
        private ImageView imgPokemon;
        private CardView cardItemPokemon;

        public PokedexViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvID = itemView.findViewById(R.id.tvID);
            tvType1 = itemView.findViewById(R.id.tvType1);
            tvType2 = itemView.findViewById(R.id.tvType2);
            imgPokemon = itemView.findViewById(R.id.imgPokemon);
            cardItemPokemon = itemView.findViewById(R.id.cardItemPokemon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemPokemonListener != null)
                itemPokemonListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemPokemonListener {
        void onItemClick(View view, int postion);
    }
}

package com.example.btlpokedex.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlpokedex.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private String[] colorList = {"#58ABF6", "#CA8179", "#9F5BBA", "#F7786B", "#2CDAB1", "#FFCE4B", "#6C79DB", "#4FC1A6"};
    private ItemMenuListener itemMenuListener;

    public void setItemMenuListener(ItemMenuListener itemMenuListener) {
        this.itemMenuListener = itemMenuListener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.tvItemMenu.setText("Generation " + (position + 1));
        holder.cardItemMenu.setCardBackgroundColor(Color.parseColor(colorList[position]));
    }

    @Override
    public int getItemCount() {
        return colorList.length;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvItemMenu;
        private CardView cardItemMenu;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemMenu = itemView.findViewById(R.id.tvItemMenu);
            cardItemMenu = itemView.findViewById(R.id.cardtemMenu);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemMenuListener != null) itemMenuListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemMenuListener {
        void onItemClick(View view, int postion);
    }
}

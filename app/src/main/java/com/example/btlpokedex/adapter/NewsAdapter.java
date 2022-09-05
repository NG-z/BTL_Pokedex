package com.example.btlpokedex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btlpokedex.R;
import com.example.btlpokedex.model.Article;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    private List<Article> listArticle;
    private ItemNewsListener itemNewsListener;

    public NewsAdapter(Context context, List<Article> listArticle) {
        this.context = context;
        this.listArticle = listArticle;
    }

    public void setItemNewsListener(ItemNewsListener itemNewsListener) {
        this.itemNewsListener = itemNewsListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = listArticle.get(position);
        Glide.with(context).load(article.getImage()).into(holder.imgArticle);
        holder.tvTitle.setText(article.getTitle());
        holder.tvTime.setText(article.getPubDate());
    }

    @Override
    public int getItemCount() {
        return listArticle.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgArticle;
        private TextView tvTitle, tvTime;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgArticle = itemView.findViewById(R.id.imgArticle);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTime = itemView.findViewById(R.id.tvTime);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemNewsListener != null) itemNewsListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemNewsListener {
        void onItemClick(View view, int postion);
    }
}

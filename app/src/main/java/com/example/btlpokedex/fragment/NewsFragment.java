package com.example.btlpokedex.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlpokedex.R;
import com.example.btlpokedex.activity.ArticleActivity;
import com.example.btlpokedex.adapter.NewsAdapter;
import com.example.btlpokedex.api.APIRss;
import com.example.btlpokedex.common.Common;
import com.example.btlpokedex.dao.RoomDB;
import com.example.btlpokedex.model.Article;
import com.example.btlpokedex.model.RSSFeed;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends BottomSheetDialogFragment implements NewsAdapter.ItemNewsListener {
    private TextView tvNoInternet;
    private RecyclerView rvNews;
    private NewsAdapter newsAdapter;
    private List<Article> listArticle = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        if (Common.isOnline(getContext())) {
            fetchArticle();
        } else tvNoInternet.setVisibility(View.VISIBLE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().setCanceledOnTouchOutside(true);
    }

    private void initView(View view) {
        tvNoInternet = view.findViewById(R.id.tvNoInternet);
        rvNews = view.findViewById(R.id.rvNews);
    }

    private void setView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listArticle = RoomDB.getInstance(getContext()).articleDAO().getAll();
        newsAdapter = new NewsAdapter(getContext(), listArticle);
        rvNews.setLayoutManager(linearLayoutManager);
        rvNews.setAdapter(newsAdapter);
        newsAdapter.setItemNewsListener(this);
    }

    public void fetchArticle() {
        RoomDB.getInstance(getContext()).articleDAO().deleteAll();
        APIRss.apiService.getRSSFeed().enqueue(new Callback<RSSFeed>() {
            @Override
            public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
                if (response.isSuccessful()) {
                    for (Article a : response.body().getArticleList()) {
                        String title = a.getTitle().replaceAll("<.*?>" , " ");
                        String link = a.getLink();
                        String pubDate = a.getPubDate().replace(" 07:00:00 -0400", "");
                        String image = a.getThumbnail().getUrl();
                        Article article = new Article(title, link, pubDate, image);
                        RoomDB.getInstance(getContext()).articleDAO().insertArticle(article);
                    }
                    setView();
                }
            }

            @Override
            public void onFailure(Call<RSSFeed> call, Throwable t) {
            }
        });
    }

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent(getContext(), ArticleActivity.class);
        intent.putExtra("link", listArticle.get(postion).getLink());
        startActivity(intent);
    }
}

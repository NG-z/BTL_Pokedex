package com.example.btlpokedex.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlpokedex.R;
import com.example.btlpokedex.adapter.MoveAdapter;
import com.example.btlpokedex.dao.RoomDB;
import com.example.btlpokedex.model.MoveDetail;
import com.example.btlpokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class MoveFragment extends Fragment {
    private RecyclerView rvMove;
    private MoveAdapter moveAdapter;
    private Pokemon pokemon;

    public MoveFragment(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_move, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setView();
    }

    private void initView(View view) {
        rvMove = view.findViewById(R.id.rvMove);
    }

    private void setView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        List<MoveDetail> listMoveDetail = new ArrayList<>();
        for (Integer i : pokemon.getListMove())
            listMoveDetail.add(RoomDB.getInstance(getContext()).moveDetailDAO().getMoveDetail(i));
        moveAdapter = new MoveAdapter(listMoveDetail);
        rvMove.setLayoutManager(manager);
        rvMove.setAdapter(moveAdapter);
    }
}

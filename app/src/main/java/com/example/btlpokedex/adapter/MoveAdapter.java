package com.example.btlpokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlpokedex.R;
import com.example.btlpokedex.model.MoveDetail;

import java.util.List;

public class MoveAdapter extends RecyclerView.Adapter<MoveAdapter.MoveViewHolder> {
    private List<MoveDetail> listMoveDetail;

    public MoveAdapter(List<MoveDetail> listMoveDetail) {
        this.listMoveDetail = listMoveDetail;
    }

    @NonNull
    @Override
    public MoveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_move, parent, false);
        return new MoveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoveViewHolder holder, int position) {
        MoveDetail moveDetail = listMoveDetail.get(position);
        if (moveDetail.getName() != null) holder.tvName.setText(moveDetail.getName());
        else holder.tvName.setText("-");

        if (moveDetail.getPower() != null) holder.tvPower.setText(moveDetail.getPower());
        else holder.tvPower.setText("-");

        if (moveDetail.getPp() != null) holder.tvPP.setText(moveDetail.getPp());
        else holder.tvPP.setText("-");

        if (moveDetail.getAccuracy() != null) holder.tvAccuracy.setText(moveDetail.getAccuracy());
        else holder.tvAccuracy.setText("-");

        switch (moveDetail.getCategory()) {
            case "physical":
                holder.imgCategory.setImageResource(R.drawable.move_physical);
                break;
            case "special":
                holder.imgCategory.setImageResource(R.drawable.move_special);
                break;
            case "status":
                holder.imgCategory.setImageResource(R.drawable.move_status);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listMoveDetail.size();
    }

    public class MoveViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPower, tvPP, tvAccuracy;
        private ImageView imgCategory;

        public MoveViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPower = itemView.findViewById(R.id.tvPower);
            tvPP = itemView.findViewById(R.id.tvPP);
            tvAccuracy = itemView.findViewById(R.id.tvAccuracy);
            imgCategory = itemView.findViewById(R.id.imgCategory);
        }
    }
}

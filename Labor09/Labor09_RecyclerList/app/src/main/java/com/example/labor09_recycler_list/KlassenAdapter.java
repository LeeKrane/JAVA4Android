package com.example.labor09_recycler_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class KlassenAdapter extends RecyclerView.Adapter<KlassenAdapter.KlassenViewHolder> {
    private List<Klasse> klassen;
    private KlassenOnClickListener onClickListener;

    public KlassenAdapter(List<Klasse> klassen, KlassenOnClickListener onClickListener) {
        this.klassen = klassen;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public KlassenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new KlassenViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull KlassenViewHolder holder, int position) {
        holder.klassenName.setText(klassen.get(position).getName());
        holder.schuelerCount.setText(String.format(Locale.ENGLISH, "(%d)", klassen.get(position).getSchuelerList().size()));
    }

    @Override
    public int getItemCount() {
        return klassen.size();
    }

    public static class KlassenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView klassenName;
        TextView schuelerCount;
        KlassenOnClickListener onClickListener;

        public KlassenViewHolder(@NonNull View itemView, KlassenOnClickListener onClickListener) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_klassen);
            klassenName = itemView.findViewById(R.id.tv_klassenName);
            schuelerCount = itemView.findViewById(R.id.tv_schuelerCount);
            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(getAdapterPosition());
        }
    }
}

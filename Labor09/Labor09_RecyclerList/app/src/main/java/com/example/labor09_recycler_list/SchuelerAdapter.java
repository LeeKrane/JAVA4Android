package com.example.labor09_recycler_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SchuelerAdapter extends RecyclerView.Adapter<SchuelerAdapter.SchuelerViewHolder> {
    private List<Schueler> schueler;

    public SchuelerAdapter(List<Schueler> schueler) {
        this.schueler = schueler;
    }

    @NonNull
    @Override
    public SchuelerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schueler_item, parent, false);
        return new SchuelerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchuelerViewHolder holder, int position) {
        Schueler schueler = this.schueler.get(position);
        holder.katNr.setText(String.valueOf(schueler.getKatNr()));
        holder.vorname.setText(schueler.getVorname());
        holder.nachname.setText(schueler.getNachname());
        // TODO: Geschlecht Icon
    }

    @Override
    public int getItemCount() {
        return schueler.size();
    }

    public static class SchuelerViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView katNr;
        TextView vorname;
        TextView nachname;

        public SchuelerViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_klassen);
            katNr = itemView.findViewById(R.id.tv_katNr);
            vorname = itemView.findViewById(R.id.tv_vorname);
            nachname = itemView.findViewById(R.id.tv_nachname);
        }
    }
}

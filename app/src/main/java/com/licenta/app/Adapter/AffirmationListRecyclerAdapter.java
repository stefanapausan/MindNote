package com.licenta.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.licenta.app.AffirmationAddEditActivity;
import com.licenta.app.Model.Affirmation;
import com.licenta.app.R;

import java.util.ArrayList;
import java.util.Random;

public class AffirmationListRecyclerAdapter extends RecyclerView.Adapter<AffirmationListRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<Affirmation> affirmations;

    public AffirmationListRecyclerAdapter(Context context, ArrayList<Affirmation> affirmations) {
        this.mContext = context;
        this.affirmations = affirmations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_affirmation_item, parent, false);
        return new ViewHolder(v);
    }

    public String[] mColors = {
            "5BA477E8", "66E6EF8F", "66FFA6AD", "5693E6A6", "52FF87B4", "57B0E3FF", "5772E1DD",
            "5BA477E8", "66E6EF8F", "66FFA6AD", "5693E6A6", "52FF87B4", "57B0E3FF", "5772E1DD",
            "5BA477E8", "66E6EF8F", "66FFA6AD", "5693E6A6", "52FF87B4", "57B0E3FF", "5772E1DD",
            "5BA477E8", "66E6EF8F", "66FFA6AD", "5693E6A6", "52FF87B4", "57B0E3FF", "5772E1DD",
    };

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Affirmation currentAffirmation = affirmations.get(position);
        holder.tvAffirmationDate.setText(currentAffirmation.getDate());
        holder.tvAffirmationNote.setText(currentAffirmation.getNote());

        holder.tvAffirmationNote.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, AffirmationAddEditActivity.class);
            intent.putExtra("note", currentAffirmation.getNote());
            intent.putExtra("key", currentAffirmation.getKey());

            mContext.startActivity(intent);
        });

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor ("#"+mColors[new Random().nextInt(28)]));
        holder.tvAffirmationNote.setBackground(shape);
    }

    @Override
    public int getItemCount() {
        return affirmations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvAffirmationDate, tvAffirmationNote;

        public ViewHolder(View itemView) {
            super(itemView);
            tvAffirmationDate = itemView.findViewById(R.id.tv_affirmation_date);
            tvAffirmationNote = itemView.findViewById(R.id.tv_affirmation_note);
        }
    }

}

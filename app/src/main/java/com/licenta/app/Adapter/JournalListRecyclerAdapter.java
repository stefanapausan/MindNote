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

import com.licenta.app.JournalAddEditActivity;
import com.licenta.app.Model.Journal;
import com.licenta.app.R;

import java.util.ArrayList;
import java.util.Random;

public class JournalListRecyclerAdapter extends RecyclerView.Adapter<JournalListRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<Journal> journals;

    public JournalListRecyclerAdapter(Context context, ArrayList<Journal> posProductItems) {
        this.mContext = context;
        this.journals = posProductItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_journal_item, parent, false);
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
        Journal currentJournal = journals.get(position);
        holder.tvJournalDate.setText(currentJournal.getDate());
        holder.tvJournalNote.setText(currentJournal.getNote());

        holder.tvJournalNote.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, JournalAddEditActivity.class);
            intent.putExtra("note", currentJournal.getNote());
            intent.putExtra("key", currentJournal.getKey());
            intent.putExtra("feeling", currentJournal.getFeeling());
            intent.putExtra("mode", currentJournal.getMode());
            mContext.startActivity(intent);
        });

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor ("#"+mColors[new Random().nextInt(28)]));
        holder.tvJournalNote.setBackground(shape);
    }

    @Override
    public int getItemCount() {
        return journals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvJournalDate, tvJournalNote;

        public ViewHolder(View itemView) {
            super(itemView);
            tvJournalDate = itemView.findViewById(R.id.tv_journal_date);
            tvJournalNote = itemView.findViewById(R.id.tv_journal_note);
        }
    }

}

package com.licenta.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.licenta.app.Adapter.JournalListRecyclerAdapter;
import com.licenta.app.JournalAddEditActivity;
import com.licenta.app.Model.Journal;
import com.licenta.app.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class JournalFragment extends Fragment {

    private JournalListRecyclerAdapter mAdapter;
    private ArrayList<Journal> mJournalItems;
    private ValueEventListener mDBListener;
    private DatabaseReference mDatabaseRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        FloatingActionButton fabAddJournal = view.findViewById(R.id.fab_add_journal);
        fabAddJournal.setOnClickListener(view1 -> startActivity(new Intent(requireActivity(), JournalAddEditActivity.class)));

        RecyclerView mRecyclerView = view.findViewById(R.id.journal_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mJournalItems = new ArrayList<>();
        mAdapter = new JournalListRecyclerAdapter(requireActivity(), mJournalItems);
        mRecyclerView.setAdapter(mAdapter);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users")
                .child(Objects.requireNonNull(firebaseAuth.getUid())).child("Journals");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mJournalItems.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Journal journal = dataSnapshot1.getValue(Journal.class);
                    mJournalItems.add( journal);
                }
                Collections.reverse(mJournalItems);
                mAdapter.notifyDataSetChanged();
                view.findViewById(R.id.progress_bar).setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(requireActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void onDestroy() {
        mDatabaseRef.removeEventListener(mDBListener);
        super.onDestroy();
    }
}
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.licenta.app.Adapter.AffirmationListRecyclerAdapter;
import com.licenta.app.AffirmationAddEditActivity;
import com.licenta.app.Model.Affirmation;
import com.licenta.app.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class AffirmationFragment extends Fragment {

    private AffirmationListRecyclerAdapter mAdapter;
    private ArrayList<Affirmation> mAffirmationsItems;
    private ValueEventListener mDBListener;
    private DatabaseReference mDatabaseRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_affirmation, container, false);

        FloatingActionButton fabAddAffirmation = view.findViewById(R.id.fab_add_affirmation);
        fabAddAffirmation.setOnClickListener(view1 -> startActivity(new Intent(requireActivity(), AffirmationAddEditActivity.class)));

        RecyclerView mRecyclerView = view.findViewById(R.id.affirmation_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mAffirmationsItems = new ArrayList<>();
        mAdapter = new AffirmationListRecyclerAdapter(requireActivity(), mAffirmationsItems);
        mRecyclerView.setAdapter(mAdapter);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users")
                .child(Objects.requireNonNull(firebaseAuth.getUid())).child("Affirmations");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mAffirmationsItems.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Affirmation affirmation = dataSnapshot1.getValue(Affirmation.class);
                    mAffirmationsItems.add(affirmation);
                }
                Collections.reverse(mAffirmationsItems);
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
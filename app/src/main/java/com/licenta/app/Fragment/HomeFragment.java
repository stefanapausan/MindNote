package com.licenta.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.licenta.app.BreathingActivity;
import com.licenta.app.MainActivity;
import com.licenta.app.Model.UserProfile;
import com.licenta.app.R;
import com.licenta.app.RelaxationActivity;

import java.util.Objects;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView ivWrite = view.findViewById(R.id.iv_write);
        ImageView ivDraw = view.findViewById(R.id.iv_draw);
        ImageView ivBreathing = view.findViewById(R.id.iv_breathing);
        ImageView ivRelaxation = view.findViewById(R.id.iv_relaxation);

        ivWrite.setOnClickListener(view1 -> ((MainActivity) requireActivity()).loadJournalFragment());
        ivDraw.setOnClickListener(view1 -> ((MainActivity) requireActivity()).loadDrawingFragment());
        ivBreathing.setOnClickListener(view1 -> startActivity(new Intent(requireActivity(), BreathingActivity.class)));
        ivRelaxation.setOnClickListener(view1 -> startActivity(new Intent(requireActivity(), RelaxationActivity.class)));

        TextView tvUsername = view.findViewById(R.id.tv_username);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(Objects.requireNonNull(firebaseAuth.getUid())).child("Profile");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                if (userProfile != null) {
                    tvUsername.setText(userProfile.getUserName());
                    view.findViewById(R.id.progress_bar).setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(requireActivity(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    public void onDestroy() {
        super.onDestroy();
    }
}
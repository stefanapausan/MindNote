package com.licenta.app;

import static com.licenta.app.MainActivity.DarkModePREFERENCES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.licenta.app.Model.UserProfile;

import java.util.Calendar;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    private String s_name, s_email;
    private SharedPreferences sharedpreferences;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(Objects.requireNonNull(firebaseAuth.getUid())).child("Profile");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                if (userProfile != null) {
                    s_name = userProfile.getUserName();
                    s_email = userProfile.getUserEmail();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SettingsActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        TextView tvVersion = findViewById(R.id.tv_version);
        tvVersion.setText("Version " + BuildConfig.VERSION_NAME);

        sharedpreferences = getSharedPreferences(DarkModePREFERENCES, Context.MODE_PRIVATE);
    }

    public void updateAccount(View v) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_account_update, findViewById(R.id.account_update_view));
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(SettingsActivity.this).create();
        alertDialog.setView(view);

        EditText etName = view.findViewById(R.id.et_update_name);
        EditText etEmail = view.findViewById(R.id.et_update_email);
        Button btnSave = view.findViewById(R.id.btn_save);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        etName.setText(s_name);
        etEmail.setText(s_email);

        btnSave.setOnClickListener(view1 -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();

            if (name.isEmpty()) etName.setError("Enter name!");
            else if (email.isEmpty()) etEmail.setError("Enter email!");
            else if (!email.contains("@")||!email.contains(".")) etEmail.setError("Enter valid email!");
            else {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                        .child(Objects.requireNonNull(firebaseAuth.getUid())).child("Profile");

                UserProfile userProfile = new UserProfile(name, email);

                databaseReference.setValue(userProfile).addOnCompleteListener(task -> {
                    alertDialog.dismiss();
                    Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();
                });
            }
        });
        btnCancel.setOnClickListener(view1 -> alertDialog.dismiss());
        alertDialog.show();
    }

    public void darkMode(View v) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dark_mode, findViewById(R.id.dark_mode_view));
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(SettingsActivity.this).create();
        alertDialog.setView(view);

        Button btnSave = view.findViewById(R.id.btn_save);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        RadioGroup darkModeGroup = view.findViewById(R.id.dark_mode_group);

        int DARK_MODE = sharedpreferences.getInt("DARK_MODE", 1);
        if (DARK_MODE == 1) ((RadioButton)darkModeGroup.getChildAt(0)).setChecked(true);
        else if (DARK_MODE == 2) ((RadioButton)darkModeGroup.getChildAt(1)).setChecked(true);
        else ((RadioButton)darkModeGroup.getChildAt(2)).setChecked(true);

        btnSave.setOnClickListener(view1 -> {
            SharedPreferences.Editor editor = sharedpreferences.edit();

            int radioButton = darkModeGroup.indexOfChild(darkModeGroup.findViewById(darkModeGroup.getCheckedRadioButtonId()));
            if (radioButton == 0) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putInt("DARK_MODE", 1);
            } else if (radioButton == 1) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putInt("DARK_MODE", 2);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                editor.putInt("DARK_MODE", 3);
            }
            editor.apply();
            alertDialog.dismiss();
        });
        btnCancel.setOnClickListener(view1 -> alertDialog.dismiss());
        alertDialog.show();
    }

    public void setReminder(View v) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_reminder_dialog, findViewById(R.id.reminder_dialog_view));
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(SettingsActivity.this).create();
        alertDialog.setView(view);

        EditText etReminder = view.findViewById(R.id.et_reminder);

        Button btnNext = view.findViewById(R.id.btn_next);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        btnNext.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.TITLE, etReminder.getText().toString());
            startActivity(intent);
            alertDialog.dismiss();
        });
        btnCancel.setOnClickListener(view1 -> alertDialog.dismiss());
        alertDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
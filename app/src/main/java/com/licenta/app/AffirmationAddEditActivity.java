package com.licenta.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.licenta.app.Model.Journal;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class AffirmationAddEditActivity extends AppCompatActivity {

    private EditText etNote;
    private String s_note=null, s_key=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affirmation_add_edit);

        setTitle("Affirmation");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etNote = findViewById(R.id.et_note);

        s_note = getIntent().getStringExtra("note");
        s_key = getIntent().getStringExtra("key");

        if (s_note!=null & s_key!=null){
            etNote.setText(s_note);
        } else etNote.requestFocus();
    }

    private void uploadJournal(String note) {
        Date date = new Date();
        String stringDate = DateFormat.getDateTimeInstance().format(date);

        findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(firebaseAuth.getUid())).child("Affirmations");

        String key;
        if (s_note!=null & s_key!=null) key = s_key;
        else key = databaseReference.push().getKey();
        Journal userProfile = new Journal(note, stringDate, key);

        databaseReference.child(Objects.requireNonNull(key)).setValue(userProfile).addOnCompleteListener(task -> finish());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_affirmation_add_edit, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                uploadJournal(etNote.getText().toString());
                return true;
            case R.id.action_delete:
                if (s_note!=null & s_key!=null) {
                    new MaterialAlertDialogBuilder(this)
                            .setMessage("Are you sure you want to delete?")
                            .setPositiveButton("YES", (dialogInterface, i) -> {
                                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                                        .getReference("Users").child(Objects.requireNonNull(firebaseAuth.getUid())).child("Affirmations").child(s_key);
                                databaseReference.removeValue().addOnCompleteListener(task -> finish());
                            })
                            .setNegativeButton("NO", null)
                            .show();
                } else discard();
                return true;
            case R.id.action_discard:
                discard();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void discard() {
        if (etNote.getText().toString().isEmpty() || etNote.getText().toString().equals(s_note)) finish();
        else new MaterialAlertDialogBuilder(this)
                .setMessage("Are you sure you want to discard?")
                .setPositiveButton("YES", (dialogInterface, i) -> finish())
                .setNegativeButton("NO", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        discard();
    }
}
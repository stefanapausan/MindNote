package com.licenta.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.licenta.app.Model.Journal;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class JournalAddEditActivity extends AppCompatActivity {

    private EditText etNote,desired_feeling_text;
    private String s_note=null, s_key=null,s_mode=null,s_feeling=null;
    TextView  mode1,mode2,mode3,mode4,mode5,mode6,mode7,mode8,mode9,mode10;
    ArrayList<String> feelingArrayLIst =new ArrayList<String>();
    TextView feeling1,feeling2,feeling3,feeling4,feeling5,feeling6,feeling7,feeling8,feeling9,feeling10,
            feeling11,feeling12,feeling13,feeling14,feeling15,feeling16,feeling17,feeling18,feeling19,feeling20,feeling21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_add_edit);
        setTitle("Journal");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        feelingArrayLIst.add("Happy");feelingArrayLIst.add("Grateful");feelingArrayLIst.add("Confident");feelingArrayLIst.add("Excited");
        feelingArrayLIst.add("Optimistic");feelingArrayLIst.add("Loved");feelingArrayLIst.add("Hopeful");feelingArrayLIst.add("Super");
        feelingArrayLIst.add("Grate");feelingArrayLIst.add("Good");feelingArrayLIst.add("Meh");feelingArrayLIst.add("Bad");
        feelingArrayLIst.add("Sick");feelingArrayLIst.add("Awful");feelingArrayLIst.add("Bored");feelingArrayLIst.add("Frustrated");
        feelingArrayLIst.add("Anxious");feelingArrayLIst.add("Stressed");feelingArrayLIst.add("Scared");feelingArrayLIst.add("Angry");
        feelingArrayLIst.add("Lonely");


        feeling1=findViewById(R.id.feeling1);
        feeling2=findViewById(R.id.feeling2);
        feeling3=findViewById(R.id.feeling3); feeling4=findViewById(R.id.feeling4); feeling5=findViewById(R.id.feeling5);
        feeling6=findViewById(R.id.feeling6); feeling7=findViewById(R.id.feeling7); feeling8=findViewById(R.id.feeling8);
        feeling9=findViewById(R.id.feeling9);
        feeling10=findViewById(R.id.feeling10); feeling11=findViewById(R.id.feeling11); feeling12=findViewById(R.id.feeling12);
        feeling13=findViewById(R.id.feeling13); feeling14=findViewById(R.id.feeling14); feeling15=findViewById(R.id.feeling15);
        feeling16=findViewById(R.id.feeling16); feeling17=findViewById(R.id.feeling17); feeling18=findViewById(R.id.feeling18);
        feeling19=findViewById(R.id.feeling19);   feeling20=findViewById(R.id.feeling20);   feeling21=findViewById(R.id.feeling21);


        etNote = findViewById(R.id.et_note);
        desired_feeling_text=findViewById(R.id.desired_feeling_text);
        mode1=findViewById(R.id.mode1);
        mode2=findViewById(R.id.mode2);
        mode3=findViewById(R.id.mode3); mode4=findViewById(R.id.mode4); mode5=findViewById(R.id.mode5); mode6=findViewById(R.id.mode6);
        mode7=findViewById(R.id.mode7); mode8=findViewById(R.id.mode8); mode9=findViewById(R.id.mode9); mode10=findViewById(R.id.mode10);
        s_note = getIntent().getStringExtra("note");
        s_key = getIntent().getStringExtra("key");
        s_feeling=getIntent().getStringExtra("feeling");
        s_mode=getIntent().getStringExtra("mode");

        if (s_note!=null & s_key!=null & s_mode !=null & s_feeling!=null){
            if (feelingArrayLIst.get(0).equals(s_feeling)) {
                feeling1.setBackground(getDrawable(R.drawable.mode121_bg));
            }
            if (feelingArrayLIst.get(1).equals(s_feeling)) {
                feeling2.setBackground(getDrawable(R.drawable.mode121_bg));
            }
            if (feelingArrayLIst.get(2).equals(s_feeling)) {
                feeling3.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(3).equals(s_feeling)) {
                feeling4.setBackground(getDrawable(R.drawable.mode121_bg));
            }
            if (feelingArrayLIst.get(4).equals(s_feeling)) {
                feeling5.setBackground(getDrawable(R.drawable.mode121_bg));
            }
            if (feelingArrayLIst.get(5).equals(s_feeling)) {
                feeling6.setBackground(getDrawable(R.drawable.mode121_bg));
            }
            if (feelingArrayLIst.get(6).equals(s_feeling)) {
                feeling7.setBackground(getDrawable(R.drawable.mode121_bg));
            }
            if (feelingArrayLIst.get(7).equals(s_feeling)) {
                feeling8.setBackground(getDrawable(R.drawable.mode121_bg));
            }
            if (feelingArrayLIst.get(8).equals(s_feeling)) {
                feeling9.setBackground(getDrawable(R.drawable.mode121_bg));
            }
            if (feelingArrayLIst.get(9).equals(s_feeling)) {
                feeling10.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(10).equals(s_feeling)) {
                feeling11.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(11).equals(s_feeling)) {
                feeling12.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(12).equals(s_feeling)) {
                feeling13.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(13).equals(s_feeling)) {
                feeling14.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(14).equals(s_feeling)) {
                feeling15.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(15).equals(s_feeling)) {
                feeling16.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(16).equals(s_feeling)) {
                feeling17.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(17).equals(s_feeling)) {
                feeling18.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(18).equals(s_feeling)) {
                feeling19.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(19).equals(s_feeling)) {
                feeling20.setBackground(getDrawable(R.drawable.mode121_bg));
            }if (feelingArrayLIst.get(20).equals(s_feeling)) {
                feeling21.setBackground(getDrawable(R.drawable.mode121_bg));
            }
            if(!feelingArrayLIst.contains(s_feeling)) {
                desired_feeling_text.setText(s_feeling);
                desired_feeling_text.setVisibility(View.VISIBLE);
            }

            etNote.setText(s_note);
            if(s_mode.equals("1")){
                mode1.setBackground(getDrawable(R.drawable.mode12_bg));

            }
            if(s_mode.equals("2")){
                mode2.setBackground(getDrawable(R.drawable.mode12_bg));

            }
            if(s_mode.equals("3")){
                mode3.setBackground(getDrawable(R.drawable.mode34_bg));
            }
            if(s_mode.equals("4")){
                mode4.setBackground(getDrawable(R.drawable.mode34_bg));
            }
            if(s_mode.equals("5")){
                mode5.setBackground(getDrawable(R.drawable.mode56_bg));
            }
            if(s_mode.equals("6")){
                mode6.setBackground(getDrawable(R.drawable.mode56_bg));
            }
            if(s_mode.equals("7")){
                mode7.setBackground(getDrawable(R.drawable.mode78_bg));
            }
            if(s_mode.equals("8")){
                mode8.setBackground(getDrawable(R.drawable.mode78_bg));
            }
            if(s_mode.equals("9")){
                mode9.setBackground(getDrawable(R.drawable.mode910_bg));
            }
            if(s_mode.equals("10")){
                mode10.setBackground(getDrawable(R.drawable.mode910_bg));
            }



        } else etNote.requestFocus();

    }



    private void uploadJournal(String note) {
        Date date = new Date();
        String stringDate = DateFormat.getDateTimeInstance().format(date);

        findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(firebaseAuth.getUid())).child("Journals");

        String key;
        if (s_note!=null & s_key!=null) key = s_key;
        else key = databaseReference.push().getKey();
        Journal userProfile = new Journal(note, stringDate, key,s_mode,s_feeling);

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
        inflater.inflate(R.menu.menu_jaournal_add_edit, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                if(desired_feeling_text.getVisibility() == View.VISIBLE){
                    if(desired_feeling_text.getText().toString().trim().isEmpty()){
                        desired_feeling_text.setError("required");
                        return true;
                    }
                    s_feeling=desired_feeling_text.getText().toString().trim();
                }
                if(etNote.getText().toString().trim().isEmpty()){
                    etNote.setError("required");
                    return true;
                }
                uploadJournal(etNote.getText().toString());
                return true;
            case R.id.action_delete:
                if (s_note!=null & s_key!=null) {
                    new MaterialAlertDialogBuilder(this)
                            .setMessage("Are you sure you want to delete?")
                            .setPositiveButton("YES", (dialogInterface, i) -> {
                                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                                        .getReference("Users").child(Objects.requireNonNull(firebaseAuth.getUid())).child("Journals").child(s_key);
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

    public void setMode1(View view) {

        mode1.setBackground(getDrawable(R.drawable.mode12_bg));mode2.setBackground(getDrawable(R.drawable.btn_bg));
        mode3.setBackground(getDrawable(R.drawable.btn_bg));mode4.setBackground(getDrawable(R.drawable.btn_bg));
        mode5.setBackground(getDrawable(R.drawable.btn_bg));mode6.setBackground(getDrawable(R.drawable.btn_bg));
        mode7.setBackground(getDrawable(R.drawable.btn_bg));   mode8.setBackground(getDrawable(R.drawable.btn_bg));
        mode9.setBackground(getDrawable(R.drawable.btn_bg));   mode10.setBackground(getDrawable(R.drawable.btn_bg));
        s_mode="1";
    }
    public void setMode2(View view) {
        mode2.setBackground(getDrawable(R.drawable.mode12_bg));mode1.setBackground(getDrawable(R.drawable.btn_bg));
        mode3.setBackground(getDrawable(R.drawable.btn_bg));mode4.setBackground(getDrawable(R.drawable.btn_bg));
        mode5.setBackground(getDrawable(R.drawable.btn_bg));mode6.setBackground(getDrawable(R.drawable.btn_bg));
        mode7.setBackground(getDrawable(R.drawable.btn_bg));   mode8.setBackground(getDrawable(R.drawable.btn_bg));
        mode9.setBackground(getDrawable(R.drawable.btn_bg));   mode10.setBackground(getDrawable(R.drawable.btn_bg));
        s_mode="2";
    }
    public void setMode3(View view) {
        mode3.setBackground(getDrawable(R.drawable.mode34_bg));mode2.setBackground(getDrawable(R.drawable.btn_bg));
        mode1.setBackground(getDrawable(R.drawable.btn_bg));mode4.setBackground(getDrawable(R.drawable.btn_bg));
        mode5.setBackground(getDrawable(R.drawable.btn_bg));mode6.setBackground(getDrawable(R.drawable.btn_bg));
        mode7.setBackground(getDrawable(R.drawable.btn_bg));   mode8.setBackground(getDrawable(R.drawable.btn_bg));
        mode9.setBackground(getDrawable(R.drawable.btn_bg));   mode10.setBackground(getDrawable(R.drawable.btn_bg));
        s_mode="3";
    }
    public void setMode4(View view) {
        mode4.setBackground(getDrawable(R.drawable.mode34_bg));mode2.setBackground(getDrawable(R.drawable.btn_bg));
        mode3.setBackground(getDrawable(R.drawable.btn_bg));mode1.setBackground(getDrawable(R.drawable.btn_bg));
        mode5.setBackground(getDrawable(R.drawable.btn_bg));mode6.setBackground(getDrawable(R.drawable.btn_bg));
        mode7.setBackground(getDrawable(R.drawable.btn_bg));   mode8.setBackground(getDrawable(R.drawable.btn_bg));
        mode9.setBackground(getDrawable(R.drawable.btn_bg));   mode10.setBackground(getDrawable(R.drawable.btn_bg));
        s_mode="4";
    }
    public void setMode5(View view) {
        mode5.setBackground(getDrawable(R.drawable.mode56_bg));mode2.setBackground(getDrawable(R.drawable.btn_bg));
        mode3.setBackground(getDrawable(R.drawable.btn_bg));mode4.setBackground(getDrawable(R.drawable.btn_bg));
        mode1.setBackground(getDrawable(R.drawable.btn_bg));mode6.setBackground(getDrawable(R.drawable.btn_bg));
        mode7.setBackground(getDrawable(R.drawable.btn_bg));   mode8.setBackground(getDrawable(R.drawable.btn_bg));
        mode9.setBackground(getDrawable(R.drawable.btn_bg));   mode10.setBackground(getDrawable(R.drawable.btn_bg));
        s_mode="5";
    }
    public void setMode6(View view) {
        mode6.setBackground(getDrawable(R.drawable.mode56_bg));mode2.setBackground(getDrawable(R.drawable.btn_bg));
        mode3.setBackground(getDrawable(R.drawable.btn_bg));mode4.setBackground(getDrawable(R.drawable.btn_bg));
        mode5.setBackground(getDrawable(R.drawable.btn_bg));mode1.setBackground(getDrawable(R.drawable.btn_bg));
        mode7.setBackground(getDrawable(R.drawable.btn_bg));   mode8.setBackground(getDrawable(R.drawable.btn_bg));
        mode9.setBackground(getDrawable(R.drawable.btn_bg));   mode10.setBackground(getDrawable(R.drawable.btn_bg));
        s_mode="6";
    }
    public void setMode7(View view) {
        mode7.setBackground(getDrawable(R.drawable.mode78_bg));mode2.setBackground(getDrawable(R.drawable.btn_bg));
        mode3.setBackground(getDrawable(R.drawable.btn_bg));mode4.setBackground(getDrawable(R.drawable.btn_bg));
        mode5.setBackground(getDrawable(R.drawable.btn_bg));mode6.setBackground(getDrawable(R.drawable.btn_bg));
        mode1.setBackground(getDrawable(R.drawable.btn_bg));   mode8.setBackground(getDrawable(R.drawable.btn_bg));
        mode9.setBackground(getDrawable(R.drawable.btn_bg));   mode10.setBackground(getDrawable(R.drawable.btn_bg));
        s_mode="7";
    }
    public void setMode8(View view) {
        mode8.setBackground(getDrawable(R.drawable.mode78_bg));mode2.setBackground(getDrawable(R.drawable.btn_bg));
        mode3.setBackground(getDrawable(R.drawable.btn_bg));mode4.setBackground(getDrawable(R.drawable.btn_bg));
        mode5.setBackground(getDrawable(R.drawable.btn_bg));mode6.setBackground(getDrawable(R.drawable.btn_bg));
        mode7.setBackground(getDrawable(R.drawable.btn_bg));   mode1.setBackground(getDrawable(R.drawable.btn_bg));
        mode9.setBackground(getDrawable(R.drawable.btn_bg));   mode10.setBackground(getDrawable(R.drawable.btn_bg));
        s_mode="8";
    }
    public void setMode9(View view) {
        mode9.setBackground(getDrawable(R.drawable.mode910_bg));mode2.setBackground(getDrawable(R.drawable.btn_bg));
        mode3.setBackground(getDrawable(R.drawable.btn_bg));mode4.setBackground(getDrawable(R.drawable.btn_bg));
        mode5.setBackground(getDrawable(R.drawable.btn_bg));mode6.setBackground(getDrawable(R.drawable.btn_bg));
        mode7.setBackground(getDrawable(R.drawable.btn_bg));   mode8.setBackground(getDrawable(R.drawable.btn_bg));
        mode1.setBackground(getDrawable(R.drawable.btn_bg));   mode10.setBackground(getDrawable(R.drawable.btn_bg));
        s_mode="9";
    }
    public void setMode10(View view) {
        mode10.setBackground(getDrawable(R.drawable.mode910_bg));mode2.setBackground(getDrawable(R.drawable.btn_bg));
        mode3.setBackground(getDrawable(R.drawable.btn_bg));mode4.setBackground(getDrawable(R.drawable.btn_bg));
        mode5.setBackground(getDrawable(R.drawable.btn_bg));mode6.setBackground(getDrawable(R.drawable.btn_bg));
        mode7.setBackground(getDrawable(R.drawable.btn_bg));   mode8.setBackground(getDrawable(R.drawable.btn_bg));
        mode9.setBackground(getDrawable(R.drawable.btn_bg));   mode1.setBackground(getDrawable(R.drawable.btn_bg));
        s_mode="10";
    }

    public void showDesiredText(View view) {
        feeling1.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));
        desired_feeling_text.setVisibility(View.VISIBLE);
    }

    public void setFelling1(View view) {
        s_feeling="Happy";
        desired_feeling_text.setVisibility(View.GONE);
        feeling1.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));

    } public void setFelling2(View view) {
        s_feeling="Grateful";
        desired_feeling_text.setVisibility(View.GONE);
        feeling2.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling1.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));
    } public void setFelling3(View view) {
        s_feeling="Confident";
        desired_feeling_text.setVisibility(View.GONE);
        feeling3.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling1.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));
    } public void setFelling4(View view) {
        desired_feeling_text.setVisibility(View.GONE);
        feeling4.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling1.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));
        s_feeling="Excited";
    } public void setFelling5(View view) {
        s_feeling="Optimistic";
        desired_feeling_text.setVisibility(View.GONE);
        feeling5.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling1.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));
    } public void setFelling6(View view) {
        desired_feeling_text.setVisibility(View.GONE);
        feeling6.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling1.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));
        s_feeling="Loved";
    } public void setFelling7(View view) {
        s_feeling="Hopeful";
        desired_feeling_text.setVisibility(View.GONE);
        feeling7.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling1.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));    }
        public void setFelling8(View view) {
        s_feeling="Super";
            feeling8.setBackground(getDrawable(R.drawable.mode121_bg));
            feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling1.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));
            desired_feeling_text.setVisibility(View.GONE);

    } public void setFelling9(View view) {
        s_feeling="Grate";
        desired_feeling_text.setVisibility(View.GONE);
        feeling9.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling1.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));

    } public void setFelling10(View view) {
        s_feeling="Good";
        desired_feeling_text.setVisibility(View.GONE);
        feeling10.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling1.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));    }
        public void setFelling11(View view) {
        s_feeling="Meh";
            feeling11.setBackground(getDrawable(R.drawable.mode121_bg));
            feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling12.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling1.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));
            desired_feeling_text.setVisibility(View.GONE);
    }



    public void setFelling12(View view) {
        s_feeling="Bad";
        desired_feeling_text.setVisibility(View.GONE);
        feeling12.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling1.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));    }



        public void setFelling13(View view) {
        s_feeling="Sick";
            desired_feeling_text.setVisibility(View.GONE);
            feeling13.setBackground(getDrawable(R.drawable.mode121_bg));
            feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling1.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));
    } public void setFelling14(View view) {
        s_feeling="Awful";
        desired_feeling_text.setVisibility(View.GONE);
        feeling14.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling1.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));    }



        public void setFelling15(View view) {
        s_feeling="Bored";

            feeling15.setBackground(getDrawable(R.drawable.mode121_bg));
            feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling1.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));

            desired_feeling_text.setVisibility(View.GONE);

    } public void setFelling16(View view) {
        s_feeling="Frustrated";
        feeling16.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling1.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        desired_feeling_text.setVisibility(View.GONE);
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));    }

        public void setFelling17(View view) {
        s_feeling="Anxious";
            desired_feeling_text.setVisibility(View.GONE);
            feeling17.setBackground(getDrawable(R.drawable.mode121_bg));
            feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling1.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));





    } public void setFelling18(View view) {
        s_feeling="Stressed";
        desired_feeling_text.setVisibility(View.GONE);
        feeling18.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling1.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));    }

        public void setFelling19(View view) {
        s_feeling="Scared";
            desired_feeling_text.setVisibility(View.GONE);
            feeling19.setBackground(getDrawable(R.drawable.mode121_bg));
            feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling1.setBackground(getDrawable(R.drawable.feeling_bg));
            feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));
    }



    public void setFelling20(View view) {
        s_feeling="Angry";
        desired_feeling_text.setVisibility(View.GONE);
        feeling20.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling1.setBackground(getDrawable(R.drawable.feeling_bg)); feeling21.setBackground(getDrawable(R.drawable.feeling_bg));

    } public void setFelling21(View view) {
        s_feeling="Lonely";
        desired_feeling_text.setVisibility(View.GONE);
        feeling21.setBackground(getDrawable(R.drawable.mode121_bg));
        feeling2.setBackground(getDrawable(R.drawable.feeling_bg)); feeling3.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling4.setBackground(getDrawable(R.drawable.feeling_bg)); feeling5.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling6.setBackground(getDrawable(R.drawable.feeling_bg)); feeling7.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling8.setBackground(getDrawable(R.drawable.feeling_bg)); feeling9.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling10.setBackground(getDrawable(R.drawable.feeling_bg)); feeling11.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling12.setBackground(getDrawable(R.drawable.feeling_bg)); feeling13.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling14.setBackground(getDrawable(R.drawable.feeling_bg)); feeling15.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling16.setBackground(getDrawable(R.drawable.feeling_bg)); feeling17.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling18.setBackground(getDrawable(R.drawable.feeling_bg)); feeling19.setBackground(getDrawable(R.drawable.feeling_bg));
        feeling20.setBackground(getDrawable(R.drawable.feeling_bg)); feeling1.setBackground(getDrawable(R.drawable.feeling_bg));
    }
}
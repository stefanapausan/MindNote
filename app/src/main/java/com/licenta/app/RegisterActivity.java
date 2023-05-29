package com.licenta.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.licenta.app.Model.UserProfile;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText etRegisterEmail, etRegisterPassword, etRegisterConfirmPassword;
    private FirebaseAuth firebaseAuth;
    private String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();

        etRegisterEmail = findViewById(R.id.et_register_email);
        etRegisterPassword = findViewById(R.id.et_register_password);
        etRegisterConfirmPassword = findViewById(R.id.et_register_confirm_password);

        user_name = getIntent().getStringExtra("name");

        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(view -> {
            String email = etRegisterEmail.getText().toString();
            String password = etRegisterPassword.getText().toString();
            String confirm_password = etRegisterConfirmPassword.getText().toString();
            if (validate(email, password, confirm_password)) requestRegister(email, password);
        });

    }

    private boolean validate(String email, String password, String confirm_password) {
        if (email.isEmpty()) etRegisterEmail.setError("Enter email!");
        else if (!email.contains("@")||!email.contains(".")) etRegisterEmail.setError("Enter valid email!");
        else if (password.isEmpty()) etRegisterPassword.setError("Enter password!");
        else if (password.length()<6) etRegisterPassword.setError("Password must be at least 6 characters!");
        else if (confirm_password.isEmpty()) etRegisterConfirmPassword.setError("Enter password!");
        else if (!password.equals(confirm_password)) etRegisterConfirmPassword.setError("Password not matched!");
        else return true;
        return false;
    }

    private void requestRegister(String email, String password) {
        findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getCreateUserWithEmailOnClickListener(email));
    }
    private OnCompleteListener<AuthResult> getCreateUserWithEmailOnClickListener(String email) {
        return task -> {
            if (task.isSuccessful()) {
                sendUserData(email);
            } else {
                findViewById(R.id.progress_bar).setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Registration failed!", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        };
    }

    private void sendUserData(String email) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(Objects.requireNonNull(firebaseAuth.getUid())).child("Profile");

        UserProfile userProfile = new UserProfile(user_name, email);

        databaseReference.setValue(userProfile).addOnCompleteListener(task -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                user.sendEmailVerification();
                firebaseAuth.signOut();
            }
        });
        findViewById(R.id.progress_bar).setVisibility(View.GONE);

        new MaterialAlertDialogBuilder(this)
                .setTitle("Verify Email")
                .setMessage("Registration successful! Please check your email to verify your account.")
                .setCancelable(false)
                .setPositiveButton("LOGIN", (dialogInterface, i) -> finish())
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
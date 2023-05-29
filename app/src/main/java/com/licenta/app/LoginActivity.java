package com.licenta.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText etLoginEmail, etLoginPassword;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail = findViewById(R.id.et_login_email);
        etLoginPassword = findViewById(R.id.et_login_password);
        Button btnLogin = findViewById(R.id.btn_login);
        firebaseAuth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(view -> {
            String email = etLoginEmail.getText().toString();
            String password = etLoginPassword.getText().toString();
            if (validate(email, password)) requestLogin(email, password);
        });


        findViewById(R.id.tv_reset_password).setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
            startActivity(intent);
        });


        String name = getIntent().getStringExtra("name");
        findViewById(R.id.tv_new_register).setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
        });
    }

    private boolean validate(String email, String password) {
        if (email.isEmpty()) etLoginEmail.setError("Enter email!");
        else if (!email.contains("@")||!email.contains(".")) etLoginEmail.setError("Enter valid email!");
        else if (password.isEmpty()) etLoginPassword.setError("Enter password!");
        else if (password.length()<6) etLoginPassword.setError("Password must be at least 6 characters!");
        else return true;
        return false;
    }

    private void requestLogin(String email, String password) {
        findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null ) {
                            user.reload();
                            if (user.isEmailVerified()) {
                                findViewById(R.id.progress_bar).setVisibility(View.GONE);
                                openHomeActivity();
                            } else {
                                findViewById(R.id.progress_bar).setVisibility(View.GONE);
                                firebaseAuth.signOut();
                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Unverified email!", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        }
                    } else {
                        findViewById(R.id.progress_bar).setVisibility(View.GONE);
                    }})
                .addOnFailureListener(e -> {
                    if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Invalid email or password!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else if (e instanceof FirebaseAuthInvalidUserException) {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Invalid user!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Network error!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });
    }

    private void openHomeActivity() {
        finishAffinity();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}
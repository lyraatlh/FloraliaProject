package com.example.floraliaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtConfirmPassword;
    private Button btnAction;
    private TextView switchMode;
    private DatabaseHelper databaseHelper;
    private boolean isLoginMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnAction = findViewById(R.id.btnAction);
        switchMode = findViewById(R.id.switchMode);

        updateUI();

        btnAction.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String confirmPassword = edtConfirmPassword.getText().toString().trim();

            if (isLoginMode) {
                // Login logic
                if (databaseHelper.checkUser(username, password)) {
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, ContentActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (databaseHelper.isUsernameTaken(username)) {
                    Toast.makeText(this, "Username already taken", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    long result = databaseHelper.addUser(username, password);
                    if (result > 0) {
                        Toast.makeText(this, "Registration successful! Please login.", Toast.LENGTH_SHORT).show();
                        switchToLoginMode();
                    } else {
                        Toast.makeText(this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        switchMode.setOnClickListener(v -> {
            isLoginMode = !isLoginMode;
            updateUI();
        });
    }

    private void updateUI() {
        if (isLoginMode) {
            btnAction.setText("Login");
            switchMode.setText("Don't have an account? Register");
            edtConfirmPassword.setVisibility(View.GONE);
        } else {
            btnAction.setText("Register");
            switchMode.setText("Already have an account? Login");
            edtConfirmPassword.setVisibility(View.VISIBLE);
        }
    }

    private void switchToLoginMode() {
        isLoginMode = true;
        updateUI();
    }
}
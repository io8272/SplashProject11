package com.example.splashproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity3 extends AppCompatActivity {

    private TextView j_IDTextView, j_PasswordTextView, j_statusTextView;
    String id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        j_IDTextView = findViewById(R.id.TextViewID);
        j_PasswordTextView = findViewById(R.id.TextViewPassword);
        j_statusTextView = findViewById(R.id.TextView);

        Intent intent = getIntent();
        if(intent != null) {
            id = intent.getStringExtra("ID");
            password = intent.getStringExtra("Password");

            j_IDTextView.setText(id);
            j_PasswordTextView.setText(password);
        }
    }
    public void check(View v) {
        Intent intent = new Intent();
        if(isUserValid(id, password)) {
            intent.putExtra("status", "로그인 성공");
        }
        else{
            intent.putExtra("status", "로그인 실패");
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    private boolean isUserValid(String username, String password){
        return username.equals("abcd") && password.equals("1234");
    }

}
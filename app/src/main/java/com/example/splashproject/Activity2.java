package com.example.splashproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {

    String tag = "LoginChecker";
    ActivityResultLauncher<Intent> launcher;

    private EditText j_emailEditText, j_passwordEditText;
    private TextView j_statusText;
    private Button buttonlogin;
    private Button j_btn_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        j_emailEditText = findViewById(R.id.EditText1);
        j_passwordEditText = findViewById(R.id.EditText2);
        j_statusText = findViewById(R.id.statusText);
        buttonlogin = findViewById(R.id.LoginButton);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //아이디와 비밀번호를 String변수에 대입
                String email = j_emailEditText.getText().toString();
                String password = j_passwordEditText.getText().toString();

                Log.d(tag, "입력 아이디: " + email);
                Log.d(tag, "입력 비밀번호: " + password);

                Intent intent = new Intent(Activity2.this, Activity3.class);
                intent.putExtra("ID", email);
                intent.putExtra("Password", password);

                //런처 실행
                launcher.launch(intent);
            }
        });

        //launcher

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()== Activity.RESULT_OK) {
                        Intent data = result.getData();
                        j_statusText.setText(data.getStringExtra("status"));
                    }
                    j_btn_main = findViewById(R.id.btn_main);
                    if("로그인성공".equals(j_statusText.getText().toString())) {
                        j_btn_main.setVisibility(View.VISIBLE);
                    } else {
                        j_btn_main.setVisibility(View.INVISIBLE);
                    }
        });
    }

    public void onClicked_main(View view) {
        Intent intent =new Intent(Activity2.this, Activity4.class);
        startActivity(intent);
    }
}
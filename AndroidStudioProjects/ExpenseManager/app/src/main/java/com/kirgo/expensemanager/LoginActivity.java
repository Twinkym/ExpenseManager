package com.kirgo.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends MainActivity {

    Button btnLogin;
    TextView inputUserName;
    TextView inputPassword;
    private View textLogin;
    private View textPassword;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        textLogin = findViewById(R.id.textLogin);
        textPassword = findViewById(R.id.textPassword);
        inputUserName = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.inputPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(LoginActivity.this, ProfileActivity.class);
                startActivity(profile);
            }
        });
    }

}

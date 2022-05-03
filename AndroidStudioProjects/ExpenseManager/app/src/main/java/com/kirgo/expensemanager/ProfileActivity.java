package com.kirgo.expensemanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ProfileActivity extends LoginActivity {
    Button btnLogout;
    Button btnSave;
    TextView txtUserName;
    ImageView imgAvatar;
    EditText inputName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnLogout = findViewById(R.id.btn_Logout);
        btnSave = findViewById(R.id.btn_Save);
        txtUserName = findViewById(R.id.txt_UserName);
        imgAvatar = findViewById(R.id.img_Avatar);
        inputName = findViewById(R.id.input_Name);

        btnLogout.setOnClickListener(view -> finish());
        btnSave.setOnClickListener(view -> {

        });
    }
}

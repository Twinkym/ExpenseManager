package com.kirgo.expensemanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends MainActivity{
    Button btnCreate;
    TextView UserName;
    Button btnImg;
    ImageView imgAvatar;
    EditText inputEmail;

    protected void onCreate(Bundle SaveInstanceState) {

        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_register);
    }

}

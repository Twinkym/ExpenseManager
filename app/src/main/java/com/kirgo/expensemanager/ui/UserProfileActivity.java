package com.kirgo.expensemanager.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

import com.kirgo.expensemanager.R;

public class UserProfileActivity extends AppCompatActivity {

    Button Logout;
    Button Save;
    TextView txtUserName;
    ImageView imgAvatar;
    EditText inputName;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Logout = findViewById(R.id.Logout);
        Save = findViewById(R.id.btn_save);
        txtUserName = findViewById(R.id.input_username);
        imgAvatar = findViewById(R.id.user_avatar);
        inputName = findViewById(R.id.input_name);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Save.setOnClickListener(new View.OnClickListener (){
            public void onClick(View view){

            }
        });

    }
}

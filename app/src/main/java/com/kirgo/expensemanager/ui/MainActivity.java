package com.kirgo.expensemanager.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


import com.kirgo.expensemanager.R;
import com.kirgo.expensemanager.ui.adapter.PayerListAdapter;
import com.kirgo.expensemanager.Model.PayerInfo;
import com.kirgo.expensemanager.Model.UserInfo;


public class MainActivity extends AppCompatActivity {
//    private ImageSwitcher imageSwitcher;
//    private int[] galeria = {R.drawable.portada, R.drawable.portada2, R.drawable.portada3};
//    private int position;
//    private static final int DURATION = 9000;
//    private Timer timer = null;


    EditText txt_amount;
    EditText txt_date;
    EditText txt_description;
    TextView lbl_warning;
    Button btnAddPayer;
    Button btnSave;
    ArrayList<UserInfo> users;
    ArrayList<PayerInfo> payers = new ArrayList<PayerInfo>();
    PayerListAdapter adapter;
    Spinner payer_spinner;
    Integer totalAmount;
    ProgressBar progressBar;
    String expenseID;
    String tripID;
    boolean editionMode;
    int spinnerCurrentIndexSelected = 0;

    boolean savedCorrectly = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editionMode = false;
        getWindow ().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        txt_amount = findViewById(R.id.txtf_amount);
        txt_date = findViewById(R.id.txtf_date);
        txt_description = findViewById(R.id.txtf_description);
        lbl_warning = findViewById (R.id.lbl_warning);
        lbl_warning.setVisibility (View.INVISIBLE);
        btnAddPayer = findViewById(R.id.btn_add_payer);
        progressBar = findViewById (R.id.progressBar);
        progressBar.setVisibility (View.INVISIBLE);
        btnSave = findViewById (R.id.btn_save);
        btnAddPayer.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                UserInfo user = users.get(index);
                index = (index +1) % users.size();
                PayerInfo newPayer = new PayerInfo("", user.name,"", 0);
                payers.add(newPayer);
                adapter.notifyItemInserted(payers.size()-1);
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setInAnimation(fadeOut);

        imageSwitcher = findViewById(R.id.mainSwitcher);
        imageSwitcher.setFactory(() -> {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        });
    }

    public void updateLabelWarning ( ) {
    }
}
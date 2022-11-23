package com.kirgo.expensemanager.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import com.kirgo.expensemanager.Model.ExpenseInfo;
import com.kirgo.expensemanager.Model.PayerInfo;
import com.kirgo.expensemanager.Model.UserInfo;

import com.kirgo.expensemanager.R;
import com.kirgo.expensemanager.ui.adapter.UserAsPayerListAdapter;


public class ResumeActivity extends AppCompatActivity {

    public ArrayList< UserInfo > users = new ArrayList <UserInfo> (  );
    public ArrayList< ExpenseInfo > expenses = new ArrayList <ExpenseInfo> (  );
    TextView TripName;
    TextView TripAmount;

    @Override
    protected void onCreate ( Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_resume);

        TripName = findViewById (R.id.trip_name);
        TripAmount = findViewById (R.id.trip_amount);

        users.add (new UserInfo ("kirgo", "ddlapuente@uoc.edu"));

        ArrayList< PayerInfo > payers1 = new ArrayList <PayerInfo> (  );
        payers1.add (new PayerInfo (null, "kirgo", "ddlapuente@uoc.edu", 158));
        ExpenseInfo expenses1 = new ExpenseInfo ("Expense1","(12/04/2019)", 158, payers1);

        expenses.add (expenses1);

        CreateUserListFromExpenses ();

        RecyclerView list_users = findViewById (R.id.list_users);
        UserAsPayerListAdapter adapter = new UserAsPayerListAdapter (users, this);
        list_users.setHasFixedSize (true);
        list_users.setLayoutManager (new LinearLayoutManager (this));
        list_users.setAdapter (adapter);
    }



    public void CreateUserListFromExpenses(){
        int tripTotalAmount = 0;
        for ( UserInfo user:users ) {
            for ( ExpenseInfo expense:expenses ) {
                tripTotalAmount+= expense.totalAmount;
                for ( PayerInfo payer:expense.payers ) {
                    if ( user.email.compareTo(payer.email) == 0 ){
                        user.amountPaid += payer.amount;
                    }
                }
            }

        }
        tripTotalAmount /= users.size ( );
        TripAmount.setText (tripTotalAmount + " € ");

        for ( UserInfo user:users ) {
            user.PayOrReceive = user.amountPaid - (tripTotalAmount/ users.size ( ));
        }
    }
}

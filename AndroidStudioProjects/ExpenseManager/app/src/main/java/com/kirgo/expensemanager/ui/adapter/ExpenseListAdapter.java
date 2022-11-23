package com.kirgo.expensemanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kirgo.expensemanager.R;
import com.kirgo.expensemanager.Model.ExpenseInfo;
import com.kirgo.expensemanager.Model.UserInfo;
import com.kirgo.expensemanager.ui.MainActivity;

import java.util.ArrayList;

public class ExpenseListAdapter extends RecyclerView.Adapter < UserListAdapter.ViewHolder > {
    private ExpenseInfo[] listdata;
    private Context activityContext;
    ArrayList< UserInfo > users;

    public ExpenseListAdapter (ExpenseInfo[] listdata, Context context, ArrayList < UserInfo > users) {
        this.listdata = listdata;
        this.activityContext = context;
        this.users = users;
    }


    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from (parent.getContext ( ));
        View listItem = layoutInflater.inflate (R.layout.list_item_expense, parent, false);
        UserListAdapter.ViewHolder viewHolder = new UserListAdapter.ViewHolder (listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder ( UserListAdapter.ViewHolder holder, int position) {
        final ExpenseInfo myListData = listdata[position];
        holder.textView_Desc.setText(listdata[position].description);
        holder.textView_Date.setText(listdata[position].date);
        holder.textView_Amount.setText("" + listdata[position].totalAmount + " € ");
        holder.relativeLayout.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick (View view) {
                Intent k = new Intent ( activityContext, MainActivity.class);
                k.putExtra ("Description", myListData.description);
                k.putExtra ("Date", myListData.date);
                k.putExtra ("Amount", myListData.totalAmount);
                k.putExtra ("Users", users);
                activityContext.startActivity (k);

//                Toast.makeText (view.getContext (), "click on item: " + myListData.description, Toast.LENGTH_LONG).show ( );
//                listdata[holder.getBindingAdapterPosition ()].setDescription("Lo que tu quieras");
//                notifyItemChanged (holder.getBindingAdapterPosition ());
            }
        });
    }


    @Override
    public int getItemCount ( ) {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
//        public ImageSwitcher imageView;
        private ImageView imageView;
        private TextView textView_Desc;
        private TextView textView_Date;
        private TextView textView_Amount;
        public RelativeLayout relativeLayout;

        public ViewHolder ( View itemView) {
            super (itemView);
            this.imageView = (ImageView ) itemView.findViewById (R.id.user_avatar);
            this.textView_Desc = (TextView ) itemView.findViewById (R.id.text_View_description);
            this.textView_Date = (TextView ) itemView.findViewById (R.id.textView_date);
            this.textView_Amount = (TextView ) itemView.findViewById (R.id.text_view_amount);

            relativeLayout = (RelativeLayout ) itemView.findViewById (R.id.relativelayout);
        }
    }
}

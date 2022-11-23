package com.kirgo.expensemanager.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.kirgo.expensemanager.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import com.kirgo.expensemanager.Model.UserInfo;

public class UserAsPayerListAdapter extends RecyclerView.Adapter < ExpenseListAdapter.ViewHolder > {
    private ArrayList< UserInfo > listdata;
    private Context activityContext;

    public UserAsPayerListAdapter (ArrayList < UserInfo > listdata, Context activityContext) {
        this.listdata = listdata;
        this.activityContext = activityContext;
    }

    @Override
    public ExpenseListAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from (parent.getContext ());
        View listItem = layoutInflater.inflate (R.layout.list_item_expense, parent, false);
        ExpenseListAdapter.ViewHolder viewHolder = new ExpenseListAdapter.ViewHolder (listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder ( ExpenseListAdapter.ViewHolder holder, int position) {
        final UserInfo myListData = listdata.get (position);
        holder.txt_user.setText(myListData.name);
        holder.txt_amount.setText(myListData.amountPaid + " € ");
        holder.txt_PayOrReceive.setText(myListData.PayOrReceive + " € ");
        if ( listdata.get (position).url_avatar.compareTo ("") == 0 ){
            holder.imageView.setImageResource (R.drawable.user_avatar);
        }
        else {
            new DownloadImageTask(holder.itemView).execute(listdata.get (position).url_avatar);
        }
        holder.relativeLayout.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick (View view) {
                Toast.makeText (view.getContext ( ), "click on item: " + myListData.description, Toast.LENGTH_LONG).show ( );
                listdata.get (holder.getBindingAdapterPosition ( )).setDescription("Lo que tu quieras. ");
                notifyItemChanged (holder.getBindingAdapterPosition ());
            }
        });
    }

    @Override
    public int getItemCount ( ) {
        return listdata.size ();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView txt_user;
        public TextView txt_amount;
        public TextView txt_PayOrReceive;
        public RelativeLayout relativeLayout;
        public ViewHolder ( View itemView) {
            super (itemView);
            this.txt_user = (TextView ) itemView.findViewById (R.id.text_View_description);
            this.txt_amount = (TextView ) itemView.findViewById (R.id.text_view_amount);
            this.txt_PayOrReceive = (TextView ) itemView.findViewById (R.id.textView_date);
            this.imageView = (ImageView ) itemView.findViewById (R.id.user_avatar);
            relativeLayout = (RelativeLayout ) itemView.findViewById (R.id.relativelayout);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap >{
        ImageView imageView;

        public DownloadImageTask (View itemView) {}

        @Override
        protected Bitmap doInBackground (String...urls) {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL (urlOfImage).openStream ();
                logo = BitmapFactory.decodeStream (is);
            }catch ( Exception e){
                e.printStackTrace ();
            }
            return logo;
        }
        protected void onPostExcute(Bitmap result){
            imageView.setImageBitmap (result);
        }
    }

}

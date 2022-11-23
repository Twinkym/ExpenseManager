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

import androidx.recyclerview.widget.RecyclerView;

import com.kirgo.expensemanager.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import com.kirgo.expensemanager.Model.UserInfo;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private ArrayList < UserInfo > listdata;
    private Context activityContext;

    public UserListAdapter (ArrayList < UserInfo > listdata, Context context) {
        this.listdata = listdata;
        this.activityContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from (parent.getContext ( ));
        View listItem = layoutInflater.inflate (R.id.list_item_user, parent, false);
        ViewHolder viewHolder = new ViewHolder (listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        final UserInfo myListData = listdata.get (position );
        if ( listdata.get (position).url_avatar.compareTo ("") == 0 ){
            holder.imageView.setImageResource (R.drawable.user_avatar);
        }else {
            new DownloadImageTask (holder.imageView).execute (listdata.get (position).url_avatar);
        }
        holder.relativeLayout.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick (View view) {
//                Toast.makeText (view.getContext ( ), "click on item: " + myListData.describeContents (), Toast.LENGTH_LONG).show ( );
//                listdata.get (holder.getBindingAdapterPosition ( )).setDescription("");
//                notifyItemChanged (holder.getBindingAdapterPosition ( ));
            }
        });
    }

    @Override
    public int getItemCount(){
        return listdata.size ();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView){
            super(itemView);
            this.imageView = (ImageView ) itemView.findViewById (R.id.image_view);
            relativeLayout = (RelativeLayout ) itemView.findViewById (R.id.relativelayout);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
        ImageView imageView;

        public DownloadImageTask (ImageView imageView) {
            this.imageView = imageView;
        }

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

        @Override
        protected void onPostExecute (Bitmap result) {
            imageView.setImageBitmap (result);
        }
    }
}
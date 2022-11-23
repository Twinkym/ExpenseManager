package com.kirgo.expensemanager.ui.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kirgo.expensemanager.Model.PayerInfo;
import com.kirgo.expensemanager.R;
import com.kirgo.expensemanager.Utitilities.DownloadImageTask;
import com.kirgo.expensemanager.ui.MainActivity;

import java.util.ArrayList;

public class PayerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList< PayerInfo > listdata;
    private MainActivity activity;

    public PayerListAdapter(ArrayList<PayerInfo> payers, MainActivity activity){
        this.listdata = payers;
        this.activity = activity;
    }

    public ViewHolderGeneral onCreateViewHolder (ViewGroup parent, int viewType) {
        switch ( viewType ) {
            case 0: {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View listItem = layoutInflater.inflate(R.layout.list_item_empty, parent, false);

                return new ViewHolderEmpty(listItem);
            }
            case 1:
            default:
            {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View listItem = layoutInflater.inflate (R.layout.list_item_payer, parent, false);

                return new ViewHolderGeneral(listItem);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (listdata.size( ) == 0 ) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount ( ) {
        if (listdata.size() == 0 ){
            return 1;
        }
        return listdata.size();
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder_, int position) {
        switch(holder_.getItemViewType()){
            case 0: {
                ViewHolderEmpty holder = (ViewHolderEmpty) holder_;
                holder.text_info.setText(View.TEXT_ALIGNMENT_CENTER);
            }
            break;
            case 1: {
                ViewHolderGeneral holder = (ViewHolderGeneral ) holder_;
                final PayerInfo myListData = listdata.get (position);
                holder.textView_Desc.setText(listdata.get (position).name);
                holder.textView_Date.setText(listdata.get (position).email + " " + li   stdata.get (position).amount);
                if ( listdata.get (position).image_url.compareTo ("") == 0 ){
                    holder.imageView.setImageResource(R.drawable.trip);
                }else {
                    new DownloadImageTask (holder.imageView).execute (listdata.get (position).image_url);
                }
                holder.btn_amount.setText(" " + listdata.get (position).amount + " € ");
                holder.btn_amount.setOnClickListener(new View.OnClickListener (){
                    public void onClick(View view){
                        AlertDialog.Builder builder = new AlertDialog.Builder (activity);
                        builder.setTitle (" Amount for " + myListData.name + ": ");
                        View viewInFlated = LayoutInflater.from (activity).inflate (R.layout.amount_input, (ViewGroup ) null, false);
                        final EditText input = (EditText ) viewInFlated.findViewById (R.id.input);
                        input.setText ((" " + myListData.amount));
                        builder.setView (viewInFlated);

                        builder.setPositiveButton (android.R.string.ok, new DialogInterface.OnClickListener ( ) {
                            @Override
                            public void onClick (DialogInterface dialogInterface, int which) {
                                dialog.dismiss();
                                String str = input.getText ().toString ();
                                try {
                                    int number = Integer.parseInt (str);
                                    System.out.println ( number );
                                    myListData.amount = number;
                                    int pos = holder.getBindingAdapterPosition ();
                                    PayerListAdapter.this.notifyItemChanged (pos);
                                    activity.updateLabelWarning();
                                }
                                catch ( NumberFormatException ex ) {
                                    ex.printStackTrace ( );
                                }


                            }
                        });
                        builder.setNegativeButton (android.R.string.cancel, new DialogInterface.OnClickListener ( ) {
                            @Override
                            public void onClick (DialogInterface dialogInterface, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show ();
                    }
                });

                holder.btn_delete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick (View view) {
                        new AlertDialog.Builder (activity).setTitle ("Do you really want to delete the payer " + myListData.name + " ?").setMessage ("").setPositiveButton (android.R.string.yes, new DialogInterface.OnClickListener ( ) {
                            @Override
                            public void onClick (DialogInterface dialogInterface, int which) {
                                int pos = holder.getBindingAdapterPosition ();
                                listdata.remove (pos);
                                notifyItemRemoved (pos);
                                activity.updateLabelWarning();
                            }
                        }).setNegativeButton (android.R.string.cancel, new DialogInterface.OnClickListener ( ) {
                            @Override
                            public void onClick (DialogInterface dialogInterface, int which) {
                                dialog.cancel();
                            }
                        }).setIcon (android.R.drawable.ic_dialog_alert).show ();
                    }
                });
            }
        }
    }


    private class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty (View itemView) {super (itemView );
        this.textView_Info = (TextView) itemView.findViewById (R.id.text_info);
        }
    }

    private class ViewHolderGeneral extends RecyclerView.ViewHolder {

        public ViewHolderGeneral (View itemView) {super (itemView);
            this.imageView = (ImageView) itemView.findViewById (R.id.image_view);
            this.textView_Desc = (TextView) itemView.findViewById (R.id.text_View_description);
            this.textView_Date = (TextView) itemView.findViewById (R.id.textView_date);
            this.btn_amount = (Button) itemView.findViewById (R.id.btn_amount);
            this.btn_delete = (Button) itemView.findViewById (R.id.btn_delete);
            relativeLayout = (RelativeLayout)itemView.findViewById (R.id.relativelayout);
        }
    }
}

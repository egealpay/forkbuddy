package com.forkbuddy.forkbuddy;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class ForkHistoryAdapter extends ArrayAdapter<ForkHistory> {

    public ForkHistoryAdapter(@NonNull Context context, List<ForkHistory> list) {
        super(context, R.layout.row_history, list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View rowView = convertView;
        final ForkHistoryAdapter.MyViewHolder holder;
        if(rowView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_history, null);
        }

        if(rowView.getTag() == null){
            holder = new ForkHistoryAdapter.MyViewHolder(rowView);
            rowView.setTag(holder);
        }else{
            holder = (ForkHistoryAdapter.MyViewHolder) rowView.getTag();
        }

        holder.nameSurname.setText(getItem(position).getNameSurname());
        holder.foodHistory.setText("You ate " + getItem(position).getFoodType() + " with him last time.");
        //holder.profilePic.setImageURI(getItem(position).getProfilePicture());

        holder.removeRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.UpFork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgupfork.setImageResource(R.drawable.upforked);
                holder.UpFork.setText("Upforked");
                holder.imgdownfork.setImageResource(R.drawable.ic_downfork);
                holder.DownFork.setText("Downfork");
            }
        });

        holder.DownFork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgdownfork.setImageResource(R.drawable.downforked);
                holder.DownFork.setText("Downforked");
                holder.imgupfork.setImageResource(R.drawable.ic_upfork);
                holder.UpFork.setText("Upfork");
            }
        });

        holder.sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "This is message button", Toast.LENGTH_SHORT).show();
            }
        });

        holder.removeRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryFragment.forkHistoryList.remove(position);
                HistoryFragment.forkHistoryAdapter.notifyDataSetChanged();
            }
        });

        return rowView;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView nameSurname;
        private TextView foodHistory;
        private ImageView profilePic;
        private ImageView removeRow;
        private TextView UpFork;
        private TextView DownFork;
        private ImageView sendMessage;
        private ImageView imgupfork, imgdownfork;

        public MyViewHolder(View itemView) {
            super(itemView);

            nameSurname = itemView.findViewById(R.id.historyNameSurname);
            foodHistory = itemView.findViewById(R.id.foodHistory);
            profilePic = itemView.findViewById(R.id.profilePicture);
            removeRow = itemView.findViewById(R.id.deleteHistory);
            UpFork = itemView.findViewById(R.id.txtUpfork);
            DownFork = itemView.findViewById(R.id.txtDownfork);
            sendMessage = itemView.findViewById(R.id.imgMessage);
            imgdownfork  = itemView.findViewById(R.id.imgDownfork);
            imgupfork = itemView.findViewById(R.id.imgUpfork);
        }
    }
}


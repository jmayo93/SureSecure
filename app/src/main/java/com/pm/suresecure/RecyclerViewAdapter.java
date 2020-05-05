package com.pm.suresecure;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> accountBNames;
    private Context mContext;
    private String username;


    public RecyclerViewAdapter(ArrayList<String> accountNames, Context context, String user) {
        accountBNames = accountNames;
        mContext = context;
        username = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false); //13:01
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.accountButton.setText(accountBNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + accountBNames.get(position));
                Toast.makeText(mContext, accountBNames.get(position), Toast.LENGTH_SHORT).show();
                //String name = accountBNames.get(position);
                Intent password_info_screen = new Intent(mContext, password_info_screen.class);
                password_info_screen.putExtra("com.pm.suresecure.SOMETHING", accountBNames.get(position));
                password_info_screen.putExtra("username",username);
                mContext.startActivity(password_info_screen);
            }
        });

    }

    @Override
    public int getItemCount() {
        return accountBNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView accountButton;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accountButton = itemView.findViewById((R.id.image_name));
            parentLayout = itemView.findViewById((R.id.parent_layout));

        }
    }
}

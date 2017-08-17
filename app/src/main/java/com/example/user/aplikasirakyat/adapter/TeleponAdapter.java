package com.example.user.aplikasirakyat.adapter;

/**
 * Created by sukmaragil on 12-May-17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.aplikasirakyat.R;
import com.example.user.aplikasirakyat.Telepon;

import java.util.List;

public class TeleponAdapter extends RecyclerView.Adapter<TeleponAdapter.MyViewHolder> {

    private List<Telepon> teleponList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Number, Keterangan;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            Number = (TextView) view.findViewById(R.id.Number);
            Keterangan = (TextView) view.findViewById(R.id.Keterangan);
        }
    }


    public TeleponAdapter(List<Telepon> teleponList) {
        this.teleponList = teleponList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.telepon_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        Telepon telepon = teleponList.get(position);
        holder.Name.setText(telepon.getTitle());
        holder.Keterangan.setText(telepon.getProgress());
        holder.Number.setText(telepon.getNumb());
    }

    @Override
    public int getItemCount() {
        return teleponList.size();
    }
}
package com.example.user.aplikasirakyat.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.user.aplikasirakyat.AppController;
import com.example.user.aplikasirakyat.R;
import com.example.user.aplikasirakyat.activity.ListAllPermohonanRT;

import java.util.List;

/**
 * Created by USER on 11/05/2016.
 */
public class CustomListAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<ListAllPermohonanRT> listAllPermohonanRTs;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<ListAllPermohonanRT> listAllPermohonanRTs){
        this.activity = activity;
        this.listAllPermohonanRTs = listAllPermohonanRTs;
    }

    @Override
    public int getCount(){
        return listAllPermohonanRTs.size();
    }

    @Override
    public Object getItem(int location){
        return listAllPermohonanRTs.get(location);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.activity_list_row,null);
        if(imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView)convertView.findViewById(R.id.thumbnail);

        TextView namaDepan = (TextView) convertView.findViewById(R.id.namaDepan);
        TextView nik = (TextView) convertView.findViewById(R.id.nik);
        TextView jenisPelayanan = (TextView)convertView.findViewById(R.id.jenisPelayanan);

        ListAllPermohonanRT l = listAllPermohonanRTs.get(position);

        thumbNail.setImageUrl(l.getThumbnailUrl(), imageLoader);

        namaDepan.setText("Nama: " + l.getNamaDepan());
        nik.setText("NIK: " +l.getNik());
        jenisPelayanan.setText(l.getJenisPelayanan());

        return convertView;
    }


    }



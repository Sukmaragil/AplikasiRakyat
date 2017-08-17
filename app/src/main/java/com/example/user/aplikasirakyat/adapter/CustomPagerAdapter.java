package com.example.user.aplikasirakyat.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.aplikasirakyat.R;

/**
 * Created by sukmaragil on 15-Aug-17.
 */

public class CustomPagerAdapter extends PagerAdapter {
    Context context;
    int images[];
    //String text[];
    LayoutInflater layoutInflater;

    public CustomPagerAdapter(Context context, int images[]) {
        this.context = context;
        this.images = images;
        //this.text = text;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);
        //TextView textView = layoutInflater.inflate(R.layout.title, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        //TextView tView = (TextView) textView.findViewById(R.id.newsTitle);
        imageView.setImageResource(images[position]);

        container.addView(itemView);
        //Listening to image click
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image" + (position+1), Toast.LENGTH_LONG).show();
            }
        });
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

package com.example.user.aplikasirakyat.adapter;

/**
 * Created by sukmaragil on 12-May-17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.aplikasirakyat.Movie;
import com.example.user.aplikasirakyat.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count, progress, numb, status;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            progress = (TextView) view.findViewById(R.id.progress);
//            numb = (TextView) view.findViewById(R.id.numb);
//            status = (TextView) view.findViewById(R.id.hidden_id);
        }
    }

    public MoviesAdapter(Context mContext, List<Movie> moviesList) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.progress.setText(movie.getProgress());
//        holder.numb.setText(movie.getNumb());
//        holder.status.setId(movie.getStatus());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
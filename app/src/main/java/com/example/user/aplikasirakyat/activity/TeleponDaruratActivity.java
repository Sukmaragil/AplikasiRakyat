package com.example.user.aplikasirakyat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.user.aplikasirakyat.DividerItemDecoration;
import com.example.user.aplikasirakyat.Movie;
import com.example.user.aplikasirakyat.R;
import com.example.user.aplikasirakyat.RecyclerTouchListener;
import com.example.user.aplikasirakyat.Telepon;
import com.example.user.aplikasirakyat.adapter.TeleponAdapter;

import java.util.ArrayList;
import java.util.List;

public class TeleponDaruratActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Telepon> teleponList = new ArrayList<>();
    private TeleponAdapter telpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telepon_darurat);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        telpAdapter = new TeleponAdapter(teleponList);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(telpAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new MainActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = teleponList.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void prepareTeleponData() {
        Telepon telepon = new Telepon("Ambulan", "Action & Adventure", "118");
        teleponList.add(telepon);

        telepon = new Telepon("Pemadam Kebakaran", "Animation, Kids & Family", "113");
        teleponList.add(telepon);

        telepon = new Telepon("Gangguan Telkom", "Action", "117");
        teleponList.add(telepon);

        telepon = new Telepon("Gangguan Listrik PLN", "Animation", "123");
        teleponList.add(telepon);

        telepon = new Telepon("SAR (Search and Rescue)", "Science Fiction & Fantasy", "115");
        teleponList.add(telepon);

        telepon = new Telepon("Mission: Impossible Rogue Nation", "Action", "2015");
        teleponList.add(telepon);

        telepon = new Telepon("Up", "Animation", "2009");
        teleponList.add(telepon);

        telepon = new Telepon("Star Trek", "Science Fiction", "2009");
        teleponList.add(telepon);

        telepon = new Telepon("The LEGO Telepon()", "Animation", "2014");
        teleponList.add(telepon);

        telepon = new Telepon("Iron Man", "Action & Adventure", "2008");
        teleponList.add(telepon);

        telepon = new Telepon("Aliens", "Science Fiction", "1986");
        teleponList.add(telepon);

        telepon = new Telepon("Chicken Run", "Animation", "2000");
        teleponList.add(telepon);

        telepon = new Telepon("Back to the Future", "Science Fiction", "1985");
        teleponList.add(telepon);

        telepon = new Telepon("Raiders of the Lost Ark", "Action & Adventure", "1981");
        teleponList.add(telepon);

        telepon = new Telepon("Goldfinger", "Action & Adventure", "1965");
        teleponList.add(telepon);

        telepon = new Telepon("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        teleponList.add(telepon);

        telpAdapter.notifyDataSetChanged();
    }
}

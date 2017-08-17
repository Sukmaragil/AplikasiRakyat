package com.example.user.aplikasirakyat.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.user.aplikasirakyat.Config;
import com.example.user.aplikasirakyat.DataFish;
import com.example.user.aplikasirakyat.Movie;
import com.example.user.aplikasirakyat.R;
import com.example.user.aplikasirakyat.RecyclerTouchListener;
import com.example.user.aplikasirakyat.adapter.AdapterFish;
import com.example.user.aplikasirakyat.adapter.CustomPagerAdapter;
import com.example.user.aplikasirakyat.adapter.MoviesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    private boolean LoggedIn = false;

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterFish sAdapter;
    private MoviesAdapter mAdapter;
    private FloatingActionButton btnCreate;
    ViewPager viewPager;
    int images[] = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3};
    CustomPagerAdapter myCustomPagerAdapter;

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView) findViewById(R.id.webView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        btnCreate = (FloatingActionButton) findViewById(R.id.fabReport);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

//        check logged in or not for show in apps
//        if(!loggedIn) {
//            View v = getLayoutInflater().inflate(R.layout.start, null);
//        } else {
//            new Asyncfetch().execute();
//            // webview.loadData(summary, "text/html", "UTF-8");
//            // webview.loadUrl("http:www.google.com/");
//            // navigation drawer
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//            drawer.addDrawerListener(toggle);
//            toggle.syncState();
//
//            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//            navigationView.setNavigationItemSelectedListener(this);
//
//            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//            mAdapter = new MoviesAdapter(movieList);
//            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//            recyclerView.setItemAnimator(new DefaultItemAnimator());
//            recyclerView.setAdapter(mAdapter);
//
//            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
//                @Override
//                public void onClick(View view, int position) {
//                          Movie movie = movieList.get(position);
//                          Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
//                          Intent i = new Intent(getApplicationContext(),
//                              ViewSuratActivity.class);
//                          i.putExtra("status", movieList.get(position).getStatus());
//                          i.putExtra("progress", movieList.get(position).getProgress());
//                          i.putExtra("nomor", movieList.get(position).getNumb());
//                          i.putExtra("name", movieList.get(position).getName());
//                          i.putExtra("keterangan", movieList.get(position).getKeterangan());
//                          i.putExtra("jenis_surat", movieList.get(position).getTitle());
//                          startActivity(i);
//                }
//
//                @Override
//                public void onLongClick(View view, int position) {
//
//                }
//            }));
//
//            prepareMovieData();
//            // action button click listener
//        }

//        new Asyncfetch().execute();
        // webview.loadData(summary, "text/html", "UTF-8");
        // webview.loadUrl("http:www.google.com/");
        // navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        myCustomPagerAdapter = new CustomPagerAdapter(MainActivity.this, images);
        viewPager.setAdapter(myCustomPagerAdapter);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        movieList = new ArrayList<>();
        mAdapter = new MoviesAdapter(this, movieList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
//                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),
                        ViewSuratActivity.class);
                i.putExtra("status", movieList.get(position).getStatus());
                i.putExtra("progress", movieList.get(position).getProgress());
                i.putExtra("nomor", movieList.get(position).getNumb());
                i.putExtra("name", movieList.get(position).getName());
                i.putExtra("keterangan", movieList.get(position).getKeterangan());
                i.putExtra("jenis_surat", movieList.get(position).getTitle());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();
        // action button click listener
        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        PermohonanActivity.class);
                startActivity(i);
            }
        });
    }



    //load data from database
    private class Asyncfetch extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //method will be running on UI thread
            progressDialog.setMessage("\tLoading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL("sukmaragil.xyz/fishData.json");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                //setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(100000);
                conn.setRequestMethod("POST");
                // setDoOutput to true as we receive data from json file
                conn.setDoOutput(true);
            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                int response_code = conn.getResponseCode();
                //check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    //read data from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    //pass data to onPostExecute method
                    return (result.toString());
                } else {
                    return ("Unsuccessful");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String result) {
            //this method will be running on UI thread
            progressDialog.dismiss();
            List<DataFish> data = new ArrayList<>();

            progressDialog.dismiss();
            try {
                JSONArray jsonArray = new JSONArray(result);
                // extract data from json and store into Arraylist as class object

                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject json_data = jsonArray.getJSONObject(i);
                    DataFish fishData = new DataFish();
                    fishData.fishImage = json_data.getString("fish_img");
                    fishData.fishName = json_data.getString("fish_name");
                    fishData.catName = json_data.getString("cat_name");
                    fishData.sizeName = json_data.getString("size_name");
                    fishData.price = json_data.getInt("price");
                    data.add(fishData);
                }
                // setup and handover data to recyclerview
                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

                sAdapter = new AdapterFish(MainActivity.this, data);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);


            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }


    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    // dummy data to show recycler list view
    private void prepareMovieData() {
        Movie movie = new Movie("Surat belum menikah", "Luthfi", "19284098", "selesai", "Saya mau menikah", 1);
        movieList.add(movie);

        movie = new Movie("Surat belum memiliki rumah", "Luthfi", "18294722", "selesai", "Saya mau beli rumah kredit rakyat", 1);
        movieList.add(movie);

        movie = new Movie("Surat izin bepergian", "Luthfi", "17284521", "proses", "Saya ingin backpacker ke beberapa provinsi di Indonesia", 0);
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }

    // back pressed navigation button
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.create) {
            Intent intent = new Intent(MainActivity.this,PermohonanActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.kirimSaran) {
            Intent intent = new Intent(MainActivity.this,SaranActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.Bantuan) {
            Intent intent = new Intent(MainActivity.this,BantuanActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.Profile) {
            Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.Logout) {
            Toast.makeText(this, "Successfully Logout", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
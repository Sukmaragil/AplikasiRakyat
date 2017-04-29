package com.example.user.aplikasirakyat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView)findViewById(R.id.webView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String summary = "<html>" +
                                "<head><center><h3><bold>Selamat Datang di Go Citizen</bold></h3></center></head>" +
                "<p>Aplikasi ini dirancang sebagai alternatif dalam pengajuan permohonan pelayanan publik secara online</p>" +
                "<p>Pelayanan publik yang dapat diajukan, menurut Peraturan Walikota Bogor Nomor 29 Tahun 2013 dan Peraturan Walikota Bogor Nomor 45 Tahun 2013 antara lain: </p>" +
                "<ol>" +
                "<li>Surat Keterangan Belum Pernah Menikah</li>" +
                "<li>Surat Keterangan Penghasilan Tidak Tetap</li>" +
                "<li>Surat Keterangan Belum Memiliki Rumah</li>" +
                "<li>Surat Keterangan Tidak Keberatan Tetangga</li>" +
                "<li>Surat Keterangan Izin Berpergian</li>"+

                        "</html>";
        webview.loadData(summary, "text/html", "UTF-8");
     //   webview.loadUrl("http:www.google.com/");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

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

        if (id == R.id.belum_menikah) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this,PermohonanBelumMenikah.class);
            startActivity(intent);
        } else if (id == R.id.penghasilan_tidak_tetap) {
            Intent intent = new Intent(MainActivity.this,PermohonanPenghasilanTidakTetap.class);
            startActivity(intent);


        } else if (id == R.id.belum_memiliki_rumah) {
            Intent intent = new Intent(MainActivity.this,PermohonanBelumMemilikiRumah.class);
            startActivity(intent);

        } else if (id == R.id.tidak_keberatan_tetangga) {
            Intent intent = new Intent(MainActivity.this,PermohonanTidakKeberatanTetangga.class);
            startActivity(intent);

        } else if (id == R.id.izin_berpergian) {
            Intent intent = new Intent(MainActivity.this,PermohonanIzinBerpergian.class);
            startActivity(intent);

        }
        else if (id == R.id.info_belum_memiliki_rumah) {
            Intent intent = new Intent(MainActivity.this,InformasiBelumMemilikiRumah.class);
            startActivity(intent);

        }
        else if (id == R.id.info_belum_menikah) {
            Intent intent = new Intent(MainActivity.this,InformasiBelumMenikah.class);
            startActivity(intent);

        }
        else if (id == R.id.info_izin_berpergian) {
            Intent intent = new Intent(MainActivity.this,InformasiIzinBerpergian.class);
            startActivity(intent);

        }
        else if (id == R.id.info_penghasilan_tidak_tetap) {
            Intent intent = new Intent(MainActivity.this,InformasiPenghasilanTidakTetap.class);
            startActivity(intent);

        }
        else if (id == R.id.info_tidak_keberatan_tetangga) {
            Intent intent = new Intent(MainActivity.this,InformasiTidakKeberatanTetangga.class);
            startActivity(intent);

        }
        else if(id == R.id.lacak){
            Intent intent = new Intent(MainActivity.this,LacakPermohonan.class);
            startActivity(intent);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

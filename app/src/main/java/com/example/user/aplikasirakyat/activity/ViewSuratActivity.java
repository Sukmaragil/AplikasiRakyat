package com.example.user.aplikasirakyat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.aplikasirakyat.R;

public class ViewSuratActivity extends AppCompatActivity {
    public TextView nama, nomer, keperluan;
    public Button download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int status = getIntent().getExtras().getInt("status");

        Bundle bundle = getIntent().getExtras();

        String keterangan = bundle.getString("keterangan");
        String name = bundle.getString("name");
        String nomor = bundle.getString("nomor");
        String jenis = bundle.getString("jenis_surat");

        SpannableStringBuilder resultBuilder = new SpannableStringBuilder();
        SpannableString item = new SpannableString(keterangan);
        item.setSpan(new AbsoluteSizeSpan(18, true), 0, item.length(), 0);
        resultBuilder.append(item);

        if (status == 0) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_surat_disable);

            nomer = (TextView) findViewById(R.id.noSurat);
            nama = (TextView) findViewById(R.id.namaLengkap);
            keperluan = (TextView) findViewById(R.id.keperluan);

            nomer.setText(nomor);
            nama.setText(name);
            keperluan.setText(keterangan, TextView.BufferType.SPANNABLE);

            Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else if (status == 1) { // surat available
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_surat);

            nomer = (TextView) findViewById(R.id.noSurat);
            nama = (TextView) findViewById(R.id.namaLengkap);
            keperluan = (TextView) findViewById(R.id.keperluan);
            download = (Button) findViewById(R.id.btnDownload);


            nomer.setText(nomor);
            nama.setText(name);
            keperluan.setText(keterangan, TextView.BufferType.SPANNABLE);

            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    final ProgressDialog progressDialog = new ProgressDialog(ViewSuratActivity.this,
//                            R.style.Theme_AppCompat_DayNight_Dialog);
//                    progressDialog.setIndeterminate(true);
//                    progressDialog.setMessage("Mengunduh...");
//                    progressDialog.show();
//
//                    Runnable progressRunnable = new Runnable() {
//
//                        @Override
//                        public void run() {
//                            progressDialog.cancel();
//                        }
//                    };
//
//                    Handler pdCanceller = new Handler();
//                    pdCanceller.postDelayed(progressRunnable, 3000);

                    Toast.makeText(getApplicationContext(),"Mengunduh . . .", Toast.LENGTH_LONG).show();
                }
            });

            Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}

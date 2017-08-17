package com.example.user.aplikasirakyat.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.aplikasirakyat.R;

public class SaranActivity extends AppCompatActivity {
    private EditText judul, keterangan;
    private TextInputLayout layout_judul, layout_keterangan;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saran);

        judul = (EditText) findViewById(R.id.judul_saran);
        keterangan = (EditText) findViewById(R.id.input_keterangan);

        layout_judul = (TextInputLayout) findViewById(R.id.input_judul_saran);
        layout_keterangan = (TextInputLayout) findViewById(R.id.input_layout_keterangan);
        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress ();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "Pesan berhasil dikirim", Toast.LENGTH_LONG);
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);

                    }
                }, 3000);
            }
        });



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    ProgressDialog dialog;
    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setMessage("Please wait");
        dialog.show();
        Toast.makeText(getApplicationContext(), "Pesan berhasil dikirim", Toast.LENGTH_LONG);
    }
}

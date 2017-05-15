package com.example.user.aplikasirakyat.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.aplikasirakyat.Config;
import com.example.user.aplikasirakyat.R;
import com.example.user.aplikasirakyat.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by USER on 23/06/2016.
 */
public class LacakPermohonan extends AppCompatActivity implements View.OnClickListener {

    private int lji = 9;
    private EditText noPelayanan;
    private TextView textViewnik, judulNIK;
    private TextView textViewnamaLengkap,judulNamaLengkap;
    private TextView textViewjenisPermohonan, judulJenisPermohonan;
    private TextView textViewtglPengajuan, judulTglPengajuan;
    private TextView textViewvalidasiRT, judulValidasiRT;
    private TextView textViewvalidasiRW, judulValidasiRW;
    private TextView textViewvalidasiKelurahan, TglValidasiRT, TglValidasiRW, TglValidasiLurah, judulValidasiLurah, judulTglRT, judulTglRW, judulTglLurah;

    public String nik,namaLengkap,jenisPermohonan,tglPengajuan,validasiRT,validasiRW,validsaiKelurahan, tglValidasiRT, tglValidasiRW, tglValidasiLurah;

    private String nomerResi;

  //  private String noPelayanan;
    private Button buttonLacak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lacak_permohonan);

        noPelayanan = (EditText)findViewById(R.id.noPelayanan);


        textViewnik = (TextView)findViewById(R.id.nik);
        textViewnamaLengkap = (TextView) findViewById(R.id.namaLengkap);
        textViewjenisPermohonan = (TextView) findViewById(R.id.jenisPermohonan);
        textViewtglPengajuan = (TextView) findViewById(R.id.tglPengajuan);
        textViewvalidasiRT = (TextView) findViewById(R.id.validasiRT);
        textViewvalidasiRW = (TextView) findViewById(R.id.validasiRW);
        textViewvalidasiKelurahan = (TextView) findViewById(R.id.validasiKelurahan);
        TglValidasiRT = (TextView)findViewById(R.id.TglvalidasiRT);
        TglValidasiRW = (TextView)findViewById(R.id.TglvalidasiRW);
        TglValidasiLurah = (TextView)findViewById(R.id.TglvalidasiLurah);

        judulNIK = (TextView)findViewById(R.id.judulNIK);
        judulNamaLengkap = (TextView)findViewById(R.id.judulNamaLengkap);
        judulJenisPermohonan = (TextView)findViewById(R.id.judulJenisPermohonan);
        judulTglPengajuan = (TextView)findViewById(R.id.judulTglPengajuan);
        judulValidasiRT = (TextView)findViewById(R.id.judulValidasiRT);
        judulValidasiRW =  (TextView)findViewById(R.id.judulValidasiRW);
        judulValidasiLurah = (TextView)findViewById(R.id.judulValidasiLurah);
        judulTglRT = (TextView)findViewById(R.id.judulTglRT);
        judulTglRW = (TextView)findViewById(R.id.judulTglRW);
        judulTglLurah = (TextView)findViewById(R.id.judulTglLurah);

        buttonLacak = (Button) findViewById(R.id.buttonLacak);

        textViewnik.setVisibility(View.INVISIBLE);
        textViewnamaLengkap.setVisibility(View.INVISIBLE);
        textViewjenisPermohonan.setVisibility(View.INVISIBLE);
        textViewtglPengajuan.setVisibility(View.INVISIBLE);
        textViewvalidasiRT.setVisibility(View.INVISIBLE);
        textViewvalidasiRW.setVisibility(View.INVISIBLE);
        textViewvalidasiKelurahan.setVisibility(View.INVISIBLE);
        TglValidasiRT.setVisibility(View.INVISIBLE);
        TglValidasiRW.setVisibility(View.INVISIBLE);
        TglValidasiLurah.setVisibility(View.INVISIBLE);

        judulNIK.setVisibility(View.INVISIBLE);
        judulNamaLengkap.setVisibility(View.INVISIBLE);
        judulJenisPermohonan.setVisibility(View.INVISIBLE);
        judulTglPengajuan.setVisibility(View.INVISIBLE);
        judulValidasiRT.setVisibility(View.INVISIBLE);
        judulValidasiRW.setVisibility(View.INVISIBLE);
        judulValidasiLurah.setVisibility(View.INVISIBLE);
        judulTglRT.setVisibility(View.INVISIBLE);
        judulTglRW.setVisibility(View.INVISIBLE);
        judulTglLurah.setVisibility(View.INVISIBLE);

        buttonLacak.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void onClick(View v) {
        if(v == buttonLacak){
            nomerResi = noPelayanan.getText().toString();
            GetPermohonan gp = new GetPermohonan();
            gp.execute();

            textViewnik.setVisibility(View.VISIBLE);
            textViewnamaLengkap.setVisibility(View.VISIBLE);
            textViewjenisPermohonan.setVisibility(View.VISIBLE);
            textViewtglPengajuan.setVisibility(View.VISIBLE);
            textViewvalidasiRT.setVisibility(View.VISIBLE);
            textViewvalidasiRW.setVisibility(View.VISIBLE);
            textViewvalidasiKelurahan.setVisibility(View.VISIBLE);
            TglValidasiRT.setVisibility(View.VISIBLE);
            TglValidasiRW.setVisibility(View.VISIBLE);
            TglValidasiLurah.setVisibility(View.VISIBLE);

            judulNIK.setVisibility(View.VISIBLE);
            judulNamaLengkap.setVisibility(View.VISIBLE);
            judulJenisPermohonan.setVisibility(View.VISIBLE);
            judulTglPengajuan.setVisibility(View.VISIBLE);
            judulValidasiRT.setVisibility(View.VISIBLE);
            judulValidasiRW.setVisibility(View.VISIBLE);
            judulValidasiLurah.setVisibility(View.VISIBLE);
            judulTglRT.setVisibility(View.VISIBLE);
            judulTglRW.setVisibility(View.VISIBLE);
            judulTglLurah.setVisibility(View.VISIBLE);



        }
    }


        class GetPermohonan extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LacakPermohonan.this, "Mengambil Data ...", "Tunggu ...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                if(s.contains("null")){

                  //  Toast.makeText(LacakPermohonan.this,"salah",Toast.LENGTH_LONG).show();

                    new AlertDialog.Builder(LacakPermohonan.this).setMessage("Maaf, No Pelayanan yang Anda masukkan tidak terdaftar, Silahkan Coba Lagi")
                            .setTitle("Pelacakan Gagal")
                            .setIcon(R.drawable.ic_warning_black_24dp)
                            .setPositiveButton("OKE",null)
                            .show();

                } else {

                    hasilLacak(s);

                    textViewnik.setText(nik);
                    textViewnamaLengkap.setText(namaLengkap);
                    textViewjenisPermohonan.setText(jenisPermohonan);
                    textViewtglPengajuan.setText(tglPengajuan);


                    if (validasiRT.equals("1")) {
                        textViewvalidasiRT.setText("SUDAH");
                        TglValidasiRT.setText(tglValidasiRT);
                    } else {
                        textViewvalidasiRT.setText("BELUM");
                    }

                    if (validasiRW.equals("1")) {
                        textViewvalidasiRW.setText("SUDAH");
                        TglValidasiRW.setText(tglValidasiRW);

                    } else {
                        textViewvalidasiRW.setText("BELUM");
                    }
                    if (validsaiKelurahan.equals("1")) {
                        textViewvalidasiKelurahan.setText("SUDAH");
                        TglValidasiLurah.setText(tglValidasiLurah);
                    } else {
                        textViewvalidasiKelurahan.setText("BELUM");
                    }
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_LACAK_PERMOHONAN, nomerResi);
                return s;

            }
        }

    public void hasilLacak(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);

             nik = c.getString("nik");
             namaLengkap = c.getString(Config.TAG_PENDUDUK_NAMA_LENGKAP);
             jenisPermohonan = c.getString("namaPelayanan");
             tglPengajuan = c.getString("tanggalPelayanan");
             validasiRT = c.getString("validasiRT").toString();
             validasiRW = c.getString("validasiRW");
             validsaiKelurahan = c.getString("validasiKelurahan");
             tglValidasiRT = c.getString("tanggalRT").toString();
             tglValidasiRW = c.getString("tanggalRW").toString();
             tglValidasiLurah = c.getString("tanggalLurah").toString();

        } catch (JSONException e){
            e.printStackTrace();
        }
    }


}

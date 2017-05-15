package com.example.user.aplikasirakyat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.example.user.aplikasirakyat.R;

/**
 * Created by USER on 16/06/2016.
 */
public class InformasiIzinBerpergian  extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_belum_menikah);

        webView = (WebView)findViewById(R.id.webView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String summary = "<html>" +
                "<head><center><h4><bold>Informasi Pengajuan Permohonan Surat Keterangan Izin Berpergian</bold></h4></center></head>" +
                "<body>" +

                "<p>Berikut ini adalah langkah - langkah pengajuan permohonan Surat Keterangan Izin Berpergian </p>" +
                "<ol>" +
                "<li>Klik tombol navigasi pada pojok kiri atas halaman <strong>'Beranda'</strong>.</li>" +
                "<li>Pada bagian 'Ajukan Permohonan', klik pilihan <strong>'Izin Berpergian'</strong>.</li> " +
                "<li>Isi Nomor Induk Kependudukan sesuai identitas Anda.</li>" +
                "<li>Isi Alamat Email Anda yang aktif. Hal ini diperlukan untuk mengirimkan nomer resi pelayanan Anda.</li>" +
                "<li>Upload Identitas Anda, KTP (Kartu Tanda Penduduk) dan KK (Kartu Keluarga).</li>" +
                "<li>Isi Keperluan Anda berpergian .</li>"+
                "<li>Isi Tujuan Anda berpergian.</li>"+
                "<li>Isi lama Anda berpergian.</li>"+
                "<li>Isi orang yang turut berpergian bersama Anda.</li>"+
                "<li>Isi barang yang Anda bawa ketika berpergian.</li>"+
                "<li>Isi kendaraan yang Anda gunakan ketika berpergian.</li>"+
                "<li>Klik tombol <strong>'AJUKAN PERMOHONAN'</strong> untuk mengirim data diatas.</li>" +
                "<li>Tunggu beberapa detik, hingga muncul pesan sukses.</li>" +
                "<li>Selamat Anda <strong>BERHASIL</strong> mengajukan permohonan. Permohonan Anda sedang diproses. Silahkan cek Email Anda untuk melihat nomer resi.</li>" +
                "</ol>" +

                "</body>" +
                "</html>";
        webView.loadData(summary, "text/html", "UTF-8");
    }
}
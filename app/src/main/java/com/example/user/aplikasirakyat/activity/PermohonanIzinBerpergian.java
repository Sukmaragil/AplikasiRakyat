package com.example.user.aplikasirakyat.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.aplikasirakyat.Config;
import com.example.user.aplikasirakyat.R;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by USER on 20/04/2016.
 */
public class PermohonanIzinBerpergian extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNik;
    private EditText editTextEmail;
    private EditText editTextMaksudBerpergian;
    private EditText editTextTujuan;
    private EditText editTextLamaBerpergian;
    private EditText editTextYangTurutBerpergian;
    private EditText editTextBarangYangDibawa;
    private EditText editTextKendaraanYangDigunakan;

    final String TAG = this.getClass().getName();
    private ImageView ivImage, ivImage2;
    private TextInputLayout input_layout_maksud_berpergian, input_layout_nik, input_layout_email;
    private TextInputLayout input_layout_tujuan_berpergian, input_layout_lama_berpergian, input_layout_yang_turut_berpergian;
    private TextInputLayout input_layout_barang_yang_dibawa,input_layout_kendaraan_yang_digunakan;
    private GalleryPhoto galleryPhoto;
    private final int GALLERY_REQUEST = 123;
    private final int GALLERY_REQUEST2 = 321;

    private Button ivGallery, ivGallery2;
    private Bitmap bitmap, bitmap2;
    private Uri filePath, filePath2;
    private static final int STORAGE_PERMISSION_CODE = 234;
    private ProgressDialog loading;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permohonan_izin_berpergian);

        editTextNik = (EditText) findViewById(R.id.editTextNik);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextMaksudBerpergian = (EditText) findViewById(R.id.editTextMaksudBerpergian);
        editTextTujuan = (EditText) findViewById(R.id.editTujuanBerpergian);
        editTextLamaBerpergian = (EditText) findViewById(R.id.editTextLamaBerpergian);
        editTextYangTurutBerpergian = (EditText) findViewById(R.id.editTextYangTurutBerpergian);
        editTextBarangYangDibawa = (EditText) findViewById(R.id.editTextBarangYangDibawa);
        editTextKendaraanYangDigunakan = (EditText) findViewById(R.id.editTextKendaraanYangDigunakan);

        input_layout_nik = (TextInputLayout) findViewById(R.id.input_layout_nik);
        input_layout_email = (TextInputLayout) findViewById(R.id.input_layout_email);
        input_layout_maksud_berpergian = (TextInputLayout) findViewById(R.id.input_layout_maksud_berpergian);
        input_layout_tujuan_berpergian = (TextInputLayout) findViewById(R.id.input_layout_tujuan_berpergian);
        input_layout_lama_berpergian = (TextInputLayout) findViewById(R.id.input_layout_lama_berpergian);
        input_layout_yang_turut_berpergian = (TextInputLayout) findViewById(R.id.input_layout_yang_turut_berpergian);
        input_layout_barang_yang_dibawa = (TextInputLayout) findViewById(R.id.input_layout_barang_yang_dibawa);
        input_layout_kendaraan_yang_digunakan = (TextInputLayout) findViewById(R.id.input_layout_kendaraan_yang_digunakan);

        editTextNik.addTextChangedListener(new MyTextWatcher(editTextNik));
        editTextEmail.addTextChangedListener(new MyTextWatcher(editTextEmail));
        editTextMaksudBerpergian.addTextChangedListener(new MyTextWatcher(editTextMaksudBerpergian));
        editTextTujuan.addTextChangedListener(new MyTextWatcher(editTextTujuan));
        editTextLamaBerpergian.addTextChangedListener(new MyTextWatcher(editTextLamaBerpergian));
        editTextYangTurutBerpergian.addTextChangedListener(new MyTextWatcher(editTextYangTurutBerpergian));
        editTextBarangYangDibawa.addTextChangedListener(new MyTextWatcher(editTextBarangYangDibawa));
        editTextKendaraanYangDigunakan.addTextChangedListener(new MyTextWatcher(editTextKendaraanYangDigunakan));

        ivGallery = (Button) findViewById(R.id.ivGallery);
        ivGallery2 = (Button) findViewById(R.id.ivGallery2);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        ivImage2 = (ImageView) findViewById(R.id.ivImage2);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonSubmit.setOnClickListener(this);
        ivGallery.setOnClickListener(this);
        ivGallery2.setOnClickListener(this);

        galleryPhoto = new GalleryPhoto(getApplicationContext());

    }

    public void cekNikdanPhoto(){

        String nik = editTextNik.getText().toString().trim();

        HashMap<String, String> postData = new HashMap<>();
        postData.put(Config.KEY_PERMOHONAN_NIK,nik);

        PostResponseAsyncTask task = new PostResponseAsyncTask(PermohonanIzinBerpergian.this, postData, new AsyncResponse() {

            @Override
            public void processFinish(String s) {
                if(s.contains("Berhasil")){
                    uploadMultiPart();
                } else if(s.contains("NIK")) {
                    new AlertDialog.Builder(PermohonanIzinBerpergian.this)
                            .setTitle("Permohonan Gagal")
                            .setMessage("Maaf, Nomor Induk Kependudukan (NIK) Anda belum terdaftar. Silahkan hubungi kantor kelurahan Anda")
                            .setPositiveButton("Oke", null)
                            .setIcon(R.drawable.ic_warning_black_24dp)
                            .show();
                }
            }
        });
        task.execute(Config.URL_CEK_NIK);
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                Toast.makeText(getApplicationContext(),
                        "Tidak Bisa Terhubung ke Server", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                Toast.makeText(getApplicationContext(),
                        "URL Error", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                Toast.makeText(getApplicationContext(),
                        "Protocol Error", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                Toast.makeText(getApplicationContext(),
                        "Encoding Error", Toast.LENGTH_SHORT ).show();
            }
        });
    }

    public void uploadMultiPart(){

        Calendar calender = Calendar.getInstance();

        final String path = getPath(filePath);
        final String path2 = getPath(filePath2);
        final String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        final String pelayananId = "7";
        final String nik = editTextNik.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String maksudBerpergian = editTextMaksudBerpergian.getText().toString().trim();
        final String tujuan = editTextTujuan.getText().toString().trim();
        final String lama = editTextLamaBerpergian.getText().toString().trim();
        final String turutBerpergian = editTextYangTurutBerpergian.getText().toString().trim();
        final String barang = editTextBarangYangDibawa.getText().toString().trim();
        final String kendaraan = editTextKendaraanYangDigunakan.getText().toString().trim();
        final String tanggalPelayanan = sdf.format(calender.getTime());


        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    //   ProgressDialog.show(PermohonanBelumMenikah.this, "Loading", "Mohon Menunggu", false, false);
                    Thread.sleep(3000);

                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(PermohonanIzinBerpergian.this, uploadId, Config.URL_ADD_PERMOHONAN_BERPERGIAN)
                            .addFileToUpload(path, "ktp")
                            .addFileToUpload(path2, "kk")
                            .addParameter(Config.KEY_PERMOHONAN_NIK, nik)
                            .addParameter(Config.KEY_PERMOHONAN_EMAIL, email)
                            .addParameter(Config.KEY_PELAYANAN_ID, pelayananId)
                            .addParameter(Config.KEY_TANGGAL_PELAYANAN, tanggalPelayanan)
                            .addParameter(Config.KEY_MAKSUD_BERPERGIAN, maksudBerpergian)
                            .addParameter(Config.KEY_TUJUAN_BERPERGIAN, tujuan)
                            .addParameter(Config.KEY_LAMA_BERPERGIAN, lama)
                            .addParameter(Config.KEY_ANGGOTA_BERPERGIAN, turutBerpergian)
                            .addParameter(Config.KEY_BARANG_BAWAAN_BERPERGIAN, barang)
                            .addParameter(Config.KEY_KENDARAAN_BERPERGIAN, kendaraan)
                            .setNotificationConfig(new UploadNotificationConfig())
                            .setMaxRetries(2)
                            .startUpload();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPreExecute() {

                loading = new ProgressDialog(PermohonanIzinBerpergian.this);
                loading.setTitle("Loading");
                loading.setMessage("Mohon Tunggu");
                loading.setCancelable(false);
                loading.setIndeterminate(false);
                loading.show();
            }
            @Override
            protected void onPostExecute(Void result) {
                if (loading != null) {
                    loading.dismiss();

                    AlertDialog.Builder builder = new AlertDialog.Builder(PermohonanIzinBerpergian.this);
                    builder.setTitle("Permohonan Berhasil");
                    builder.setIcon(R.drawable.ic_check_black_24dp);
                    builder.setMessage("Selamat ! Pengajuan Anda berhasil dilakukan. Silahkan cek Email untuk melihat nomer resi pelayanan");
                    builder.setPositiveButton("Oke", null);
                    final AlertDialog alert = builder.create();
                    PermohonanIzinBerpergian.this.runOnUiThread(new java.lang.Runnable() {
                        public void run() {
                            //show AlertDialog
                            alert.show();
                        }
                    });
                }
            }


        };
        task.execute();
    }

    public String getPath(Uri uri){
        Cursor cursor = getContentResolver().query(uri, null, null, null,null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onClick(View v){
        if(v == buttonSubmit){
            submitForm();
        } else if(v == ivGallery){
            showFileChooser();
        } else if(v == ivGallery2){
            showFileChooser2();
        }
    }

    private void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST);
    }

    private void showFileChooser2(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST2);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == GALLERY_REQUEST2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath2 = data.getData();
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath2);
                ivImage2.setImageBitmap(bitmap2);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view){
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.editTextNik:
                    validateNik();
                    break;
                case R.id.editTextEmail:
                    validateEmail();
                    break;
                case R.id.editTextMaksudBerpergian:
                    validateMaksud();
                    break;
                case R.id.editTujuanBerpergian:
                    validateTujuan();
                    break;
                case R.id.editTextLamaBerpergian:
                    validateLama();
                    break;
                case R.id.editTextYangTurutBerpergian:
                    validateAnggota();
                    break;
                case R.id.editTextBarangYangDibawa:
                    validateBarang();
                    break;
                case R.id.input_layout_kendaraan_yang_digunakan:
                    validateKendaraan();
                    break;
            }
        }
    }

    private void submitForm() {
        if (!validateNik()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validateMaksud()){
            return;
        }

        if(!validateTujuan()){
            return;
        }

        if (!validateAnggota()) {

            return;
        }
        if(!validateBarang()){
            return;
        }

        if(!validateKendaraan()){
            return;
        }
        if(!validateLama()){
            return;
        }

        cekNikdanPhoto();
    }

    private boolean validateNik() {
        if (editTextNik.getText().toString().trim().isEmpty()) {
            input_layout_nik.setError(getString(R.string.err_nik));
            requestFocus(editTextNik);
            return false;
        } else {
            input_layout_nik.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMaksud() {
        if (editTextMaksudBerpergian.getText().toString().trim().isEmpty()) {
            input_layout_maksud_berpergian.setError(getString(R.string.err_maksud_berpergian));
            requestFocus(editTextMaksudBerpergian);
            return false;
        } else {
            input_layout_maksud_berpergian.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail(){
        String email = editTextEmail.getText().toString().trim();
        if(email.isEmpty() || !isValidEmail(email)){
            input_layout_email.setError(getString(R.string.err_email));
            requestFocus(editTextEmail);
            return false;
        } else {
            input_layout_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateTujuan() {
        if (editTextTujuan.getText().toString().trim().isEmpty()) {
            input_layout_tujuan_berpergian.setError(getString(R.string.err_tujuan_berpergian));
            requestFocus(editTextTujuan);
            return false;
        } else {
            input_layout_tujuan_berpergian.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateLama() {
        if (editTextLamaBerpergian.getText().toString().trim().isEmpty()) {
            input_layout_lama_berpergian.setError(getString(R.string.err_lama_berpergian));
            requestFocus(editTextLamaBerpergian);
            return false;
        } else {
            input_layout_lama_berpergian.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAnggota() {
        if (editTextYangTurutBerpergian.getText().toString().trim().isEmpty()) {
            input_layout_yang_turut_berpergian.setError(getString(R.string.err_anggota_berpergian));
            requestFocus(editTextYangTurutBerpergian);
            return false;
        } else {
            input_layout_yang_turut_berpergian.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateBarang() {
        if (editTextBarangYangDibawa.getText().toString().trim().isEmpty()) {
            input_layout_barang_yang_dibawa.setError(getString(R.string.err_barang_berpergian));
            requestFocus(editTextBarangYangDibawa);
            return false;
        } else {
            input_layout_barang_yang_dibawa.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateKendaraan() {
        if (editTextKendaraanYangDigunakan.getText().toString().trim().isEmpty()) {
            input_layout_kendaraan_yang_digunakan.setError(getString(R.string.err_kendaraan_berpergian));
            requestFocus(editTextKendaraanYangDigunakan);
            return false;
        } else {
            input_layout_kendaraan_yang_digunakan.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}


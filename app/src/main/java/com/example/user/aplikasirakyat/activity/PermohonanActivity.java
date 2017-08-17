package com.example.user.aplikasirakyat.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.aplikasirakyat.AutoInsertEditText;
import com.example.user.aplikasirakyat.R;

import java.util.Calendar;

public class PermohonanActivity extends AppCompatActivity {
    private EditText editNIK, editFullName, editKeterangan, editDate;
    private TextInputLayout input_layout_NIK, input_layout_fullName, input_layout_date, input_layout_keterangan;
    private Spinner spinner_surat;
    private Button btnSubmit;

    private EditText date;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permohonan);

        // dropdownlist untuk jenis surat
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        editNIK = (EditText) findViewById(R.id.inputNIK);
        editFullName = (EditText) findViewById(R.id.input_fullName);
        editKeterangan = (EditText) findViewById(R.id.input_comment);
        btnSubmit = (Button) findViewById(R.id.buttonSubmit);

        editNIK.addTextChangedListener(new AutoInsertEditText(editNIK, "-", 4, 8, 12));

        // pilih tanggal
        date = (EditText) findViewById(R.id.input_date);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calendar classes instance and get current date
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR); // current year
                int mMonth = calendar.get(Calendar.MONTH); // current month
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(PermohonanActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void submit() {
        if (!validate()) {
            onSubmitFailed();
            return;
        }

        btnSubmit.setEnabled(false);

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

    public void onSubmitFailed() {
        Toast.makeText(getBaseContext(), "Submit failed", Toast.LENGTH_LONG).show();

        btnSubmit.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = editFullName.getText().toString();
        String keterangan = editKeterangan.getText().toString();
        String tanggal = date.getText().toString();

        if (name.isEmpty()) {
            editFullName.setError("masukan nama lengkap");
            valid = false;
        } else {
            editFullName.setError(null);
        }

        if (keterangan.isEmpty()) {
            editKeterangan.setError("masukan alasan untuk pembuatan surat");
            valid = false;
        } else {
            editKeterangan.setError(null);
        }

        if (tanggal.isEmpty()) {
            date.setError("masukan tanggal lahir");
            valid = false;
        } else {
            date.setError(null);
        }

        return valid;
    }

    ProgressDialog dialog;
    private void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setMessage("Please wait");
        dialog.show();
        Toast.makeText(getApplicationContext(), "Pesan berhasil dikirim", Toast.LENGTH_LONG);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner_surat = (Spinner) findViewById(R.id.spinner_surat);
        spinner_surat.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {
        spinner_surat = (Spinner) findViewById(R.id.spinner_surat);

    }

    public class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString() + id,
                    Toast.LENGTH_LONG).show();
            if (id == 0) {
                // show id = belum memiliki rumah
            } else if (id == 1) {
                //show id = belum menikah
                setContentView(R.layout.activity_permohonan2);
            } else if (id == 2) {
                // show id 2 = izin bepergian
            } else if (id == 3) {
                // show id 3 = penghasilan tidak tetap
            } else {
                // show id 4 = tidak keberatan bertetangga
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }
}

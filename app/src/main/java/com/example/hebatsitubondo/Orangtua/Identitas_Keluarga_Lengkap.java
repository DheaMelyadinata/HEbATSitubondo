package com.example.hebatsitubondo.Orangtua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hebatsitubondo.Admin.Input_Agenda;
import com.example.hebatsitubondo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.refactor.lib.colordialog.PromptDialog;

public class Identitas_Keluarga_Lengkap extends AppCompatActivity {

    Button save;
    ProgressDialog pDialog;
    EditText txt_nama_anak, txt_tempat_lahir,txt_tanggal_lahir, txt_jenis_kelamin, txt_agama,txt_anak_ke ;
    String URL_add = "http://192.168.43.62/API_HEbATSitubondo/public/anak/save";
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    private int getId_orangtua = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identitas__keluarga__lengkap);

        Toolbar tollbarianak = (Toolbar)findViewById(R.id.tollbarianak);
        setSupportActionBar(tollbarianak);
        getSupportActionBar().setTitle("Identitas Keluarga");

        save = (Button) findViewById(R.id.save);
        txt_nama_anak = (EditText) findViewById(R.id.txt_nama_anak);
        txt_tempat_lahir = (EditText) findViewById(R.id.txt_tempat_lahir);
        txt_tanggal_lahir = (EditText) findViewById(R.id.txt_tanggal_lahir);
        txt_jenis_kelamin = (EditText) findViewById(R.id.txt_jenis_kelamin);
        txt_agama = (EditText) findViewById(R.id.txt_agama);
        txt_anak_ke = (EditText) findViewById(R.id.txt_anak_ke);

        Intent intent = getIntent();
        getId_orangtua = intent.getIntExtra("id_keluarga", 0);

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        txt_tanggal_lahir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Identitas_Keluarga_Lengkap.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog = new ProgressDialog(save.getContext());
                pDialog.setCancelable(false);
                pDialog.setMessage("Proses Menyimpan ...");
                showDialog();
                StringRequest request = new StringRequest(Request.Method.POST, URL_add, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {

                        ShowDialog();
                        hideDialog();
                        txt_nama_anak.setText("");
                        txt_tempat_lahir.setText("");
                        txt_tanggal_lahir.setText("");
                        txt_jenis_kelamin.setText("");
                        txt_agama.setText("");
                        txt_anak_ke.setText("");
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Identitas_Keluarga_Lengkap.this, "Failed Save Story Telling "+volleyError, Toast.LENGTH_LONG).show();;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("id_orangtua", String.valueOf(getId_orangtua));
                        parameters.put("nama_anak", txt_nama_anak.getText().toString());
                        parameters.put("tempat_lahir", txt_tempat_lahir.getText().toString());
                        parameters.put("tanggal_lahir", txt_tanggal_lahir.getText().toString());
                        parameters.put("jenis_kelamin", txt_jenis_kelamin.getText().toString());
                        parameters.put("agama", txt_agama.getText().toString());
                        parameters.put("anak_ke", txt_anak_ke.getText().toString());
                        return parameters;
                    }
                };
                RequestQueue rQueue = Volley.newRequestQueue(Identitas_Keluarga_Lengkap.this);
                rQueue.add(request);
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txt_tanggal_lahir.setText(sdf.format(myCalendar.getTime()));
    }


    private void ShowDialog(){
        PromptDialog dialog = new PromptDialog(this);
        dialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
        dialog.setAnimationEnable(true);
        dialog.setTitleText("SUCCESS");
        dialog.setContentText("Successfully save data family");
        dialog.setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
            @Override
            public void onClick(PromptDialog dialog) {
                dialog.dismiss();
            }
        }).show();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Identitas_Keluarga_Lengkap.this, Dashboard.class);
        startActivity(intent);
        super.onBackPressed();
    }
}

package com.example.hebatsitubondo.Orangtua;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hebatsitubondo.Login;
import com.example.hebatsitubondo.R;
import com.example.hebatsitubondo.Registrasi;

import java.util.HashMap;
import java.util.Map;

public class Identitas_Keluarga extends AppCompatActivity {

    EditText txt_nama_ayah, txt_pekerjaan_ayah, txt_no_handphone_ayah, txt_nama_ibu, txt_pekerjaan_ibu, txt_no_handphone_ibu ;
    Button next;
    String URL = "http://192.168.43.62/API_HEbATSitubondo/public/orangtua/save";
    private String getId_orangtua = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identitas_keluarga);

        Toolbar toolbar6 = (Toolbar)findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar6);
        getSupportActionBar().setTitle("Identitas Keluarga");

        txt_nama_ayah = (EditText) findViewById(R.id.txt_nama_ayah);
        txt_pekerjaan_ayah = (EditText) findViewById(R.id.txt_pekerjaan_ayah);
        txt_no_handphone_ayah = (EditText) findViewById(R.id.txt_no_handphone_ayah);
        txt_nama_ibu = (EditText) findViewById(R.id.txt_nama_ibu);
        txt_pekerjaan_ibu = (EditText) findViewById(R.id.txt_pekerjaan_ibu);
        txt_no_handphone_ibu = (EditText) findViewById(R.id.txt_no_handphone_ibu);
        next = (Button) findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {

                        Toast.makeText(Identitas_Keluarga.this, "Save data Successful", Toast.LENGTH_LONG).show();
                        next();
//                        startActivity(new Intent(Identitas_Keluarga.this, Identitas_Keluarga_Lengkap.class));
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Identitas_Keluarga.this, "Save data failed -> "+volleyError, Toast.LENGTH_LONG).show();;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("nama_ayah", txt_nama_ayah.getText().toString());
                        parameters.put("pekerjaan_ayah", txt_pekerjaan_ayah.getText().toString());
                        parameters.put("no_handphone_ayah", txt_no_handphone_ayah.getText().toString());
                        parameters.put("nama_ibu", txt_nama_ibu.getText().toString());
                        parameters.put("pekerjaan_ibu", txt_pekerjaan_ibu.getText().toString());
                        parameters.put("no_handphone_ibu", txt_no_handphone_ibu.getText().toString());
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(Identitas_Keluarga.this);
                rQueue.add(request);
            }
        });
    }

    private void next() {
        Intent intent = new Intent(Identitas_Keluarga.this, Identitas_Keluarga_Lengkap.class);
        intent.putExtra("id_keluarga", getId_orangtua);
        startActivity(intent);
    }

//    public void next(View view) {
//        Intent intent = new Intent(Identitas_Keluarga.this, Identitas_Keluarga_Lengkap.class);
//        startActivity(intent);
//    }

//    public void next(View view) {
//        Intent intent = new Intent(Identitas_Keluarga.this, Identitas_Keluarga_Lengkap.class);
//        startActivity(intent);
//    }
}

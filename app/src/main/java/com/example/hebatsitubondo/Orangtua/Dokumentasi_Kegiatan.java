package com.example.hebatsitubondo.Orangtua;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_Dokumentasi_Kegiatan;
import com.example.hebatsitubondo.Orangtua.Adapter.Adapter_Dokumentasi;
import com.example.hebatsitubondo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dokumentasi_Kegiatan extends AppCompatActivity {

    private String url_dokumentasi_kegiatan = "http://192.168.43.62/API_HEbATSitubondo/public/dokumentasi_kegiatan";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Model_Dokumentasi_Kegiatan> mData = new ArrayList<Model_Dokumentasi_Kegiatan>();
    private RecyclerView myrv;
    private Adapter_Dokumentasi adapterDokumentasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokumentasi_kegiatan);

        Toolbar toolbar4 = (Toolbar)findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar4);
        getSupportActionBar().setTitle("Dokumentasi Kegiatan");

        myrv = findViewById(R.id.recycleDokumentasi_read);
        myrv.setLayoutManager(new GridLayoutManager(this, 2));

        myrv.setHasFixedSize(true);
        mData = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        getData();
    }

    private void getData() {

        ArrayRequest = new JsonArrayRequest(url_dokumentasi_kegiatan, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        Model_Dokumentasi_Kegiatan model_dokumentasi_kegiatan = new Model_Dokumentasi_Kegiatan();
                        model_dokumentasi_kegiatan.setId_Dokumentasi(jsonObject.getInt("id_dokumentasi_kegiatan"));
                        model_dokumentasi_kegiatan.setNamaKegiatan(jsonObject.getString("nama_kegiatan"));
                        model_dokumentasi_kegiatan.setPhotoDokumentasi(jsonObject.getString("photo_dokumentasi"));
                        model_dokumentasi_kegiatan.setDeskripsiKegiatan(jsonObject.getString("deskripsi_kegiatan"));
                        mData.add(model_dokumentasi_kegiatan);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterPush(mData);
                Toast.makeText(Dokumentasi_Kegiatan.this, "Size of Liste " + String.valueOf(mData.size()), Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(Dokumentasi_Kegiatan.this);
        requestQueue.add(ArrayRequest);
    }

    private void adapterPush(List<Model_Dokumentasi_Kegiatan> mData) {
        adapterDokumentasi = new Adapter_Dokumentasi(this, mData);
        myrv.setLayoutManager(new GridLayoutManager(this, 2));
        myrv.setAdapter(adapterDokumentasi);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Dokumentasi_Kegiatan.this, Dashboard.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
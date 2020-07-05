package com.example.hebatsitubondo.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hebatsitubondo.Admin.Admin_Adapter.RecyclerViewAdaptorD;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_Dokumentasi_Kegiatan;
import com.example.hebatsitubondo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Data_Input_Dokumentasi extends AppCompatActivity {

    private String url = "http://192.168.43.62/API_HEbATSitubondo/public/dokumentasi_kegiatan";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Model_Dokumentasi_Kegiatan> mData = new ArrayList<Model_Dokumentasi_Kegiatan>();
    private RecyclerView myrv;
    private SwipeRefreshLayout refresh;
    private RecyclerViewAdaptorD recyclerViewAdaptorD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input_dokumentasi);

        Toolbar toolbar4 = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar4);
        getSupportActionBar().setTitle("Data Input Dokumentasi");

        refresh = (SwipeRefreshLayout) findViewById(R.id.swipedown);
        myrv = (RecyclerView) findViewById(R.id.recycleDokumentasi);
        myrv.setHasFixedSize(true);
        myrv.setLayoutManager(new LinearLayoutManager(this));

        mData = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        getData();

        FloatingActionButton floatingActionButton=findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Data_Input_Dokumentasi.this, Input_Dokumentasi.class);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "Add Dokumentasi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {
        refresh.setRefreshing(true);
        ArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        Model_Dokumentasi_Kegiatan modelDokumentasiKegiatan = new Model_Dokumentasi_Kegiatan();
                        modelDokumentasiKegiatan.setId_Dokumentasi(jsonObject.getInt("id_dokumentasi_kegiatan"));
                        modelDokumentasiKegiatan.setNamaKegiatan(jsonObject.getString("nama_kegiatan"));
                        modelDokumentasiKegiatan.setPhotoDokumentasi(jsonObject.getString("photo_dokumentasi"));
                        modelDokumentasiKegiatan.setDeskripsiKegiatan(jsonObject.getString("deskripsi_kegiatan"));
                        mData.add(modelDokumentasiKegiatan);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterPush(mData);
                refresh.setRefreshing(false);
                Toast.makeText(Data_Input_Dokumentasi.this, "Size of Liste " + String.valueOf(mData.size()), Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(Data_Input_Dokumentasi.this);
        requestQueue.add(ArrayRequest);
    }

    private void adapterPush(List<Model_Dokumentasi_Kegiatan> mData) {
        recyclerViewAdaptorD = new RecyclerViewAdaptorD(this, mData);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(recyclerViewAdaptorD);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Data_Input_Dokumentasi.this, Dashboard_Admin.class);
        startActivity(intent);
        super.onBackPressed();
    }

}

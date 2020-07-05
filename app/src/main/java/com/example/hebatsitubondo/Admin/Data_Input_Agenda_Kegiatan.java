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
import com.example.hebatsitubondo.Admin.Admin_Adapter.RecyclerViewAdaptorAK;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_Kegiatan;
import com.example.hebatsitubondo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Data_Input_Agenda_Kegiatan extends AppCompatActivity {

    private String url = "http://192.168.43.62/API_HEbATSitubondo/public/kegiatan";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Model_Kegiatan> mData = new ArrayList<Model_Kegiatan>();
    private RecyclerView myrv;
    private SwipeRefreshLayout refresh;
    private RecyclerViewAdaptorAK recyclerViewAdaptorAK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input_agenda_kegiatan);

        Toolbar toolbar5 = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar5);
        getSupportActionBar().setTitle("Data Input Agenda kegiatan");

        refresh = (SwipeRefreshLayout) findViewById(R.id.swipedown);
        myrv = (RecyclerView) findViewById(R.id.recycleAKegiatan);
        myrv.setHasFixedSize(true);
        myrv.setLayoutManager(new LinearLayoutManager(this));

        mData = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        getData();
        FloatingActionButton floatingActionButton=findViewById(R.id.fab_add_kegiatan);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Data_Input_Agenda_Kegiatan.this, Input_Agenda.class);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "Add Agenda Kegiatan", Toast.LENGTH_SHORT).show();
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
                        Model_Kegiatan modelKegiatan = new Model_Kegiatan();
                        modelKegiatan.setIdKegiatan(jsonObject.getInt("id_kegiatan"));
                        modelKegiatan.setJenisKegiatan(jsonObject.getString("jenis_kegiatan"));
                        modelKegiatan.setNamaKegiatan(jsonObject.getString("nama_kegiatan"));
                        modelKegiatan.setWaktu_kegiatan(jsonObject.getString("waktu_kegiatan"));
                        modelKegiatan.setTanggal_pelaksanaan(jsonObject.getString("tanggal_pelaksanaan"));
                        modelKegiatan.setTempat(jsonObject.getString("tempat"));
                        modelKegiatan.setPhotoKegiatan(jsonObject.getString("photo_kegiatan"));
                        mData.add(modelKegiatan);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterPush(mData);
                refresh.setRefreshing(false);
                Toast.makeText(Data_Input_Agenda_Kegiatan.this, "Size of Liste " + String.valueOf(mData.size()), Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(Data_Input_Agenda_Kegiatan.this);
        requestQueue.add(ArrayRequest);
    }

    private void adapterPush(List<Model_Kegiatan> mData) {
        recyclerViewAdaptorAK = new RecyclerViewAdaptorAK(this, mData);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(recyclerViewAdaptorAK);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Data_Input_Agenda_Kegiatan.this, Dashboard_Admin.class);
        startActivity(intent);
        super.onBackPressed();
    }


}
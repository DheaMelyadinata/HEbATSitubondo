package com.example.hebatsitubondo.Orangtua;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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
//import com.example.hebatsitubondo.Adapter;
import com.android.volley.toolbox.Volley;
import com.example.hebatsitubondo.Admin.Tambah_Tema;
import com.example.hebatsitubondo.Admin.Admin_Adapter.RecyclerViewAdaptor;
import com.example.hebatsitubondo.Orangtua.Adapter.Adapter_Portofolio;
import com.example.hebatsitubondo.Orangtua.Model.TemaPortofolioTM;
import com.example.hebatsitubondo.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Portofolio extends AppCompatActivity  {

    private String url = "http://192.168.43.62/API_HEbATSitubondo/public/tema";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<TemaPortofolioTM> mData = new ArrayList<TemaPortofolioTM>();
    private RecyclerView myrv;
    private SwipeRefreshLayout refresh;
    private Adapter_Portofolio adapterPortofolio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portofolio);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Portofolio Berbasis TM");

        refresh = (SwipeRefreshLayout) findViewById(R.id.swipedown);
        myrv = (RecyclerView) findViewById(R.id.recyclePortofolio_read);
        myrv.setHasFixedSize(true);
        myrv.setLayoutManager(new LinearLayoutManager(this));

        mData = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        getData();
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
                        TemaPortofolioTM temaPortofolioTM = new TemaPortofolioTM();
                        temaPortofolioTM.setIdTema(jsonObject.getInt("id_tema"));
                        temaPortofolioTM.setNamaTema(jsonObject.getString("nama_tema"));
                        temaPortofolioTM.setJudulTema(jsonObject.getString("judul_tema"));
                        temaPortofolioTM.setTujuan(jsonObject.getString("tujuan"));
                        temaPortofolioTM.setKegiatanTema(jsonObject.getString("kegiatan"));
                        temaPortofolioTM.setCatatan(jsonObject.getString("catatan"));
                        temaPortofolioTM.setGambar_url(jsonObject.getString("gambar_tema"));
                        mData.add(temaPortofolioTM);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterPush(mData);
                refresh.setRefreshing(false);
                Toast.makeText(Portofolio.this, "Size of Liste " + String.valueOf(mData.size()), Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(Portofolio.this);
        requestQueue.add(ArrayRequest);
    }

    private void adapterPush(List<TemaPortofolioTM> mData) {
        adapterPortofolio = new Adapter_Portofolio(this, mData);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(adapterPortofolio);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Portofolio.this, Dashboard.class);
        startActivity(intent);
        super.onBackPressed();
    }
}

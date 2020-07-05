package com.example.hebatsitubondo.Orangtua;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hebatsitubondo.Orangtua.Adapter.Adapter_JurnalKegiatan;
import com.example.hebatsitubondo.Orangtua.Model.TemaPortofolioTM;
import com.example.hebatsitubondo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Jurnal_Kegiatan extends AppCompatActivity {
    private String url = "http://192.168.43.62/API_HEbATSitubondo/public/tema";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<TemaPortofolioTM> mData = new ArrayList<TemaPortofolioTM>();
    private RecyclerView myrv;
    private Adapter_JurnalKegiatan recyclerViewAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurnal_kegiatan);

        Toolbar toolbar3 = (Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        getSupportActionBar().setTitle("Jurnal Kegiatan");

        myrv = (RecyclerView) findViewById(R.id.recycleJKegiatan);
        myrv.setHasFixedSize(true);
        myrv.setLayoutManager(new LinearLayoutManager(this));

        mData = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        getData();
    }

    private void getData() {

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
                        temaPortofolioTM.setGambar_url(jsonObject.getString("gambar_tema"));
                        mData.add(temaPortofolioTM);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterPush(mData);
                Toast.makeText(Jurnal_Kegiatan.this, "Size of Liste " + String.valueOf(mData.size()), Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(Jurnal_Kegiatan.this);
        requestQueue.add(ArrayRequest);
    }

    private void adapterPush(List<TemaPortofolioTM> mData) {
        recyclerViewAdaptor = new Adapter_JurnalKegiatan(this, mData);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(recyclerViewAdaptor);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Jurnal_Kegiatan.this, Dashboard.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
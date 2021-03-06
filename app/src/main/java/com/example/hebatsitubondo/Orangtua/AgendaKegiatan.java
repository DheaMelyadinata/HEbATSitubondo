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
import com.example.hebatsitubondo.Admin.Model_Admin.Model_Kegiatan;
import com.example.hebatsitubondo.Admin.Admin_Adapter.RecyclerViewAdaptorAK;
import com.example.hebatsitubondo.Orangtua.Adapter.Adapter_AgendaKegiatan;
import com.example.hebatsitubondo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AgendaKegiatan extends AppCompatActivity {

    private String url_kegiatan = "http://192.168.43.62/API_HEbATSitubondo/public/kegiatan";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Model_Kegiatan> mData = new ArrayList<Model_Kegiatan>();
    private RecyclerView myrv;
    private Adapter_AgendaKegiatan adapter_agendaKegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_kegiatan);

        Toolbar toolbar6 = (Toolbar)findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar6);
        getSupportActionBar().setTitle("Agenda Kegiatan");

        myrv = (RecyclerView) findViewById(R.id.recycleAKegiatan_read);
        myrv.setHasFixedSize(true);
        myrv.setLayoutManager(new LinearLayoutManager(this));

        mData = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        getData();
    }

    private void getData() {

        ArrayRequest = new JsonArrayRequest(url_kegiatan, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        Model_Kegiatan modelKegiatan = new Model_Kegiatan();
                        modelKegiatan.setJenisKegiatan(jsonObject.getString("jenis_kegiatan"));
                        modelKegiatan.setNamaKegiatan(jsonObject.getString("nama_kegiatan"));
                        modelKegiatan.setWaktu_kegiatan(jsonObject.getString("waktu_kegiatan"));
                        modelKegiatan.setTempat(jsonObject.getString("tempat"));
                        modelKegiatan.setPhotoKegiatan(jsonObject.getString("photo_kegiatan"));
                        mData.add(modelKegiatan);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterPush(mData);
                Toast.makeText(AgendaKegiatan.this, "Size of Liste " + String.valueOf(mData.size()), Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(AgendaKegiatan.this);
        requestQueue.add(ArrayRequest);
    }

    private void adapterPush(List<Model_Kegiatan> mData) {
        adapter_agendaKegiatan = new Adapter_AgendaKegiatan(this, mData);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(adapter_agendaKegiatan);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AgendaKegiatan.this, Dashboard.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
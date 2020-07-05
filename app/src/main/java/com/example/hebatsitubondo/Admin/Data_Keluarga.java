package com.example.hebatsitubondo.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hebatsitubondo.Admin.Admin_Adapter.Adapter_DataKeluarga;
import com.example.hebatsitubondo.Admin.Admin_Adapter.Adapter_StoryTelling;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_Keluarga;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_StoryTelling;
import com.example.hebatsitubondo.Orangtua.Model.TemaPortofolioTM;
import com.example.hebatsitubondo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data_Keluarga extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private String url_keluarga = "http://192.168.43.62/API_HEbATSitubondo/public/keluarga_meta";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Model_Keluarga> data = new ArrayList<Model_Keluarga>();
    private RecyclerView myrv;
    private SwipeRefreshLayout refresh;
    private Adapter_DataKeluarga adapterDataKeluarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_keluarga);

        Toolbar toolbarkeluarga = (Toolbar) findViewById(R.id.toolbar_keluarga);
        setSupportActionBar(toolbarkeluarga);
        getSupportActionBar().setTitle("Data Keluarga");

        refresh = (SwipeRefreshLayout) findViewById(R.id.swipedown);
        myrv =  findViewById(R.id.recycleKeluarga);

        refresh.setOnRefreshListener(this);
        refresh.post(new Runnable() {
            @Override
            public void run() {
                data.clear();
                getData();
            }
        });
    }

    private void getData() {
        refresh.setRefreshing(true);

        ArrayRequest = new JsonArrayRequest(url_keluarga, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        Model_Keluarga modelKeluarga = new Model_Keluarga();
                        modelKeluarga.setNama(jsonObject.getString("nama"));
                        modelKeluarga.setNama_status(jsonObject.getString("nama_status"));
                        modelKeluarga.setNama_kepala_keluarga(jsonObject.getString("nama_kepala_keluarga"));
                        modelKeluarga.setAlamat_keluarga(jsonObject.getString("alamat_keluarga"));
                        modelKeluarga.setTtl(jsonObject.getString("ttl"));
                        modelKeluarga.setPekerjaan(jsonObject.getString("pekerjaan"));
                        modelKeluarga.setNo_handphone(jsonObject.getString("no_handphone"));
                        data.add(modelKeluarga);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterPush(data);
                refresh.setRefreshing(false);
                Toast.makeText(Data_Keluarga.this, "Size of Liste " + String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(Data_Keluarga.this);
        requestQueue.add(ArrayRequest);
    }


    private void adapterPush(List<Model_Keluarga> data) {

        adapterDataKeluarga = new Adapter_DataKeluarga(this, data);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(adapterDataKeluarga);
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Data_Keluarga.this, Dashboard_Admin.class);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

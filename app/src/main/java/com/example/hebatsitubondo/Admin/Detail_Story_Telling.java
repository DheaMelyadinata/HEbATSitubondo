package com.example.hebatsitubondo.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hebatsitubondo.Admin.Admin_Adapter.Adapter_StoryTelling;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_StoryTelling;
import com.example.hebatsitubondo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Detail_Story_Telling extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__story__telling);

        Toolbar toolbars = (Toolbar) findViewById(R.id.toolbar_detail_storytelling);
        setSupportActionBar(toolbars);
        getSupportActionBar().setTitle("Detail Story Telling");

//        Recieve data

        Intent intent = getIntent();
        String nama_tema = intent.getStringExtra("nama_tema");
        String nama_anak = intent.getStringExtra("nama_anak");
        String judul_tema = intent.getStringExtra("judul_tema");
        String cerita = intent.getStringExtra("cerita");

        TextView namaTema = findViewById(R.id.txt_temastory_detail);
        TextView namaAnak = findViewById(R.id.txt_namaanak_detail);
        TextView judulTema = findViewById(R.id.txt_judultema_detail);
        TextView Cerita = findViewById(R.id.txt_cerita_detail);

        namaTema.setText(nama_tema);
        namaAnak.setText(nama_anak);
        judulTema.setText(judul_tema);
        Cerita.setText(cerita);

//
    }
}
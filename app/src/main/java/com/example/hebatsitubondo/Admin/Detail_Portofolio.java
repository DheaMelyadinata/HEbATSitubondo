package com.example.hebatsitubondo.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hebatsitubondo.Orangtua.Input_StoryTelling;
import com.example.hebatsitubondo.Orangtua.Portofolio_Detail;
import com.example.hebatsitubondo.R;

public class Detail_Portofolio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__portofolio);

        Toolbar toolbardt = (Toolbar) findViewById(R.id.toolbar_detail_tema);
        setSupportActionBar(toolbardt);
        getSupportActionBar().setTitle("Detail Tema");

//        Recieve data

        Intent intent = getIntent();
        String gambar_tema = intent.getStringExtra("gambar_tema");
        String nama_tema = intent.getStringExtra("nama_tema");
        String judul_tema = intent.getStringExtra("judul_tema");
        String tujuan = intent.getStringExtra("tujuan");
        String kegiatan = intent.getStringExtra("kegiatan");
        String catatan = intent.getStringExtra("catatan");

        ImageView imageView = findViewById(R.id.imgTema_detail);
        TextView namaTema = findViewById(R.id.text_nama_tema_detail);
        TextView judulTema = findViewById(R.id.text_judul_tema_detail);
        TextView Tujuan = findViewById(R.id.text_tujuan_detail);
        TextView Kegiatan = findViewById(R.id.text_kegiatan_detail);
        TextView Catatan = findViewById(R.id.text_catatn_detail);

        Glide.with(this)
                .load("http://192.168.43.62/API_HEbATSitubondo/public/"+ gambar_tema)
                .apply(new RequestOptions().override(350, 550))
                .into(imageView);

        namaTema.setText(nama_tema);
        judulTema.setText(judul_tema);
        Tujuan.setText(tujuan);
        Kegiatan.setText(kegiatan);
        Catatan.setText(catatan);
//
    }
}

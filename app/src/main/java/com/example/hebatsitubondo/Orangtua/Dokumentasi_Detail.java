package com.example.hebatsitubondo.Orangtua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hebatsitubondo.R;

public class Dokumentasi_Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokumentasi__detail);

        Toolbar toolbardt = (Toolbar) findViewById(R.id.toolbar_detail_dokumentasi);
        setSupportActionBar(toolbardt);
        getSupportActionBar().setTitle("Detail Dokumentasi");

//        Recieve data

        Intent intent = getIntent();
        String photodokumentasi = intent.getStringExtra("photo_dokumentasi");
        String namakegiatan = intent.getStringExtra("nama_kegiatan");
        String deskripsikegiatan = intent.getStringExtra("deskripsi_kegiatan");

        ImageView imageView = findViewById(R.id.imgDokumentasi_detail);
        TextView namaKegiatan = findViewById(R.id.text_nama_dokumentasi_detail);
        TextView deskripsiKegiatan = findViewById(R.id.deskripsiDokumentasi);

        Glide.with(this)
                .load("http://192.168.43.62/API_HEbATSitubondo/public/"+ photodokumentasi)
                .apply(new RequestOptions().override(350, 550))
                .into(imageView);

        namaKegiatan.setText(namakegiatan);
        deskripsiKegiatan.setText(deskripsikegiatan);

    }
}

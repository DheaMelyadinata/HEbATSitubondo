package com.example.hebatsitubondo.Orangtua;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hebatsitubondo.R;


public class AgendaKegiatan_Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_kegiatan__detail);

        Intent intent = getIntent();
        String gambar_tema = intent.getStringExtra("gambar_tema");
        String nama_tema = intent.getStringExtra("nama_tema");
        String judul_tema = intent.getStringExtra("judul_tema");
        String tujuan = intent.getStringExtra("tujuan");
        String kegiatan = intent.getStringExtra("kegiatan");
        String catatan = intent.getStringExtra("catatan");

        ImageView imageView = findViewById(R.id.image_view_detail);
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
        judulTema.setText("Judul Tema : "+judul_tema);
        Tujuan.setText("Tujuan: " + tujuan);
        Kegiatan.setText("Kegiatan: " + kegiatan);
        Catatan.setText("Catatan: " + catatan);
    }
}

package com.example.hebatsitubondo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DokumentasiKegiatan extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdaptorK adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokumentasi_kegiatan);
        recyclerView=(RecyclerView) findViewById(R.id.recycleDokumentasi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adaptor=new RecyclerViewAdaptorK(kegiatanTMList());
        recyclerView.setAdapter(adaptor);
    }

    public List<AgendaKegiatanTM> kegiatanTMList(){
        List<AgendaKegiatanTM> kegiatan = new ArrayList<>();
        kegiatan.add(new AgendaKegiatanTM("Kegiatan Playdate HEbAT Situbondo", R.drawable.kegiatan1));
        kegiatan.add(new AgendaKegiatanTM("Angry Bird Games", R.drawable.kegiatan2));
        kegiatan.add(new AgendaKegiatanTM("Playdate Donut Karakter", R.drawable.kegiatan3));
        return kegiatan;
    }
}

package com.example.hebatsitubondo.Orangtua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hebatsitubondo.R;

import java.util.ArrayList;
import java.util.List;

public class AgendaKegiatan extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdaptorAK adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_kegiatan);

    recyclerView=(RecyclerView) findViewById(R.id.recycleAKegiatan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    adaptor=new RecyclerViewAdaptorAK(kegiatanTMList());
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

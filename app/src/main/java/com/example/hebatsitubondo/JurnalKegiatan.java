package com.example.hebatsitubondo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class JurnalKegiatan extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdaptorJK adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurnal_kegiatan);

    recyclerView=(RecyclerView) findViewById(R.id.recycleJKegiatan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    adaptor=new RecyclerViewAdaptorJK(temaPortofolioTMList());
        recyclerView.setAdapter(adaptor);
}

    public List<TemaPortofolioTM> temaPortofolioTMList(){
        List<TemaPortofolioTM> tema = new ArrayList<>();
        tema.add(new TemaPortofolioTM("Tema 1", "Alam dan Sekitarnya", R.drawable.tema1));
        tema.add(new TemaPortofolioTM("Tema 2", "Kesehatan", R.drawable.tema2));
        tema.add(new TemaPortofolioTM("Tema 3", "Kerajinan", R.drawable.tema3));
        tema.add(new TemaPortofolioTM("Tema 4", "Kuliner/Boga", R.drawable.tema4));
        tema.add(new TemaPortofolioTM("Tema 5", "Transportasi", R.drawable.tema5));
        tema.add(new TemaPortofolioTM("Tema 6", "Pariwisata", R.drawable.tema6));
        tema.add(new TemaPortofolioTM("Tema 7", "Lokomotif", R.drawable.tema7));
        tema.add(new TemaPortofolioTM("Tema 8", "Elektronik", R.drawable.tema8));
        tema.add(new TemaPortofolioTM("Tema 9", "Telekomunikasi", R.drawable.tema9));
        tema.add(new TemaPortofolioTM("Tema 10", "Astronomi", R.drawable.tema10));
        tema.add(new TemaPortofolioTM("Tema 11", "Keamanan", R.drawable.tema11));
        tema.add(new TemaPortofolioTM("Tema 12", "Entertaiment", R.drawable.tema12));
        return tema;
    }
}

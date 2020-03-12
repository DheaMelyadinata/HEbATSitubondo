package com.example.hebatsitubondo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Portofolio extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portofolio);

        recyclerView=(RecyclerView) findViewById(R.id.recyclePortofolio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adaptor=new RecyclerViewAdaptor(temaPortofolioTMList());
        recyclerView.setAdapter(adaptor);
    }

    public List<TemaPortofolioTM> temaPortofolioTMList(){
        List<TemaPortofolioTM> tema = new ArrayList<>();
        tema.add(new TemaPortofolioTM("Tema 1", "Alam dan Sekitarnya", R.drawable.tema1));
        tema.add(new TemaPortofolioTM("Tema 2", "Kesehatan", R.drawable.tema2));
        tema.add(new TemaPortofolioTM("Tema 3", "Kerajinan", R.drawable.tema3));
        tema.add(new TemaPortofolioTM("Tema 4", "Kuliner/Boga", R.drawable.tema4));
        tema.add(new TemaPortofolioTM("Tema 5", "Transportasi", R.drawable.tema5));

        return tema;
    }
}

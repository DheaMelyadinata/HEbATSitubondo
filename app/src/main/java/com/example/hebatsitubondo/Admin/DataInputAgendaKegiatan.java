package com.example.hebatsitubondo.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.hebatsitubondo.R;

public class DataInputAgendaKegiatan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input_agenda_kegiatan);

        Toolbar toolbar2 = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);

        getSupportActionBar().setTitle("Input Agenda Kegiatan");

    }
}

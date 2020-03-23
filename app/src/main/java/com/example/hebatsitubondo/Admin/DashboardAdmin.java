package com.example.hebatsitubondo.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hebatsitubondo.Orangtua.Dashboard;
import com.example.hebatsitubondo.Orangtua.Portofolio;
import com.example.hebatsitubondo.R;

public class DashboardAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
    }

    public void DataPortofolio(View view) {
        Intent intent = new Intent(DashboardAdmin.this, Portofolio.class);
        startActivity(intent);
    }

    public void StoryTelling(View view) {
        Intent intent = new Intent(DashboardAdmin.this, Portofolio.class);
        startActivity(intent);
    }

    public void DataKeluarga(View view) {
        Intent intent = new Intent(DashboardAdmin.this, Portofolio.class);
        startActivity(intent);
    }

    public void UploadDokumentasi(View view) {
        Intent intent = new Intent(DashboardAdmin.this, Portofolio.class);
        startActivity(intent);
    }

    public void UploadAgenda(View view) {
        Intent intent = new Intent(DashboardAdmin.this, Portofolio.class);
        startActivity(intent);
    }
}

package com.example.hebatsitubondo.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

//import com.example.hebatsitubondo.Orangtua.Portofolio;
import com.example.hebatsitubondo.Kalender;
import com.example.hebatsitubondo.Orangtua.Akun_Profile;
import com.example.hebatsitubondo.Orangtua.Dashboard;
import com.example.hebatsitubondo.R;
import com.example.hebatsitubondo.SliderAdapter;
import com.example.hebatsitubondo.SliderItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Dashboard_Admin extends AppCompatActivity {

    private BottomNavigationView bottom_navigation_admin;
    private ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        bottom_navigation_admin = (BottomNavigationView) findViewById(R.id.bottom_navigation_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Memberikan listener saat menu item di bottom navigation diklik
        bottom_navigation_admin.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_home:
                        Toast.makeText(Dashboard_Admin.this, "Anda sedang berada di beranda", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_kalender:
                        Toast.makeText(Dashboard_Admin.this, "Kalender clicked", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Dashboard_Admin.this, Kalender.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_akun:
                        Toast.makeText(Dashboard_Admin.this, "Akun clicked", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(Dashboard_Admin.this, Profil_Admin.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });

        viewPager2 = findViewById(R.id.ViewPagerImageSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.cover));
        sliderItems.add(new SliderItem(R.drawable.kegiatan1));
        sliderItems.add(new SliderItem(R.drawable.kegiatan2));
        sliderItems.add(new SliderItem(R.drawable.kegiatan3));
        sliderItems.add(new SliderItem(R.drawable.kegiatan4));
        sliderItems.add(new SliderItem(R.drawable.kegiatan5));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(60));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
    }

    public void StoryTelling(View view) {
        Intent intent = new Intent(Dashboard_Admin.this, Story_Telling.class);
        startActivity(intent);
    }

    public void DataKeluarga(View view) {
        Intent intent = new Intent(Dashboard_Admin.this, Data_Keluarga.class);
        startActivity(intent);
    }

    public void UploadDokumentasi(View view) {
        Intent intent = new Intent(Dashboard_Admin.this, Data_Input_Dokumentasi.class);
        startActivity(intent);
    }

    public void UploadAgenda(View view) {
        Intent intent = new Intent(Dashboard_Admin.this, Data_Input_Agenda_Kegiatan.class);
        startActivity(intent);
    }

    public void DataPortofolio(View view) {
        Intent intent = new Intent(Dashboard_Admin.this, Data_Input_Portofolio.class);
        startActivity(intent);
    }

    private void ShowDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Konfirmasi Keluar") //untuk membuat title/judul pada pesan dialog yang muncul
                .setMessage("Anda Yakin Ingin Keluar?") // untuk menampilkan pesan
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    //if user pressed "yes", then he is allowed to exit from application

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { ;
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    //if user select "No", just cancel this dialog and continue with app
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        alert.show(); //ntuk menampilkan Alert Dialog
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            ShowDialog();
        }
        return true;
    }
}

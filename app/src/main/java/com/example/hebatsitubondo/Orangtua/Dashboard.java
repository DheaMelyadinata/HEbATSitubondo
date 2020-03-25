package com.example.hebatsitubondo.Orangtua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hebatsitubondo.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class Dashboard extends AppCompatActivity {

//    private BottomNavigationView bottomNavigation;

    private int[] mImage = new int[]{
         R.drawable.kegiatan1, R.drawable.kegiatan2, R.drawable.kegiatan3
    };

    private String[] mImageTitle = new String[]{
            "kegiatan1", "kegiatan2", "kegiatan3"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        bottomNavigation = findViewById(R.id.bottom_navigation);
//        bottomNavigation.inflateMenu(R.menu.bottom_navigation);
//
//        //Memberikan listener saat menu item di bottom navigation diklik
//        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                switch (id) {
//                    case R.id.nav_home:
//                        Toast.makeText(Dashboard.this, "Anda sedang berada di beranda", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_kalender:
//                        Toast.makeText(Dashboard.this, "Jadwal clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_akun:
//                        Intent intent0 = new Intent(Dashboard.this, ProfilUser.class);
//                        startActivity(intent0);
//                        break;
//                }
//                return true;
//            }
//        });

        CarouselView carouselView = findViewById(R.id.carousel);
        carouselView.setPageCount(mImage.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImage[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(Dashboard.this, mImageTitle[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Portofolio(View view) {
        Intent intent = new Intent(Dashboard.this, Portofolio.class);
        startActivity(intent);
    }

    public void Akegiatan(View view) {
        Intent intent = new Intent(Dashboard.this, JurnalKegiatan.class);
        startActivity(intent);
    }

    public void AgendaKegiatan(View view) {
        Intent intent = new Intent(Dashboard.this, AgendaKegiatan.class);
        startActivity(intent);
    }

    public void ProfilIdentitas(View view) {
        Intent intent = new Intent(Dashboard.this, ProfilIdentitas.class);
        startActivity(intent);
    }

    public void Dokumentasi(View view) {
        Intent intent = new Intent(Dashboard.this, DokumentasiKegiatan.class);
        startActivity(intent);
    }

    public void Kontak(View view) {
        Intent intent = new Intent(Dashboard.this, ProfilUser.class);
        startActivity(intent);
    }

}

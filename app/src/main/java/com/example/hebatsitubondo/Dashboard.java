package com.example.hebatsitubondo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class Dashboard extends AppCompatActivity {
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

    public void Kontak(View view) {
        Intent intent = new Intent(Dashboard.this, KontakKami.class);
        startActivity(intent);
    }

    public void Portofolio(View view) {
        Intent intent = new Intent(Dashboard.this, Portofolio.class);
        startActivity(intent);
    }

    public void Akegiatan(View view) {
        Intent intent = new Intent(Dashboard.this, JurnalKegiatan.class);
        startActivity(intent);
    }
}

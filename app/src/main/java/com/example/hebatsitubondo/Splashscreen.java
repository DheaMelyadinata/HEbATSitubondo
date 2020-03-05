package com.example.hebatsitubondo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(3000); //3detik
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                    startActivity(intent);

                    finish();
                }
            }
        };

        thread.start();
    }
}

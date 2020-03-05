package com.example.hebatsitubondo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Daftar(View view) {
        Intent intent = new Intent(Login.this, Registrasi.class);
        startActivity(intent);
    }
}

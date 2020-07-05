package com.example.hebatsitubondo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registrasi extends AppCompatActivity {

    Button btnsignup, btnsignin;
    EditText usernameBox, passwordBox, emailBox, no_handphoneBox;
    String URL = "http://192.168.43.62/API_HEbATSitubondo/public/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        btnsignin = (Button) findViewById(R.id.btnsignin);
        btnsignup = (Button) findViewById(R.id.btnsignup);
        usernameBox = (EditText) findViewById(R.id.usernameBox);
        passwordBox = (EditText) findViewById(R.id.passwordBox);
        emailBox = (EditText) findViewById(R.id.emailBox);
        no_handphoneBox = (EditText) findViewById(R.id.no_handphoneBox);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {

                            Toast.makeText(Registrasi.this, "Registration Successful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Registrasi.this, Login.class));

                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Registrasi.this, "Register failed -> "+volleyError, Toast.LENGTH_LONG).show();;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("username", usernameBox.getText().toString());
                        parameters.put("password", passwordBox.getText().toString());
                        parameters.put("email", emailBox.getText().toString());
                        parameters.put("no_handphone", no_handphoneBox.getText().toString());

                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(Registrasi.this);
                rQueue.add(request);
            }
        });
            }
        }


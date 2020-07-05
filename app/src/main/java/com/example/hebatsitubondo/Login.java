package com.example.hebatsitubondo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.hebatsitubondo.Admin.Dashboard_Admin;
import com.example.hebatsitubondo.Orangtua.Akun_Profile;
import com.example.hebatsitubondo.Orangtua.Dashboard;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button btnsignup, btnsignin;
    EditText usernameBox, passwordBox;
    Intent intent;

    String URL = "http://192.168.43.62/API_HEbATSitubondo/public/login";

    SharedPreferences sharedpreferences;
    Boolean session = false;

    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    public static final String status = "status";
    public static final String id_user = "id_user";
    public static final String username = "username";
    public static final String email = "email";
    public static final String no_handphone = "no_handphone";
    public static final String photo = "photo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnsignin = (Button) findViewById(R.id.btnsignin);
        btnsignup = (Button) findViewById(R.id.btnsignup);
        usernameBox = (EditText) findViewById(R.id.usernameBox);
        passwordBox = (EditText) findViewById(R.id.passwordBox);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();


        //ini sharedpreferences.getBoolean(session_status,false ngambil kondisi login yg disimpan, false nilai default
        //ini jika sudah login
        if (sharedpreferences.getBoolean(session_status,false)) {
            //sama statusnya user
            if (sharedpreferences.getString(status, "").equals("admin")) {
                startActivity(new Intent(Login.this, Dashboard_Admin.class));
                finishAffinity();
            }else if(sharedpreferences.getString(status, "").equals("user")){
                startActivity(new Intent(Login.this, Dashboard.class));
                finishAffinity();
            }
        }

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {

                JSONObject datauser = null;
                try{
                    datauser = new JSONObject(s);
                    JSONObject getData = datauser.getJSONObject("result");
                    String get_id = getData.getString("id_user");
                    String get_username = getData.getString("username");
                    String get_email = getData.getString("email");
                    String get_no_handphone = getData.getString("no_handphone");
                    String get_status = getData.getString("status");
                    String get_foto = getData.getString("photo"); //tambahin wes dhe ini belom maap:( dahh

                    editor.putString(photo, get_foto);
                    editor.putString(id_user, get_id);
                    editor.putString(username, get_username);
                    editor.putString(email, get_email);
                    editor.putString(no_handphone, get_no_handphone);
                    editor.putString(status, get_status);
                    editor.putBoolean(session_status, true); //untuk menyimpan kondisi login
                    editor.commit();

                    Toast.makeText(Login.this, "Login Successful .. Status : "+ get_status, Toast.LENGTH_LONG).show();

                    if (get_status.equals("admin")) {
                        startActivity(new Intent(Login.this, Dashboard_Admin.class));

                    } else if (get_status.equals("user")) {
                        startActivity(new Intent(Login.this, Dashboard.class));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Login.this, "Sorry, the username or password you entered is incorrect -> "+volleyError, Toast.LENGTH_LONG).show();;
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("username", usernameBox.getText().toString());
                parameters.put("password", passwordBox.getText().toString());
                return parameters;
            }
        };

        RequestQueue rQueue = Volley.newRequestQueue(Login.this);
        rQueue.getCache().clear();
        rQueue.add(request);
            }
        });


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                intent = new Intent(Login.this, Registrasi.class);
                finish();
                startActivity(intent);
            }
        });
    }
}


package com.example.hebatsitubondo.Admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.hebatsitubondo.Login;
import com.example.hebatsitubondo.Orangtua.Akun_Profile;
import com.example.hebatsitubondo.Orangtua.Model.Model_User;
import com.example.hebatsitubondo.Orangtua.Update_user;
import com.example.hebatsitubondo.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profil_Admin extends AppCompatActivity {

    Button btn_logout;
    SharedPreferences sharedpreferences;
    final int PICK_IMAGE_REQUEST = 1;
    final int RESULT_LOAD_IMAGE = 1;
    Uri uri;

    String URL = "http://192.168.43.62/API_HEbATSitubondo/public/users";

    /* Deklarasi dan Menginisialisasi variable nama dengan Label Nama dari Layout Profil_akun */
    TextView nama_profil, username_profil, email, no_handphone, status_profile, update_profil_admin;
    CircularImageView profil_admin;
    List<Model_User> itemsUser;

    private String getId_user = "";
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    public static final String get_id = "id_user";
    public static final String get_status = "status";
    public static final String get_username = "username";
    public static final String get_email = "email";
    public static final String get_no_handphone = "no_handphone";
    public static final String photo = "photo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil__admin);

        Toolbar toolbarakun = findViewById(R.id.toolbarakun);
        setSupportActionBar(toolbarakun);
        getSupportActionBar().setTitle("Akun Admin");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama_profil = (TextView) findViewById(R.id.nama_profil);
        username_profil = (TextView) findViewById(R.id.username_profil);
        email = (TextView) findViewById(R.id.email);
        no_handphone = (TextView) findViewById(R.id.no_handphone);
        status_profile = (TextView) findViewById(R.id.status_profile);
        update_profil_admin = (TextView) findViewById(R.id.updateadmin);
        itemsUser = new ArrayList<>();

        sharedpreferences = getSharedPreferences( my_shared_preferences, Context.MODE_PRIVATE);
        sharedpreferences.getString(photo,"");
        getId_user = sharedpreferences.getString(get_id,"");
        username_profil.setText(sharedpreferences.getString(get_username, ""));
        email.setText(sharedpreferences.getString(get_email, ""));
        no_handphone.setText(sharedpreferences.getString(get_no_handphone, ""));
        status_profile.setText(sharedpreferences.getString(get_status, ""));

        //foto profil fotonya blm di set ya
        profil_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(Profil_Admin.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });//kanjutin dhee

        Glide.with(this)
                .load("http://192.168.43.62/API_HEbATSitubondo/public/"+sharedpreferences.getString(photo,""))
                .into(profil_admin);


        //update
        update_profil_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profil_Admin.this, Update_user.class);
                intent.putExtra("id_dokumentasi_kegiatan", getId_user);//ini idi buat update user dilempar ke halaman update user
//                intent.putExtra("nama_kegiatan", modelDokumentasiKegiatan.getNamaKegiatan());
//                intent.putExtra("photo_dokumentasi", modelDokumentasiKegiatan.getPhotoDokumentasi());
                startActivity(intent);
            }
        });

//        Viewdata();
        btn_logout = (Button) findViewById(R.id.btnlogout1);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            // update login session ke FALSE untuk logout dan mengkosongkan nilai login
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(session_status, false);
                editor.commit();

                Intent intent = new Intent(Profil_Admin.this, Login.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK

            //You can get File object from intent
            File file= ImagePicker.Companion.getFile(data);
            sendData(getStringImage(file));
            profil_admin.setImageURI(data.getData());
            //You can also get File Path from intent

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    public String getStringImage (File file){
        Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] imageBytes = baos.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void sendData (final String decoded){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Proses Menambahkan ...");
        pDialog.show();
        String URL = "http://192.168.43.62/API_HEbATSitubondo/public/users/upload";
        StringRequest srSendData = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profil_Admin.this, "Maaf ada kesalahan Upload Foto  ", Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> parameters = new HashMap<>();
                parameters.put("id_user", getId_user);
                parameters.put("photo", decoded);

                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Profil_Admin.this);
        requestQueue.add(srSendData);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

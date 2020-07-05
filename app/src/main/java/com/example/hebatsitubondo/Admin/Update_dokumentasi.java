package com.example.hebatsitubondo.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hebatsitubondo.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Update_dokumentasi extends AppCompatActivity implements View.OnClickListener {

    Button update_dokumentasi;
    ImageButton update_image_dokumentasi;
    ImageView update_imageview_dokumentasi;
    EditText txt_update_deskripsi, update_kegiatan_dokumentasi;
    private int getId_Dokumentasi = 0;

    ProgressDialog pDialog;
    Bitmap bitmap, decoded;
    int success;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 40; // range 1 - 100

    Intent intent;
    Uri fileUri;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;

    int max_resolution_image = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dokumentasi);

        Toolbar updokumentasi = (Toolbar) findViewById(R.id.tupdate_dokumentasi);
        setSupportActionBar(updokumentasi);
        getSupportActionBar().setTitle("Dokumentasi Kegiatan");

        update_dokumentasi = (Button) findViewById(R.id.update_dokumentasi);
        update_kegiatan_dokumentasi = (EditText) findViewById(R.id.update_kegiatan_dokumentasi);
        txt_update_deskripsi = (EditText) findViewById(R.id.txt_update_deskripsi);
        update_image_dokumentasi = (ImageButton) findViewById(R.id.update_image_dokumentasi);
        update_imageview_dokumentasi = (ImageView) findViewById(R.id.update_imageview_dokumentasi);

        update_dokumentasi.setOnClickListener(this);
        update_image_dokumentasi.setOnClickListener(this);
        update_imageview_dokumentasi.setOnClickListener(this);

        Intent intent = getIntent();
        getId_Dokumentasi = intent.getIntExtra("id_dokumentasi_kegiatan",0);
        String nama_kegiatan = intent.getStringExtra("nama_kegiatan");
        String photo_dokumentasi = intent.getStringExtra("photo_dokumentasi");
        String deskripsi_kegiatan = intent.getStringExtra("deskripsi_kegiatan");

        Glide.with(this)
                .load("http://192.168.43.62/API_HEbATSitubondo/public/"+ photo_dokumentasi)
                .apply(new RequestOptions().override(350, 550))
                .into(update_imageview_dokumentasi);
        update_kegiatan_dokumentasi.setText(nama_kegiatan);
        txt_update_deskripsi.setText(deskripsi_kegiatan);
    }

    public void onClick(View v) {
        switch (v.getId()){

            case R.id.update_dokumentasi:
                String namaKegiatan = update_kegiatan_dokumentasi.getText().toString();
                String deskripsiKegiatan = txt_update_deskripsi.getText().toString();

                if ( namaKegiatan.trim().length() > 0 && deskripsiKegiatan.trim().length() > 0) {
                    sendData(namaKegiatan, deskripsiKegiatan, String.valueOf(getId_Dokumentasi));
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Field tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.update_imageview_dokumentasi:
                pickImage();
                break;
        }
    }
    private void pickImage() {
        update_imageview_dokumentasi.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Update_dokumentasi.this);
        builder.setTitle("Add Photo!");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    fileUri = getOutputMediaFileUri();
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void kosong() {
        update_imageview_dokumentasi.setImageResource(0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                try {
                    Log.e("CAMERA", fileUri.getPath());

                    bitmap = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
                try {
                    // mengambil gambar dari Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(Update_dokumentasi.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getStringImage (Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void sendData ( final String namaKegiatan, final String deskripsiKegiatan,  String id_Dokumentasi){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Proses Menambahkan ...");
        showDialog();
        String URL = "http://192.168.43.62/API_HEbATSitubondo/public/dokumentasi_kegiatan/" + id_Dokumentasi + "/update";
        StringRequest srSendData = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(Update_dokumentasi.this, Data_Input_Dokumentasi.class);
                Toast.makeText(Update_dokumentasi.this, "Dokumentasi Berhasil Diubah", Toast.LENGTH_LONG).show();
                kosong();
                startActivity(intent);
                finish();
                hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Update_dokumentasi.this, "Maaf ada kesalahan mengubah Data Dokumentasi  ", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> parameters = new HashMap<>();
                parameters.put("nama_kegiatan", namaKegiatan);
                parameters.put("deskripsi_kegiatan", deskripsiKegiatan);
                if( getStringImage(decoded) != null){
                    parameters.put("photo_dokumentasi", getStringImage(decoded));
                }//jika gambarnya di ubah
                else{
                    parameters.put("photo_dokumentasi", "null");//jika tidak diubah
                }
                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Update_dokumentasi.this);
        requestQueue.add(srSendData);
    }

    private String imgToString (Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        String encodeImage = null;
        if (bitmap != null) {
            byte[] imageByte = outputStream.toByteArray();
            encodeImage = Base64.encodeToString(imageByte, Base64.DEFAULT);
        }

        return encodeImage;
    }

    private void setToImageView (Bitmap bmp){
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        update_imageview_dokumentasi.setImageBitmap(decoded);
    }

    // fungsi resize image
    public Bitmap getResizedBitmap (Bitmap image,int maxSize){
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
}


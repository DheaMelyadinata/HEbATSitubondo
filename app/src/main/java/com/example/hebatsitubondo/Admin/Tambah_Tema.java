package com.example.hebatsitubondo.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.hebatsitubondo.Orangtua.Portofolio;
import com.example.hebatsitubondo.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tambah_Tema extends AppCompatActivity implements View.OnClickListener {

    Button addtema;
    ImageButton upload_imagetema;
    ImageView imagetema;
    EditText txt_tema, txt_judultema, txt_tujuan, txt_kegiatan, txt_catatan;
    String URL = "http://192.168.43.62/API_HEbATSitubondo/public/tema/save";

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
        setContentView(R.layout.activity_tambah__tema);

        addtema = (Button) findViewById(R.id.addtema);
        txt_tema = (EditText) findViewById(R.id.txt_tema);
        txt_judultema = (EditText) findViewById(R.id.txt_judultema);
        txt_tujuan = (EditText) findViewById(R.id.txt_tujuan);
        txt_kegiatan = (EditText) findViewById(R.id.txt_kegiatan);
        txt_catatan = (EditText) findViewById(R.id.txt_catatan);
        upload_imagetema = (ImageButton) findViewById(R.id.id_imagebutton_tema);
        imagetema = (ImageView) findViewById(R.id.imageview_tema);

        addtema.setOnClickListener(this);
        upload_imagetema.setOnClickListener(this);
        imagetema.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.addtema:

                String namaTema = txt_tema.getText().toString();
                String judulTema = txt_judultema.getText().toString();
                String tujuan = txt_tujuan.getText().toString();
                String kegiatanTema = txt_kegiatan.getText().toString();
                String catatan = txt_catatan.getText().toString();

                if (namaTema.trim().length() > 0 && judulTema.trim().length() > 0
                        && tujuan.trim().length() > 0 && kegiatanTema.trim().length() > 0 && catatan.trim().length() > 0) {
                    sendData(namaTema, judulTema, tujuan, kegiatanTema, catatan);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Field tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.imageview_tema:
                pickImage();
                break;
        }
    }

    private void pickImage() {
        imagetema.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Tambah_Tema.this);
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
        imagetema.setImageResource(0);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                try {
                    Log.e("CAMERA", fileUri.getPath());
//                    File file= ImagePicker.Companion.getFile(data);
//                    sendData(getStringImage(file));
//                    imagetema.setImageURI(data.getData());
                    bitmap = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
                try {
                    // mengambil gambar dari Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(Tambah_Tema.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getStringImage (Bitmap bmp){
//        Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void sendData ( final String namaTema, final String judulTema, final String tujuan,
                            final String kegiatanTema, final String catatan){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Proses Menambahkan ...");
        showDialog();
        StringRequest srSendData = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(Tambah_Tema.this, Data_Input_Portofolio.class);
                Toast.makeText(Tambah_Tema.this, "Tema Berhasil ditambahkan", Toast.LENGTH_LONG).show();
                kosong();
                startActivity(intent);
                finish();
                hideDialog();
//                sendNotification();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Tambah_Tema.this, "Maaf ada kesalahan menambah Data Tema  ", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> parameters = new HashMap<>();
                parameters.put("nama_tema", namaTema);
                parameters.put("judul_tema", judulTema);
                parameters.put("tujuan", tujuan);
                parameters.put("kegiatan", kegiatanTema);
                parameters.put("catatan", catatan);
                parameters.put("gambar_tema",getStringImage(decoded));

                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Tambah_Tema.this);
        requestQueue.add(srSendData);
    }

//    private void sendNotification() {
//
//    }

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
        bmp.compress(Bitmap.CompressFormat.PNG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imagetema.setImageBitmap(decoded);
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

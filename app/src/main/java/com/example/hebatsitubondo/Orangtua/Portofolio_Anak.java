package com.example.hebatsitubondo.Orangtua;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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
import com.example.hebatsitubondo.Admin.Input_Agenda;
import com.example.hebatsitubondo.Admin.Tambah_Tema;
import com.example.hebatsitubondo.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Portofolio_Anak extends AppCompatActivity implements View.OnClickListener {

    Button add_portofolioTM;
    ImageButton id_upload_pkegiatan;
    ImageView imageview_portofolioTM;
    EditText txt_tema_agenda, txt_nama_anak, txt_usia, txt_waktu_kegiatan, txt_lokasi_kegiatan,txt_aspek_perkembangan ;
    String URL = "http://192.168.43.62/API_HEbATSitubondo/public/portofolio/save";
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    Uri UriPhoto;
    Bitmap BitPhoto;
    String StringImage;
    ProgressDialog pDialog;

    Bitmap bitmap, decoded;
    int success;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100

    Intent intent;
    Uri fileUri;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;

    int max_resolution_image = 2048;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portofolio__anak);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_iportofolio);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Portofolio Berbasis TM");

        add_portofolioTM = (Button) findViewById(R.id.add_portofolioTM);
        txt_tema_agenda = (EditText) findViewById(R.id.txt_tema_agenda);
        txt_nama_anak = (EditText) findViewById(R.id.txt_nama_anak);
        txt_usia = (EditText) findViewById(R.id.txt_usia);
        txt_waktu_kegiatan = (EditText) findViewById(R.id.txt_waktu_kegiatan);
        txt_lokasi_kegiatan = (EditText) findViewById(R.id.txt_lokasi_kegiatan);
        txt_aspek_perkembangan = (EditText) findViewById(R.id.txt_aspek_perkembangan);
        id_upload_pkegiatan = (ImageButton) findViewById(R.id.id_upload_pkegiatan);
        imageview_portofolioTM = (ImageView) findViewById(R.id.imageview_pkegiatan);

        add_portofolioTM.setOnClickListener(this);
        id_upload_pkegiatan.setOnClickListener(this);
        imageview_portofolioTM.setOnClickListener(this);

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        txt_waktu_kegiatan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Portofolio_Anak.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {

        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txt_waktu_kegiatan.setText(sdf.format(myCalendar.getTime()));
    }

    public void onClick(View v) {
        switch (v.getId()){

            case R.id.add_portofolioTM:

                String namaTema = txt_tema_agenda.getText().toString();
                String namaAnak = txt_nama_anak.getText().toString();
                String usia = txt_usia.getText().toString();
                String waktuKegiatan = txt_waktu_kegiatan.getText().toString();
                String lokasiKegiatan = txt_lokasi_kegiatan.getText().toString();
                String aspekPerkembangan = txt_aspek_perkembangan.getText().toString();

                if (namaTema.trim().length() > 0 && namaAnak.trim().length() > 0
                        && usia.trim().length() > 0 && waktuKegiatan.trim().length() > 0 && lokasiKegiatan.trim().length() > 0&& aspekPerkembangan.trim().length() > 0) {
                    sendData(namaTema, namaAnak, usia, waktuKegiatan, lokasiKegiatan, aspekPerkembangan);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Field tidak boleh kosong", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.imageview_pkegiatan:
                pickImage();
                break;
        }
    }

    private void pickImage() {
        imageview_portofolioTM.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Portofolio_Anak.this);
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
        imageview_portofolioTM.setImageResource(0);

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
                    bitmap = MediaStore.Images.Media.getBitmap(Portofolio_Anak.this.getContentResolver(), data.getData());
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

        private void sendData ( final String namaTema, final String namaAnak, final String usia,
        final String waktuKegiatan, final String lokasiKegiatan,  final String aspekPerkembangan){
            pDialog = new ProgressDialog(this);
            pDialog.setCancelable(false);
            pDialog.setMessage("Proses Menambahkan ...");
            showDialog();
            StringRequest srSendData = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Intent intent = new Intent(Portofolio_Anak.this, Portofolio.class);
                    Toast.makeText(Portofolio_Anak.this, "Portofolio save succesfully", Toast.LENGTH_LONG).show();
                    kosong();
                    startActivity(intent);
                    finish();
                    hideDialog();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Portofolio_Anak.this, "Sorry, failed save data Portofolio  ", Toast.LENGTH_LONG).show();
                    hideDialog();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> parameters = new HashMap<>();
                    parameters.put("nama_tema", namaTema);
                    parameters.put("nama_anak", namaAnak);
                    parameters.put("usia", usia);
                    parameters.put("waktu_kegiatan", waktuKegiatan);
                    parameters.put("lokasi_kegiatan", lokasiKegiatan);
                    parameters.put("aspek_perkembangan", aspekPerkembangan);
                    parameters.put("foto_kegiatanTM", getStringImage(decoded));

                    return parameters;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Portofolio_Anak.this);
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
            imageview_portofolioTM.setImageBitmap(decoded);
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
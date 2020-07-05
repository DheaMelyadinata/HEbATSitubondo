package com.example.hebatsitubondo.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Update_kegiatan extends AppCompatActivity implements View.OnClickListener {

    Button update_agendakegiatan;
    ImageButton id_upload_agendakegiatan;
    ImageView update_img_agendakegiatan;
    EditText txt_update_jenis_kegiatan, txt_update_nama_kegiatan, txt_update_waktu_kegiatan, txt_update_tanggal_pelaksanaan, txt_update_tempat;
    private int getIdKegiatan = 0;

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

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
        setContentView(R.layout.activity_update_kegiatan);

        Toolbar upkegiatan = (Toolbar) findViewById(R.id.tupdate_kegiatan);
        setSupportActionBar(upkegiatan);
        getSupportActionBar().setTitle("Agenda Kegiatan");

        update_agendakegiatan = (Button) findViewById(R.id.update_agendakegiatan);
        txt_update_jenis_kegiatan = (EditText) findViewById(R.id.txt_update_jenis_kegiatan);
        txt_update_nama_kegiatan = (EditText) findViewById(R.id.txt_update_nama_kegiatan);
        txt_update_waktu_kegiatan = (EditText) findViewById(R.id.txt_update_waktu_kegiatan);
        txt_update_tanggal_pelaksanaan = (EditText) findViewById(R.id.txt_update_tanggal_pelaksanaan);
        txt_update_tempat = (EditText) findViewById(R.id.txt_update_tempat);
        id_upload_agendakegiatan = (ImageButton) findViewById(R.id.id_upload_agendakegiatan);
        update_img_agendakegiatan = (ImageView) findViewById(R.id.update_img_agendakegiatan);

        update_agendakegiatan.setOnClickListener(this);
        id_upload_agendakegiatan.setOnClickListener(this);
        update_img_agendakegiatan.setOnClickListener(this);
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

        txt_update_tanggal_pelaksanaan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Update_kegiatan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        txt_update_waktu_kegiatan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Update_kegiatan.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_update_waktu_kegiatan.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        Intent intent = getIntent();
        getIdKegiatan = intent.getIntExtra("id_kegiatan", 0);
        String jenis_kegiatan = intent.getStringExtra("jenis_kegiatan");
        String nama_kegiatan = intent.getStringExtra("nama_kegiatan");
        String waktu_kegiatan = intent.getStringExtra("waktu_kegiatan");
        String tanggal_pelaksanaan = intent.getStringExtra("tanggal_pelaksanaan");
        String tempat = intent.getStringExtra("tempat");
        String photo_kegiatan = intent.getStringExtra("photo_kegiatan");


        Glide.with(this)
                .load("http://192.168.43.62/API_HEbATSitubondo/public/" + photo_kegiatan)
                .apply(new RequestOptions().override(350, 550))
                .into(update_img_agendakegiatan);
        txt_update_jenis_kegiatan.setText(jenis_kegiatan);
        txt_update_nama_kegiatan.setText(nama_kegiatan);
        txt_update_waktu_kegiatan.setText(waktu_kegiatan);
        txt_update_tanggal_pelaksanaan.setText(tanggal_pelaksanaan);
        txt_update_tempat.setText(tempat);

    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txt_update_tanggal_pelaksanaan.setText(sdf.format(myCalendar.getTime()));
    }

    public void onClick(View v) {
        switch (v.getId()){

            case R.id.update_agendakegiatan:
                String jeniskegiatan = txt_update_jenis_kegiatan.getText().toString();
                String namaKegiatan = txt_update_nama_kegiatan.getText().toString();
                String waktukegiatan = txt_update_waktu_kegiatan.getText().toString();
                String tanggalPelaksanaan = txt_update_tanggal_pelaksanaan.getText().toString();
                String tempat = txt_update_tempat.getText().toString();

                if ( jeniskegiatan.trim().length() > 0 && namaKegiatan.trim().length() > 0 &&
                        waktukegiatan.trim().length() > 0 && tanggalPelaksanaan.trim().length() > 0 && tempat.trim().length() > 0) {
                    sendData(jeniskegiatan, namaKegiatan, waktukegiatan,tanggalPelaksanaan,tempat, String.valueOf(getIdKegiatan));
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Field tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.update_img_agendakegiatan:
                pickImage();
                break;
        }
    }
    private void pickImage() {
        update_img_agendakegiatan.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Update_kegiatan.this);
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
        update_img_agendakegiatan.setImageResource(0);
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
                    bitmap = MediaStore.Images.Media.getBitmap(Update_kegiatan.this.getContentResolver(), data.getData());
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

    private void sendData ( final String jenisKegiatan, final String namaKegiatan, final String waktuKegiatan,
            final String tanggalPelaksanaan, final String tempat, String idKegiatan){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Proses Menambahkan ...");
        showDialog();
        String URL = "http://192.168.43.62/API_HEbATSitubondo/public/kegiatan/" + idKegiatan + "/update";
        StringRequest srSendData = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(Update_kegiatan.this, Data_Input_Agenda_Kegiatan.class);
                Toast.makeText(Update_kegiatan.this, "Kegiatan Berhasil Diubah", Toast.LENGTH_LONG).show();
                kosong();
                startActivity(intent);
                finish();
                hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Update_kegiatan.this, "Maaf ada kesalahan mengubah Data Kegiatan  ", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> parameters = new HashMap<>();
                parameters.put("jenis_kegiatan", jenisKegiatan);
                parameters.put("nama_kegiatan", namaKegiatan);
                parameters.put("waktu_kegiatan", waktuKegiatan);
                parameters.put("tanggal_pelaksanaan", tanggalPelaksanaan);
                parameters.put("tempat", tempat);
                if( getStringImage(decoded) != null){
                    parameters.put("photo_kegiatan", getStringImage(decoded));
                }//jika gambarnya di ubah
                else{
                    parameters.put("photo_kegiatan", "null");//jika tidak diubah
                }
                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Update_kegiatan.this);
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
        update_img_agendakegiatan.setImageBitmap(decoded);
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

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
import com.example.hebatsitubondo.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class Input_Agenda extends AppCompatActivity implements View.OnClickListener {

    Button add_agendakegiatan;
    ImageButton id_upload_agendakegiatan;
    ImageView imageview_agendakegiatan;
    EditText txt_jenis_kegiatan, txt_nama_kegiatan, txt_waktu_pelaksanaan, txt_tanggal_pelaksanaan, txt_tempat_kegiatan;
    String URL = "http://192.168.43.62/API_HEbATSitubondo/public/kegiatan/save";


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
        setContentView(R.layout.activity_input__agenda);

        Toolbar toolbar_kegiatan = (Toolbar) findViewById(R.id.toolbar_kegiatan);
        setSupportActionBar(toolbar_kegiatan);
        getSupportActionBar().setTitle("Input Agenda Kegiatan");

        add_agendakegiatan = (Button) findViewById(R.id.add_agendakegiatan);
        txt_jenis_kegiatan = (EditText) findViewById(R.id.txt_jenis_kegiatan);
        txt_nama_kegiatan = (EditText) findViewById(R.id.txt_nama_kegiatan);
        txt_waktu_pelaksanaan = (EditText) findViewById(R.id.txt_waktu_pelaksanaan);
        txt_tanggal_pelaksanaan=(EditText) findViewById(R.id.txt_tanggal_pelaksanaan);
        txt_tempat_kegiatan = (EditText) findViewById(R.id.txt_tempat_kegiatan);
        id_upload_agendakegiatan = (ImageButton) findViewById(R.id.id_upload_agendakegiatan);
        imageview_agendakegiatan = (ImageView) findViewById(R.id.imageview_agendakegiatan);

        add_agendakegiatan.setOnClickListener(this);
        id_upload_agendakegiatan.setOnClickListener(this);
        imageview_agendakegiatan.setOnClickListener(this);

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

        txt_tanggal_pelaksanaan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Input_Agenda.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        txt_waktu_pelaksanaan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Input_Agenda.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_waktu_pelaksanaan.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txt_tanggal_pelaksanaan.setText(sdf.format(myCalendar.getTime()));
    }


    public void onClick(View v) {
        switch (v.getId()){

            case R.id.add_agendakegiatan:

                String jenisKegiatan = txt_jenis_kegiatan.getText().toString();
                String namaKegiatan = txt_nama_kegiatan.getText().toString();
                String waktuKegiatan = txt_waktu_pelaksanaan.getText().toString();
                String tanggalPelaksanaan = txt_tanggal_pelaksanaan.getText().toString();
                String tempat = txt_tempat_kegiatan.getText().toString();

                if (jenisKegiatan.trim().length() > 0 && namaKegiatan.trim().length() > 0
                        && waktuKegiatan.trim().length() > 0 && tanggalPelaksanaan.trim().length() > 0 && tempat.trim().length() > 0) {
                    sendData(jenisKegiatan, namaKegiatan, waktuKegiatan,tanggalPelaksanaan, tempat);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Field tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.imageview_agendakegiatan:
                pickImage();
                break;
        }
    }

    private void pickImage() {
        imageview_agendakegiatan.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Input_Agenda.this);
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
        imageview_agendakegiatan.setImageResource(0);
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
                    bitmap = MediaStore.Images.Media.getBitmap(Input_Agenda.this.getContentResolver(), data.getData());
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

    private void sendData (final String jenisKegiatan, final String namaKegiatan,
                           final String waktuKegiatan,final String tanggalPelaksanaan ,final String tempat){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Proses Menambahkan ...");
        showDialog();
        StringRequest srSendData = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(Input_Agenda.this, Data_Input_Agenda_Kegiatan.class);
                Toast.makeText(Input_Agenda.this, "Data Kegiatan Berhasil ditambahkan", Toast.LENGTH_LONG).show();
                kosong();
                startActivity(intent);
                finish();
                hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Input_Agenda.this, "Maaf ada kesalahan menambah Data Kegiatan  ", Toast.LENGTH_LONG).show();
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
                parameters.put("photo_kegiatan", getStringImage(decoded));

                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Input_Agenda.this);
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
        imageview_agendakegiatan.setImageBitmap(decoded);
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

package com.example.hebatsitubondo.Orangtua;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hebatsitubondo.R;

import java.util.HashMap;
import java.util.Map;

import cn.refactor.lib.colordialog.PromptDialog;

public class Input_StoryTelling extends AppCompatActivity {

    Button simpantema;
    ProgressDialog pDialog;
    EditText Tema_Select, namaanak, judultema, txt_cerita;
    String URL_add = "http://192.168.43.62/API_HEbATSitubondo/public/story_telling/save";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_story_telling);

        Toolbar toolbar2 = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setTitle("Story Telling");

        simpantema = (Button) findViewById(R.id.simpantema);
        Tema_Select = (EditText) findViewById(R.id.Tema_Select);
        namaanak = (EditText) findViewById(R.id.namaanak);
        judultema = (EditText) findViewById(R.id.judultema);
        txt_cerita = (EditText) findViewById(R.id.txt_cerita);


        simpantema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog = new ProgressDialog(simpantema.getContext());
                pDialog.setCancelable(false);
                pDialog.setMessage("Proses Menyimpan ...");
                showDialog();
                StringRequest request = new StringRequest(Request.Method.POST, URL_add, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {

                        ShowDialog();
                        hideDialog();
                        Tema_Select.setText("");
                        namaanak.setText("");
                        judultema.setText("");
                        txt_cerita.setText("");
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Input_StoryTelling.this, "Failed Save Story Telling "+volleyError, Toast.LENGTH_LONG).show();;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("nama_tema", Tema_Select.getText().toString());
                        parameters.put("nama_anak", namaanak.getText().toString());
                        parameters.put("judul_tema", judultema.getText().toString());
                        parameters.put("cerita", txt_cerita.getText().toString());
                        return parameters;
                    }
                };
                RequestQueue rQueue = Volley.newRequestQueue(Input_StoryTelling.this);
                rQueue.add(request);
            }
        });
    }

    private void ShowDialog(){
        PromptDialog dialog = new PromptDialog(this);
        dialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
        dialog.setAnimationEnable(true);
        dialog.setTitleText("SUCCESS");
        dialog.setContentText("Successfully save story telling");
        dialog.setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
            @Override
            public void onClick(PromptDialog dialog) {
                dialog.dismiss();
            }
        }).show();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Input_StoryTelling.this, Portofolio.class);
        startActivity(intent);
        super.onBackPressed();
    }

}


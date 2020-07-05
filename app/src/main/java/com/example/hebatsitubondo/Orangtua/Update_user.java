package com.example.hebatsitubondo.Orangtua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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

import static com.example.hebatsitubondo.Login.id_user;

public class Update_user extends AppCompatActivity {

    Button simpan_update_user;
    EditText txt_username_update, txt_email_update, txt_no_handphone_update;
    private int getId_user = 0;
    String URL_Update_user = "http://192.168.43.62/API_HEbATSitubondo/public/users" + id_user + "/update";
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        Toolbar toolbar_update = (Toolbar) findViewById(R.id.toolbar_update_user);
        setSupportActionBar(toolbar_update);
        getSupportActionBar().setTitle("Update Profil User");

        simpan_update_user = (Button) findViewById(R.id.simpan_update_user);
        txt_username_update = (EditText) findViewById(R.id.txt_username_update);
        txt_email_update = (EditText) findViewById(R.id.txt_email_update);
        txt_no_handphone_update = (EditText) findViewById(R.id.txt_no_handphone_update);


        simpan_update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog = new ProgressDialog(simpan_update_user.getContext());
                pDialog.setCancelable(false);
                pDialog.setMessage("Proses Menyimpan ...");
                showDialog();
                StringRequest request = new StringRequest(Request.Method.POST, URL_Update_user , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        ShowDialog();
                        hideDialog();
                        txt_username_update.setText("");
                        txt_email_update.setText("");
                        txt_no_handphone_update.setText("");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Update_user.this, "Failed Update Profil User " + volleyError, Toast.LENGTH_LONG).show();
                        ;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("username", txt_username_update.getText().toString());
                        parameters.put("email", txt_email_update.getText().toString());
                        parameters.put("no_handphone", txt_no_handphone_update.getText().toString());
                        return parameters;
                    }
                };
                RequestQueue rQueue = Volley.newRequestQueue(Update_user.this);
                rQueue.add(request);
            }
        });
    }

    private void ShowDialog() {
        PromptDialog dialog = new PromptDialog(this);
        dialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
        dialog.setAnimationEnable(true);
        dialog.setTitleText("SUCCESS");
        dialog.setContentText("Successfully updated");
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
        Intent intent = new Intent(Update_user.this, Akun_Profile.class);
        startActivity(intent);
        super.onBackPressed();
    }
}

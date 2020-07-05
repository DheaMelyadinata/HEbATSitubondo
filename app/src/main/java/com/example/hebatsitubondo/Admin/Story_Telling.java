package com.example.hebatsitubondo.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hebatsitubondo.Admin.Admin_Adapter.Adapter_StoryTelling;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_StoryTelling;
import com.example.hebatsitubondo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Story_Telling extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private String url_storytelling = "http://192.168.43.62/API_HEbATSitubondo/public/story_telling";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Model_StoryTelling> data = new ArrayList<Model_StoryTelling>();
    private RecyclerView myrv;
    private SwipeRefreshLayout refresh;
    private Adapter_StoryTelling adapter_storyTelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story__telling);

        Toolbar toolbars = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbars);
        getSupportActionBar().setTitle("Story Telling");

        refresh = (SwipeRefreshLayout) findViewById(R.id.swipedown);
        myrv = (RecyclerView) findViewById(R.id.recycleStory_Telling);

        refresh.setOnRefreshListener(this);
        refresh.post(new Runnable() {
            @Override
            public void run() {
                data.clear();
                getData();
            }
        });
    }

    private void getData() {
        refresh.setRefreshing(true);

        ArrayRequest = new JsonArrayRequest(url_storytelling, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        Model_StoryTelling model_storyTelling = new Model_StoryTelling();
                        model_storyTelling.setId_storytelling(jsonObject.getInt("id_story_telling"));
                        model_storyTelling.setNama_tema(jsonObject.getString("nama_tema"));
                        model_storyTelling.setNama_anak(jsonObject.getString("nama_anak"));
                        model_storyTelling.setJudul_tema(jsonObject.getString("judul_tema"));
                        model_storyTelling.setCerita(jsonObject.getString("cerita"));
                        data.add(model_storyTelling);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterPush(data);
                refresh.setRefreshing(false);
                Toast.makeText(Story_Telling.this, "Size of Liste " + String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(Story_Telling.this);
        requestQueue.add(ArrayRequest);
    }

    private void adapterPush(List<Model_StoryTelling> data) {
        adapter_storyTelling = new Adapter_StoryTelling(this, data);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(adapter_storyTelling);

    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Story_Telling.this, Dashboard_Admin.class);
        startActivity(intent);
        super.onBackPressed();
    }
}

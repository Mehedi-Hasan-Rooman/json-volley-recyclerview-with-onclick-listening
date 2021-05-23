package com.example.json_volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements com.example.json_volley.ExampleAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "likeCount";

    private RecyclerView RecyclerView;
    private ExampleAdapter ExampleAdapter;
    private ArrayList<Example_item> ExampleList;
    private RequestQueue RequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView = findViewById(R.id.recyclerview);
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ExampleList = new ArrayList<>();

        RequestQueue = Volley.newRequestQueue(this);


        ParseJSON();




    }

    private void ParseJSON() {

        String url = "https://pixabay.com/api/?key=20386069-b56c8a2d46e3e1fd5a57d8d14&q=yellow+flowers&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Get & Set Data From Web
                        try {

                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i = 0; i < jsonArray.length(); i++ ){

                                JSONObject web_data = jsonArray.getJSONObject(i);

                                String Image_URL = web_data.getString("webformatURL");
                                String Creator_Name = web_data.getString("user");
                                int Likes = web_data.getInt("likes");

                                ExampleList.add(new Example_item(Image_URL,Creator_Name,Likes));




                            }


                          //  ExampleAdapter = new ExampleAdapter();

                            ExampleAdapter = new ExampleAdapter(MainActivity.this,ExampleList);
                            RecyclerView.setAdapter(ExampleAdapter);
                            ExampleAdapter.SetOnItemClickListener(MainActivity.this);


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });


        RequestQueue.add(request);
    }

    // Interfacing Method for Click
    @Override
    public void OnItemClick(int position) {

        Intent details_intent = new Intent(this,DetailsActivity.class);
        Example_item current_item_clicked = ExampleList.get(position);

        details_intent.putExtra(EXTRA_URL,current_item_clicked.getImageurl());
        details_intent.putExtra(EXTRA_CREATOR,current_item_clicked.getCreator_name());
        details_intent.putExtra(EXTRA_LIKES,current_item_clicked.getLikes());
        startActivity(details_intent);
    }
}
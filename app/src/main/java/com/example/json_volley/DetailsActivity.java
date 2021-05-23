package com.example.json_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.json_volley.MainActivity.EXTRA_CREATOR;
import static com.example.json_volley.MainActivity.EXTRA_LIKES;
import static com.example.json_volley.MainActivity.EXTRA_URL;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        String image_url = intent.getStringExtra(EXTRA_URL);
        String creator_name = intent.getStringExtra(EXTRA_CREATOR);
        int likes = intent.getIntExtra(EXTRA_LIKES,0);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewCreator = findViewById(R.id.text_view_creator_detail);
        TextView textViewLikes = findViewById(R.id.text_view_like_detail);


        Picasso.get().load(image_url).into(imageView);
        textViewCreator.setText("Name : " + creator_name);
        textViewLikes.setText("Likes :" + likes);


    }
}
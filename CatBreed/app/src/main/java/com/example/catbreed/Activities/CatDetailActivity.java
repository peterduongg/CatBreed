package com.example.catbreed.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.catbreed.Other.AppDatabase;
import com.example.catbreed.R;
import com.example.catbreed.model.Cat;
import com.google.gson.Gson;

public class CatDetailActivity extends AppCompatActivity {
//defining different elements to be included in the detail view
    TextView breedNameTextView;
    TextView temperamentTextView;
    TextView originTextView;
    TextView lifeSpanTextView;
    TextView wikiTextView;
    TextView dogFriendTextView;
    TextView descriptionTextView;
    ImageView catImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting content to respective element in XML file
        setContentView(R.layout.cat_breed_detail);

        breedNameTextView = findViewById(R.id.breedNameDetail);
        temperamentTextView = findViewById(R.id.temperamentDetail);
        originTextView = findViewById(R.id.originDetail);
        lifeSpanTextView = findViewById(R.id.lifeSpanDetail);
        wikiTextView = findViewById(R.id.wikiDetail);
        dogFriendTextView = findViewById(R.id.dogFriendDetail);
        descriptionTextView = findViewById(R.id.descriptionDetail);
        catImageView = findViewById(R.id.catImageViewDetail);

        //intent to pass id, to open detail
        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        //saving cat to db
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        Cat cat = db.catDao().getCat(id);

        //setting text
        breedNameTextView.setText(cat.getName());
        temperamentTextView.setText(cat.getTemperament());
        originTextView.setText(cat.getOrigin());
        lifeSpanTextView.setText(cat.getLife_span() + " Years old");
        wikiTextView.setText(cat.getWikipedia_url());
        dogFriendTextView.setText(" " + cat.getDog_friendly() + " ");
        descriptionTextView.setText(cat.getDescription());

        //calling method to put cat image into ImageView, passing in specific cat id
        setCatImage(cat.getId());

    }

    private void setCatImage(String catId) {
        //getting image for specified cat (by using id)
        String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + catId;
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        Response.Listener<String> responseListener = new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CatBreedImage[] objectsArray = gson.fromJson(response, CatBreedImage[].class);
                Glide.with(getApplicationContext()).load(objectsArray[0].getUrl()).into(catImageView);
                requestQueue.stop();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"The request failed: Try connecting to the Internet", Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);
    }
}

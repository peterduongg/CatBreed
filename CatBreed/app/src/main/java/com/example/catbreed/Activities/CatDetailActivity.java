package com.example.catbreed.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.catbreed.Other.AppDatabase;
import com.example.catbreed.Other.CatBreedAdapter;
import com.example.catbreed.R;
import com.example.catbreed.model.Cat;

public class CatDetailActivity extends AppCompatActivity {

    ConstraintLayout catConstraintLayout;
    TextView breedNameTextView;
    //TextView weightTextView;
    TextView temperamentTextView;
    TextView originTextView;
    TextView lifeSpanTextView;
    TextView wikiTextView;
    TextView dogFriendTextView;
    TextView descriptionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_breed_detail);

        breedNameTextView = findViewById(R.id.breedNameDetail);
//        weightTextView = catConstraintLayout.findViewById(R.id.weightDetail);
        temperamentTextView = findViewById(R.id.temperamentDetail);
        originTextView = findViewById(R.id.originDetail);
        lifeSpanTextView = findViewById(R.id.lifeSpanDetail);
        wikiTextView = findViewById(R.id.wikiDetail);
        dogFriendTextView = findViewById(R.id.dogFriendDetail);
        descriptionTextView = findViewById(R.id.descriptionDetail);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        Cat cat = db.catDao().getCat(id);

        breedNameTextView.setText(cat.getName());
//        weightTextView.setText(cat.getWeight());
        temperamentTextView.setText(cat.getTemperament());
        originTextView.setText(cat.getOrigin());
        lifeSpanTextView.setText(cat.getLife_span() + " Years old");
        wikiTextView.setText(cat.getWikipedia_url());
        dogFriendTextView.setText(" " + cat.getDog_friendly() + " ");
        descriptionTextView.setText(cat.getDescription());

        //String imageUrl = book.getBookImage();
        //Glide.with(this).load(imageUrl).into(coverImageView);
    }
}

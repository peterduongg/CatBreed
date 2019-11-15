package com.example.catbreed.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.catbreed.Other.AppDatabase;
import com.example.catbreed.Other.CatBreedAdapter;
import com.example.catbreed.R;
import com.example.catbreed.model.Cat;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatBreedFragment extends Fragment {
    private RecyclerView recyclerView;


    public CatBreedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_cat_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_breed);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final EditText searchText = view.findViewById(R.id.searchBar);

        final CatBreedAdapter catBreedAdapter = new CatBreedAdapter(getContext());
        final AppDatabase db = AppDatabase.getInstance(getContext());

        // Start Volley

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        String url = "https://api.thecatapi.com/v1/breeds?api_key=d2ce3042-32ad-4d16-8414-8247b9664dfb";

        // Response.Listener<String>. We define what to do after a response is received.

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

//
                Gson gson = new Gson();

                //Saving Json results into catArray
                Cat[] catArray = gson.fromJson(response, Cat[].class);
                final List<Cat> catList = Arrays.asList(catArray);

                db.catDao().insert(catList);
                catBreedAdapter.setData(catList);

                recyclerView.setAdapter(catBreedAdapter);

                requestQueue.stop();


                searchText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        final ArrayList<Cat> filteredList = new ArrayList<>();
                        for (Cat cat : catList) {
                            if (cat.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                                filteredList.add(cat);
                            }
                        }

                        catBreedAdapter.filterList(filteredList);
                        recyclerView.setAdapter(catBreedAdapter);
                    }
                });

            }
        };
//
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //catBreedAdapter.setData(catBreedAdapter);

                Toast.makeText(getContext(), "The request failed: Try connecting to the internet", Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };
//
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
//

        requestQueue.add(stringRequest);

        return view;
    }
}




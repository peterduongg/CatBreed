package com.example.catbreed.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catbreed.Other.AppDatabase;
import com.example.catbreed.Other.CatBreedAdapter;
import com.example.catbreed.Other.FavouriteAppDatabase;
import com.example.catbreed.R;
import com.example.catbreed.model.Cat;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment {
    private RecyclerView recyclerView;


    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_favourite_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_favourite);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final CatBreedAdapter catBreedAdapter = new CatBreedAdapter(getContext());
        final FavouriteAppDatabase db = FavouriteAppDatabase.getInstance(getContext());

        catBreedAdapter.setData(db.favouriteCatDao().getAllCats());


        recyclerView.setAdapter(catBreedAdapter);


        return view;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String string);
    }
}




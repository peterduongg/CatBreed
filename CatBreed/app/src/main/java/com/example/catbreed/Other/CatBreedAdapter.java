package com.example.catbreed.Other;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.catbreed.Activities.CatDetailActivity;
import com.example.catbreed.R;
import com.example.catbreed.model.Cat;


import java.util.ArrayList;
import java.util.List;
public class CatBreedAdapter extends RecyclerView.Adapter<CatBreedAdapter.CatBreedViewHolder>{
    // class variable that holds the data that we want to adapt
    private List<Cat> catsToAdapt;

    //defining database instance
    AppDatabase database;
    FavouriteAppDatabase database2;

    public CatBreedAdapter(Context context){
        database = AppDatabase.getInstance(context);
        database2 = FavouriteAppDatabase.getInstance(context);
    }

    public void setData(List<Cat> catsToAdapt) {
        // This is basically a Setter that we use to give data to the adapter
        this.catsToAdapt = catsToAdapt;

    }

    @NonNull
    @Override
    public CatBreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // First create a View from the layout file. It'll probably be a ViewGroup like
        // ConstraintLayout that contains more Views inside it.
        // This view now represents your entire one item.
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat_breed, parent, false);

        // Then create an instance of your custom ViewHolder with the View you got from inflating
        // the layout.
        CatBreedViewHolder catBreedViewHolder = new CatBreedViewHolder(view);
        return catBreedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CatBreedViewHolder holder, int position) {
        final Cat catAtPosition = catsToAdapt.get(position);

        holder.catBreedTextView.setText(catAtPosition.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("id", catAtPosition.getId());
                context.startActivity(intent);


            }
        });

        if ((database2.favouriteCatDao().getCatId(catAtPosition.getId())) == 1){
            holder.isBookmarked = true;
            holder.favouriteImageView.setImageResource(R.drawable.ic_add_box);
            holder.unfavouriteImageView.setImageResource(R.drawable.ic_minus);
        }else {
            holder.isBookmarked = false;
            holder.unfavouriteImageView.setImageResource(R.drawable.ic_minus_box);
            holder.favouriteImageView.setImageResource(R.drawable.ic_add);
        }


        holder.favouriteImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                database2.favouriteCatDao().insert(catAtPosition);
                holder.favouriteImageView.setImageResource(R.drawable.ic_add_box);
                holder.unfavouriteImageView.setImageResource(R.drawable.ic_minus);
                Toast.makeText(view.getContext(), "Cat favourited.", Toast.LENGTH_SHORT).show();
                }

        });

        holder.unfavouriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database2.favouriteCatDao().delete(catAtPosition);
                holder.favouriteImageView.setImageResource(R.drawable.ic_add);
                holder.unfavouriteImageView.setImageResource(R.drawable.ic_minus_box);
                Toast.makeText(view.getContext(), "Cat removed from favourites.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();

    }
    public void filterList (ArrayList<Cat> filteredList){
        catsToAdapt = filteredList;
        notifyDataSetChanged();

    }


    // ViewHolder represents one item, but doesn't have data when it's first constructed.
    // We assign the data in onBindViewHolder.
    public static class CatBreedViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView catBreedTextView;
        public ImageView favouriteImageView;
        public ImageView unfavouriteImageView;
        public boolean isBookmarked = false;

        // This constructor is used in onCreateViewHolder
        public CatBreedViewHolder(View v) {
            super(v);  // runs the constructor for the ViewHolder superclass
            view = v;
            catBreedTextView = v.findViewById(R.id.breedName);
            favouriteImageView = v.findViewById(R.id.favouriteImageButton);
            unfavouriteImageView = v.findViewById(R.id.unfavouriteImageButton);


            // We can define onClickListener for bookmark button here because it depends on data
            // unique to this ViewHolder (i.e. whether this item has already been bookmarked or not)
            // Technically, we can do everything that we do in onBindViewHolder in here as well.






        }



    }
}
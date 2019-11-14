package com.example.catbreed.Other;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.catbreed.Activities.CatDetailActivity;
import com.example.catbreed.R;
import com.example.catbreed.model.Cat;


import java.util.ArrayList;
import java.util.List;
public class CatBreedAdapter extends RecyclerView.Adapter<CatBreedAdapter.CatBreedViewHolder>{//} implements Filterable {
    // class variable that holds the data that we want to adapt
    private List<Cat> catsToAdapt;

    //making copy of cat list
//    private List<Cat> catsToAdaptFull;

    public void setData(List<Cat> catsToAdapt) {
        // This is basically a Setter that we use to give data to the adapter
        this.catsToAdapt = catsToAdapt;

        //creating an array that contains same items as "catsToAdapt"
//        catsToAdaptFull = new ArrayList<>(catsToAdapt);
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
        CatBreedViewHolder catBreedViewHolderViewHolder = new CatBreedViewHolder(view);
        return catBreedViewHolderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatBreedViewHolder holder, int position) {
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

//        holder.shareImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Context context = view.getContext();
//                Intent intent = new Intent(Intent.ACTION_SEND);
//
//                intent.putExtra(Intent.EXTRA_TEXT, catAtPosition.getTitle());
//                intent.setType("text/plain");
//                context.startActivity(intent);
//            }
//        });

////        holder.articleImageView.setImageResource(articleAtPosition.getImageDrawableId());
//        try {
//            //i wrapped this in a try catch bcos the null check wasnt working
//            if (catAtPosition.getMedia() != null) {
//                String imageUrl = catAtPosition.getMedia()[0].getMedia_metadata()[0].getUrl();
//                Glide.with(holder.view.getContext()).load(imageUrl).into(holder.articleImageView);
//            }
//        }catch (ArrayIndexOutOfBoundsException e){
//            //if you cant find an image, use this image of a cloud with a line through it instead
//            holder.articleImageView.setImageResource(R.drawable.ic_cloud_off_black_24dp);
//        }
    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();

    }
    public void filterList (ArrayList<Cat> filteredList){
        catsToAdapt = filteredList;
        notifyDataSetChanged();

    }


//    @Override
//    public Filter getFilter(){
//        return  exampleFilter;
//
//    }
//
//    private Filter exampleFilter = new Filter(){
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<Cat> filteredCatList = new ArrayList<>();
//            if(constraint == null || constraint.length() == 0){
//                filteredCatList.addAll(catsToAdaptFull);
//            } else {
//                //making sure search is not case sensitive by turning to lowercase
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                //comparing search to each breed in the Cat list
//                for(Cat cat : catsToAdaptFull){
//                    if (cat.getName().toLowerCase().contains(filterPattern)){
//                        filteredCatList.add(cat);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filteredCatList;
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            catsToAdapt.clear();
//            catsToAdapt.addAll((List)results.values);
//            notifyDataSetChanged();
//        }
//    };

    // ViewHolder represents one item, but doesn't have data when it's first constructed.
    // We assign the data in onBindViewHolder.
    public static class CatBreedViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView catBreedTextView;
//        public ImageView shareImageView;
//        public ImageView bookmarkImageView;
        public boolean isBookmarked = false;

        // This constructor is used in onCreateViewHolder
        public CatBreedViewHolder(View v) {
            super(v);  // runs the constructor for the ViewHolder superclass
            view = v;
            catBreedTextView = v.findViewById(R.id.breedName);
//            shareImageView = v.findViewById(R.id.newsShareButton);
//            bookmarkImageView = v.findViewById(R.id.newsSaveButton);

            // We can define onClickListener for bookmark button here because it depends on data
            // unique to this ViewHolder (i.e. whether this item has already been bookmarked or not)
            // Technically, we can do everything that we do in onBindViewHolder in here as well.




//            bookmarkImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(isBookmarked) {
//                        bookmarkImageView.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
//                    } else {
//                        bookmarkImageView.setImageResource(R.drawable.ic_bookmark_black_24dp);
//                    }
//                    isBookmarked = !isBookmarked;
//                }
//            });

        }



    }
}
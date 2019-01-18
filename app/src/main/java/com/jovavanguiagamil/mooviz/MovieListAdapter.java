package com.jovavanguiagamil.mooviz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jovavanguiagamil.mooviz.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> filmList;


    public MovieListAdapter(Context context, ArrayList<Movie> filmList) {
        this.context = context;
        this.filmList = filmList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v;
        LayoutInflater mInflater = LayoutInflater.from(context);
        v = mInflater.inflate(R.layout.card_item_filmes, viewGroup, false );
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        final Movie film = filmList.get(position);
        holder.filmeTitle.setText(film.getTitle());
        Picasso.get().load(film.getBackdropPath()).into(holder.filmImage);
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView filmImage;
        TextView filmeTitle;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            filmImage = itemView.findViewById(R.id.film_image_id);
            filmeTitle = itemView.findViewById(R.id.film_title_id);
        }
    }
}

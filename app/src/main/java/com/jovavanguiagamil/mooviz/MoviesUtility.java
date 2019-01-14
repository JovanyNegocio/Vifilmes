package com.jovavanguiagamil.mooviz;

import com.jovavanguiagamil.mooviz.models.Movie;

import java.util.ArrayList;


public class MoviesUtility {
    public static String createStringWithMovies(ArrayList<Movie> movies) {
        String allMovies = "";

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);

            allMovies = allMovies + "\n" +
                    movie.getTitle() + " " +
                    movie.getReleaseDate();
        }

        return allMovies;
    }
}

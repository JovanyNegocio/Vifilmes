package com.jovavanguiagamil.mooviz.models;
import com.google.gson.annotations.SerializedName;

public class Movie {
    private int id;
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_Path")
    private String backdropPath;
    @SerializedName("release_date")
    private String releaseDate;
    private String originalLanguage;


    public Movie(int id, String title, String posterPath, String backdropPath, String releaseDate, String originalLanguage) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.originalLanguage = originalLanguage;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPostpath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }
}


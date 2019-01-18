package com.jovavanguiagamil.mooviz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jovavanguiagamil.mooviz.api.TmdbApi;
import com.jovavanguiagamil.mooviz.models.Movie;
import com.jovavanguiagamil.mooviz.models.Page;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import static com.jovavanguiagamil.mooviz.R.id.textview_movies;

public class MainActivity extends AppCompatActivity {
    private TmdbApi api;
    private TextView moviesTextView;
    private ProgressBar loadingProgressBar;
    private Toolbar toolbar;
    private RecyclerView mrecyclerview;
    private FirebaseAuth mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Vifilmes");
        mFirebase = FirebaseAuth.getInstance();

        mrecyclerview = findViewById(R.id.films_recyclerview_id);
        mrecyclerview.setLayoutManager(new GridLayoutManager(this, 2));

        //moviesTextView = findViewById(textview_movies);
        loadingProgressBar = findViewById(R.id.progressbar_loading);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(TmdbApi.class);

        PopularMoviesRequestTask moviesRequestTask = new PopularMoviesRequestTask();
        moviesRequestTask.execute();

    }



    private void menuInicial(){
        Intent inicio = new Intent(MainActivity.this, Login.class);
        startActivity(inicio);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            PopularMoviesRequestTask moviesRequestTask = new PopularMoviesRequestTask();
            moviesRequestTask.execute();


            return true;

        }else if (item.getItemId() == R.id.sair);{
        FirebaseAuth.getInstance().signOut();
menuInicial();


    }

        return super.onOptionsItemSelected(item);
    }

    class PopularMoviesRequestTask extends AsyncTask<Void, Void, ArrayList<Movie>> {
        @Override
        protected void onPreExecute() {
//            moviesTextView.setText("");
            loadingProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            try {
                Response<Page> response = api.getPopularMovie().execute();
                if (response.isSuccessful()) {
                    Page page = response.body();
                    return page.getResults();
                } else {
                    return null;
                }

            } catch (IOException exception) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            loadingProgressBar.setVisibility(View.INVISIBLE);

            if (movies != null) {

                MovieListAdapter recyclerViewAdapter = new MovieListAdapter(MainActivity.this, movies);
                mrecyclerview.setAdapter(recyclerViewAdapter);
                //String moviesText = MoviesUtility.createStringWithMovies(movies);
                //moviesTextView.setText(moviesText);
            }
        }

        private void fillTextMovies(ArrayList<Movie> movies) {
            String allMovies = " ";


            for (Movie movie : movies) {
                allMovies = allMovies + movie.getTitle() + "\n" + movie.getReleaseDate() + "\n"
                        + movie.getPostpath() + "\n" + movie.getBackdropPath() + "\n" + movie.getOriginalLanguage();
            }
            moviesTextView.setText(allMovies);
        }
    }
}

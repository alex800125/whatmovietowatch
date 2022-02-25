package com.example.whatmovietowatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.whatmovietowatch.Utils.ConnectApiUtils;
import com.example.whatmovietowatch.Utils.JsonUtils;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    private Button search;
    private TextView searchTitle;
    private TextView searchTagline;
    private TextView searchOverview;
    private TextView searchGenre;
    private TextView searchLanguage;
    private TextView searchRelease;
    private TextView searchVoteAverage;
    private TextView searchVoteCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search_button);
        searchTitle = findViewById(R.id.search_title);
        searchTagline = findViewById(R.id.search_tagline);
        searchOverview = findViewById(R.id.search_overview);
        searchGenre = findViewById(R.id.search_genre);
        searchLanguage = findViewById(R.id.search_language);
        searchRelease = findViewById(R.id.search_release);
        searchVoteAverage = findViewById(R.id.search_voteAverage);
        searchVoteCount = findViewById(R.id.search_voteCount);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search search = new Search();
                search.execute();
            }
        });
    }

    private class Search extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String value = ConnectApiUtils.getMovieFromApi(getUrl());

            while (value.isEmpty()) {
                value = ConnectApiUtils.getMovieFromApi(getUrl());
            }

            Log.i(TAG, "value: " + value);
            return value;
        }

        @Override
        protected void onPostExecute(String s) {
            Movie movie = JsonUtils.getMovie(s);
            updateMainActivity(movie);
        }

        private String getUrl() {
            return Constants.URL_API + getRandomNumber() + Constants.API_KEY;
        }

        private Integer getRandomNumber() {
            final int min = 5;
            final int max = 100000;
            return (int) Math.floor(Math.random() * (max - min + 1) + min);
        }

        private void updateMainActivity(Movie movie) {
            searchTitle.setText(movie.getTitle());
            searchTagline.setText(movie.getTagline());
            searchOverview.setText(movie.getOverview());
            searchGenre.setText(movie.getGenre());
            searchLanguage.setText(movie.getLanguage());
            searchRelease.setText(movie.getRelease());
            searchVoteAverage.setText(movie.getVoteAverage());
            searchVoteCount.setText(movie.getVoteCount());
        }
    }
}
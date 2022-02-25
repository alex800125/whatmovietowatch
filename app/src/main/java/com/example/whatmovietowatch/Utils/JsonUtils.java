package com.example.whatmovietowatch.Utils;

import android.util.Log;

import com.example.whatmovietowatch.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {

    private final static String TAG = "JsonUtils";

    public static Movie getMovie(String json) {
        Movie movie = new Movie();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String genres = "";

            movie.setTitle(jsonObject.getString("title"));
            movie.setTagline(jsonObject.getString("tagline"));
            movie.setLanguage(jsonObject.getString("original_language"));
            movie.setRelease(jsonObject.getString("release_date"));
            movie.setOverview(jsonObject.getString("overview"));
            movie.setVoteAverage(jsonObject.getString("vote_average"));
            movie.setVoteCount(jsonObject.getString("vote_count"));

            JSONArray jsonArray = new JSONArray(jsonObject.getString("genres"));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectGenre = jsonArray.getJSONObject(i);

                if (genres.isEmpty()) {
                    genres = jsonObjectGenre.getString("name");
                } else {
                    genres = genres + "/" + jsonObjectGenre.getString("name");
                }
            }
            movie.setGenre(genres);

        } catch (Exception e) {
            Log.e(TAG, "Exeption e = " + e);
        }
        return movie;
    }
}

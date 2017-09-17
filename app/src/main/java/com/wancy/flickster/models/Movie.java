package com.wancy.flickster.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.string.no;

public class Movie {

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s", backdropPath);
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    String posterPath;
    String backdropPath;
    String originalTitle;
    String overview;
    double voteAverage;

    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.optString("poster_path");
        this.originalTitle = jsonObject.optString("original_title");
        this.overview = jsonObject.optString("overview");
        this.backdropPath = jsonObject.optString("backdrop_path");
        this.voteAverage = jsonObject.optDouble("vote_average");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> result = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                result.add(new Movie(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

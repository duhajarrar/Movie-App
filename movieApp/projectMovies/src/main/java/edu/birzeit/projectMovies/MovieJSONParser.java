package edu.birzeit.projectMovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieJSONParser {
    public static ArrayList<Movies> parse(String json){
        try{
            ArrayList<Movies> movielist = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Movies Movie = new Movies();
                Movie.setId(jsonObject.getInt("id"));
                Movie.setTitle(jsonObject.getString("title"));
                Movie.setYear(jsonObject.getInt("year"));
                Movie.setGenres(jsonObject.getString("genres"));
                Movie.setDuration(jsonObject.getString("duration"));
                Movie.setReleaseDate(jsonObject.getString("releaseDate"));
                Movie.setStoryline(jsonObject.getString("storyline"));
                Movie.setActors(jsonObject.getString("actors"));
                Movie.setImdbRating(jsonObject.getString("imdbRating"));
                Movie.setPosterurl(jsonObject.getString("posterurl"));
                movielist.add(Movie);
                Log.d("Result",jsonObject.getString("posterurl"));
                MainActivity.dataBaseHelper.addMovie(Movie);
            }
            return movielist;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

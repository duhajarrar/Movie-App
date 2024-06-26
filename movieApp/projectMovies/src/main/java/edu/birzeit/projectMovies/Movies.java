package edu.birzeit.projectMovies;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Movies implements Comparable<Movies>{
    int id;
    String title;
    int year;
    String genres ;
    String duration;
    String releaseDate;
    String storyline;
    String actors ;
    String imdbRating;
    String posterurl;
    private Date dateOfWatched;
    private boolean watched;


    public Movies() {
    }
    public Movies(int id, String title, int year, String genres, String duration, String releaseDate, String storyline, String actors, String imdbRating, String posterurl) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.storyline = storyline;
        this.actors = actors;
        this.imdbRating = imdbRating;
        this.posterurl = posterurl;
    }
    public Movies(int id, String title, int year, String genres, String duration, String releaseDate, String storyline, String actors, String imdbRating, String posterurl, String watched, String dateOfWatched) throws ParseException {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.storyline = storyline;
        this.actors = actors;
        this.imdbRating = imdbRating;
        this.posterurl = posterurl;
        this.watched = watched.equalsIgnoreCase("true");
        this.setDateOfWatched(dateOfWatched);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }



    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating( String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterurl() {
        return posterurl;
    }

    public void setPosterurl(String posterurl) {
        this.posterurl = posterurl;
    }

    public String getDateOfWatched() {
        try {
            return dateOfWatched.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public void setDateOfWatched(String date) throws ParseException {
        if(date != null)
            dateOfWatched = new Date(date);
    }
    public boolean isWatched() {
        return watched;
    }


    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int compareTo(Movies o) {
        return Integer.valueOf(this.id).compareTo(o.id);
    }

}

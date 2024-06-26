package edu.birzeit.projectMovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.RatingBar;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MOVIE(ID INTEGER PRIMARY KEY,TITLE TEXT, YEAR INTEGER, GENRES TEXT, DURATION TEXT,RELEASEDATE TEXT,STORYLINE TEXT,ACTORS TEXT,IMDBRATING TEXT,POSTERURL TEXT, WATCHED TEXT, DATE_OF_WATCHED TEXT)");
        db.execSQL("CREATE TABLE USER(EMAIL TEXT PRIMARY KEY, FIRST_NAME TEXT, LAST_NAME TEXT, GENDER TEXT, PASSWORD TEXT, PHONE TEXT, WATCHED_MOVIE TEXT, RATED_MOVIE TEXT)");
        db.execSQL("CREATE TABLE COMMENT(EMAIL TEXT,ID INTEGER ,COMTXT TEXT)");
        db.execSQL("CREATE TABLE RATES(EMAIL TEXT,ID INTEGER ,RATE TEXT,TITLE TEXT, YEAR INTEGER, GENRES TEXT, DURATION TEXT,RELEASEDATE TEXT,STORYLINE TEXT,ACTORS TEXT,IMDBRATING TEXT,POSTERURL TEXT,PRIMARY KEY(EMAIL,ID))");
        db.execSQL("CREATE TABLE WATCH(EMAIL TEXT,ID INTEGER ,DATEWATCH TEXT,TITLE TEXT, YEAR INTEGER, GENRES TEXT, DURATION TEXT,RELEASEDATE TEXT,STORYLINE TEXT,ACTORS TEXT,IMDBRATING TEXT,POSTERURL TEXT, PRIMARY KEY(EMAIL,ID))");


    }
    public boolean addComment(String comtxt,int id) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            //onCreate(db);
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID",id-1);
            contentValues.put("EMAIL", HomeActivity.user.getFirstname());
            contentValues.put("COMTXT",comtxt);
            db.insert("COMMENT", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Comment> getAllComments(int movieId) throws ParseException {
        ArrayList<Comment> com = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        movieId=movieId-1;
        Cursor cursor = db.rawQuery("SELECT * FROM COMMENT WHERE ID="+movieId, null);
        while(cursor.moveToNext()) {
            com.add(
                    new Comment(
                            cursor.getString(0),
                            cursor.getString(2)
                    ));
        }
        return com;
    }
   /* public boolean addRate(float r,Movies movie) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            //onCreate(db);
            String s=r+"";
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID",movie.getId());
            contentValues.put("EMAIL", HomeActivity.user.getEmail());
            contentValues.put("RATE",s);
            contentValues.put("TITLE",movie.getTitle());
            contentValues.put("YEAR", movie.getYear());
            contentValues.put("GENRES",movie.getGenres());
            contentValues.put("DURATION",movie.getDuration());
            contentValues.put("RELEASEDATE",movie.getReleaseDate());
            contentValues.put("STORYLINE",movie.getStoryline());
            contentValues.put("ACTORS",movie.getActors());
            contentValues.put("IMDBRATING",movie.getImdbRating());
            contentValues.put("POSTERURL",movie.getPosterurl());
            db.insert("RATES", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addWatch(Movies movie) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            //onCreate(db);
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID",movie.getId());
            contentValues.put("EMAIL", HomeActivity.user.getEmail());
            contentValues.put("DATEWATCH",new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()));
            contentValues.put("TITLE",movie.getTitle());
            contentValues.put("YEAR", movie.getYear());
            contentValues.put("GENRES",movie.getGenres());
            contentValues.put("DURATION",movie.getDuration());
            contentValues.put("RELEASEDATE",movie.getReleaseDate());
            contentValues.put("STORYLINE",movie.getStoryline());
            contentValues.put("ACTORS",movie.getActors());
            contentValues.put("IMDBRATING",movie.getImdbRating());
            contentValues.put("POSTERURL",movie.getPosterurl());
            db.insert("WATCH", null, contentValues);
            Log.d("res","add watch");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addMovie(Movies movie) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            //onCreate(db);
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", movie.getId());
            contentValues.put("TITLE",movie.getTitle());
            contentValues.put("YEAR", movie.getYear());
            contentValues.put("GENRES",movie.getGenres());
            contentValues.put("DURATION",movie.getDuration());
            contentValues.put("RELEASEDATE",movie.getReleaseDate());
            contentValues.put("STORYLINE",movie.getStoryline());
            contentValues.put("ACTORS",movie.getActors());
            contentValues.put("IMDBRATING",movie.getImdbRating());
            contentValues.put("POSTERURL",movie.getPosterurl());
            contentValues.put("WATCHED", movie.isWatched() ? "true" : "false");
            contentValues.put("DATE_OF_WATCHED", movie.getDateOfWatched());
            db.insert("MOVIE", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMovie(int movieId) {
        SQLiteDatabase db = getWritableDatabase();
            return db.delete("MOVIE", "ID = " + movieId, null) > 0;
    }

    public boolean updateMovie(Movies movie) {
        try {
            return deleteMovie(movie.getId()) && addMovie(movie);
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Movies> getAllMovies() throws ParseException {
        ArrayList<Movies> movie = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        //onCreate(db);
        Cursor cursor = db.rawQuery("SELECT * FROM MOVIE", null);
        while(cursor.moveToNext()) {
            movie.add(
                    new Movies(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getString(9),
                            cursor.getString(10),
                            cursor.getString(11)
                            ));
        }
        return movie;
    }
// ID INTEGER ,TITLE TEXT, YEAR INTEGER, GENRES TEXT, DURATION TEXT,RELEASEDATE TEXT,STORYLINE TEXT,ACTORS TEXT,IMDBRATING TEXT,POSTERURL TEXT, PRIMARY KEY(EMAIL,ID))");
    public ArrayList<Movies> getAllWatchMovies() throws ParseException {
        ArrayList<Movies> movie = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM WATCH WHERE EMAIL='"+HomeActivity.user.getEmail()+"'", null);
        while(cursor.moveToNext()) {

            movie.add(
                    new Movies(cursor.getInt(1),
                            cursor.getString(3),
                            cursor.getInt(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getString(9),
                            cursor.getString(10),
                            cursor.getString(11)
                    ));
            Log.d("get",cursor.getString(3));
        }
        return movie;
    }
    public ArrayList<Movies> getAllRates() throws ParseException {
            ArrayList<Movies> movie = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM RATES WHERE EMAIL='"+HomeActivity.user.getEmail()+"'", null);
            while(cursor.moveToNext()) {
                movie.add(
                        new Movies(cursor.getInt(1),
                                cursor.getString(3),
                                cursor.getInt(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getString(7),
                                cursor.getString(8),
                                cursor.getString(9),
                                cursor.getString(10),
                                cursor.getString(11),
                                cursor.getString(12),
                                cursor.getString(13)
                        ));
            }
            return movie;
        }

    public boolean deleteUser(String email, String password) {
        if(getUserByLoginInfo(email, password) != null) {
            SQLiteDatabase db = getWritableDatabase();
            return db.delete("USER", "EMAIL = '" + email + "'", null) > 0;
        }
        return false;
    }

    public boolean updateUser(User user) {
        deleteUser(user.getEmail(), user.getPassword());
        return addUser(user);
    }

    public boolean addUser(User user){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("EMAIL", user.getEmail());
            contentValues.put("FIRST_NAME", user.getFirstname());
            contentValues.put("LAST_NAME", user.getLastname());
            contentValues.put("GENDER", user.getGender());
            contentValues.put("PASSWORD", user.getPassword());
            contentValues.put("PHONE", user.getPhone());
            contentValues.put("WATCHED_MOVIE", DataBaseHelper.convertArrayToString(user.getWatchedMovies()));
            contentValues.put("RATED_MOVIE", DataBaseHelper.convertArrayToString(user.getRates()));
            db.insert("USER", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByLoginInfo(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        User user = null;
        Cursor cursor = db.rawQuery("SELECT * FROM USER WHERE EMAIL = '" + email.toLowerCase() + "'", null);
        if(cursor.moveToNext() && password.equals(cursor.getString(4))){
            String[] watchedMovie;
            String[] rateMovie;
            try {
                watchedMovie = cursor.getString(6).split(",");
            } catch (Exception e) {
                e.printStackTrace();
                watchedMovie = null;
            }
            try {
                rateMovie = cursor.getString(7).split(",");
            } catch (Exception e) {
                e.printStackTrace();
                rateMovie = null;
            }
            user = new User(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));
            if(watchedMovie != null)
                for(String i : watchedMovie)
                    try {
                        user.watchedMovie(Integer.parseInt(i));
                    } catch (Exception ignored) {

                    }
            if(rateMovie != null)
                for(String i : rateMovie)
                    try {
                        user.addToRate(Integer.parseInt(i));
                    } catch (Exception ignored) {

                    }
        }
        return user;
    }

    public static String convertArrayToString(ArrayList<Movies> arrayList){
        if(arrayList == null)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        for(Movies i : arrayList){
            stringBuilder.append(i.getId());
            stringBuilder.append(",");
        }
        if(stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) == ',') stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

}

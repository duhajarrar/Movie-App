package edu.birzeit.projectMovies;

import android.text.TextUtils;
import android.util.Patterns;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import edu.birzeit.projectMovies.DataBaseHelper;
public class User {
    private final static String passRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()*_!/])(?=\\S+$).{4,}$";
    private final static String phoneRegex = "^(009705)(([^0-9]*[0-9][^0-9]*){8,})$";
    private final static String nameRegex = "^([^A-Za-z]*[A-Za-z][^A-Za-z]*){3,}$";
    public static DataBaseHelper dataBaseHelper;

    private String email;
    private String firstname;
    private String lastname;
    private String gender;
    private String password;
    private String phone;
    private ArrayList<Movies> watchedMovies = new ArrayList<>();
    private ArrayList<Movies> rates = new ArrayList<>();
    private ArrayList<Comment> commentslist=new ArrayList<>();

    public User() {
    }

    public User(String email, String firstname, String lastname, String gender, String password, String phone) {
        this.email = email.toLowerCase();
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.password = password;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = User.encrypt(password);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone.replaceAll("[^0-9]*", "");
    }

    public ArrayList<Movies> getWatchedMovies() {
        return watchedMovies;
    }

    public boolean watchedMovie(int movieId) {
        try {
            Movies movie = MainActivity.movieslist.get(movieId-1);
            movie.setWatched(true);
            movie.setDateOfWatched((new Date()).toString());
            this.watchedMovies.add(movie);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delWatch(int movieId) {
        try {
            watchedMovies.remove(MainActivity.movieslist.get(movieId-1));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public ArrayList<Movies> getRates() {
        return rates;
    }
    public boolean isExit(ArrayList<Movies> a , int movieId){
        for(int i=0;i<a.size();i++){
            if(a.get(i).getId()==movieId)
                return true;}
        return false;
    }
    public boolean addToRates(int movieId,float r) {
        try {
            MainActivity.movieslist.get(movieId-1).setImdbRating(r+"");
            rates.add(MainActivity.movieslist.get(movieId-1));
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean addToRate(int movieId) {
        try {
            rates.add(MainActivity.movieslist.get(movieId-1));
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateRate(Movies movie,float r) {
        try {
            return delRates(movie.getId()) && addToRates(movie.getId(),r);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delRates(int movieId) {
        try {
            rates.remove(MainActivity.movieslist.get(movieId-1));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static String encrypt(String input) {
        try {
            return (new BigInteger(1, MessageDigest.getInstance("SHA-512").digest(input.getBytes()))).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidName(CharSequence target) {
        return Pattern.matches(nameRegex, target);
    }

    public static boolean isValidPassword(CharSequence target) {
        return Pattern.matches(passRegex, target);
    }

    public static boolean isConfirmedPassword(CharSequence target, String s) {
        return target.toString().equals(s);
    }

    public static boolean isValidPhone(CharSequence target) {
        return Pattern.matches(phoneRegex, target);
    }
}

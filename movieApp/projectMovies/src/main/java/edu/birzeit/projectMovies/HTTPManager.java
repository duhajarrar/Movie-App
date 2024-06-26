package edu.birzeit.projectMovies;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPManager {
    public static String getData(String Url){
        BufferedReader bufferedReader;
        try {
            URL url = new URL(Url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while(line != null){
                stringBuilder.append(line).append('\n');
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        }catch (Exception e){
            Log.d("HttpURLConnection", e.toString());
            return null;
        }
    }
}

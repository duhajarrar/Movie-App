package edu.birzeit.projectMovies;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Movies> movieslist,movieslistRate,movieslistWatch;
    public static DataBaseHelper dataBaseHelper;
    public static String version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.dataBaseHelper = new DataBaseHelper(MainActivity.this, "Users", null, 1);
        MainActivity.movieslist = new ArrayList<>();
        final SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(this);
        MainActivity.version = sharedPrefManager.readString("version", " ");
                Button connect = (Button) findViewById(R.id.connect);
                connect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                           // SQLiteDatabase db = getDa;

                            ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask();
                            connectionAsyncTask.execute("https://firebasestorage.googleapis.com/v0/b/advance-proj1.appspot.com/o/movies-in-theaters.json?alt=media&token=e3121ae7-be1b-4480-99d8-c1d0ad2eaa2f");
                            sharedPrefManager.writeString("version", MainActivity.version);
                            sharedPrefManager.writeString("getStarted", "false");
                            movieslist=dataBaseHelper.getAllMovies();
                           // movieslistRate=dataBaseHelper.getAllRates();
                            //movieslistWatch=dataBaseHelper.getAllWatchMovies();

                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast toast = Toast.makeText(MainActivity.this, "Failed to connect to server", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
            }

    }


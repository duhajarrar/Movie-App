package edu.birzeit.projectMovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    public static User user;
    public static DataBaseHelper dataBaseHelper;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataBaseHelper = new DataBaseHelper(this, "Users", null, 1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        //navigationView.
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_movie_menu, R.id.nav_watched_movie, R.id.nav_rated_movies, R.id.nav_profile, R.id.nav_call_us, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View view = LayoutInflater.from(HomeActivity.this).inflate(R.layout.nav_header_home, navigationView, true);
        ((TextView) view.findViewById(R.id.username)).setText(HomeActivity.user.getFirstname() );
        ((TextView) view.findViewById(R.id.email_side)).setText(HomeActivity.user.getEmail());
        ImageView img =view.findViewById(R.id.imageView1);
        if(HomeActivity.user.getGender().equals("Male"))
            img.setImageResource(R.drawable.man128);
        else if(HomeActivity.user.getGender().equals("Female"))
            img.setImageResource(R.drawable.woman128);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logout() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this, "Users", null, 1);
        dataBaseHelper.updateUser(HomeActivity.user);
        HomeActivity.user = null;
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void reload() {
        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}

package edu.birzeit.projectMovies;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoginActivity extends AppCompatActivity {

    private final LoginFragment loginFragment = new LoginFragment();
    private final SignupFragment signupFragment = new SignupFragment();
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, loginFragment);
        fragmentTransaction.commit();
    }

    protected void switchFragment(){
        if(signupFragment.isAdded()){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, loginFragment);
            fragmentTransaction.commit();
        }else{
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, signupFragment);
            fragmentTransaction.commit();
        }
    }

    protected void login(User user){
        HomeActivity.user = user;
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

}

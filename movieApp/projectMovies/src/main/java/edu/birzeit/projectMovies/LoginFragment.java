package edu.birzeit.projectMovies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    int IsDark;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button gotoSignup = (Button) getActivity().findViewById(R.id.gotosignup);
        gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity)getActivity()).switchFragment();
            }
        });
        final SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        String savedEmail = sharedPrefManager.readString("email", "");
        String savedPassword = sharedPrefManager.readString("password", "");
        final EditText email = (EditText) getActivity().findViewById(R.id.email_login);
        email.setText(savedEmail);
        final EditText password = (EditText) getActivity().findViewById(R.id.password_login);
        password.setText(savedPassword);
        final CheckBox rememberMe = (CheckBox) getActivity().findViewById(R.id.remember);
        if(sharedPrefManager.readString("checked", "").equals("true"))
            rememberMe.setChecked(true);
        Button login = (Button) getActivity().findViewById(R.id.login);
        final Switch aSwitch = (Switch) getActivity().findViewById(R.id.mode);
        IsDark = sharedPrefManager.readInt("Mode", 0);

        if (IsDark==1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            aSwitch.setChecked(true);
        } else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            aSwitch.setChecked(false);}


        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( IsDark==1){

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sharedPrefManager.writeInt("Mode", 0);


                }

                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sharedPrefManager.writeInt("Mode", 1);

                }



            }});
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rememberMe.isChecked()){
                    sharedPrefManager.writeString("email", email.getText().toString());
                    sharedPrefManager.writeString("password", password.getText().toString());
                    sharedPrefManager.writeString("checked", "true");
                }
                if(!User.isValidEmail(email.getText()) || !User.isValidPassword(password.getText())){
                    Toast toast = Toast.makeText(getContext(), "Invalid email or password format", Toast.LENGTH_SHORT);
                    toast.show();
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "Users", null, 1);
                User user = dataBaseHelper.getUserByLoginInfo(email.getText().toString(), User.encrypt(password.getText().toString()));
                if(user == null) {
                    Toast toast = Toast.makeText(getContext(), "No user with such email and password", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    ((LoginActivity) getActivity()).login(user);
                }
            }
        });
    }
}

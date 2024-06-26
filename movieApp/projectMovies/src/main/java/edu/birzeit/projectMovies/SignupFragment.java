package edu.birzeit.projectMovies;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button gotoLogin = (Button) getActivity().findViewById(R.id.gotologin);
        final boolean[] checker = new boolean[6];
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity)getActivity()).switchFragment();
            }
        });
        final EditText email = (EditText) getActivity().findViewById(R.id.email_signup);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(User.isValidEmail(s)){
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.GREEN);
                    ViewCompat.setBackgroundTintList(email, colorStateList);
                    checker[0] = true;
                }else{
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
                    ViewCompat.setBackgroundTintList(email, colorStateList);
                    checker[0] = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final EditText firstname = (EditText) getActivity().findViewById(R.id.firstname);
        firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(User.isValidName(s)){
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.GREEN);
                    ViewCompat.setBackgroundTintList(firstname, colorStateList);
                    checker[1] = true;
                }else{
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
                    ViewCompat.setBackgroundTintList(firstname, colorStateList);
                    checker[1] = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final EditText lastname = (EditText) getActivity().findViewById(R.id.firstname);
        lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(User.isValidName(s)){
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.GREEN);
                    ViewCompat.setBackgroundTintList(lastname, colorStateList);
                    checker[2] = true;
                }else{
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
                    ViewCompat.setBackgroundTintList(lastname, colorStateList);
                    checker[2] = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final EditText password = (EditText) getActivity().findViewById(R.id.password_signup);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(User.isValidPassword(s)){
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.GREEN);
                    ViewCompat.setBackgroundTintList(password, colorStateList);
                    checker[3] = true;
                }else{
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
                    ViewCompat.setBackgroundTintList(password, colorStateList);
                    checker[3] = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final EditText passconfirm = (EditText) getActivity().findViewById(R.id.passconfirm);
        passconfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(User.isConfirmedPassword(s, password.getText().toString())){
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.GREEN);
                    ViewCompat.setBackgroundTintList(passconfirm, colorStateList);
                    checker[4] = true;
                }else{
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
                    ViewCompat.setBackgroundTintList(passconfirm, colorStateList);
                    checker[4] = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final EditText phone = (EditText) getActivity().findViewById(R.id.phone_signup);
        phone.setText("009705");
        Selection.setSelection(phone.getText(), phone.getText().length());
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(User.isValidPhone(s)){
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.GREEN);
                    ViewCompat.setBackgroundTintList(phone, colorStateList);
                    checker[5] = true;
                }else{
                    ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
                    ViewCompat.setBackgroundTintList(phone, colorStateList);
                    checker[5] = false;
                }
                if(!s.toString().startsWith("009705")){
                    phone.setText("009705");
                    Selection.setSelection(phone.getText(), phone.getText().length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final Spinner gender = (Spinner) getActivity().findViewById(R.id.gender);
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, genders);
        gender.setAdapter(arrayAdapter);
        Button signup = (Button) getActivity().findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;
                for(int i = 0; i < checker.length; i++){
                    flag = (flag && checker[i]);
                }
                if(flag){
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity(), "Users", null, 1);
                    User user = new User();
                    user.setEmail(email.getText().toString());
                    user.setFirstname(firstname.getText().toString());
                    user.setLastname(lastname.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setGender(gender.getSelectedItem().toString());
                    user.setPhone(phone.getText().toString());
                    if(dataBaseHelper.addUser(user)) {
                        Toast toast = Toast.makeText(getContext(), "User Signed up successfully", Toast.LENGTH_SHORT);
                        toast.show();
                        ((LoginActivity) getActivity()).switchFragment();
                    }else{
                        Toast toast = Toast.makeText(getContext(), "Failed to sign up!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }
}

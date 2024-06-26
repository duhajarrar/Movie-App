package edu.birzeit.projectMovies.ui.profile;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import edu.birzeit.projectMovies.DataBaseHelper;
import edu.birzeit.projectMovies.HomeActivity;
import edu.birzeit.projectMovies.R;
import edu.birzeit.projectMovies.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view =inflater.inflate(R.layout.fragment_profile, container, false);

        ImageView img =view.findViewById(R.id.imageView1);
        if(HomeActivity.user.getGender().equals("Male"))
            img.setImageResource(R.drawable.man128);
        else if(HomeActivity.user.getGender().equals("Female"))
            img.setImageResource(R.drawable.woman128);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText firstname = (EditText) getActivity().findViewById(R.id.firstname_profile);
        firstname.setText(HomeActivity.user.getFirstname());
        final EditText lastname = (EditText) getActivity().findViewById(R.id.firstname_profile);
        lastname.setText(HomeActivity.user.getLastname());
        final EditText email = (EditText) getActivity().findViewById(R.id.email_profile);
        email.setText(HomeActivity.user.getEmail());
        email.setEnabled(false);
        final EditText password = (EditText) getActivity().findViewById(R.id.password_profile);
        final EditText gender = (EditText) getActivity().findViewById(R.id.gender_profile);
        gender.setText(HomeActivity.user.getGender());
        gender.setEnabled(false);

        final EditText phone = (EditText) getActivity().findViewById(R.id.phone_profile);
        phone.setText(HomeActivity.user.getPhone());
        //final Switch dist = (Switch) getActivity().findViewById(R.id.switch1);
        Button submit = (Button) getActivity().findViewById(R.id.submit_profile);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User.isValidName(firstname.getText())
                && User.isValidName(lastname.getText())
                && (password.getText().toString().isEmpty() || User.isValidPassword(password.getText()))
                && User.isValidPhone(phone.getText())) {
                    if(!password.getText().toString().isEmpty())
                        HomeActivity.user.setPassword(password.getText().toString());
                    HomeActivity.user.setFirstname(firstname.getText().toString());
                    HomeActivity.user.setLastname(lastname.getText().toString());
                    HomeActivity.user.setPhone(phone.getText().toString());
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity(),  "Users", null, 1);
                    dataBaseHelper.updateUser(HomeActivity.user);
                    ((HomeActivity) getActivity()).reload();
                }else {
                    if(!User.isValidName(firstname.getText())) {
                        ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
                        ViewCompat.setBackgroundTintList(firstname, colorStateList);
                    }
                    if(!User.isValidName(lastname.getText())) {
                        ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
                        ViewCompat.setBackgroundTintList(lastname, colorStateList);
                    }
                    if(!password.getText().toString().isEmpty() && !User.isValidPassword(password.getText())) {
                        ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
                        ViewCompat.setBackgroundTintList(password, colorStateList);
                    }
                    if(!User.isValidPhone(phone.getText())) {
                        ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
                        ViewCompat.setBackgroundTintList(phone, colorStateList);
                    }
                }

            }
        });
    }
}

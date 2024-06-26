package edu.birzeit.projectMovies.ui.moviemenu;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import edu.birzeit.projectMovies.HomeActivity;
import edu.birzeit.projectMovies.MainActivity;
import edu.birzeit.projectMovies.Movies;
import edu.birzeit.projectMovies.R;

public class MovieMenuFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MovieMenuFragment() {
    }
    public static MovieMenuFragment newInstance(String param1, String param2) {
        MovieMenuFragment fragment = new MovieMenuFragment();
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
    ArrayList<Movies>  minClear, maxClear, dateClear, ratingClear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_menu, container, false);
        recyclerView = view.findViewById(R.id.movie_menu_recycle_view);
        recyclerView.removeAllViews();
        try {
            movies = (ArrayList<Movies>) MainActivity.dataBaseHelper.getAllMovies().clone();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        MenuRecycleAdapter menuRecycleAdapter = new MenuRecycleAdapter(getActivity(), movies, HomeActivity.user);
        recyclerView.setAdapter(menuRecycleAdapter);
        return view;
    }

    ArrayList<Movies> movies=(ArrayList<Movies>) MainActivity.movieslist.clone();
    RecyclerView recyclerView;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText minDuration = (EditText) getActivity().findViewById(R.id.duration_min);
        final EditText maxDuration = (EditText) getActivity().findViewById(R.id.duration_max);
        final RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.movie_menu_recycle_view);
        final Spinner ratingFilter = (Spinner) getActivity().findViewById(R.id.rating_filter);
        final EditText date = (EditText)getActivity().findViewById(R.id.date);
        final ArrayList<String> ratings = new ArrayList<>();
        ArrayAdapter<String> ratesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ratings);
        ratesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingFilter.setAdapter(ratesAdapter);
        ratings.add("Rating");
        ratings.add("More than 5");
        ratings.add("Less than 5");
        ratingClear = new ArrayList<>();
        ratingFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("click = ","true");
                for (Movies i : ratingClear)
                    movies.add(i);
                ratingClear.clear();
                if (parent.getItemAtPosition(position).equals("More than 5")) {
                    for (Movies i : movies)
                        if (i.getImdbRating() != "")
                            continue;
                        else if (!(Float.parseFloat(i.getImdbRating()) >= 5.0)) {
                            ratingClear.add(i);
                            Log.d("rateeeMore = ", i.getImdbRating());
                        }

                }  if (parent.getItemAtPosition(position).equals("Less than 5")) {
                    for (Movies i : movies)
                        if (i.getImdbRating() != "") {
                            ratingClear.add(i);
                            continue;
                        } else if (!(Float.parseFloat(i.getImdbRating()) < 5.0)) {
                            ratingClear.add(i);
                            Log.d("rateeeLess = ", i.getImdbRating());
                        }
                }
                for (Movies i : ratingClear)
                    movies.remove(i);
                Collections.sort(movies);
                MenuRecycleAdapter menuRecycleAdapter = new MenuRecycleAdapter(getContext(), movies, HomeActivity.user);
                recyclerView.setAdapter(menuRecycleAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dateClear = new ArrayList<>();
        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for(Movies i : dateClear)
                    movies.add(i);
                dateClear.clear();
                if(!TextUtils.isEmpty(s)){
                    String rdate = s.toString();
                    for(Movies i : movies){
                        if(i.getReleaseDate()=="")
                            continue;
                        if(!rdate.equals(i.getReleaseDate()))
                            dateClear.add(i);}
                    for(Movies i : dateClear)
                        movies.remove(i);
                }
                Collections.sort(movies);
                MenuRecycleAdapter menuRecycleAdapter = new MenuRecycleAdapter(getContext(), movies, HomeActivity.user);
                recyclerView.setAdapter(menuRecycleAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
                minClear = new ArrayList<>();
                minDuration.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        for(Movies i : minClear)
                            movies.add(i);
                        minClear.clear();
                        if(!TextUtils.isEmpty(s)){
                            if (!s.toString().equals("")){
                            int low = Integer.parseInt(s.toString());
                            for(Movies i : movies){
                                if(!i.getDuration().equals("")){
                                String s1=i.getDuration();
                                String s2=s1.substring(2,s1.length());
                                s2=s2.replace(s2.charAt(s2.length()-1), ' ');
                                s2 = s2.trim();
                                Log.d("Duration",s.toString()+" "+s2);
                             if((Integer.parseInt(s2)<low))
                                    minClear.add(i);}}}
                            for(Movies i : minClear)
                                movies.remove(i);
                        }
                        Collections.sort(movies);
                        MenuRecycleAdapter menuRecycleAdapter = new MenuRecycleAdapter(getContext(), movies, HomeActivity.user);
                        recyclerView.setAdapter(menuRecycleAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                maxClear = new ArrayList<>();
                maxDuration.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        for(Movies i : maxClear)
                            movies.add(i);
                        maxClear.clear();
                        if(!TextUtils.isEmpty(s)){
                            if (!s.toString().equals("")){
                                int h = Integer.parseInt(s.toString());
                                for(Movies i : movies){
                                    if(!i.getDuration().equals("")){
                                        String s1=i.getDuration();
                                        String s2=s1.substring(2,s1.length());
                                        s2=s2.replace(s2.charAt(s2.length()-1), ' ');
                                        s2 = s2.trim();
                                        //String s3 = s2.substring(0,s2.length()-1);
                                        Log.d("Duration",s.toString()+" "+s2);
                                        if((Integer.parseInt(s2)>h))
                                            maxClear.add(i);}}}
                            for(Movies i : maxClear)
                                movies.remove(i);
                        }
                        Collections.sort(movies);
                        MenuRecycleAdapter menuRecycleAdapter = new MenuRecycleAdapter(getContext(), movies, HomeActivity.user);
                        recyclerView.setAdapter(menuRecycleAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                MenuRecycleAdapter menuRecycleAdapter = new MenuRecycleAdapter(getContext(), movies, HomeActivity.user);
                recyclerView.setAdapter(menuRecycleAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }




}


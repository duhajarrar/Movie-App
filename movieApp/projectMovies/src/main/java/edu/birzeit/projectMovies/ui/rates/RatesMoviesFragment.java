package edu.birzeit.projectMovies.ui.rates;

import android.os.Bundle;
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

import edu.birzeit.projectMovies.DataBaseHelper;
import edu.birzeit.projectMovies.HomeActivity;
import edu.birzeit.projectMovies.MainActivity;
import edu.birzeit.projectMovies.Movies;
import edu.birzeit.projectMovies.R;
import edu.birzeit.projectMovies.ui.rates.RatesRecycleAdapter;
import edu.birzeit.projectMovies.ui.watches.WatchesRecycleAdapter;

public class RatesMoviesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RatesMoviesFragment() {
    }
    public static RatesMoviesFragment newInstance(String param1, String param2) {
        RatesMoviesFragment fragment = new RatesMoviesFragment();
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
    public static DataBaseHelper dataBaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rates_movies, container, false);
        recyclerView = view.findViewById(R.id.rate_recycle_view);
        recyclerView.removeAllViews();

        ArrayList<Movies> movie = (ArrayList<Movies>) HomeActivity.user.getRates().clone();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        if(movie!=null){
            WatchesRecycleAdapter menuRecycleAdapter = new WatchesRecycleAdapter(getContext(), movie, HomeActivity.user);
            recyclerView.setAdapter(menuRecycleAdapter);}

        RatesRecycleAdapter menuRecycleAdapter = new RatesRecycleAdapter(getContext(), movie, HomeActivity.user);
        menuRecycleAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(menuRecycleAdapter);
        return view;
    }

    ArrayList<Movies> movie = (ArrayList<Movies>) HomeActivity.user.getRates().clone();
    static RecyclerView recyclerView;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}

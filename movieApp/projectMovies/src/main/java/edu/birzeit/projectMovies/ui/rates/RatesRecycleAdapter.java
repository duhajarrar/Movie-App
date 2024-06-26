package edu.birzeit.projectMovies.ui.rates;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import edu.birzeit.projectMovies.Comment;
import edu.birzeit.projectMovies.DownloadImageTask;
import edu.birzeit.projectMovies.HomeActivity;
import edu.birzeit.projectMovies.MainActivity;
import edu.birzeit.projectMovies.Movies;
import edu.birzeit.projectMovies.PopUpActivity;
import edu.birzeit.projectMovies.R;
import edu.birzeit.projectMovies.User;
import edu.birzeit.projectMovies.ui.moviemenu.MenuRecycleAdapter;

public class RatesRecycleAdapter extends RecyclerView.Adapter<RatesRecycleAdapter.MenuHolder> {

    ArrayList<Movies> movies;
    Context context;
    User user;

    public RatesRecycleAdapter(Context context, ArrayList<Movies> movies, User user) {
        this.movies = movies;
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_main, parent, false);
        return new MenuHolder(view);
    }
    TextView title, duration, story, year,actor,gener,reles;
    Button addToWatch ,Comment;
    RatingBar addtoratebtn,rate;
    EditText txtcmt;
    ProgressBar progressBar;
    ImageView pimg;
    ImageButton closePop,closePop2;
    Button showCom;

    @SuppressLint({"SetTextI18n", "WrongViewCast"})
    @Override
    public void onBindViewHolder(@NonNull final RatesRecycleAdapter.MenuHolder holder, int position) {
        final Movies movie = movies.get(position);
        new DownloadImageTask(holder.poster, holder.progressBar).execute(movies.get(position).getPosterurl());
        Log.d("URL",movies.get(position).getPosterurl());
        holder.title.setText(movie.getTitle());
        holder.duration.setText(movie.getDuration());
        if(movie.getImdbRating().equals("")){
            holder.rate.setRating(0);
        }else{
            holder.rate.setRating(Float.parseFloat(movie.getImdbRating()));}
        holder.genres.setText(movie.getGenres());
        holder.releasedate.setText(movie.getReleaseDate());

        LayoutInflater inflater = LayoutInflater.from(context);
        LayoutInflater inflater1 = LayoutInflater.from(context);

        final View popupView = inflater.inflate(R.layout.activity_pop_up,null);
        final View popupComments = inflater1.inflate(R.layout.popup_comments,null);
        final TextView txtCom=(TextView)popupComments.findViewById(R.id.commentsTEXT);
        // final TextView txtCom = new TextView(context);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        final PopupWindow popupWindow1 = new PopupWindow(popupComments, width, height, true);

        closePop=(ImageButton)popupView.findViewById(R.id.closePop);
        closePop2=(ImageButton)popupComments.findViewById(R.id.closePop2);
        title=(TextView) popupView.findViewById(R.id.ptitle);
        title.setText(movie.getTitle());
        duration=(TextView) popupView.findViewById(R.id.pduration);
        duration.setText(movie.getDuration());
        story=(TextView) popupView.findViewById(R.id.pstory);
        story.setText(movie.getStoryline());
        year=(TextView) popupView.findViewById(R.id.pyear);
        year.setText(movie.getYear()+"");
        actor=(TextView) popupView.findViewById(R.id.pactor);
        actor.setText(movie.getActors());
        gener=(TextView) popupView.findViewById(R.id.pgener);
        gener.setText(movie.getGenres());
        reles=(TextView) popupView.findViewById(R.id.preles);
        reles.setText(movie.getReleaseDate());
        txtcmt=(EditText)popupView.findViewById(R.id.txtcmnt);
        pimg=(ImageView)popupView.findViewById(R.id.pimg);
        rate=(RatingBar)popupView.findViewById(R.id.ratingBar2);
        showCom=(Button)popupView.findViewById(R.id.show_comments);
        progressBar=(ProgressBar)popupView.findViewById(R.id.progressBar2);
        new DownloadImageTask(pimg,progressBar).execute(movie.getPosterurl());
        if((movie.getImdbRating()).equals("")){
            rate.setRating(0);
        }else{
            rate.setRating(Float.parseFloat(movie.getImdbRating()));}
        addToWatch = (Button) popupView.findViewById(R.id.addwatchbtn);
        Comment = (Button) popupView.findViewById(R.id.comment);
        addtoratebtn=(RatingBar)popupView.findViewById(R.id.addrate);
        addToWatch = (Button) popupView.findViewById(R.id.addwatchbtn);
        final ScrollView commentView1 = popupComments.findViewById(R.id.scroll1);

        closePop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        closePop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        if(movie.isWatched()){
            holder.dateWatch.setText(movie.getDateOfWatched());
        }else{
            holder.dateWatch.setText("");
        }
        addToWatch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //  MainActivity.dataBaseHelper.addWatch(movie);
                if(!HomeActivity.user.isExit(HomeActivity.user.getWatchedMovies(),movie.getId())){
                    Toast toast = Toast.makeText(context, "add successfully", Toast.LENGTH_SHORT);
                    toast.show();
                    HomeActivity.user.watchedMovie(movie.getId());
                    HomeActivity.dataBaseHelper.updateMovie(movie);
                    HomeActivity.dataBaseHelper.updateUser(HomeActivity.user);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    holder.dateWatch.setText(formatter.format(new Date()));


                    addToWatch.setText("Remove from Watch list");
                }else {
                    HomeActivity.user.delWatch(movie.getId());
                    addToWatch.setText("Add to Watche list");
                    Toast toast = Toast.makeText(context, "Delete movie from watched list successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        final HashMap<String,String> m=new HashMap<>();
        txtCom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                m.put("textcom",s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dataBaseHelper.addComment(m.get("textcom")+"",movie.getId());
                Log.d("text com",m.get("textcom")+"===add comment");
                Toast toast = Toast.makeText(context, "add comment successfully", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final ScrollView commentView = popupComments.findViewById(R.id.commentView);
        //  commentView.removeAllViewsInLayout();
        showCom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(context, "Before Show Comments", Toast.LENGTH_SHORT);
                toast.show();
                ArrayList<Comment> c= null;
                try {
                    c = HomeActivity.dataBaseHelper.getAllComments(movie.getId());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String s="";
                if(c!=null){
                    for(int i=0;i<c.size();i++){
                        s+="USER: "+c.get(i).getEmail()+"\n"+"Commented << "+c.get(i).getTextCom()+" >>"+"\n"+"---------------------------------------"+"\n";}
                    Log.d("Comm",s);
                    txtCom.setText(s);
                    //commentView.addView(txtCom);
                }
                else
                    txtCom.setText("This movie does not have comments yet .. ");
                Toast toast1 = Toast.makeText(context, "Show Comments", Toast.LENGTH_SHORT);
                toast1.show();
                popupWindow1.showAtLocation( popupComments, Gravity.CENTER, 0, 0);


            }
        });
        addtoratebtn.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //  MainActivity.dataBaseHelper.addRate(rating,movie);
                Log.d("indexxxxxxx = ",movie.getId()+"");
                if(HomeActivity.user.isExit(HomeActivity.user.getRates(),movie.getId())){
                    user.updateRate(movie,rating);
                    Toast toast = Toast.makeText(context, "Update Rate successfully", Toast.LENGTH_SHORT);
                    toast.show();
                    HomeActivity.dataBaseHelper.updateUser(HomeActivity.user);
                    for(int i=0;i<HomeActivity.user.getRates().size();i++)
                        Log.d("index "+i,HomeActivity.user.getRates().get(i).getTitle()+"");

                }else{
                    for(int i=0;i<HomeActivity.user.getRates().size();i++)
                        Log.d("index "+i,HomeActivity.user.getRates().get(i).getTitle()+"");
                    user.addToRates(movie.getId(),rating);
                    Toast toast = Toast.makeText(context, "Rated successfully", Toast.LENGTH_SHORT);
                    toast.show();
                    HomeActivity.dataBaseHelper.updateUser(HomeActivity.user);
                    for(int i=0;i<HomeActivity.user.getRates().size();i++)
                        Log.d("index After "+i,HomeActivity.user.getRates().get(i).getTitle()+"");
                }
                MenuRecycleAdapter menuRecycleAdapter = new MenuRecycleAdapter(context, movies, HomeActivity.user);
                RatesMoviesFragment.recyclerView.setAdapter(menuRecycleAdapter);
                RatesMoviesFragment.recyclerView.setLayoutManager(new LinearLayoutManager(context));

            }
        });

        holder.moreDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAtLocation( popupView, Gravity.CENTER, 0, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();

    }

    public class MenuHolder extends RecyclerView.ViewHolder {


        Button moreDet;
        RatingBar rate;
        ImageView poster;
        TextView title,duration,releasedate,genres,dateWatch;
        ProgressBar progressBar;

        public MenuHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            moreDet = itemView.findViewById(R.id.moreDet);
            duration=itemView.findViewById(R.id.duration);
            genres=itemView.findViewById(R.id.genres);
            releasedate=itemView.findViewById(R.id.releasedate);
            poster=itemView.findViewById(R.id.poster);
            rate=itemView.findViewById(R.id.ratingBar);
            progressBar = itemView.findViewById(R.id.progressBar);
            dateWatch=itemView.findViewById(R.id.date_of_watched);


        }
    }
}

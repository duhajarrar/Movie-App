package edu.birzeit.projectMovies;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class PopUpActivity extends Activity {

    TextView title, duration, story, year,actor,gener,reles;
    PopupWindow popUp;
    Button addToWatch ,Comment;
    RatingBar addtoratebtn,rate;
    EditText txtcmt;
    static Movies movie;
    ProgressBar progressBar;
    ImageView pimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popUp = new PopupWindow(this);
        setContentView(R.layout.activity_pop_up);
        title=(TextView) findViewById(R.id.ptitle);
        title.setText(movie.getTitle());
        duration=(TextView) findViewById(R.id.pduration);
        duration.setText(movie.getDuration());
        story=(TextView) findViewById(R.id.pstory);
        story.setText(movie.getStoryline());
        year=(TextView) findViewById(R.id.pyear);
        year.setText(movie.getYear()+"");
        actor=(TextView) findViewById(R.id.pactor);
        actor.setText(movie.getActors());
        gener=(TextView) findViewById(R.id.pgener);
        gener.setText(movie.getGenres());
        reles=(TextView) findViewById(R.id.preles);
        reles.setText(movie.getReleaseDate());
        pimg=(ImageView)findViewById(R.id.pimg);
        new DownloadImageTask(pimg,progressBar).execute(movie.getPosterurl());
        if(movie.getImdbRating().equals("")){
            rate.setRating(0);
        }else{
            rate.setRating(Float.parseFloat(movie.getImdbRating()));}
        addToWatch = (Button) findViewById(R.id.addwatchbtn);
        addToWatch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });
        Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dataBaseHelper.addComment(txtcmt.getText().toString(),movie.getId());
            }
        });
        addtoratebtn.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });
    }

}

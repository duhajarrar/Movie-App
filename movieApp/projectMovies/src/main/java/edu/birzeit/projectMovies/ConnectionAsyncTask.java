package edu.birzeit.projectMovies;

import android.os.AsyncTask;

public class ConnectionAsyncTask extends AsyncTask<String, String, String> {
    public ConnectionAsyncTask(){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MainActivity.movieslist = MovieJSONParser.parse(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        return HTTPManager.getData(strings[0]);
    }
}

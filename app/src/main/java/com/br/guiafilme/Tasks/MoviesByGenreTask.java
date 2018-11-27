package com.br.guiafilme.Tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.br.guiafilme.R;

public class MoviesByGenreTask extends AsyncTask<Void,Void,String> {

    private final Context context;

    public MoviesByGenreTask(Context context,int idGenre){
        this.context = context;
    }
    
    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    private String CreateURL(){
        String baseURl = context.getString(R.string.API_URL);
        String apiKey = context.getString(R.string.API_KEY);
        return "";
    }
}

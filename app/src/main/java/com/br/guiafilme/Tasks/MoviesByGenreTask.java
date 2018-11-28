package com.br.guiafilme.Tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.br.guiafilme.R;
import com.br.guiafilme.Web.WebClient;
import com.br.guiafilme.model.Movie;
import com.br.guiafilme.model.MovieList;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MoviesByGenreTask extends AsyncTask<Void,Void,ArrayList<Movie>> {

    private final Context context;
    private final int idGenre;
    private final ArrayList<Movie> movieList;

    public MoviesByGenreTask(Context context,int idGenre){
        this.context = context;
        this.idGenre = idGenre;
        this.movieList = new ArrayList<Movie>();
    }
    
    @Override
    protected ArrayList<Movie> doInBackground(Void... voids) {
        int page =1;

        Gson gson = new Gson();

        while(movieList.size() < 50){
            String endpoint = CreateURL(page);
            WebClient client = new WebClient();
            String retorno = client.Get(context,endpoint);

            MovieList lista = gson.fromJson(retorno,MovieList.class);
            movieList.addAll(lista.getResults());
            page ++;
        }

        return movieList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> s) {
        super.onPostExecute(s);

    }

    private String CreateURL(int page){
        String baseURl = context.getString(R.string.API_URL);
        String apiKey = context.getString(R.string.API_KEY);
        String endpoint =  baseURl+ "discover/movie?api_key="+apiKey
                +"&sort_by=popularity.desc&include_adult=false&include_video=false&page="+page
                +"&with_genres="+idGenre;
        return endpoint;
    }
}

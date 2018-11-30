package com.br.guiafilme.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.br.guiafilme.Adapters.MovieAdapter;
import com.br.guiafilme.R;
import com.br.guiafilme.Web.WebClient;
import com.br.guiafilme.model.Movie;
import com.br.guiafilme.model.MovieList;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MoviesByGenreTask extends AsyncTask<Void,Void,ArrayList<Movie>> {

    private final Context context;
    private final int idGenre;
    private final ArrayList<Movie> movies;
    private final MovieAdapter adapter;
    private ProgressDialog dialog;

    public MoviesByGenreTask(Context context,int idGenre, ArrayList<Movie> movies,MovieAdapter adapter){
        this.context = context;
        this.idGenre = idGenre;
        this.movies = movies;
        this.adapter = adapter;
    }
    
    @Override
    protected ArrayList<Movie> doInBackground(Void... voids) {
        int page =1;

        Gson gson = new Gson();
        movies.clear();

        while(movies.size() < 50){
            String endpoint = CreateURL(page);
            WebClient client = new WebClient();
            String retorno = client.Get(context,endpoint);

            MovieList lista = gson.fromJson(retorno,MovieList.class);
            movies.addAll(lista.getResults());
            page ++;
        }

        return movies;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context , "Aguarde", "Buscando Filmes ...", true,false);
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> s) {
        super.onPostExecute(s);
        dialog.dismiss();
        adapter.notifyDataSetChanged();
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

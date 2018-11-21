package com.br.guiafilme.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.br.guiafilme.R;
import com.br.guiafilme.Web.WebClient;
import com.br.guiafilme.model.Genre;
import com.google.gson.Gson;

import java.util.ArrayList;


public class GetGenreTask extends AsyncTask<Void,Void,String> {

    private final ArrayList<Genre> genres;
    private final Context context;
    private ProgressDialog dialog;

    public GetGenreTask(Context context, ArrayList<Genre> genres){
        this.genres = genres;
        this.context= context;
    }


    @Override
    protected String doInBackground(Void... voids) {
        String endpoint = CreateURL(context);
        WebClient client = new WebClient();

        String response = client.Get(context,endpoint);
        return response;
    }

    @Override
    protected void onPostExecute(String json) {
        dialog.dismiss();
        if(!json.isEmpty()) {
            Genre[] var = new Gson().fromJson(json, Genre[].class);
            this.genres.clear();
            for (Genre g : var) {
                this.genres.add(g);
            }
        }
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context , "Aguarde", "Buscando Generos...", true,true);
    }

    private String CreateURL(Context context) {
        String baseUrl = context.getString(R.string.API_URL);
        String url_key = context.getString(R.string.API_KEY);
        return baseUrl + "genre/movie/list?api_key="+url_key;
    }
}

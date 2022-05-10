package com.example.englishdictionary.dictionaryapi;

import android.content.Context;
import android.widget.Toast;

import com.example.englishdictionary.dictionaryapi.api.EntriesApi;
import com.example.englishdictionary.dictionaryapi.api.TranslateAPI;
import com.example.englishdictionary.dictionaryapi.api.SearchApi;
import com.example.englishdictionary.dictionaryapi.model.RetrieveEntry;
import com.example.englishdictionary.dictionaryapi.model.Wordlist;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    Context context;
    final String BASE_URL = "https://od-api.oxforddictionaries.com:443/api/v2/";
    final String app_id = "7a87338f";
    final String app_key = "09211d76f08186f5fd125b5a1bb95541";
    final Integer LIMIT = 10;
    final Boolean PREFIX = true;


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public  RequestManager(Context context) {
        this.context = context;
    }

    public void getWordMeaning(OnFetchDataListener listener, String word) {
        EntriesApi entriesApi = retrofit.create(EntriesApi.class);

        Call<RetrieveEntry> call = entriesApi
                .getDictionaryEntries("en-gb", word, app_id, app_key);
        try {
            call.enqueue(new Callback<RetrieveEntry>() {
                @Override
                public void onResponse(Call<RetrieveEntry> call, Response<RetrieveEntry> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "Word does not exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body(), response.message());
                }

                @Override
                public void onFailure(Call<RetrieveEntry> call, Throwable t) {
                    if(t instanceof IOException) {
                        listener.onError("Network failure");
                        t.printStackTrace();
                    } else {
                        listener.onError("Conversion issue");
                        t.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show();
        }
    }

    public void getTranslate(OnFetchDataListener listener, String sourceLang, String targetLang
                            , String word) {
        TranslateAPI translateAPI = retrofit.create(TranslateAPI.class);

        Call<RetrieveEntry> call = translateAPI.getTranslate(sourceLang, targetLang, word, app_id, app_key);

        try {
            call.enqueue(new Callback<RetrieveEntry>() {
                @Override
                public void onResponse(Call<RetrieveEntry> call, Response<RetrieveEntry> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Word does not exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body(), response.message());
                }

                public void onFailure(Call<RetrieveEntry> call, Throwable t) {
                    if (t instanceof IOException) {
                        listener.onError("Network failure");
                        t.printStackTrace();
                    } else {
                        listener.onError("Conversion issue");
                        t.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show();
        }
    }
    public void getWordSearch(OnFetchSearchDataListener listener, String query) {
        SearchApi searchApi = retrofit.create(SearchApi.class);

        Call<Wordlist> call = searchApi
                .getSearchSourceLang("en-gb", app_id, app_key, query, PREFIX, LIMIT);
        try {
            call.enqueue(new Callback<Wordlist>() {
                @Override
                public void onResponse(Call<Wordlist> call, Response<Wordlist> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "Find nothing", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body(), response.message());
                }

                @Override
                public void onFailure(Call<Wordlist> call, Throwable t) {
                    if(t instanceof IOException) {
                        listener.onError("Network failure");
                        t.printStackTrace();
                    } else {
                        listener.onError("Conversion issue");
                        t.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show();
        }
    }
}

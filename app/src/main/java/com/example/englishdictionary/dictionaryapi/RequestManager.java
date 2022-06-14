package com.example.englishdictionary.dictionaryapi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.englishdictionary.MyApplication;
import com.example.englishdictionary.dictionaryapi.api.EntriesApi;
import com.example.englishdictionary.dictionaryapi.api.SearchTranslateAPI;
import com.example.englishdictionary.dictionaryapi.api.TranslateAPI;
import com.example.englishdictionary.dictionaryapi.api.SearchApi;
import com.example.englishdictionary.dictionaryapi.model.HeadwordEntry;
import com.example.englishdictionary.dictionaryapi.model.RetrieveEntry;
import com.example.englishdictionary.dictionaryapi.model.Wordlist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    Context context;
    final String BASE_URL = "https://od-api.oxforddictionaries.com:443/api/v2/";
    final String app_id = "907aad3a";
    final String app_key = "6c609793748a3d295566de4be6367286";
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

//    public void get

    public void getTranslate(OnFetchDataListener listener, String sourceLang, String targetLang
                            , String word) {
        List<String> expected_word = new ArrayList<>();
        SearchTranslateAPI searchTranslateAPI = retrofit.create(SearchTranslateAPI.class);
        Call<RetrieveEntry> call_search = searchTranslateAPI.getSearchTranslate(sourceLang
                , targetLang, word, app_id, app_key, 5);

        try {
            call_search.enqueue(new Callback<RetrieveEntry>() {
                @Override
                public void onResponse(Call<RetrieveEntry> call, Response<RetrieveEntry> response) {
                    if (!response.isSuccessful()) {
                        Log.v("ExpectedWord:", "0");
                        listener.onError("Translation not available");
                        return;
                    }
                    try {
                        List<HeadwordEntry> headwordEntries = response.body().getResults();
                        for (HeadwordEntry h : headwordEntries)
                            expected_word.add(h.getWord());

                        TranslateAPI translateAPI = retrofit.create(TranslateAPI.class);
                        if(expected_word.size() < 1)
                            expected_word.add(word);
                        Call<RetrieveEntry> call_trans = translateAPI.getTranslate(sourceLang, targetLang, expected_word.get(0), app_id, app_key);
                        try {
                            call_trans.enqueue(new Callback<RetrieveEntry>() {
                                @Override
                                public void onResponse(Call<RetrieveEntry> call, Response<RetrieveEntry> response) {
                                    if (!response.isSuccessful()) {
                                        Toast.makeText(context, "No available translation", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    try {
                                        listener.onFetchData(response.body(), response.message());
                                    }
                                    catch (NullPointerException e) {
                                        e.printStackTrace();
                                    }
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
                    catch (NullPointerException e) {
                        e.printStackTrace();
                    }
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

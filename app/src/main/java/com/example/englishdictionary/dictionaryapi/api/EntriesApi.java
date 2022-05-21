package com.example.englishdictionary.dictionaryapi.api;

import com.example.englishdictionary.dictionaryapi.model.RetrieveEntry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface EntriesApi {
    @GET("entries/{source_lang}/{word_id}")
    Call<RetrieveEntry> getDictionaryEntries(
            @Path("source_lang") String sourceLang,
            @Path("word_id") String wordId,
            @Header("app_id") String appId,
            @Header("app_key") String appKey
    );
}

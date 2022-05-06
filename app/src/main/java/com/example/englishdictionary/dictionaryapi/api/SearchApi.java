package com.example.englishdictionary.dictionaryapi.api;

import com.example.englishdictionary.dictionaryapi.model.Wordlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchApi {
    @GET("search/{source_lang}")
    Call<List<Wordlist>> searchSourceLangGet(
            @Path("source_lang") String sourceLang,
            @Header("app_id") String appId,
            @Header("app_key") String appKey,
            @Query("q") String q, @Query("prefix") Boolean prefix,
            @Query("regions") String regions,
            @Query("limit") String limit,
            @Query("offset") String offset
    );
}

package com.example.englishdictionary.dictionaryapi.api;

import com.example.englishdictionary.dictionaryapi.model.RetrieveEntry;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchTranslateAPI {
    @GET("search/translations/{source_lang_search}/{target_lang_search}")
    Call<RetrieveEntry> getSearchTranslate(
            @Path("source_lang_search") String sourceLangTrans,
            @Path("target_lang_search") String targetLangTrans,
            @Query("q") String q,
            @Header("app_id") String appId,
            @Header("app_key") String appKey,
            @Query("limit") Integer limit
    );
}

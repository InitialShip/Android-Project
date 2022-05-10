package com.example.englishdictionary.dictionaryapi.api;

import com.example.englishdictionary.dictionaryapi.model.RetrieveEntry;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface TranslateAPI {
    @GET("translations/{source_lang_translate}/{target_lang_translate}/{word_id}")
    Call<RetrieveEntry> getTranslate(
            @Path("source_lang_translate") String sourceLangTrans,
            @Path("target_lang_translate") String targetLangTrans,
            @Path("word_id") String wordId,
            @Header("app_id") String appId,
            @Header("app_key") String appKey
    );
}

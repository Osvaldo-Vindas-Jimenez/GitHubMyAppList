package com.example.myapplicationlist.api

import com.example.myapplicationlist.models.EpisodeListModel
import com.example.myapplicationlist.models.EpisodeModels
import io.reactivex.Observable
import retrofit2.http.GET

interface APIService {
    /**
     Método GET de mi APISERVICE GetEpisode
    */
    @GET("episodes.json")
    fun getEpisodes(): Observable<EpisodeListModel<List<EpisodeModels>>>
}


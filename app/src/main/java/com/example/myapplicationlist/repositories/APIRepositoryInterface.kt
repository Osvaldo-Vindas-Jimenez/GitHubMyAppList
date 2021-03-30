package com.example.myapplicationlist.repositories

import com.example.myapplicationlist.models.EpisodeModels
import com.example.myapplicationlist.models.NetworkResponseModel
import io.reactivex.Observable

interface APIRepositoryInterface {
    fun getEpisodes(): Observable<NetworkResponseModel<List<EpisodeModels>>>

}
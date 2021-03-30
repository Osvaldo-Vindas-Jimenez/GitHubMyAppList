package com.example.myapplicationlist.repositories

import com.example.myapplicationlist.api.APIService
import com.example.myapplicationlist.models.EpisodeModels
import com.example.myapplicationlist.models.NetworkResponseModel
import io.reactivex.Observable

class APIRepository(private val api: APIService): APIRepositoryInterface  {

    override fun getEpisodes(): Observable<NetworkResponseModel<List<EpisodeModels>>> =
        api.getEpisodes().map { NetworkResponseModel(data = it.episodes) }
}
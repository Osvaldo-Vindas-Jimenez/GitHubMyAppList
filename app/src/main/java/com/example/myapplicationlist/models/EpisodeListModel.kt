package com.example.myapplicationlist.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EpisodeListModel<out T:Any>(
    @SerializedName("episodes") @Expose val episodes: List<EpisodeModels>
 )
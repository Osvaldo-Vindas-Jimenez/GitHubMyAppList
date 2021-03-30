package com.example.myapplicationlist.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity

data class EpisodeModels (
        @SerializedName("title") @Expose val title: String,
        @SerializedName("description") @Expose val description: String,
        @SerializedName("small_artwork_url") @Expose val small_artwork_url: String,
        @SerializedName("large_artwork_url") @Expose val large_artwork_url: String,
        @SerializedName("web_url") @Expose val web_url: String

)

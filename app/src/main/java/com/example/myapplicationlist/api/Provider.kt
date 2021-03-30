package com.example.myapplicationlist.api

import com.example.myapplicationlist.api.Provider.converter
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//Utiliza retrofit para consumir los datos desde mi APIService

object Provider {

    private var gsonGonverterFactory: GsonConverterFactory? = null


    //Conversor de Json a un objeto o clase Kotlin con GsonConverterFactory

    val converter: GsonConverterFactory
        get() {
            if (gsonGonverterFactory == null) {
                gsonGonverterFactory = GsonConverterFactory
                    .create(GsonBuilder()
                        .setLenient()
                        .disableHtmlEscaping()
                        .create())
            }
            return gsonGonverterFactory!!
        }

}


  //Creamos la clase APIProvider Tomamos La Información de mi apiService para hacer las solicitudes de la aplicación con retrofit
  //Además convertimos ese JSON en un objeto con gSonComberter

object APIProvider {
    private val MyUrl: String = "https://www.nsscreencast.com/api/"
    fun provider(): APIService = Retrofit.Builder()
        .baseUrl(MyUrl)
        .addConverterFactory(converter)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(APIService::class.java)
}
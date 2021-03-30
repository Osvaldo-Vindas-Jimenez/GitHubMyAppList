package com.example.myapplicationlist.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplicationlist.R
import com.example.myapplicationlist.api.APIService
import com.example.myapplicationlist.api.APIProvider
import com.example.myapplicationlist.common.Resource
import com.example.myapplicationlist.models.EpisodeModels
import com.example.myapplicationlist.repositories.APIRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val context: Context) {
    /**
    Variable de tipo proveedor de API
     */
    private val provider: APIService = APIProvider.provider()
    private val compositeDisposable: CompositeDisposable =  CompositeDisposable()
    /**
    Guarda los datos en un objeto mutable
     */
    private var data: MutableLiveData<Resource<List<EpisodeModels>>> = MutableLiveData()
    private val apiRepository = APIRepository(provider)
    /**
    Función que retorna los episodios de la consulta en data
     */
    fun getData(): LiveData<Resource<List<EpisodeModels>>>? {
        getAPIEpisodes()
        return data
    }

    /**
    Busca los datos de el API
     */
    private fun getAPIEpisodes() {
        data.value = Resource.loading()
        compositeDisposable.add(apiRepository.getEpisodes()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                data.value = Resource.success(it.data)
            }, {
                data.value = Resource.error(context.getString(R.string.app_name ) +
                        it.message.toString())
            })
        )
    }


}
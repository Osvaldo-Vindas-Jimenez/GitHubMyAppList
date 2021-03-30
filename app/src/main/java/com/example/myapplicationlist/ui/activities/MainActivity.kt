package com.example.myapplicationlist.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationlist.R
import com.example.myapplicationlist.common.BaseActivity
import com.example.myapplicationlist.common.Status
import com.example.myapplicationlist.models.EpisodeModels
import com.example.myapplicationlist.ui.adapters.EpisodeAdapter
import com.example.myapplicationlist.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
@RequiresApi(Build.VERSION_CODES.O)
/**
Hereda de activityBase  que es una base con el onCreate y métodos abstractos
 */
class MainActivity : BaseActivity() {
    /**
    Instancia del ViewModel Principal
     */
    private lateinit var viewModelMain: MainViewModel
    /**
    Instancia del objeto adaptador, es el que inyecta la información en el activity
     */
    private lateinit var episodeAdapter: EpisodeAdapter
    /**
    Mi objeto lista de episodios
     */
    private lateinit var episodeList: List<EpisodeModels>
    /**
    Identifica el activity  a crear
     */
    override val layout: Int get() = R.layout.activity_main

    /**
    Método que inicializa el Viewmodel de esta actividad
     */
    override fun initializeViewModel() {
        viewModelMain = MainViewModel(this)
    }

    /**
    Llama a la función que obtiene los datos del ViewModel
     */
    override fun observeLiveData() {
        initEpisodesObserver()
    }

    /**
    Observa las variables mutables y en vivo en ViewMode y  obtiene los datos de los episodios y  los muéstraen RecyclerView.
     */
    private fun initEpisodesObserver() {
        /**
        Esta parte observa el valor de episodeListM:it y cuando contiene datos
         */
        viewModelMain.getData()?.observe(this, {

            when (it.status) {
                Status.CARGANDO -> progress_bar.visibility = View.VISIBLE
                Status.ERROR -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
                    progress_bar.visibility = View.GONE
                }
                Status.CREADO -> {
                    episodeList = it.data!!
                    setEpisodesAdapter()
                    progress_bar.visibility = View.GONE
                }
            }
        })
    }

    /**
    Carga los datos al episodeAdapter
     */
    private fun setEpisodesAdapter() {
        episodeAdapter = EpisodeAdapter(episodeList, this@MainActivity)
        rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = episodeAdapter
        }
    }




    override fun initializeUI() {}

    override fun initOnClicks() {}

}


package com.example.myapplicationlist.ui.activities

import android.content.Intent
//.import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplicationlist.R
import com.example.myapplicationlist.common.BaseActivity
import com.example.myapplicationlist.models.EpisodeModels
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : BaseActivity() {
    /**
    Identifica el activity  a crear
     */
    override val layout: Int get() = R.layout.activity_item
    /**
    variable episodio
     */
    private lateinit var episode: EpisodeModels
    /**
    Mientras oculta el activity anterior llama a showEpisodeData
     */
    override fun initializeUI() {
        super.getSupportActionBar()?.hide()
        showEpisodeData()
    }
    /**
    Con el onClickListener llama a la clase webViewActivity y le pinta la url que tiene el episodio
     */
    override fun initOnClicks() {
        idButton.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", episode.web_url)
            startActivity(intent)
        }
    }
    /**
    Muestra el episodio seleccionado
     */
    private fun showEpisodeData() {
        /**
        Comprueba que el episodio no este vacío
         */
        if (intent.hasExtra("episode")) {
            episode = GsonBuilder().create()
                    .fromJson(intent.getStringExtra("episode").toString(), EpisodeModels::class.java)
            Glide.with(this).load(episode.small_artwork_url)
                    .error(R.drawable.ic_launcher_background).fitCenter().into(idImageItem)
            idTitleItem.text = episode.title
            idDescriptionItem.text = episode.description
        }
    }

    override fun initializeViewModel() {}
    override fun observeLiveData() {}

}


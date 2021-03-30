package com.example.myapplicationlist.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// clase abstracta baseActivity, se usacomo base para las Activitis
abstract class BaseActivity: AppCompatActivity()  {

    // Identifica el activity  a crear
    abstract val layout: Int

    // crea la activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        initialize()
    }

    // Método que implementa todas las funciones abstractas
    private fun initialize() {
        initializeUI()
        initializeViewModel()
        initOnClicks()
        observeLiveData()
    }

    //funciones abstractas
    abstract fun initializeUI()
    abstract fun initializeViewModel()
    abstract fun initOnClicks()
    abstract fun observeLiveData()


}
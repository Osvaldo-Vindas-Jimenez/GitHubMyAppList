package com.example.myapplicationlist.ui.activities

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.myapplicationlist.R
import com.example.myapplicationlist.common.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {

    // Identifica el activity  a crear
    override val layout: Int get() = R.layout.activity_web_view  // Layout loaded on the BaseAdapter


    // Mientras oculta el activity anterior llama a iniciar la vista web
    override fun initializeUI() {
        super.getSupportActionBar()?.hide()
        initWebView()
    }


    // iniciar la vista web
    private fun initWebView() {
    // Variable con la url da la pagina a mostrar
        val url = intent.getStringExtra("url")

         //Inicialización de WebView.
        viewModelWeb.webViewClient = object: WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress_bar.visibility = View.VISIBLE
            }
            override fun onPageFinished(view: WebView, url: String) {
                progress_bar.visibility = View.GONE
            }
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?,
                                         error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                progress_bar.visibility = View.GONE
                Toast.makeText(baseContext,
                        resources.getString(R.string.error_load_web_view), Toast.LENGTH_SHORT)
                        .show()
            }
        }

         //Si el url no es nul píntelo en la activity web view
        if (url != null) {
            viewModelWeb.loadUrl(url)
        }
    }


    override fun initializeViewModel() {}

    override fun initOnClicks() {}

    override fun observeLiveData() {}

}
package kr.ac.kpu.first_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings

import android.webkit.WebViewClient

import android.R.*

import android.webkit.WebView

class MainActivity4 : AppCompatActivity() {
    private lateinit var mWebView : WebView
    private lateinit var mWebSettings : WebSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        // 웹뷰 시작
        mWebView = findViewById(R.id.webView)
        mWebView!!.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        mWebSettings = mWebView!!.settings //세부 세팅 등록
        mWebSettings!!.javaScriptEnabled = true // 웹페이지 자바스클비트 허용 여부
        mWebSettings!!.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
        mWebSettings!!.javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings!!.loadWithOverviewMode = true // 메타태그 허용 여부
        mWebSettings!!.useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
        mWebSettings!!.setSupportZoom(true) // 화면 줌 허용 여부
        mWebSettings!!.builtInZoomControls = true // 화면 확대 축소 허용 여부
        mWebSettings!!.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기
        mWebSettings!!.cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
        mWebSettings!!.domStorageEnabled = true // 로컬저장소 허용 여부
        mWebView!!.loadUrl("http://10.0.2.2:8080/") // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
    }
}
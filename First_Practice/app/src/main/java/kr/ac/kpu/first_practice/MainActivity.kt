package kr.ac.kpu.first_practice

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "MainActivityLog"
    private val URL = "http://172.30.1.36:3000"
    private lateinit var retrofit: Retrofit
    private lateinit var service: ApiService
    private lateinit var btn_get: Button
    private lateinit var btn_post: Button
    private lateinit var btn_delete: Button
    private lateinit var btn_update: Button

    private lateinit var btn_hello: Button

    private lateinit var mWebView : WebView
    private lateinit var mWebSettings : WebSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstInit()
        btn_get.setOnClickListener(this)
        btn_post.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
        btn_update.setOnClickListener(this)
        btn_hello.setOnClickListener(this)

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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT
            || keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            val call_get : Call<ResponseBody> = service.getMovedFunc("get moved")
            call_get.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        try {
                            val result = response.body()!!.string()
                            Log.v(TAG, "result = $result")
                            Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    } else {
                        Log.v(TAG, "error = " + response.code().toString())
                        Toast.makeText(
                            applicationContext,
                            "error = " + response.code().toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.v(TAG, "Fail")
                    Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }


        return super.onKeyDown(keyCode, event)
    }

    /**
     * Init
     */
    private fun firstInit() {
        btn_get = findViewById(R.id.btn_get)
        btn_post = findViewById(R.id.btn_post)
        btn_delete = findViewById(R.id.btn_delete)
        btn_update = findViewById(R.id.btn_update)
        btn_hello = findViewById(R.id.btn_hello)
        retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()

        service = retrofit.create(ApiService::class.java)
    }

    /**
     * View.OnLongClickListener override method
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_get -> {

                val call_get : Call<ResponseBody> = service.getFunc("get data")

                call_get.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(applicationContext,"error = " + response.code().toString(),Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            R.id.btn_hello -> {
                val call_get_hello : Call<ResponseBody> = service.getHelloFunc("SooHyun")
                call_get_hello.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(applicationContext,"error = " + response.code().toString(),Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            R.id.btn_post -> {
                val call_post : Call<ResponseBody> = service.postFunc("post data")
                call_post.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(
                                applicationContext,
                                "error = " + response.code().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            R.id.btn_update -> {
                val call_put : Call<ResponseBody> = service.putFunc("board", "put data")
                call_put.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(
                                applicationContext,
                                "error = " + response.code().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            R.id.btn_delete -> {
                val call_delete : Call<ResponseBody> = service.deleteFunc("board")
                call_delete.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(
                                applicationContext,
                                "error = " + response.code().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            else -> {}
        }
    }
}
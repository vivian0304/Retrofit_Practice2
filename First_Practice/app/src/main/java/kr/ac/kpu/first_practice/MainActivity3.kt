package kr.ac.kpu.first_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity3 : AppCompatActivity() {

    private val TAG = "LoginLog"
    private val URL = "http://172.30.1.52:3000"
    private lateinit var retrofit: Retrofit
    private lateinit var service: ApiService
    private lateinit var idText : EditText
    private lateinit var passwordText : EditText
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        firstInit()
    }

    private fun firstInit() {
        idText = findViewById(R.id.id_tv)
        passwordText = findViewById(R.id.pw_tv)
        button = findViewById(R.id.login_btn)
        retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()

        service = retrofit.create(ApiService::class.java)
    }
}
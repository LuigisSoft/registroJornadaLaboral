package com.example.jornadalaboralflexible

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        //Uso un hadler para retrasar la ejecución

        Handler(Looper.getMainLooper()).postDelayed({
            //iniciar la actividad principal
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            //cerrar esta actividad
            finish()

        }, 3000) // 3000= 3 segundos

    }
}
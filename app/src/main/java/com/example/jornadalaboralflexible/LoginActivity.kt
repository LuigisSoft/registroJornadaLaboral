package com.example.jornadalaboralflexible

import android.app.DownloadManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

import com.example.jornadalaboralflexible.databinding.ActivityLoginBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.OkHttpClient
import okhttp3.FormBody
import okhttp3.Request
import java.io.IOException


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var usuario: EditText
    private lateinit var password: EditText
    private lateinit var login: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
       setContentView(binding.root)



        binding.login.setOnClickListener{
            val usuario=binding.login.text.toString()
            val password = binding.password.text.toString()
            if(usuario.isNotEmpty()&& password.isNotEmpty()){
                loginUser(usuario, password)

                }else{
                    Toast.makeText(this, " Por favor, completa todos los campos", Toast. LENGTH_SHORT).show()
                }
            }
        }

    private fun loginUser(username: String, password: String) {
        val client = OkHttpClient()
        val url = "http://192.168.68.107:8080/api/auth/login"

        val formBody = FormBody.Builder()
            .add("username", username)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object : Callback {

            // Método que se ejecuta si la solicitud falla
           override fun onFailure(call: Call, e: IOException) {
                // Manejar el error en la UI principal
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val responseData = response.body?.string()
                val json = JSONObject(responseData)
                val token = json.getString("token") // Si decides usar JWT

                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Login exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("TOKEN", token) // Pasando el token a la MainActivity
                    startActivity(intent)
                    finish()
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    })
}
}













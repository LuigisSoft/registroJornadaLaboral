package com.example.jornadalaboralflexible

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jornadalaboralflexible.databinding.ActivityRegisterBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import okhttp3.OkHttpClient


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var password:EditText
    private lateinit var user: EditText
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener{
            val username=binding.user2.text.toString()
            val password=binding.password2.text.toString()

            if(username.isNotEmpty() && password.isNotEmpty()){
                registerUser(username, password)
            }else{
                Toast.makeText(this,"Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }


        }

    }



    private fun registerUser(username:String, password:String){
        val client=OkHttpClient()


        val url= "http://192-168.68.107:8080/api/auth/register"

        val formBody = FormBody.Builder()
            .add("username", username)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread{
                    Toast.makeText(this@RegisterActivity, "Error al conectar con el servidor",Toast.LENGTH_SHORT).show()
                }
            }
            
            override fun onResponse(call: Call, response: Response){
                if(response.isSuccessful){
                    runOnUiThread{
                        Toast.makeText(this@RegisterActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    runOnUiThread{
                        Toast.makeText(this@RegisterActivity, "Error al registar usuario", Toast.LENGTH_SHORT).show()
                    }
                }
            }
          })


    }


}
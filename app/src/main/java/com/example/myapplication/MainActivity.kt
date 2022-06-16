package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var passwordPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        passwordPreferences = this.getSharedPreferences("passwordPreference", AppCompatActivity.MODE_PRIVATE)

        var username = findViewById<EditText>(R.id.userText)
        var password = findViewById<EditText>(R.id.pwdtxt)
        var enter = findViewById<Button>(R.id.enterbtn)
        enter?.setOnClickListener(View.OnClickListener {
            checkLogin( username.text.toString(), password.text.toString())
        })

    }

    private fun checkLogin(username: String, password: String) {
        Toast.makeText(this@MainActivity,"Username"+ username + "Password : "+ password,LENGTH_LONG).show()
        if (username != null && password != null){

            val editor = passwordPreferences!!.edit()
            editor.putString("password",password)
            editor.apply()
            //var intent = Intent(this@MainActivity,HomeActivity::class.java)
            //startActivity(intent)
        }


    }
}

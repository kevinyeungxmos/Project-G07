package com.example.project_g07.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.project_g07.R
import com.example.project_g07.data.DataManagement
import com.example.project_g07.data.HandlePrefs


class LoginActivity : AppCompatActivity() {

    val handlePref = HandlePrefs(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        
        btnLogin.setOnClickListener(){
            val name: EditText = findViewById(R.id.edittextName)
            if(name.text.toString() == ""){
                Toast.makeText(this, "Error: Name cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                val newInstance = DataManagement.getInstance()
                newInstance.name = name.text.toString()
                handlePref.initSharedPrefs(newInstance,name.text.toString())
                val intent = Intent(this, LessonListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
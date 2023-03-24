package com.example.project_g07.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.project_g07.R
import com.example.project_g07.data.DataCourse


class LoginActivity : AppCompatActivity() {

    lateinit var sharedPrefs: SharedPreferences

    fun init_sharedPrefs(newInstance:DataCourse){
        with(sharedPrefs.edit()){
            putBoolean("KEY_LOGIN", true)
            for(i in newInstance.courseList){
                putBoolean("KEY_COMPLETE_${i.code}", false)
            }
            apply()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        
        btnLogin.setOnClickListener(){
            val name: EditText = findViewById(R.id.edittextName)
            if(name.text.toString() == ""){
                Toast.makeText(this, "Error: Name cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                val newInstance = DataCourse.getInstance()
                newInstance.name = name.text.toString()
                sharedPrefs = getSharedPreferences("MY-PREFS", MODE_PRIVATE)
                this.init_sharedPrefs(newInstance)
                val intent = Intent(this, LessonDetailsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
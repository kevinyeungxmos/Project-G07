package com.example.project_g07.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.project_g07.R
import com.example.project_g07.data.DataManagement
import com.example.project_g07.data.HandlePrefs

class LessonDetailsActivity : AppCompatActivity() {
    lateinit var sharePrefs: SharedPreferences
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)


        val handlePref = HandlePrefs(this)
        val newInstance = DataManagement.getInstance()
        handlePref.prefs = getSharedPreferences("MY-PREFS", MODE_PRIVATE)

        if(intent != null){
            val cNum = intent.getIntExtra("TO", 0)
            if(handlePref.prefs.contains("KEY_COMPLETE_${cNum+1}")){
                val isComplete = handlePref.prefs.getBoolean("KEY_COMPLETE_${cNum+1}", false)
                if(isComplete){
                    Log.d("LESSONDETAIL", "course ${cNum + 1} completed")
                }else{
                    Log.d("LESSONDETAIL", "course ${cNum + 1} not yet completed")
                    handlePref.setComplete(cNum+1,true)
                    newInstance.courseList[cNum].isCompleted = true
                }
            }

        }

    }
}
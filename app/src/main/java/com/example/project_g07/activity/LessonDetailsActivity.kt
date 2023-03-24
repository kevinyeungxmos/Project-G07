package com.example.project_g07.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SharedMemory
import android.util.Log
import com.example.project_g07.R
import com.example.project_g07.data.DataCourse

class LessonDetailsActivity : AppCompatActivity() {
    lateinit var sharePrefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)

        this.sharePrefs = getSharedPreferences("MY-PREFS", MODE_PRIVATE)
        val newInstance = DataCourse.getInstance()
        for(i in newInstance.courseList){
            if(this.sharePrefs.contains("KEY_COMPLETE_${i.code}")){
                val ee = this.sharePrefs.getBoolean("KEY_COMPLETE_${i.code}", false)
                val lo = this.sharePrefs.getBoolean("KEY_LOGIN", false)
                Log.d("LESSONDETAIL", "${i.course}")
                Log.d("LESSONDETAIL", "${lo}")
                sharePrefs.edit().putBoolean("KEY_COMPLETE_${i.code}", true)
            }
        }
    }
}
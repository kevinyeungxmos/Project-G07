package com.example.project_g07.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.project_g07.R
import com.example.project_g07.data.DataManagement
import com.example.project_g07.data.HandlePrefs

class WelcomeBackActivity : AppCompatActivity() {

    // the TAG variable is used for Log.d() debugging
    val TAG = this@WelcomeBackActivity.toString()

    // property for shared preferences
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_back)

    }

    override fun onStart() {
        super.onStart()

        // instantiate the SharedPreferences variables
        // SharedPreferences = a singleton
        this.sharedPrefs = getSharedPreferences("MY-PREFS", MODE_PRIVATE)

        /////////////////calculate completed and remained lessons of
        var lessonsDatasource = DataManagement.getInstance()
        var handleFuns = HandlePrefs(this)
        var completeCount: Int = 0

        for (lesson in lessonsDatasource.courseList) {
            if (handleFuns.getComplete(lesson.code))
                completeCount++
        }

        var completePersentage = completeCount * 100 / lessonsDatasource.courseList.size
        var remainCount: Int = lessonsDatasource.courseList.size - completeCount


        //////////////////
        val tvUser: TextView = findViewById<TextView>(R.id.tvUser)
        val tvCompletedLessons: TextView = findViewById<TextView>(R.id.tvCompletedLessons)
        val tvRemainLessons: TextView = findViewById<TextView>(R.id.tvCRemainedLessons)
        val tvStatistics: TextView = findViewById<TextView>(R.id.tvStatistics)
        val tvStatistics2: TextView = findViewById(R.id.tvStatistics2)
        val pgb: ProgressBar = findViewById(R.id.progressBar2)

        // 1. Read data from sharedPreferences
        // - a. Check that the KEY you are looking for actually EXISTS
        if (this.sharedPrefs.contains("KEY_USER")) {
            // - b. if yes, then retrieve
            val nameFromSP = this.sharedPrefs.getString("KEY_USER", "N/A")
            tvUser.text = nameFromSP
        } else {
            // - c. if no, then error
            // TODO: You output an error message

            Log.d(TAG, "KEY_USER is not found in sp")
            // navigate to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            //return@onCreate
        }

        //////////

        tvCompletedLessons.text = "Lessons completed: ${completeCount.toString()}"
        tvRemainLessons.text = "Lessons remaining: ${remainCount.toString()}"
        tvStatistics.text = "You have completed ${completePersentage.toString()}% of the course!"
        tvStatistics2.text = "${completePersentage.toString()}%"
        Log.d("WBack", "complete per: $completePersentage")
        pgb.progress = completePersentage


        ////////////
        val btnContinue: Button = findViewById<Button>(R.id.btnContinue)

        btnContinue.setOnClickListener {
            Log.d(TAG, "SCREEN WelcomeBack Navigating to screen LessonListActivity")
            // navigate to LessonListActivity
            val intent = Intent(this, LessonListActivity::class.java)
            startActivity(intent)

        }

        val btnReset: Button = findViewById<Button>(R.id.btnReset)

        btnReset.setOnClickListener {
            /////Delete data from shared preferences
            val mEditor = this.sharedPrefs.edit()
            mEditor.clear()
            mEditor.apply()

            ///////////
            Log.d(TAG, "SCREEN Welcome Back Navigating to screen Login Activity")
            // navigate to Login Activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    override fun finish() {
        finishAffinity()
    }
}

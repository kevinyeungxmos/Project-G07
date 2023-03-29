package com.example.project_g07.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.*
import com.example.project_g07.R
import com.example.project_g07.data.DataManagement
import com.example.project_g07.data.HandlePrefs

class LessonDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)

    }

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_lesson_details)

        val handlePref = HandlePrefs(this)
        val lessonDatasource = DataManagement.getInstance()
        handlePref.prefs = getSharedPreferences("MY-PREFS", MODE_PRIVATE)

        val tvCourseCode = findViewById<TextView>(R.id.tvCourseCode)
        val tvCourseName = findViewById<TextView>(R.id.tvCourseName)
        val tvCourseLength = findViewById<TextView>(R.id.tvCourseLength)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val btnWatch = findViewById<Button>(R.id.btnWatch)
        val etNotes = findViewById<EditText>(R.id.etNotes)
        val chbComplete = findViewById<CheckBox>(R.id.chbComplete)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnBack: ImageButton = findViewById(R.id.imageButtonLD)
        var lessonCode: Int = 0

        if (intent != null) {

            lessonCode = intent.getIntExtra("TO", 0) + 1

            var isComplete: Boolean = false
            if (handlePref.prefs.contains("KEY_COMPLETE_${lessonCode}"))
                isComplete = handlePref.prefs.getBoolean("KEY_COMPLETE_${lessonCode}", false)

            chbComplete.isChecked = isComplete
            etNotes.setText(handlePref.prefs.getString("KEY_NOTE_${lessonCode}", ""))

            for (lesson in lessonDatasource.courseList) {
                if (lesson.code == lessonCode) {
                    tvCourseCode.text = "${lesson.code}"
                    tvCourseName.text = "${lesson.course}"
                    tvCourseLength.text = "Length: ${lesson.length}"
                    tvDescription.text = "${lesson.description}"

                    btnWatch.setOnClickListener() {

//                        val myWebView = WebView(this)
//                        setContentView(myWebView)
//                        myWebView.loadUrl(lesson.videoLink)

                       val urlToOpenAsUri: Uri = Uri.parse(lesson.videoLink)
                        val intent: Intent = Intent(Intent.ACTION_VIEW, urlToOpenAsUri)
                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent)
                        }
                        else {
                            val toast = Toast.makeText(this, "Cannot open website", Toast.LENGTH_SHORT)
                            toast.show()
                        }

                    }
                }
            }
        }

        btnSave.setOnClickListener() {
            val etNotes: EditText = findViewById(R.id.etNotes)
            handlePref.setNote(lessonCode, etNotes.text.toString())
            handlePref.setComplete(lessonCode, chbComplete.isChecked)
            Toast.makeText(this, "Your changes saved", Toast.LENGTH_SHORT).show()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
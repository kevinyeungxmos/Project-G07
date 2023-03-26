package com.example.project_g07.data

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g07.model.Course


class DataManagement {

    var name: String? = null
    var courseList: List<Course> =
        listOf<Course>(
            Course("course1", 1, 1.0, "this is course 1"),
            Course("course2", 2, 1.5, "this is course 2"),
            Course("course3", 3, 2.5, "this is course 3")
        )

    private constructor() {}

    companion object {
        @Volatile
        private lateinit var instance: DataManagement

        fun getInstance(): DataManagement {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = DataManagement()
                }
                return instance
            }
        }
    }

}

class HandlePrefs(val context: Context) {

    lateinit var prefs: SharedPreferences

    fun setLogin(isReset: Boolean) {
        this.prefs = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        with(this.prefs.edit()) {
            putBoolean("KEY_LOGIN", isReset)
            apply()
        }
    }

    fun setNote(code: Int, note: String) {
        this.prefs = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        with(this.prefs.edit()) {
            putString("KEY_NOTE_$code", note)
            apply()
        }
    }

    fun setComplete(code: Int, isComplete: Boolean) {
        this.prefs = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        with(this.prefs.edit()) {
            putBoolean("KEY_COMPLETE_$code", isComplete)
            apply()
        }
    }

    fun setSeq(isChecked: Boolean) {
        this.prefs = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        with(this.prefs.edit()) {
            putBoolean("KEY_SEQ", isChecked)
            apply()
        }
    }

    fun setName(name: String) {
        this.prefs = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        with(this.prefs.edit()) {
            putString("KEY_USER", name)
            apply()
        }
    }

    fun getNote(code: Int): String {
        this.prefs = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        val s = this.prefs.getString("KEY_NOTE_$code", "EMPTY")
        return s!!
    }

    fun getLogin(): Boolean {
        this.prefs = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        return prefs.getBoolean("KEY_LOGIN", false)
    }

    fun getComplete(code: Int): Boolean {
        this.prefs = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        return this.prefs.getBoolean("KEY_COMPLETE_$code", false)
    }

    fun getSEQ(): Boolean {
        this.prefs = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        return prefs.getBoolean("KEY_SEQ", false)
    }

    fun getName(): String? {
        this.prefs = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        return prefs.getString("KEY_USER", "N/A")
    }

    fun initSharedPrefs(newInstance: DataManagement, userName: String) {
        this.prefs = context.getSharedPreferences("MY-PREFS", AppCompatActivity.MODE_PRIVATE)
        with(prefs.edit()) {
            putBoolean("KEY_LOGIN", true)
            putBoolean("KEY_SEQ", false)
            putString("KEY_USER", userName)
            for (i in newInstance.courseList) {
                putBoolean("KEY_COMPLETE_${i.code}", false)
                putString("KEY_NOTE_${i.code}", "")
            }
            apply()
        }
    }

    fun syncPrefsAndDM(dm: DataManagement) {
        dm.name = getName()
        dm.courseList.forEachIndexed { index, course ->
            course.isCompleted = getComplete(index+1)
            course.note = getNote(index+1)
        }
    }

}
package com.example.project_g07.data

import com.example.project_g07.model.Course


class DataManagement {

    var name: String? = null
    var courseList: List<Course> =
        listOf<Course>(
            Course("Web Development Fundamentals", 1, "1:11:12",
                "Web Development Fundamentals HTML & CSS video lecture with Codesmith Alum and Former Engineering Mentor Savitri Beaver."
                    ,"https://youtu.be/2xXt11ziXSM"),
            Course("Full Stack Development", 2, "9:53:37",
                "Full stack development is the end-to-end development of applications. It includes both the front end and back end of an application. The front end is usually accessed by a client, and the back end forms the core of the application where all the business logic is applied.",
                "https://www.youtube.com/watch?v=kjBvQWHk_KI"),
            Course("Introduction to Kotlin", 3, "2:38:30",
                "Kotlin is a modern, trending programming language that was released in 2016 by JetBrains. It has become very popular since it is compatible with Java (one of the most popular programming languages out there), which means that Java code (and libraries) can be used in Kotlin programs.",
                "https://www.youtube.com/watch?v=F9UC9DY-vIU"),
            Course("Introduction to Android Development", 4, "11:26:37",
                "In this course, you'll learn the basics of building Android apps with the Kotlin programming language. Along the way, you'll develop a collection of apps to start your journey as an Android developer.",
                "https://www.youtube.com/watch?v=fis26HvvDII"),
            Course("Advanced Android Development", 5, "2:19:17",
                "he Complete Android Developer Course: Build Your Own Android Applications From Beginner To Advanced",
                "https://www.youtube.com/watch?v=yzS_mJj8lwo"),
            Course("Mobile Application Strategy", 6, "1:13:45",
                "Building a Fast and Secure Mobile App Development Strategy - Webinar Replay",
                "https://www.youtube.com/watch?v=cgR572Ufdfw")
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


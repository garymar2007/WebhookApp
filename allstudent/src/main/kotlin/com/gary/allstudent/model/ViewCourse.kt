package com.gary.allstudent.model

data class ViewCourse(
    val id: Long,
    val name: String,
    val studentsEnrolled: Iterable<String>
)

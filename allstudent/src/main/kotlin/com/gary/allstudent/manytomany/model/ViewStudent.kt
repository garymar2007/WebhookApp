package com.gary.allstudent.manytomany.model

data class ViewStudent(
    val id: Long,
    val name: String,
    val coursesEnrolledIn: Iterable<String>
)

package com.gary.allstudent.manytomany.model

import jakarta.persistence.*

@Entity
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @ManyToMany(mappedBy = "enrolledIn")
    val studentsEnrolledIn: List<Student> = listOf()
)

fun Course.toView() =
    ViewCourse(id, name, studentsEnrolledIn.map { it.name })

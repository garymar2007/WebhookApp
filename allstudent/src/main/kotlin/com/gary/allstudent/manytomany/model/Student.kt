package com.gary.allstudent.manytomany.model

import jakarta.persistence.*

@Entity
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @ManyToMany
    @JoinTable(
        name = "student_enrolled_in_course",
        joinColumns = arrayOf(JoinColumn(name = "student_id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "course_id"))
    )
    val enrolledIn: List<Course> = listOf()
)

fun Student.toView() =
    ViewStudent(id, name, enrolledIn.map { it.name })

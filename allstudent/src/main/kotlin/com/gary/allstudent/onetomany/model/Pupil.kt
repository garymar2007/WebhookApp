package com.gary.allstudent.onetomany.model

import jakarta.persistence.*

@Entity
data class Pupil(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,
    val name: String,
    val age: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolData_id")
    val schoolData: SchoolData? = null
)

fun Pupil.toView() =
    ViewPupil(id ?: 0, name, age, schoolData?.id ?: 0)
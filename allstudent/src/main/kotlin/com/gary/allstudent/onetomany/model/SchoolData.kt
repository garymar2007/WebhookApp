package com.gary.allstudent.onetomany.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
data class SchoolData(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    val id: Int? = null,
    val schoolName: String,
    val address: String,
    val phone: String,
    val email: String,
    val website: String,
    val logo: String,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolData", cascade = [CascadeType.ALL])
    val webhookDetails: List<WebhookDetails> = listOf(),
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolData", cascade = [CascadeType.ALL])
    val pupils: List<Pupil> = listOf()
) {
}

fun SchoolData.toView() =
    ViewSchoolData(id ?: 0, schoolName, address, phone,
        email, website, logo, webhookDetails.map { it.id ?: 0 },
        pupils.map { it.id ?: 0 })


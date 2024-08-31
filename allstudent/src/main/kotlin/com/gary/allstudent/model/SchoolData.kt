package com.gary.allstudent.model

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
    var webhookDetails: List<WebhookDetails>? = null
)

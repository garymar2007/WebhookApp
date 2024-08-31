package com.gary.allstudent.model

import jakarta.persistence.*

@Entity
data class WebhookDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    val id: Int? = null,
    val eventType: String,
    val endPointUrl: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolData_id")
    var schoolData: SchoolData? = null
)

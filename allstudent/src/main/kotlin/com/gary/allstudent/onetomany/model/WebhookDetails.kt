package com.gary.allstudent.onetomany.model

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

fun WebhookDetails.toView() =
    ViewWebEvent(id ?: 0, eventType, endPointUrl, schoolData?.id ?: 0)
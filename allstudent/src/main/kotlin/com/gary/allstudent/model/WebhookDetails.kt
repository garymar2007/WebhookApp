package com.gary.allstudent.model

import jakarta.persistence.*

@Entity
data class WebhookDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    val id: Int? = null,
    val eventType: EventType,
    val endPointUrl: String,
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "schoolData_id", nullable = true)
    var schoolData: SchoolData? = null
)

enum class EventType {
    STUDENT_ADD,
    STUDENT_DELETE,
    STUDENT_UPDATE
}

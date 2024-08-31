package com.gary.allstudent.model

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
    val logo: String
) {
    @JsonBackReference(value = "webhookDetails")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "schoolData", cascade = [CascadeType.ALL])
    var webhookDetails: List<WebhookDetails>? = mutableListOf()
}

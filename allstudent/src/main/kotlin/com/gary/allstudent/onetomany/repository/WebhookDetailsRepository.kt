package com.gary.allstudent.onetomany.repository

import com.gary.allstudent.onetomany.model.WebhookDetails
import org.springframework.data.jpa.repository.JpaRepository

interface WebhookDetailsRepository : JpaRepository<WebhookDetails, Int> {
}
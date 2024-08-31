package com.gary.allstudent.repository

import com.gary.allstudent.model.WebhookDetails
import org.springframework.data.jpa.repository.JpaRepository

interface WebhookDetailsRepository : JpaRepository<WebhookDetails, Int> {
}
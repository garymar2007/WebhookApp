package com.gary.allstudent.repository

import com.gary.allstudent.model.WebhookDetails
import org.springframework.data.repository.CrudRepository

interface WebhookDetailsRepository : CrudRepository<WebhookDetails, Int> {
}
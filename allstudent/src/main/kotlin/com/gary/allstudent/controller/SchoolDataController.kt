package com.gary.allstudent.controller

import com.gary.allstudent.model.SchoolData
import com.gary.allstudent.model.WebhookDetails
import com.gary.allstudent.service.SchoolDataService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/school")
class SchoolDataController(
    private val schoolDataService: SchoolDataService
) {
    @PostMapping("/addNewSchool")
    fun addSchoolData(@RequestBody schoolData: SchoolData): ResponseEntity<SchoolData> {
        val savedSchoolData = schoolDataService.addSchoolData(schoolData)
        return ResponseEntity.ok(savedSchoolData)
    }

    @PostMapping("/addWebhookEvent/{schoolId}")
    fun addWebhookEvent(
        @PathVariable schoolId: Int,
        @RequestBody webhookDetails: WebhookDetails
    ): ResponseEntity<String> {
        val schoolData = schoolDataService.findById(schoolId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid school Id!")
        val details = WebhookDetails(
            eventType = webhookDetails.eventType,
            endPointUrl = webhookDetails.endPointUrl,
        )
        details.schoolData = schoolData
        val webhooks: List<WebhookDetails> = listOf(details)
        schoolData.webhookDetails = webhooks
        schoolDataService.save(schoolData)

        return ResponseEntity.ok("Webhook added")
    }
}
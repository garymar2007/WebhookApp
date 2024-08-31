package com.gary.allstudent.controller

import com.gary.allstudent.model.SchoolData
import com.gary.allstudent.model.WebhookDetails
import com.gary.allstudent.service.SchoolDataService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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
            schoolData = schoolData
        )

        schoolDataService.save(
            schoolData.copy(webhookDetails = listOf(details)))

        return ResponseEntity.ok("Webhook added")
    }

    @GetMapping("/getSchoolData/{schoolId}")
    fun findSchoolDataById(@PathVariable schoolId: Int): ResponseEntity<SchoolData> {
        val schoolData = schoolDataService.findById(schoolId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid school Id!")
        return ResponseEntity.ok(schoolData)
    }

    @GetMapping("/getWebhookEvents/{schoolId}")
    fun findAllWebhookEvents(@PathVariable schoolId: Int): ResponseEntity<List<WebhookDetails>> {
        val schoolData = schoolDataService.findById(schoolId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid school Id!")
        return ResponseEntity.ok(schoolData.webhookDetails)
    }
}
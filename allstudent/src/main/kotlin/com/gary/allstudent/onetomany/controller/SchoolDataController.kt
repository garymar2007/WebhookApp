package com.gary.allstudent.onetomany.controller

import com.gary.allstudent.onetomany.model.*
import com.gary.allstudent.onetomany.service.SchoolDataService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
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

    @PostMapping("/addStudent/{schoolId}")
    fun addStudentToSchool(
        @PathVariable schoolId: Int,
        @RequestBody pupil: Pupil
    ): ResponseEntity<String> {
        val schoolData = schoolDataService.findById(schoolId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid school Id!")

        val pupilToBeSaved = Pupil(
            name = pupil.name,
            age = pupil.age,
            schoolData = schoolData
        )

        schoolDataService.save(
            schoolData.copy(pupils = listOf(pupilToBeSaved))
        )
        //send data as webhook
        schoolData.webhookDetails
            .stream()
            .filter { it.eventType == "STUDENT_ADD" }
            .findFirst().orElse(null)
            ?.let {
                //Send data to webhook endpoint via RestTemplate
                val url = it.endPointUrl + "/${pupil.name}"
                val result: String?  = RestTemplate().getForObject(url, pupil, String::class)
                println("Sending data to ${it.endPointUrl} and result is $result")
            }
        return ResponseEntity.ok("Student added")
    }


    @GetMapping("/getSchoolData/{schoolId}")
    fun findSchoolDataById(@PathVariable schoolId: Int): ResponseEntity<ViewSchoolData> {
        val schoolData = schoolDataService.findById(schoolId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid school Id!")
        return ResponseEntity.ok(schoolData.toView())
    }

    @GetMapping("/getWebhookEvents/{schoolId}")
    fun findAllWebhookEvents(@PathVariable schoolId: Int): ResponseEntity<List<ViewWebEvent>> {
        val schoolData = schoolDataService.findById(schoolId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid school Id!")
        return ResponseEntity.ok(schoolData.webhookDetails.map { it.toView() })
    }
}
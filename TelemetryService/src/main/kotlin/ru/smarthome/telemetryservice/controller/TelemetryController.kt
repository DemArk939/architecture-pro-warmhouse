package ru.smarthome.telemetryservice.controller

import jakarta.validation.Valid
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import ru.smarthome.api.TelemetryApi
import ru.smarthome.model.ModelApiResponse
import ru.smarthome.model.Telemetry
import ru.smarthome.telemetryservice.mapper.TelemetryMapper
import ru.smarthome.telemetryservice.model.entity.TelemetryEntity
import ru.smarthome.telemetryservice.repository.TelemetryRepository
import java.util.*

@RestController
class TelemetryController(
    private val telemetryRepository: TelemetryRepository,
    private val telemetryMapper: TelemetryMapper
) : TelemetryApi {

    private val log = KotlinLogging.logger {}

    override fun addTelemetry(telemetry: @Valid Telemetry?): ResponseEntity<ModelApiResponse?>? {
        if(telemetry == null) {
            return ResponseEntity.status(400).build()
        }
        log.info { "Adding telemetry $telemetry" }
        telemetryRepository.save(telemetryMapper.mapToEntity(telemetry))
        return ResponseEntity.ok().build()
    }

    override fun getTelemetryByDeviceId(deviceId: Int): ResponseEntity<Telemetry> {
        val telemetry = telemetryRepository.findTopByDeviceIdOrderByTelemetryDateDesc(deviceId)
        if (telemetry == null) {
            return ResponseEntity.status(404).build()
        } else {
            return ResponseEntity.ok(
                telemetryMapper.mapToDto(
                    telemetry
                )
            )
        }
    }
}
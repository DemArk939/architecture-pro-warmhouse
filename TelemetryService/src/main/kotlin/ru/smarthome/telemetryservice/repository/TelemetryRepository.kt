package ru.smarthome.telemetryservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.smarthome.telemetryservice.model.entity.TelemetryEntity

interface TelemetryRepository : JpaRepository<TelemetryEntity, Int> {

    fun findTopByDeviceIdOrderByTelemetryDateDesc(deviceId: Int): TelemetryEntity?
}
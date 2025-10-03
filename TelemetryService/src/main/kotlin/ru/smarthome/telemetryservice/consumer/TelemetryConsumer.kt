package ru.smarthome.telemetryservice.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import ru.smarthome.model.Telemetry
import ru.smarthome.telemetryservice.mapper.TelemetryMapper
import ru.smarthome.telemetryservice.model.entity.TelemetryEntity
import ru.smarthome.telemetryservice.repository.TelemetryRepository

@Service
class TelemetryConsumer(
    private val telemetryRepository: TelemetryRepository,
    private val telemetryMapper: TelemetryMapper,
    private val objectMapper: ObjectMapper
) {

//    private val log = KotlinLogging.logger {}

//    @KafkaListener(topics = ["telemetry_topic"], groupId = "telemetry-service-group")
    fun consume(message: String?) {
        try {
            val telemetry: Telemetry? = objectMapper.readValue(message, Telemetry::class.java)
            if(telemetry != null) {
                telemetryRepository.save<TelemetryEntity?>(telemetryMapper.mapToEntity(telemetry))
            }
        } catch (e: Exception) {
        }
    }

}
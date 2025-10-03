package ru.smarthome.telemetryservice.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import ru.smarthome.model.Telemetry
import ru.smarthome.telemetryservice.model.entity.TelemetryEntity

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface TelemetryMapper {

    @Mapping(target = "id", ignore = true)
    fun mapToEntity(telemetry: Telemetry): TelemetryEntity
    fun mapToDto(entity: TelemetryEntity): Telemetry

}
package ru.smarthome.telemetryservice.model.entity

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class ParametersConverter : AttributeConverter<Map<String, String>, String> {

    override fun convertToDatabaseColumn(parameters: Map<String, String>?): String? {
        if(parameters == null){
            return null;
        }
        return jacksonObjectMapper().writeValueAsString(parameters)
    }

    override fun convertToEntityAttribute(json: String?): Map<String, String> {
        if(json == null) {
            return emptyMap()
        }
        return jacksonObjectMapper().readValue(json)
    }
}
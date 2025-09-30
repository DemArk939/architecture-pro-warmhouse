package ru.smarthome.deviceservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import ru.smarthome.deviceservice.mapper.DeviceMapper;
import ru.smarthome.deviceservice.reposetory.DeviceRepository;
import ru.smarthome.model.Device;

@Slf4j
@Server
@RequiredArgsConstructor
public class DeviceConsumer {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = {"device_topic"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        log.info("Add device: {}", message);
        try{
            Device device = objectMapper.readValue(message, Device.class);
            deviceRepository.save(deviceMapper.mapToEntity(device));
        }catch (Exception e){
            log.error("Add device error", e);
        }
    }
}

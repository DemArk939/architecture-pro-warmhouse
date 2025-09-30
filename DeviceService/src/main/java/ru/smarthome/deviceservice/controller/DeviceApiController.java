package ru.smarthome.deviceservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.smarthome.api.DeviceApi;
import ru.smarthome.deviceservice.mapper.DeviceMapper;
import ru.smarthome.deviceservice.model.entity.DeviceEntity;
import ru.smarthome.deviceservice.reposetory.DeviceRepository;
import ru.smarthome.model.Device;
import ru.smarthome.model.ModelApiResponse;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class DeviceApiController implements DeviceApi {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public ResponseEntity<ModelApiResponse> addDevice(Device device) {
        deviceRepository.save(deviceMapper.mapToEntity(device));
        ModelApiResponse response = new ModelApiResponse();
        response.setStatus(ModelApiResponse.StatusEnum.SUCCESS);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteDevice(Integer deviceId) {
        deviceRepository.deleteById(deviceId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<ModelApiResponse> devicePut(Device device) {
        ModelApiResponse response = new ModelApiResponse();
        response.setStatus(ModelApiResponse.StatusEnum.SUCCESS);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Device> getDeviceById(Integer deviceId) {
        Optional<DeviceEntity> device = deviceRepository.findById(deviceId);
        return device.map(deviceEntity -> ResponseEntity.ok(
                deviceMapper.mapToDto(
                        deviceEntity
                )
        )).orElseGet(() -> ResponseEntity.status(404).build());
    }

    @Override
    public ResponseEntity<List<Device>> getDeviceByUserId(Integer userId) {
        return ResponseEntity.ok(List.of());
    }
}

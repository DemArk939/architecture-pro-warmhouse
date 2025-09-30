package ru.smarthome.deviceservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.smarthome.deviceservice.model.entity.DeviceEntity;
import ru.smarthome.model.Device;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    @Mapping(target = "id", ignore = true)
    DeviceEntity mapToEntity(Device device);

    Device mapToDto(DeviceEntity device);
}

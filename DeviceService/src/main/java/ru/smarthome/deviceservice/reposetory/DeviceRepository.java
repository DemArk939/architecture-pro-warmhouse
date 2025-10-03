package ru.smarthome.deviceservice.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smarthome.deviceservice.model.entity.DeviceEntity;

import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {

}

package ru.smarthome.deviceservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "devices")
public class DeviceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "device_type", nullable = false)
    private String deviceType;

    @Column(name = "house_id")
    private Integer houseId;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "url")
    private String url;

}

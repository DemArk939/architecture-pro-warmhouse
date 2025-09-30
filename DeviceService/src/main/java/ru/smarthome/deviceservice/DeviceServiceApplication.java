package ru.smarthome.deviceservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class DeviceServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DeviceServiceApplication.class, args);
        Environment env = ctx.getEnvironment();
        log.info("Database URL: {}", env.getProperty("spring.datasource.url"));
    }

}

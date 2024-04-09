package ru.otus.hw.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class UpdateHealthIndicator implements HealthIndicator {

    private final LocalTime time = LocalTime.now();

    @Override
    public Health health() {
        var hour = time.getHour();
        if (hour == 23) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Software is being updated")
                    .build();
        } else {
            return Health.up()
                    .status(Status.UP)
                    .withDetail("message", "Book catalog is available")
                    .build();
        }
    }
}

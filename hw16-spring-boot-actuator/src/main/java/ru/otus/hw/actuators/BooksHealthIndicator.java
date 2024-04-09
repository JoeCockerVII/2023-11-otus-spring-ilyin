package ru.otus.hw.actuators;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.hw.repositories.BookRepository;

@RequiredArgsConstructor
@Component
public class BooksHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    @Override
    public Health health() {
        int count = bookRepository.findAll().size();
        if (count > 0) {
            return Health.up()
                    .status(Status.UP)
                    .withDetail("message", "Bookshelf is not empty")
                    .build();
        } else {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Bookshelf is empty!")
                    .build();
        }
    }
}

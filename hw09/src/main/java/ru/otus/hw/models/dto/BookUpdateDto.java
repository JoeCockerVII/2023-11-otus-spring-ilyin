package ru.otus.hw.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Genre;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateDto {

    @PositiveOrZero
    private long id;

    @NotBlank(message = "Title should not be blank")
    @Size(min = 2, max = 15, message = "Title should be between 2 and 15")
    private String title;

    @NotNull
    private Author author;

    @NotNull
    private Genre genre;
}

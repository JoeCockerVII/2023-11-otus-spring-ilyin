package ru.otus.hw.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateDto {

    @NotBlank(message = "Title should not be blank")
    @Size(min = 2, max = 15, message = "Title should be between 2 and 15")
    private String title;

    @NotNull
    private Long authorId;

    @NotNull
    private Long genreId;

}

package ru.otus.hw.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String id;

    private String title;

    @NotNull
    private String authorId;

    @NotNull
    private String genreId;

}
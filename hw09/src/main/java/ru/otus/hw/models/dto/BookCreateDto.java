package ru.otus.hw.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw.models.Book;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCreateDto {

    @NotBlank(message = "Title should not be blank")
    @Size(min = 2, max = 15, message = "Title should be between 2 and 15")
    private String title;

    @NotNull
    private Long authorId;

    @NotNull
    private Long genreId;

    public static BookCreateDto toDto(Book dto) {
        return BookCreateDto.builder()
                .title(dto.getTitle())
                .authorId(dto.getAuthor().getId())
                .genreId(dto.getGenre().getId())
                .build();
    }

}

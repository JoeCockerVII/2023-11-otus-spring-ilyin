package ru.otus.hw.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw.models.Book;

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
    private Long authorId;

    @NotNull
    private Long genreId;

    public static BookUpdateDto toDto(Book dto) {
        return BookUpdateDto.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .authorId(dto.getAuthor().getId())
                .genreId(dto.getGenre().getId())
                .build();
    }

}

package ru.otus.hw.models.dto;

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

    private long id;

    private String title;

    private long authorId;

    private long genreId;

    public static BookUpdateDto toDto(Book dto) {
        return BookUpdateDto.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .authorId(dto.getAuthor().getId())
                .genreId(dto.getGenre().getId())
                .build();
    }

}

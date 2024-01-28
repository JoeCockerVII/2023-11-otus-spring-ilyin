package ru.otus.hw.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "genres")
public class Genre {
    @Id
    @Field(name = "genre_id")
    private Long id;

    private String name;

    public Genre(String name) {
        this.name = name;
    }
}

package ru.otus.hw.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class Author {
    @Id
    private String id;

    private String fullName;

    public Author(String fullName) {
        this.fullName = fullName;
    }
}

package ru.otus.hw.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import ru.otus.hw.models.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
public class JpaBookRepository implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Book> findById(long id) {
        var entityGraph = em.getEntityGraph("author-genre-entity-graph");
        return Optional.ofNullable(em.find(Book.class, id, Map.of(FETCH.getKey(), entityGraph)));
    }

    @Override
    public List<Book> findAll() {
        var entityGraph = em.getEntityGraph("author-genre-entity-graph");
        var query = em.createQuery("select b FROM Book b", Book.class);
        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    @Override
    public Book saveOrUpdate(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public void deleteById(long id) {
        var book = findById(id);
        book.ifPresent(em::remove);
    }
}
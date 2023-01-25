package com.kasenov.libpro.simplelibrary.repository;

import com.kasenov.libpro.simplelibrary.dto.projection.BooksAreOver;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.BookEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends CommonRepository<BookEntity> {
    Optional<BookEntity> findByIdAndWarehouseEntity_QuantityGreaterThan(long id, int quantity);

    @Query(value = """
            SELECT b.id, b.title, a.f_name AS firstName, a.l_name AS lastName
            from books AS b
            JOIN books_authors AS ba ON (b.id = ba.book_id)
            JOIN authors AS a ON (ba.author_id = a.id)
            JOIN warehouse AS w ON (b.id = w.book_id)
            WHERE w.quantity = 0 AND w.at_clients = 0""",
            nativeQuery = true)
    List<BooksAreOver> findWhereQuantityAndAtClientIsZero();
}

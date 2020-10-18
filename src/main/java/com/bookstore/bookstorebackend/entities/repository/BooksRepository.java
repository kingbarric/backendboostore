package com.bookstore.bookstorebackend.entities.repository;

import com.bookstore.bookstorebackend.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Integer> {
    public Books findByIsbn(String isbn);
}

package com.kasenov.libpro.simplelibrary.Repository;

import com.kasenov.libpro.simplelibrary.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}

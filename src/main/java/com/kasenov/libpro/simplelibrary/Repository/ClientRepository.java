package com.kasenov.libpro.simplelibrary.Repository;

import com.kasenov.libpro.simplelibrary.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

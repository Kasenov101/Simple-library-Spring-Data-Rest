package com.kasenov.libpro.simplelibrary.Repository;

import com.kasenov.libpro.simplelibrary.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Transactional
    @Modifying
    @Query("""
            update Client c set c.firstName = ?1, c.lastName = ?2,
            c.passportId = ?3, c.phoneNum = ?4, c.address = ?5 where c.id = ?6""")
    void updateClient(String firstName, String lastName,
                             String passportId, String phoneNum, String address, long id);
}

package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Client;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface ClientService {

    List<Client> getAllClients() throws NotFoundException;

    Client getClientById(int id) throws NotFoundException;

    ResponseEntity<Objects> saveClient(Client client) throws CannotSaveException;

    ResponseEntity<Objects> updateClient(Client client) throws CannotSaveException, NotFoundException;

    ResponseEntity<Objects> removeClient(int id) throws NotFoundException;

}

package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Client;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface ClientService {

    List<Client> getAllClients() throws NotFoundException;

    Client getClientById(long id) throws NotFoundException;

    ResponseEntity<Objects> saveOrUpdateClient(Client client) throws CannotSaveException;

    ResponseEntity<Objects> removeClient(long id) throws NotFoundException, CannotRemoveException;

}

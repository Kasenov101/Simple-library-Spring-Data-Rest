package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Client;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.Repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    // now check has been made
    @Override
    public List<Client> getAllClients() throws  NotFoundException {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()) throw new NotFoundException("Client list is empty");
        return clients;
    }

    @Override
    public Client getClientById(long id) throws NotFoundException {
        return clientRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Cannot find client by id:",id)));
    }

    @Override
    public ResponseEntity<Objects> saveClient(Client client) throws CannotSaveException {
        try {
            clientRepository.saveAndFlush(client);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new CannotSaveException(e.getMessage());
        }
    }
    @Override
    public ResponseEntity<Objects> updateClient(Client client)
            throws CannotSaveException, NotFoundException {
        if (clientRepository.findById(client.getId()).isEmpty()) throw
        new NotFoundException(String.format("client with id: %s not found", client.getId()));

        try {
            clientRepository.updateClient(client.getFirstName(),client.getLastName(),
                    client.getPassportId(), client.getPhoneNum(), client.getAddress(),
                    client.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new CannotSaveException(e.getMessage());
        }

    }

    public ResponseEntity<Objects> removeClient(long id) throws NotFoundException, CannotRemoveException {
        if (clientRepository.findById(id).isEmpty()) throw
        new NotFoundException(String.format("Client with id: %s not found", id));
        try {
            clientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new CannotRemoveException(
                    String.format("client with id: %s not found", id));
        }
    }
}

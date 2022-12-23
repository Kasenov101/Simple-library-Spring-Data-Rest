package com.kasenov.libpro.simplelibrary.Controller;


import com.kasenov.libpro.simplelibrary.Entity.Client;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.Service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() throws NotFoundException {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable long id) throws NotFoundException {
        return clientService.getClientById(id);
    }

    @PostMapping
    public void saveClients(@RequestBody Client client) throws CannotSaveException {
        clientService.saveClient(client);
    }

    @PatchMapping
    public void updateClient(@RequestBody Client client)
            throws CannotSaveException, NotFoundException {
        clientService.updateClient(client);
    }

    @DeleteMapping("/{id}")
    public void removeClient(@PathVariable("id") long id) throws NotFoundException, CannotRemoveException {
        clientService.removeClient(id);
    }
}

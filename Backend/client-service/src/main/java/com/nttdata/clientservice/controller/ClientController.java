package com.nttdata.clientservice.controller;

import com.nttdata.clientservice.model.Client;
import com.nttdata.clientservice.model.IdentityDocumentId;
import com.nttdata.clientservice.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    @Autowired
    ClientService service;

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    @PostMapping("/get-client")
    public Client getClientByIdentityDocument (@Valid @RequestBody IdentityDocumentId id) {
        try {
            logger.info(String.format("Se recibió petición con los siguientes valores. Tipo de documento:%s" +
                    ", número de documento: %s", id.getDocumentType(), id.getDocumentNumber()));

            return service.getClientByIdentityDocument(id);
        } catch (Exception e) {
            logger.error(String.format("Ocurrió el siguiente error!: %s", e.getMessage()));
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

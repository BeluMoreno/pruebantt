package com.nttdata.clientservice.service;

import com.nttdata.clientservice.model.Client;
import com.nttdata.clientservice.model.IdentityDocumentId;
import com.nttdata.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client getClientByIdentityDocument(IdentityDocumentId id) throws Exception {
        if(id.getDocumentNumber().equals("123456789")){
            throw new Exception("Número inválido");
        }
        return clientRepository.findByIdentityDocuments_id(id);
    }
}


package com.nttdata.clientservice.service;

import com.nttdata.clientservice.model.Client;
import com.nttdata.clientservice.model.IdentityDocumentId;

public interface ClientService {

    Client getClientByIdentityDocument (IdentityDocumentId id) throws Exception;
}

package com.nttdata.clientservice.config;

import com.nttdata.clientservice.model.Client;
import com.nttdata.clientservice.model.IdentityDocument;
import com.nttdata.clientservice.model.IdentityDocumentId;
import com.nttdata.clientservice.repository.ClientRepository;
import com.nttdata.clientservice.repository.IdentityDocumentRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseLoader implements ApplicationRunner {

    ClientRepository clientRepository;
    IdentityDocumentRepository idRepository;

    public DatabaseLoader(ClientRepository clientRepository, IdentityDocumentRepository idRepository) {
        this.clientRepository = clientRepository;
        this.idRepository = idRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Client client = new Client("Lucero", "Belem", "Moreno",
                "Guzmán", "5534247193","5 de Mayo", "Tlalnepantla");
        clientRepository.save(client);
        IdentityDocument identityDocument = new IdentityDocument();
        identityDocument.setClient(client);

        identityDocument.setId( new IdentityDocumentId("C", "23445322"));
        idRepository.save(identityDocument);

        client.setIdentityDocuments(List.of(identityDocument));
        clientRepository.save(client);
    }
}

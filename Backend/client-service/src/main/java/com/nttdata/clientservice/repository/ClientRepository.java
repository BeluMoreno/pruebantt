package com.nttdata.clientservice.repository;

import com.nttdata.clientservice.model.Client;
import com.nttdata.clientservice.model.IdentityDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{

    Client findByIdentityDocuments_id (IdentityDocumentId id);

}

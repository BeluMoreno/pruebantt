package com.nttdata.clientservice.repository;

import com.nttdata.clientservice.model.Client;
import com.nttdata.clientservice.model.IdentityDocument;
import com.nttdata.clientservice.model.IdentityDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityDocumentRepository extends JpaRepository<IdentityDocument, IdentityDocumentId> {



}

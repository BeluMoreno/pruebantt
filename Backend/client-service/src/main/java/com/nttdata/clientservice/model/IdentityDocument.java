package com.nttdata.clientservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class IdentityDocument {

    @NotNull
    @EmbeddedId
    private IdentityDocumentId id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public IdentityDocumentId getId() {
        return id;
    }

    public void setId(IdentityDocumentId id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

package com.nttdata.clientservice.model;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IdentityDocumentId implements Serializable {
    @NotEmpty
    private String documentType;
    @NotEmpty
    private String documentNumber;

    public IdentityDocumentId (){}

    public IdentityDocumentId (String documentType, String documentNumber){
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentityDocumentId that = (IdentityDocumentId) o;
        return documentType.equals(that.documentType) && documentNumber.equals(that.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentType, documentNumber);
    }
}

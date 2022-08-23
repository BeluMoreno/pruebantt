package com.nttdata.clientservice.service;

import com.nttdata.clientservice.model.Client;
import com.nttdata.clientservice.model.IdentityDocumentId;
import com.nttdata.clientservice.repository.ClientRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ClientServiceTest {

    @Mock
    ClientRepository repository;

    @InjectMocks
    ClientServiceImpl service;

    @Test
    public void getClient() throws Exception {
        IdentityDocumentId id = new IdentityDocumentId();
        id.setDocumentNumber("987");
        id.setDocumentType("C");

        Client client = new Client();
        client.setId(1L);
        client.setFirstName("test name");
        client.setFatherLastName("test lastname");

        Mockito.when(repository.findByIdentityDocuments_id(id)).thenReturn(client);

        var response = service.getClientByIdentityDocument(id);

        verify(repository, times(1)).findByIdentityDocuments_id(any(IdentityDocumentId.class));
        Assert.assertEquals(client, response);
    }

    @Test
    public void getClientNoMatch() throws Exception {
        IdentityDocumentId id = new IdentityDocumentId();
        id.setDocumentNumber("987");
        id.setDocumentType("C");

        var response = service.getClientByIdentityDocument(id);

        verify(repository, times(1)).findByIdentityDocuments_id(any(IdentityDocumentId.class));
        Assert.assertNull(response);
    }

    @Test(expected = NullPointerException.class)
    public void getClientDBError() throws Exception {
        service = new ClientServiceImpl();
        IdentityDocumentId id = new IdentityDocumentId();
        id.setDocumentNumber("987");
        id.setDocumentType("C");

        var response = service.getClientByIdentityDocument(id);

        verify(repository, times(1)).findByIdentityDocuments_id(any(IdentityDocumentId.class));
        Assert.assertNull(response);
    }

}

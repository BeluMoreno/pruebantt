package com.nttdata.clientservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.clientservice.model.Client;
import com.nttdata.clientservice.model.IdentityDocumentId;
import com.nttdata.clientservice.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ClientService service;

    @Test
    public void getClient() throws Exception {

        Client client = new Client("test", "test", "test",
                "test", "123456789", "test", "test");
        IdentityDocumentId id = new IdentityDocumentId("P", "12345");

        given(service.getClientByIdentityDocument(id)).willReturn(client);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/get-client")
                        .content(asJsonString(id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getClientMissingBodyError400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/get-client"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getClientMissingDocumentNumber() throws Exception {

        IdentityDocumentId id = new IdentityDocumentId("P", null);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/get-client")
                        .content(asJsonString(id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getClientMissingDocumentType() throws Exception {

        IdentityDocumentId id = new IdentityDocumentId(null, "12345");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/get-client")
                        .content(asJsonString(id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void notFoundURIError404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/wrong"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void serverError500() throws Exception {
        IdentityDocumentId id = new IdentityDocumentId("P", "123");

        given(service.getClientByIdentityDocument(id)).willThrow(new Exception());

        var response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/get-client")
                        .content(asJsonString(id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

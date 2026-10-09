package org.ecole.attestationscolaire.wallet.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void creerCrediterEtDebiterUnWallet() throws Exception {
        Long id = creerWallet();

        credit(id, "50.00");
        credit(id, "20.00");
        debit(id, "30.00");

        mockMvc.perform(get("/wallets/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.montant").value(140.0));
    }

    @Test
    void walletIntrouvableRetourne404() throws Exception {
        mockMvc.perform(get("/wallets/{id}", 9999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void debitSoldeInsuffisantRetourne409() throws Exception {
        Long id = creerWallet();

        mockMvc.perform(post("/wallets/{id}/debits", id)
                        .contentType(APPLICATION_JSON)
                        .content("{\"montant\":500.00,\"devise\":\"EUR\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void operationDeviseDifferenteRetourne422() throws Exception {
        Long id = creerWallet();

        mockMvc.perform(post("/wallets/{id}/credits", id)
                        .contentType(APPLICATION_JSON)
                        .content("{\"montant\":50.00,\"devise\":\"USD\"}"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void montantInvalideRetourne400() throws Exception {
        Long id = creerWallet();

        mockMvc.perform(post("/wallets/{id}/debits", id)
                        .contentType(APPLICATION_JSON)
                        .content("{\"montant\":-10.00,\"devise\":\"EUR\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    private Long creerWallet() throws Exception {
        MvcResult result = mockMvc.perform(post("/wallets")
                        .contentType(APPLICATION_JSON)
                        .content("{\"titulaire\":\"Awa Diop\",\"montant\":100.00,\"devise\":\"EUR\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn();
        return Long.valueOf(JsonPath.read(result.getResponse().getContentAsString(), "$.id").toString());
    }

    private void credit(Long id, String montant) throws Exception {
        mockMvc.perform(post("/wallets/{id}/credits", id)
                        .contentType(APPLICATION_JSON)
                        .content("{\"montant\":" + montant + ",\"devise\":\"EUR\"}"))
                .andExpect(status().isOk());
    }

    private void debit(Long id, String montant) throws Exception {
        mockMvc.perform(post("/wallets/{id}/debits", id)
                        .contentType(APPLICATION_JSON)
                        .content("{\"montant\":" + montant + ",\"devise\":\"EUR\"}"))
                .andExpect(status().isOk());
    }
}
package com.bank.service.boot.controller;

import com.bank.account.model.Account;
import com.bank.account.model.contract.Repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    Repository<Account, String> accountRepository;

    private Account accountFrom;
    private Account accountTo;

    @BeforeAll
    public void setup() {
        accountFrom = new Account("123456", 5000.0);
        accountTo = new Account("654321", 5000.0);
        when(accountRepository.get(argThat(account -> account != null && account.equals("123456")))).thenReturn(accountFrom);
        when(accountRepository.get(argThat(account -> account != null && account.equals("654321")))).thenReturn(accountTo);
    }

    @Test
    public void testTransferMoneySuccess() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/transfer/123456/654321/200")).andExpect(status().isOk()).andReturn();
        String receipt = result.getResponse().getContentAsString();
        assertEquals(6, receipt.length());
        verify(accountRepository, times(1)).get(argThat(account -> account != null && account.equals("123456")));
        verify(accountRepository, times(1)).get(argThat(account -> account != null && account.equals("654321")));
        verify(accountRepository, times(1)).add(accountFrom);
        verify(accountRepository, times(1)).add(accountTo);
    }

    @Test
    public void testTransferMoneyFailureInvalidValue() throws Exception {
        this.mockMvc.perform(post("/transfer/123456/654321/-10")).andExpect(status().isBadRequest());
    }

    @Test
    public void testTransferMoneyFailureExceedLimit() throws Exception {
        this.mockMvc.perform(post("/transfer/123456/654321/10000")).andExpect(status().isBadRequest());
    }
}
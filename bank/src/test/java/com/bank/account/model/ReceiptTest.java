package com.bank.account.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReceiptTest {
    @Test
    public void testReceiptSuccess(){
        Receipt receipt = new Receipt();
        assertEquals(6, receipt.getTransactionId().length());
    }

    @Test
    public void testReceiptSuccessTwoReceipts(){
        Receipt receipt = new Receipt();
        Receipt receiptNew = new Receipt();
        assertTrue(receipt.getTransactionId() != receiptNew.getTransactionId());
    }
}

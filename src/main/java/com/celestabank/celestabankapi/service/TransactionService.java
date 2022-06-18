package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.exeption.TransactionNotFoundException;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction( Transaction transaction)  throws Exception;

    Transaction viewTransaction(long transactionId) throws Exception;

    Transaction findTransactionById(long transactionId) throws TransactionNotFoundException, TransactionNotFoundException;

    List<Transaction> getAllMyAccTransactions(long accountId) throws Exception;
}

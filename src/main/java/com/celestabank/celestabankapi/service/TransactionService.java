package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.exeption.TransactionNotFoundException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface TransactionService {
    public Transaction createTransaction( Transaction transaction);

    public Transaction viewTransaction(long transactionId);

    public Transaction findTransactionById(long transactionId) throws TransactionNotFoundException, TransactionNotFoundException;

    public List<Transaction> getAllMyAccTransactions(long accountId) throws AccountNotFoundException;

    List<Transaction> getAccByNum(long accountId);

    List<Transaction> listAll();
}

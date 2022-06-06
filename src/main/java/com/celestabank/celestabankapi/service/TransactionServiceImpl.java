package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.exeption.TransactionNotFoundException;
import com.celestabank.celestabankapi.repository.AccountRepository;
import com.celestabank.celestabankapi.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        transactionRepository.saveAndFlush(transaction);
        return transaction;
    }

    @Override
    public Transaction viewTransaction(long transactionId) {
        Transaction t = transactionRepository.findById(transactionId).get();
        if (t !=null){
            return t;
        }
        return null;
    }

    @Override
    public Transaction findTransactionById(long transactionId) {
        Transaction t = transactionRepository.findById(transactionId).get();
        if(t !=null){
            return t;
        }else
        return null;
    }

    @Override
    public List<Transaction> getAllMyAccTransactions(long accountId) {

        return transactionRepository.getAllMyAccTransactions(accountId);
    }
}

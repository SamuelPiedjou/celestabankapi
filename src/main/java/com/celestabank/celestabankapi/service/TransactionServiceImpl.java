package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.exeption.BankAccountNotFoundException;
import com.celestabank.celestabankapi.exeption.TransactionNotFoundException;
import com.celestabank.celestabankapi.mappers.BankServiceMapper;
import com.celestabank.celestabankapi.repository.AccountRepository;
import com.celestabank.celestabankapi.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private final BankServiceMapper dtoMapper;
    private final AccountRepository dbb;

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
        Transaction t = transactionRepository.findById(transactionId).orElseThrow(()-> new TransactionNotFoundException("TRANSACTION NOT FOUND"));
        if (t != null) {
            return  t;
        }
        return null;
    }
/*1025838957*/
    @Override
    public List<Transaction> getAllMyAccTransactions(long accountId)  {
        Account account = dbb.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("COMPTE NON TROUVé"));
        if(account != null){
            List<Transaction> t =  transactionRepository.getAllMyAccTransactions(accountId);
            return  t;
        }
        return null;
    }

    @Override
    public List<Transaction> getAccByNum(long accountId)  {
        Account account = dbb.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("COMPTE NON TROUVé"));
        if(account != null){
            List<Transaction> t =  transactionRepository.findByAccount_AccountId(accountId);
            return  t;
        }
        return null;
    }

    @Override
    public List<Transaction> listAll(){
        return  transactionRepository.findAll();
    }
}

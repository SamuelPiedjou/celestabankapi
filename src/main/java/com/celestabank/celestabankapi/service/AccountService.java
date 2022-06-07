package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.exeption.BalanceNotSufficientException;
import com.celestabank.celestabankapi.exeption.BankAccountNotFoundException;
import com.celestabank.celestabankapi.exeption.CustomerNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsException;

import java.util.List;

public interface AccountService {



    CurrentAccount saveCurrentBankAccount(double initialBalance, long customerId)  ;

    SavingAccount saveSavingBankAccount(double initialBalance, long customerId)  ;

    boolean deleteSavingId(long accountId) throws  InvalidDetailsException;

    boolean deleteCurrentId(long accountId) throws InvalidDetailsException;

    Account getBankAccount(long accountId) throws BankAccountNotFoundException;

    SavingAccount updateSavingAccount(SavingAccount savingAccount);

    CurrentAccount  updateCurrentAccount(CurrentAccount currentAccount);

    double SoldeCompte(long accountId);

    boolean withdraw(double amount, long accountId, String remark) throws BalanceNotSufficientException, BankAccountNotFoundException, InvalidDetailsException;

    List<Account> viewAccounts(long accountId);
    List<Account> viewSavingAcc(long customerId);

    List<Account> viewCurrentAcc(long customerId);

    Transaction deposit(double amount, long accountId, String remark) throws BankAccountNotFoundException;

    boolean transfer(long senderAccountId, long reciverAccountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsException;
}

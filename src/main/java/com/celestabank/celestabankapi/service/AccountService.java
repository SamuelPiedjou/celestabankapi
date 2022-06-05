package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.exeption.BalanceNotSufficientException;
import com.celestabank.celestabankapi.exeption.BankAccountNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsException;

import java.util.List;

public interface AccountService {
    public List<Account> addAccount(Account account);
    public List<SavingAccount> addSavingAccount(SavingAccount savingAccount);

    public List<CurrentAccount> addCurrentAccount(CurrentAccount currentAccount);

    public boolean deleteSavingId(long accountId) throws BankAccountNotFoundException, InvalidDetailsException;

    public boolean deleteCurrentId(long accountId) throws InvalidDetailsException;

    public Account getAccountById(long accountId) throws BankAccountNotFoundException;

    public List<SavingAccount> updateSavingAccount(SavingAccount savingAccount);

    public List<CurrentAccount> updateCurrentAccount(CurrentAccount currentAccount);

    public Transaction withdraw(double amount, long accountId, long customerId, String password) throws BankAccountNotFoundException, BalanceNotSufficientException;



    List<Account> viewAccounts(long accountId);

    public List<Account> viewSavingAcc(long customerId);

    public List<Account> viewCurrentAcc(long customerId);

    public Transaction deposit(double amount, long accountId) throws BankAccountNotFoundException;
    public Transaction transfer(long senderAccountId, long reciverAccountId, double amount, long customerId, String password) throws BankAccountNotFoundException, BalanceNotSufficientException;
}

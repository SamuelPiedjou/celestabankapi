package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.enums.AccountStatus;
import com.celestabank.celestabankapi.exeption.*;

import java.util.List;

public interface AccountService {

    CurrentAccount saveCurrentBankAccount(double initialBalance, long customerId) throws CustomerAlreadyHaveAnAccountException, CustomerNotFoundException;

    SavingAccount saveSavingBankAccount(double initialBalance, long customerId) throws CustomerAlreadyHaveAnAccountException, CustomerNotFoundException;

    boolean deleteSavingId(long accountId) throws  InvalidDetailsException;

    boolean deleteCurrentId(long accountId) throws InvalidDetailsException;

    Account getBankAccount(long accountId) throws BankAccountNotFoundException;

    SavingAccount updateSavingAccount(SavingAccount savingAccount);

    CurrentAccount  updateCurrentAccount(CurrentAccount currentAccount);

    double SoldeCompte(long accountId) throws BankAccountNotFoundException;

    AccountStatus activateAccount(long accountId) throws BankAccountNotFoundException;

    AccountStatus suspendAccount(long accountId) throws BankAccountNotFoundException;

    Transaction deposit(long accountId, double amount, String remark) throws BankAccountNotFoundException;

    Transaction withdraw(double amount, long accountId, String remark) throws BalanceNotSufficientException, BankAccountNotFoundException, InvalidDetailsException;

    List<Account> viewAccounts(long accountId);
    Account viewSavingAcc(long customerId);

    Account viewCurrentAcc(long customerId);

    boolean transfer(long senderAccountId, long reciverAccountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsException;
}

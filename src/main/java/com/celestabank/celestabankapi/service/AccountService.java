package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.dto.AccountDto;
import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.exeption.*;

import java.util.List;

public interface AccountService {

    //    public Account CheckTypeAccount(long customerId){
    //        Customer customer = customerRepository.findById(customerId).orElse(null);
    //        customer.getAccount();
    //
    //    }
    SavingAccount savingAccount(double initialBaln, long idMachine);

    AccountDto saveCurrentBankAccount(double initialBalance, long customerId) throws CustomerAlreadyHaveAnAccountException, CustomerNotFoundException;

    AccountDto saveSavingBankAccount(double initialBalance, long customerId) throws CustomerAlreadyHaveAnAccountException, CustomerNotFoundException;

    boolean deleteSavingId(long accountId) throws  InvalidDetailsException;

    boolean deleteCurrentId(long accountId) throws InvalidDetailsException;

    Account getBankAccount(long accountId) throws BankAccountNotFoundException;

    SavingAccount updateSavingAccount(SavingAccount savingAccount);

    CurrentAccount  updateCurrentAccount(CurrentAccount currentAccount);

    double SoldeCompte(long accountId) throws BankAccountNotFoundException;

    AccountDto activateAccount(long accountId) throws BankAccountNotFoundException;

    AccountDto suspendAccount(long accountId) throws BankAccountNotFoundException;

    Transaction deposit(long accountId, double amount, String remark) throws BankAccountNotFoundException;

    Transaction withdraw(double amount, long accountId, String remark) throws BalanceNotSufficientException, BankAccountNotFoundException, InvalidDetailsException, BankAccountNotActivatedException, BankAccountSuspendedException;

    List<AccountDto> listAccounts();

    AccountDto viewSavingAcc(long accountId);

    AccountDto viewCurrentAcc(long customerId);

    boolean transfer(long senderAccountId, long reciverAccountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsException, BankAccountNotActivatedException, BankAccountSuspendedException;
}

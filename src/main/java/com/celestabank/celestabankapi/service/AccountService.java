package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.dto.AccountDto;
import com.celestabank.celestabankapi.dto.TransactionDTO;
import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
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



    boolean deleteAcc(long accountId);

    Account getBankAccount(long accountId) throws BankAccountNotFoundException;

    SavingAccount updateSavingAccount(SavingAccount savingAccount);

    CurrentAccount  updateCurrentAccount(CurrentAccount currentAccount);

    double SoldeCompte(long accountId) throws BankAccountNotFoundException;

    AccountDto activateAccount(long accountId) throws BankAccountNotFoundException;

    AccountDto suspendAccount(long accountId) throws BankAccountNotFoundException;

    AccountDto viewAcc(long accountId);

    TransactionDTO deposit(long accountId, double amount, String remark) throws BankAccountNotFoundException;

    TransactionDTO withdraw(double amount, long accountId, String remark) throws BalanceNotSufficientException, BankAccountNotFoundException, InvalidDetailsOperation, BankAccountNotActivatedException, BankAccountSuspendedException;

    List<AccountDto> listAccounts();

    AccountDto viewSavingAcc(long accountId);

    AccountDto viewCurrentAcc(long customerId);

    List<TransactionDTO> transfer(long senderAccountId, long reciverAccountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsOperation, BankAccountNotActivatedException, BankAccountSuspendedException;
}

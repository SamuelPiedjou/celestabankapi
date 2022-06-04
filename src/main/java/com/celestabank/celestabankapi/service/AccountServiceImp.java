package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.enums.TransactionStatus;
import com.celestabank.celestabankapi.enums.TransactionType;
import com.celestabank.celestabankapi.exeption.BalanceNotSufficientException;
import com.celestabank.celestabankapi.exeption.BankAccountNotFoundException;
import com.celestabank.celestabankapi.repository.AccountRepository;
import com.celestabank.celestabankapi.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImp implements AccountService {

    private AccountRepository accountRepository;
    private TransactionServiceImpl transactionService;

    @Override
    public List<Account> addAccount(Account account) {
        return null;
    }

    @Override
    public List<SavingAccount> addSavingAccount(SavingAccount savingAccount) {
        SavingAccount savingAccounts =  accountRepository.saveAndFlush(savingAccount);
        return (List<SavingAccount>) savingAccounts;
    }

    @Override
    public List<CurrentAccount> addCurrentAccount(CurrentAccount currentAccount) {
        CurrentAccount  currentAccounts=accountRepository.saveAndFlush(currentAccount);
        return (List<CurrentAccount>) currentAccounts;
    }

    @Override
    public boolean deleteSavingId(long accountId) throws BankAccountNotFoundException {
        if (accountId !=0){
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCurrentId(long accountId) {
        if (accountId !=0){
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }

    @Override
    public Account getAccountById(long accountId) throws BankAccountNotFoundException {
        Account account= accountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("Account not Found"));
        return account;
    }

    @Override
    public List<SavingAccount> updateSavingAccount(SavingAccount savingAccount) {
        return null;
    }

    @Override
    public List<CurrentAccount> updateCurrentAccount(CurrentAccount currentAccount) {
        return null;
    }

    @Override
    public Transaction withdraw(double amount, long accountId, long customerId, String password) throws BalanceNotSufficientException, BankAccountNotFoundException {
        Account a = getAccountById(accountId);
        Transaction t = new Transaction();
        if (amount> a.getBalance()){
            t.setAmount(a.getBalance());
            t.setDateTime(LocalDateTime.now());
            t.setTransactionType(TransactionType.DEBIT);
            t.setTransactionRemarks("PAS ASSEZ DE FONDS");
            t.setTransactionStatus(TransactionStatus.FAILED);
            transactionService.createTransaction(t);
            new BalanceNotSufficientException("PAS ASSEZ DE FOND");
            return null;
        }else{
            double balance = a.getBalance() - amount;
            a.setBalance(balance);
            accountRepository.save(a);
            t.setAmount(amount);
            t.setDateTime(LocalDateTime.now());
            t.setTransactionType(TransactionType.DEBIT);
            t.setTransactionRemarks("CASH_OUT OF "+amount+" "+"IS SUCCESSFUL");
            t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
            t.setAccount(accountRepository.findById(accountId).get());
            transactionService.createTransaction(t);
            return transactionService.findTransactionById(t.getTransactionId());
        }
    }

    @Override
    public List<Account> viewAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> viewSavingAcc(long customerId) {
        return accountRepository.viewSavingAcc(customerId);
    }

    @Override
    public List<Account> viewCurrentAcc(long customerId) {
        return accountRepository.viewTermAcc(customerId);
    }

    @Override
    public Transaction deposit(double amount, long accountId) throws BankAccountNotFoundException {
        Account a = getAccountById(accountId);
        double balance = a.getBalance() + amount;
        a.setBalance(balance);
        accountRepository.save(a);
        Transaction t = new Transaction();
        t.setAmount(amount);
        t.setDateTime(LocalDateTime.now());
        t.setTransactionType(TransactionType.CREDIT);
        t.setTransactionRemarks("CASH IN OF "+amount+" "+" SUCCESSFUL");
        t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        t.setAccount(accountRepository.findById(accountId).get());
        transactionService.createTransaction(t);
        return  transactionService.findTransactionById(t.getTransactionId());
    }

    @Override
    public Transaction transfer(long senderAccountId, long reciverAccountId, double amount, long customerId, String password) throws BankAccountNotFoundException, BalanceNotSufficientException {

        Account a = getAccountById(senderAccountId);
        Account b = getAccountById(reciverAccountId);
        senderAccountId =a.getAccountId();
        reciverAccountId =a.getAccountId();
        Transaction sender = new Transaction();
        Transaction reciver = new Transaction();
        if (amount>a.getBalance()){
            sender.setAmount(a.getBalance());
            sender.setDateTime(LocalDateTime.now());
            sender.setTransactionType(TransactionType.DEBIT);
            sender.setTransactionRemarks("PAS ASSEZ DE FOND");
            sender.setTransactionStatus(TransactionStatus.FAILED);
            transactionService.createTransaction(sender);
            return  null;
        }else{
            double balance = a.getBalance()-amount;
            a.setBalance(balance);
            accountRepository.save(a);
            sender.setAmount(amount);
            sender.setDateTime(LocalDateTime.now());
            sender.setTransactionType(TransactionType.DEBIT);
            sender.setTransactionRemarks("CASH_OUT DE" + amount + " XAF" + "IS SUCCESSFUL");
            sender.setTransactionStatus(TransactionStatus.SUCCESSFUL);
            sender.setAccount(accountRepository.findById(a.getAccountId()).get());
            transactionService.createTransaction(sender);

//			reciever
            double balanceReciver = b.getBalance() + amount;
            b.setBalance(balanceReciver);
            accountRepository.save(b);
            reciver.setAmount(amount);
            reciver.setDateTime(LocalDateTime.now());
            reciver.setTransactionType(TransactionType.CREDIT);
            reciver.setTransactionRemarks("CASH IN OF" + amount + "  XAF " + "IS SUCCESSFUL");
            reciver.setTransactionStatus(TransactionStatus.SUCCESSFUL);
            reciver.setAccount(accountRepository.findById(b.getAccountId()).get());
            transactionService.createTransaction(reciver);

            return transactionService.findTransactionById(reciver.getTransactionId());
        }

    }
}

package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.*;
import com.celestabank.celestabankapi.enums.AccountStatus;
import com.celestabank.celestabankapi.enums.AccountType;
import com.celestabank.celestabankapi.enums.TransactionStatus;
import com.celestabank.celestabankapi.enums.TransactionType;
import com.celestabank.celestabankapi.exeption.BalanceNotSufficientException;
import com.celestabank.celestabankapi.exeption.BankAccountNotFoundException;
import com.celestabank.celestabankapi.exeption.CustomerNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsException;
import com.celestabank.celestabankapi.repository.AccountRepository;
import com.celestabank.celestabankapi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImp implements AccountService {

    private AccountRepository accountRepository;
    private TransactionServiceImpl transactionService;
    private CustomerRepository customerRepository;

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, long customerId) throws CustomerNotFoundException {
        log.info("CREATING ACCOUNT OF CUSTOMER "+ customerId +" IN PROCESS");
        Customer customer= customerRepository.findById(customerId).orElse(null);
        if(customer==null)
            throw new CustomerNotFoundException("Customer not found");
        CurrentAccount currentAccount=new CurrentAccount();
        currentAccount.setAccountId((long) (Math.random()*(9999999- 1)+0000001));
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft( currentAccount.getOverDraft());
        currentAccount.setCustomer(customer);
        currentAccount.setAccountStatus(AccountStatus.CREATED);
        currentAccount.setAccountType(AccountType.CURRENT);
        CurrentAccount savedBankAccount = accountRepository.saveAndFlush(currentAccount);
        log.info("CREATING ACCOUNT SUCCESSFUL ! YOUR ACCOUNT N° is "+ currentAccount.getAccountId() +" and your balance id "+currentAccount.getBalance());
        return savedBankAccount;

    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, long customerId) throws CustomerNotFoundException {
        log.info("CREATING ACCOUNT OF CUSTOMER "+ customerId +" IN PROCESS");
        Customer customer= customerRepository.findById(customerId).orElse(null);
        if(customer==null)
            throw new CustomerNotFoundException("Customer not found");
        SavingAccount savingAccount=new SavingAccount();
        savingAccount.setAccountId((long) (Math.random()*(9999999- 1)+0000001));
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setMinBalance(savingAccount.getMinBalance());
        savingAccount.setAccountStatus(AccountStatus.CREATED);
        savingAccount.setCustomer(customer);
        savingAccount.setAccountType(AccountType.SAVING);
        SavingAccount savedBankAccount = accountRepository.saveAndFlush(savingAccount);
        return savedBankAccount;
    }



    @Override
    public boolean deleteSavingId(long accountId) throws  InvalidDetailsException {
        if (accountId !=0){
            SavingAccount savingAccount = (SavingAccount) accountRepository.findById(accountId).orElse(null);
            if (savingAccount.getBalance()>savingAccount.getMinBalance()){
                throw new InvalidDetailsException("IMPOSSIBLE DE SUPPIMER UN COMPTE ACTIF, VIDEZ LE COMPTE");
            }else{
                accountRepository.deleteById(accountId);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteCurrentId(long accountId) throws InvalidDetailsException {
        if (accountId !=0){
            CurrentAccount currentAccount  = (CurrentAccount) accountRepository.findById(accountId).orElse(null);
            if (currentAccount.getAccountStatus().equals(AccountStatus.ACTIVATED)){
                throw new InvalidDetailsException("THIS ACCOUNT IT'S AN ACTIF ACCOUNT, PLEASE DISABLE FIRST AND RETRY !");
            }else{
                accountRepository.deleteById(accountId);
                return true;
            }
        }
        return false;
    }
    @Override
    public Account getBankAccount(long accountId) throws BankAccountNotFoundException {
        Account bankAccount=accountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
        if(bankAccount instanceof SavingAccount){
            SavingAccount savingAccount= (SavingAccount) bankAccount;
            return savingAccount;
        } else {
            CurrentAccount currentAccount= (CurrentAccount) bankAccount;
            return currentAccount;
        }
    }

    @Override
    public SavingAccount updateSavingAccount(SavingAccount savingAccount) {
        accountRepository.saveAndFlush(savingAccount);
        return savingAccount;
    }

    @Override
    public CurrentAccount  updateCurrentAccount(CurrentAccount currentAccount) {
        accountRepository.saveAndFlush(currentAccount);
        return currentAccount;
    }

    @Override
    public boolean withdraw(double amount, long accountId, String remark) throws BalanceNotSufficientException, BankAccountNotFoundException, InvalidDetailsException {
        Account a = accountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("Bank Account not Found 404 !"));
        Transaction t = new Transaction();
        if (amount> a.getBalance()){
            t.setAmount(a.getBalance());
            t.setDateTime(LocalDateTime.now());
            t.setTransactionType(TransactionType.DEBIT);
            t.setTransactionRemarks(remark);
            t.setTransactionStatus(TransactionStatus.FAILED);
            transactionService.createTransaction(t);
            new BalanceNotSufficientException("BALANCE NOT SUFFICIENT");
            return false;
        }else if(a instanceof SavingAccount){
            if (a.getBalance()< ((SavingAccount) a).getMinBalance()) throw  new InvalidDetailsException("THIS ACCOUNT MUST HAVE ALLWAYS "+((SavingAccount) a).getMinBalance()+ "IN BALANCE");
        }
        else {
                double balance = a.getBalance() - amount;
                a.setBalance(balance);
                accountRepository.save(a);
                t.setAmount(amount);
                t.setDateTime(LocalDateTime.now());
                t.setTransactionType(TransactionType.DEBIT);
                t.setTransactionRemarks("CASH_OUT OF "+amount+" "+"IS SUCCESSFUL");
                t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                transactionService.createTransaction(t);
                return true;
            }
        return  false;
    }

    @Override
    public List<Account> viewAccounts(long accountId) {
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
    public Transaction deposit(double amount, long accountId,String remark) throws BankAccountNotFoundException {
        Account a = accountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("Bank account not found"));
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
    public boolean transfer(long senderAccountId, long reciverAccountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsException {
        withdraw(amount,senderAccountId,"transfer to "+reciverAccountId);
        deposit(amount,reciverAccountId,"transfer from "+senderAccountId);
       return true;
    }
}

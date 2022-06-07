package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.*;
import com.celestabank.celestabankapi.enums.AccountStatus;
import com.celestabank.celestabankapi.enums.AccountType;
import com.celestabank.celestabankapi.enums.TransactionStatus;
import com.celestabank.celestabankapi.enums.TransactionType;
import com.celestabank.celestabankapi.exeption.*;
import com.celestabank.celestabankapi.repository.AccountRepository;
import com.celestabank.celestabankapi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AccountServiceImp implements AccountService {

    private AccountRepository accountRepository;
    private TransactionServiceImpl transactionService;
    private CustomerRepository customerRepository;

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, long customerId)  {
        log.info("CREATING ACCOUNT OF CUSTOMER "+ customerId +" IN PROCESS");
        Customer customer= customerRepository.findById(customerId).orElse(null);
        if(customer != null ){
            if (customer.getAccount().isEmpty()){
                CurrentAccount currentAccount=new CurrentAccount();
                currentAccount.setAccountId((long) (Math.random()*(9999999- 1)+0000001));
                currentAccount.setCreatedAt(new Date());
                currentAccount.setBalance(initialBalance);
                currentAccount.setOverDraft( currentAccount.getOverDraft());
                currentAccount.setCustomer(customer);
                currentAccount.setAccountType(AccountType.CUR);
                currentAccount.setAccountStatus(AccountStatus.CREATED);
                CurrentAccount savedBankAccount = accountRepository.save(currentAccount);
                log.info("CREATING ACCOUNT SUCCESSFUL ! YOUR ACCOUNT N° is "+ currentAccount.getAccountId() +" and your balance id "+currentAccount.getBalance());
                return savedBankAccount;
            }else new CustomerAlreadyHaveAnAccountException("THE CUSTOMER \"+ customer+\" HAVE AN ACCOUNT !!!!");
        }else new  CustomerNotFoundException("NO CUSTOMER WITH THIS ACCOUNT ID EXIST");
        return null;
    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, long customerId)   {
        log.info("CREATING SAVING ACCOUNT OF CUSTOMER "+ customerId +" IN PROCESS");
        Customer customer= customerRepository.findById(customerId).orElse(null);
        if(customer != null ){
            if (customer.getAccount().isEmpty()){
                SavingAccount savingAccount=new SavingAccount();
                savingAccount.setAccountId((long) (Math.random()*(9999999- 1)+0000001));
                savingAccount.setCreatedAt(new Date());
                savingAccount.setBalance(initialBalance);
                savingAccount.setMinBalance( savingAccount.getMinBalance());
                savingAccount.setCustomer(customer);
                savingAccount.setAccountType(AccountType.SAV);
                savingAccount.setAccountStatus(AccountStatus.CREATED);
                SavingAccount savedBankAccount = accountRepository.save(savingAccount);
                log.info("CREATING ACCOUNT SUCCESSFUL ! YOUR ACCOUNT N° is "+ savedBankAccount.getAccountId() +" and your balance id "+savedBankAccount.getBalance());
                return savedBankAccount;
            }else new CustomerAlreadyHaveAnAccountException("THE CUSTOMER \"+ customer+\" HAVE AN ACCOUNT !!!!");
        }else new  CustomerNotFoundException("NO CUSTOMER WITH THIS ACCOUNT ID EXIST");
        return null;

    }



    @Override
    public boolean deleteSavingId(long accountId) {
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
    public boolean deleteCurrentId(long accountId)   {
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
    public Account getBankAccount(long accountId)   {
        Account bankAccount=accountRepository.findById(accountId).orElse(null);

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
    public double SoldeCompte(long accountId){
        Account bankAccount=accountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("Bank account not found !"));

        if(bankAccount instanceof SavingAccount){
            SavingAccount savingAccount= (SavingAccount) bankAccount;
            return savingAccount.getBalance();
        } else {
            CurrentAccount currentAccount= (CurrentAccount) bankAccount;
            return currentAccount.getBalance();
        }
    }

    @Override
    public boolean withdraw(double amount, long accountId, String remark)    {
        Account a = accountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("Bank Account not Found 404 !"));
        Transaction t = new Transaction();
        if (a.getBalance()<amount ){
            new BalanceNotSufficientException("BALANCE NOT SUFFICIENT");
            t.setAmount(amount);
            t.setDateTime(LocalDateTime.now());
            t.setTransactionType(TransactionType.DEBIT);
            t.setTransactionRemarks(remark);
            t.setTransactionStatus(TransactionStatus.FAILED);
            transactionService.createTransaction(t);
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
        t.setTransactionType(TransactionType.CREDIT);
        t.setDateTime(LocalDateTime.now());
        t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        t.setTransactionRemarks("CASH IN OF "+amount+" "+" SUCCESSFUL");
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

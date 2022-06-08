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

import javax.security.auth.login.AccountNotFoundException;
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


    public boolean customerExist(long customerId) throws CustomerNotFoundException {
        customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("CUSTOMER NOT FOUNDD"));
        return true;
    }
    public boolean customerCheckAccountExist(long customerId){
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer.getAccount().isEmpty())return  true ;
        else return false;
    }

//    public Account CheckTypeAccount(long customerId){
//        Customer customer = customerRepository.findById(customerId).orElse(null);
//        customer.getAccount();
//
//    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, long customerId) throws CustomerNotFoundException, CustomerAlreadyHaveAnAccountException {
        log.info("CREATING CURREENT ACCOUNT OF CUSTOMER "+ customerId +" IN PROCESS");
        Customer customer= customerRepository.findById(customerId).orElse(null);
        if(customerExist(customerId) ){
            if (customerCheckAccountExist(customerId)){
                CurrentAccount currentAccount=new CurrentAccount();
                currentAccount.setAccountId((long) (Math.random()*(9999999- 1)+0000001));
                currentAccount.setCreatedAt(new Date());
                currentAccount.setBalance(initialBalance);
                currentAccount.setOverDraft( currentAccount.getOverDraft());
                currentAccount.setCustomer(customer);
                currentAccount.setAccountType(AccountType.CUR);
                currentAccount.setAccountStatus(AccountStatus.CRT);
                CurrentAccount savedBankAccount = accountRepository.save(currentAccount);
                log.info("CREATING ACCOUNT SUCCESSFUL ! YOUR ACCOUNT N° is "+ currentAccount.getAccountId() +" and your balance id "+currentAccount.getBalance());
                return savedBankAccount;
            }else throw  new CustomerAlreadyHaveAnAccountException("THE CUSTOMER \"+ customer+\" HAVE AN ACCOUNT !!!!");
        }else throw new  CustomerNotFoundException("NO CUSTOMER WITH THIS ACCOUNT ID EXIST");
    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, long customerId) throws CustomerNotFoundException {
        log.info("CREATING SAVING ACCOUNT OF CUSTOMER "+ customerId +" IN PROCESS");
        Customer customer= customerRepository.findById(customerId).orElse(null);
        if(customerExist(customerId) ){
            if (customerCheckAccountExist(customerId)){
                SavingAccount savingAccount=new SavingAccount();
                savingAccount.setAccountId((long) (Math.random()*(9999999- 1)+0000001));
                savingAccount.setCreatedAt(new Date());
                savingAccount.setAccountStatus(AccountStatus.CRT);
                savingAccount.setBalance(initialBalance);
                savingAccount.setMinBalance( savingAccount.getMinBalance());
                savingAccount.setCustomer(customer);
                savingAccount.setAccountType(AccountType.SAV);
                savingAccount.setAccountStatus(AccountStatus.CRT);
                SavingAccount savedBankAccount = accountRepository.save(savingAccount);
                log.info("CREATING ACCOUNT SUCCESSFUL ! YOUR ACCOUNT N° is "+ savedBankAccount.getAccountId() +" and your balance id "+savedBankAccount.getBalance());
                return savedBankAccount;
            }else new CustomerAlreadyHaveAnAccountException("THE CUSTOMER \"+ customer+\" HAVE AN ACCOUNT !!!!");
        }else new  CustomerNotFoundException("NO CUSTOMER WITH THIS ACCOUNT ID EXIST");
        return null;

    }


    @Override
    public boolean deleteSavingId(long accountId) throws InvalidDetailsException {
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
            if (currentAccount.getAccountStatus().equals(AccountStatus.ACT)){
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
        Account a = accountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("Bank Account not Found 404 !"));

        if(a instanceof SavingAccount){
            SavingAccount savingAccount= (SavingAccount) a;
            return savingAccount;
        } else {
            CurrentAccount currentAccount= (CurrentAccount) a;
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
    public double SoldeCompte(long accountId) throws BankAccountNotFoundException {
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
    public AccountStatus activateAccount(long accountId) throws BankAccountNotFoundException {
        Account account =getBankAccount(accountId);
        account.setAccountStatus(AccountStatus.ACT);
        accountRepository.saveAndFlush(account);
        log.info("THIS ACCOUNT WAS ACTIVATED");
        return account.getAccountStatus();
    }
    @Override
    public AccountStatus suspendAccount(long accountId) throws BankAccountNotFoundException {
        Account account =getBankAccount(accountId);
        account.setAccountStatus(AccountStatus.SUS);
        accountRepository.saveAndFlush(account);
        log.info("THIS ACCOUNT WAS SUSPENDED");
        return account.getAccountStatus();
    }

    @Override
    public List<Account> viewAccounts(long accountId) {
        return accountRepository.findAll();
    }

    @Override
    public Account viewSavingAcc(long customerId) {
        return (Account) accountRepository.viewSavingAcc(customerId);
    }

    @Override
    public Account viewCurrentAcc(long customerId) {
        return accountRepository.viewTermAcc(customerId);
    }

    @Override
    public Transaction deposit(long accountId, double amount, String remark) throws BankAccountNotFoundException {
        Account a = accountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("Bank Account not Found 404 !"));
        Transaction t = new Transaction();
        if (a != null) {
            if (a instanceof SavingAccount) {
                double balance = a.getBalance() + amount;
                a.setBalance(balance);
                accountRepository.save(a);
                t.setAmount(amount);
                t.setDateTime(LocalDateTime.now());
                t.setTransactionType(TransactionType.CREDIT);
                t.setTransactionRemarks(remark);
                t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                transactionService.createTransaction(t);
                log.info("OPERATION SUCCESSFUL");
                return t;
            } else if (a instanceof CurrentAccount) {
                double balance = a.getBalance() + amount;
                a.setBalance(balance);
                accountRepository.save(a);
                t.setAmount(amount);
                t.setDateTime(LocalDateTime.now());
                t.setTransactionType(TransactionType.CREDIT);
                t.setTransactionRemarks(remark);
                t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                transactionService.createTransaction(t);
                log.info("OPERATION SUCCESSFUL");
                return t;
            }
        } else new BankAccountNotFoundException("Bank Acount not found");
        return null;
    }

    @Override
    public Transaction withdraw(double amount, long accountId, String remark) throws BankAccountNotFoundException {
        Account a = accountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("Bank Account not Found 404 !"));
        Transaction t = new Transaction();
        if (a != null){
            if(a instanceof  SavingAccount){
                if ((a.getBalance() - ((SavingAccount) a).getMinBalance())>amount ){
                    double balance = a.getBalance() - amount;
                    a.setBalance(balance);
                    accountRepository.save(a);
                    t.setAmount(amount);
                    t.setDateTime(LocalDateTime.now());
                    t.setTransactionType(TransactionType.DEBIT);
                    t.setTransactionRemarks(remark);
                    t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                    transactionService.createTransaction(t);
                    log.info("OPERATION SUCCESSFUL");
                    return t;
                }else{
                    new BalanceNotSufficientException("BALANCE NOT SUFFICIENT");
                    log.info("BALANCE NOT SUFFICIENT");
                    t.setAmount(amount);
                    t.setDateTime(LocalDateTime.now());
                    t.setTransactionType(TransactionType.DEBIT);
                    t.setTransactionRemarks(remark);
                    t.setTransactionStatus(TransactionStatus.FAILED);
                    transactionService.createTransaction(t);
                    return t;
                }

            }else {
                if ((a.getBalance()- ((CurrentAccount) a).getOverDraft())>amount ){
                    double balance = a.getBalance() - amount;
                    a.setBalance(balance);
                    accountRepository.save(a);
                    t.setAmount(amount);
                    t.setDateTime(LocalDateTime.now());
                    t.setTransactionType(TransactionType.DEBIT);
                    t.setTransactionRemarks("CASH_OUT OF "+amount);
                    t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                    transactionService.createTransaction(t);
                    log.info("OPERATION SUCCESSFUL");
                    return t;
                }else{
                    new BalanceNotSufficientException("BALANCE NOT SUFFICIENT");
                    t.setAmount(amount);
                    t.setDateTime(LocalDateTime.now());
                    t.setTransactionType(TransactionType.DEBIT);
                    t.setTransactionRemarks(remark);
                    t.setTransactionStatus(TransactionStatus.FAILED);
                    transactionService.createTransaction(t);
                    log.info("BALANCE NOT SUFFICIENT");
                    return t;
                }
            }

        }else new AccountNotFoundException("Account not Found");
        return t;
    }

    @Override
    public boolean transfer(long senderAccountId, long reciverAccountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsException {
        withdraw(amount,senderAccountId,"transfer to "+reciverAccountId);
        deposit(reciverAccountId ,amount,"transfer from "+senderAccountId);
       return true;
    }
}

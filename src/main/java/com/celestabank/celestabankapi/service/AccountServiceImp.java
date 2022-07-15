package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.dto.AccountDto;
import com.celestabank.celestabankapi.dto.CustomerDto;
import com.celestabank.celestabankapi.dto.TransactionDTO;
import com.celestabank.celestabankapi.entity.*;
import com.celestabank.celestabankapi.enums.AccountStatus;
import com.celestabank.celestabankapi.enums.AccountType;
import com.celestabank.celestabankapi.enums.TransactionStatus;
import com.celestabank.celestabankapi.enums.TransactionType;
import com.celestabank.celestabankapi.exeption.*;
import com.celestabank.celestabankapi.mappers.BankServiceMapper;
import com.celestabank.celestabankapi.repository.AccountRepository;
import com.celestabank.celestabankapi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AccountServiceImp implements AccountService {

    private AccountRepository accountRepository;
    private TransactionServiceImpl transactionService;
    private CustomerRepository customerRepository;
    private final BankServiceMapper dtoMappers;
    private final CustomerServiceImpl customerService;

    public boolean customerExist(long customerId) throws CustomerNotFoundException {
        customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("CUSTOMER NOT FOUNDD"));
        return true;
    }
    public boolean customerCheckCurrentAccountExist( long customerId){
        Customer customer= customerRepository.findById(customerId).orElse(null);
        List <Account> accounts =  customer.getAccount();
        if (accounts.stream().anyMatch(account -> account.getAccountType().equals(AccountType.COURANT) )){
            return  false;
        }else  return  true;
    }



    public boolean customerCheckSavingAccountExist( long customerId){
        Customer customer= customerRepository.findById(customerId).orElse(null);
        List <Account> accounts =  customer.getAccount();
        if (accounts.stream().anyMatch(account -> account.getAccountType().equals(AccountType.EPARGNE) )){
            return  false;
        }else  return  true;
    }

//    public Account CheckTypeAccount(long customerId){
//        Customer customer = customerRepository.findById(customerId).orElse(null);
//        customer.getAccount();
//
//    }
    @Override
    public SavingAccount savingAccount(double initialBaln, long idMachine){
        SavingAccount savingAccount=new SavingAccount();
        savingAccount.setAccountId((long) (Math.random()*(9999999- 1)+0000001));
        savingAccount.setCreatedAt(new Date());
        savingAccount.setAccountStatus(AccountStatus.CREATED);
        savingAccount.setBalance(initialBaln);
        savingAccount.setMinBalance( savingAccount.getMinBalance());
        savingAccount.setAccountType(AccountType.EPARGNE);
        savingAccount.setAccountStatus(AccountStatus.CREATED);
        savingAccount.setInterestRate(savingAccount.getInterestRate());
        SavingAccount savedBankAccount = accountRepository.save(savingAccount);
        log.info("CREATING ACCOUNT SUCCESSFUL ! YOUR ACCOUNT N° is "+ savedBankAccount.getAccountId() +" and your balance id "+savedBankAccount.getBalance());
        return savedBankAccount;
    }

    @Override
    public AccountDto saveCurrentBankAccount(double initialBalance, long customerId) {
        log.info("CREATING CURrENT ACCOUNT OF CUSTOMER "+ customerId +" IN PROCESS");
        Customer customer= customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException("CUSTOMER NOT FOUND ! ! !"));
        CustomerDto customerDto = dtoMappers.fromCustomer(customer);
        if(customerExist(customerId) ){
            CurrentAccount currentAccount=new CurrentAccount();
            currentAccount.setAccountId((long) (Math.random()*(999999999- 1)+123456719));
            currentAccount.setCreatedAt(new Date());
            currentAccount.setBalance(initialBalance);
            currentAccount.setOverDraft( currentAccount.getOverDraft());
            currentAccount.setCustomer(customer);
            currentAccount.setAccountType(AccountType.COURANT);
            currentAccount.setAccountStatus(AccountStatus.CREATED);
            currentAccount.setOverDraft(currentAccount.getOverDraft());
            if (customerCheckCurrentAccountExist(customerId)){
                CurrentAccount savedBankAccount = accountRepository.save(currentAccount);
                log.info("CREATING CURRENT ACCOUNT SUCCESSFUL ! YOUR ACCOUNT N° is "+ currentAccount.getAccountId() +" and your balance id "+currentAccount.getBalance());
                AccountDto currentAccountDTO =  dtoMappers.fromAccount(savedBankAccount);
                currentAccountDTO.setCustomerDto(customerDto);
                return  currentAccountDTO;
            }else throw  new CustomerAlreadyHaveAnAccountException("THE CUSTOMER "+ customerId +" HAVE AN ACCOUNT !!!!");
        }else throw new  CustomerNotFoundException("NO CUSTOMER WITH THIS ACCOUNT ID EXIST");
    }
    @Override
    public AccountDto saveSavingBankAccount(double initialBalance, long customerId) throws CustomerNotFoundException {
        log.info("CREATING SAVING ACCOUNT OF CUSTOMER "+ customerId +" IN PROCESS");
        Customer customer= customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException("CUSTOMER NOT FOUND ! ! !"));
        CustomerDto customerDto = dtoMappers.fromCustomer(customer);
        if(customerExist(customerId) ){
            SavingAccount savingAccount=new SavingAccount();
            savingAccount.setAccountId((long) (Math.random()*(99999999- 1)+123456719));
            savingAccount.setCreatedAt(new Date());
            savingAccount.setAccountStatus(AccountStatus.CREATED);
            savingAccount.setBalance(initialBalance);
            savingAccount.setMinBalance( savingAccount.getMinBalance());
            savingAccount.setCustomer(customer);
            savingAccount.setAccountType(AccountType.EPARGNE);
            savingAccount.setAccountStatus(AccountStatus.CREATED);
            if (customerCheckSavingAccountExist(customerId)){
                SavingAccount savedBankAccount = accountRepository.save(savingAccount);
                log.info("CREATING SAVING ACCOUNT SUCCESSFUL ! YOUR ACCOUNT N° is "+ savedBankAccount.getAccountId() +" and your balance id "+savedBankAccount.getBalance());
                AccountDto savingAccountDTO = dtoMappers.fromAccount(savedBankAccount);
                savingAccountDTO.setCustomerDto(customerDto);
                return savingAccountDTO;
            }else throw new CustomerAlreadyHaveAnAccountException("THE CUSTOMER "+ customerId +" HAVE AN ACCOUNT !!!!");
        }else throw  new  CustomerNotFoundException("NO CUSTOMER WITH THIS ACCOUNT ID EXIST");
    }


    @Override
    public boolean deleteAcc(long accountId){
        if (viewAcc(accountId)!=null){
            AccountDto accountDto  = viewAcc(accountId);
            if ( accountDto.getBalance()<2000) {
                accountRepository.deleteById(accountId);
                return  true;
            }else throw   new InvalidDetailsOperation(" IMPOSSIBLE DE SUPPRIMER UN COMPTE ACTIF ET AVEC DES FONDS, VIDEZ ET DESACTIVEZ SVP !");

        }
        return  false ;
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
    public AccountDto activateAccount(long accountId) throws BankAccountNotFoundException {
        Account account =getBankAccount(accountId);
        account.setAccountStatus(AccountStatus.ACTIVATED);
        accountRepository.saveAndFlush(account);
        log.info("THIS ACCOUNT " +accountId +" WAS ACTIVATED");
        AccountDto accountDto = new AccountDto(account);
        return accountDto;
    }
    @Override
    public AccountDto suspendAccount(long accountId) throws BankAccountNotFoundException {
        Account account =getBankAccount(accountId);
        account.setAccountStatus(AccountStatus.SUSPENDED);
        accountRepository.saveAndFlush(account);
        AccountDto accountDto = new AccountDto(account);
        log.info("THIS ACCOUNT "+accountId+"  WAS SUSPENDED");
        return accountDto;
    }

    @Override
    public List<AccountDto> listAccounts(){
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos =  accounts.stream().map(account -> new AccountDto(account)).collect(Collectors.toList());
        return accountDtos;
    }


    @Override
    public AccountDto viewSavingAcc(long accountId ) {
        Account account= accountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("COMPTE EPARGNE INEXISTANT"));
        AccountDto accountDto =dtoMappers.fromAccount(account);
        accountDto.setCustomerDto(dtoMappers.fromCustomer(account.getCustomer()));
            return  accountDto;
    }
    @Override
    public AccountDto viewCurrentAcc(long accountId ) {
        Account account= accountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("COMPTE EPARGNE INEXISTANT"));
        AccountDto accountDto =dtoMappers.fromAccount(account);
        accountDto.setCustomerDto(dtoMappers.fromCustomer(account.getCustomer()));
        return  accountDto;
    }
    @Override
    public AccountDto viewAcc(long accountId) {
        Account account= accountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("COMPTE  INEXISTANT"));
        AccountDto accountDto =dtoMappers.fromAccount(account);
        accountDto.setCustomerDto(dtoMappers.fromCustomer(account.getCustomer()));
        return  accountDto;
    }

    @Override
    public TransactionDTO deposit(long accountId, double amount, String remark) throws BankAccountNotFoundException {
        Account a = accountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("Bank Account not Found 404 !"));
        Transaction t = new Transaction();
        TransactionDTO t2 = new TransactionDTO();
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
                t.setAccountId(accountId);
                t.setReason("VALID");
                transactionService.createTransaction(t);
                log.info("CASH IN OPERATION SUCCESSFUL");
                t2= new TransactionDTO(t,a);
                return t2;
            } else if (a instanceof CurrentAccount) {
                double balance = a.getBalance() + amount;
                a.setBalance(balance);
                accountRepository.save(a);
                t.setAmount(amount);
                t.setDateTime(LocalDateTime.now());
                t.setTransactionType(TransactionType.CREDIT);
                t.setTransactionRemarks(remark);
                t.setAccountId(accountId);
                t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                t.setReason("VALID");
                transactionService.createTransaction(t);
                log.info("CASH IN OPERATION OF CASH_IN SUCCESSFUL");
                t2= new TransactionDTO(t,a);
                return t2;
            }
        } else new BankAccountNotFoundException("Bank Acount not found");
        return null;
    }

    @Override
    public TransactionDTO withdraw(double amount, long accountId, String remark) {
        Account a = accountRepository.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("Bank Account not Found "));
        Transaction t = new Transaction();
        TransactionDTO t2 = new TransactionDTO();
        if (a != null){
            if(a instanceof  SavingAccount){
                if(a.getAccountStatus().equals(AccountStatus.CREATED)){
                    throw   new BankAccountNotActivatedException("YOUR ACCOUNT IS NOT ACTIVATED  ! PLEASE CONTACT THE ADMINISTRATION TO ACTIVE YOUR ACCOUNT");
                 }else if (a.getAccountStatus().equals(AccountStatus.SUSPENDED)){ throw  new BankAccountSuspendedException("YOUR ACCOUNT IS BLOCKED, CONTACT THE ADMINISTRATOR");
                }else {
                    if ((a.getBalance() - ((SavingAccount) a).getMinBalance())>amount ){
                        double balance = a.getBalance() - amount;
                        a.setBalance(balance);
                        accountRepository.save(a);
                        t.setAmount(amount);
                        t.setDateTime(LocalDateTime.now());
                        t.setTransactionType(TransactionType.DEBIT);
                        t.setTransactionRemarks(remark);
                        t.setAccountId(accountId);
                        t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                        t.setReason("VALID");
                        transactionService.createTransaction(t);
                        log.info("OPERATION OF CASH_OUT SUCCESSFUL");
                          t2 = new TransactionDTO(t,a);
                        return t2;
                    }else{
                        log.info("BALANCE NOT SUFFICIENT");
                        new BalanceNotSufficientException("BALANCE NOT SUFFICIENT");
                        t.setAmount(amount);
                        t.setDateTime(LocalDateTime.now());
                        t.setTransactionType(TransactionType.DEBIT);
                        t.setTransactionRemarks(remark);
                        t.setAccountId(accountId);
                        t.setTransactionStatus(TransactionStatus.FAILED);
                        t.setReason("BALANCE INSUFFICIENT");
                        transactionService.createTransaction(t);
                          t2 = new TransactionDTO(t,a);
                        return t2;
                    }
                }

            }else {
                if (a.getAccountStatus().equals(AccountStatus.CREATED)) {
                     throw  new BankAccountNotActivatedException("PLEASE CONTACT THE ADMINISTRATION TO ACTIVE YOUR ACCOUNT");
                } else if (a.getAccountStatus().equals(AccountStatus.SUSPENDED)) {
                   throw    new BankAccountSuspendedException("YOUR ACCOUNT IT'S BLOCKED CONTACT THE ADMINISTRATOR");
                } else {
                    if ((a.getBalance() - ((CurrentAccount) a).getOverDraft()) > amount) {
                        double balance = a.getBalance() - amount;
                        a.setBalance(balance);
                        accountRepository.save(a);
                        t.setAmount(amount);
                        t.setDateTime(LocalDateTime.now());
                        t.setTransactionType(TransactionType.DEBIT);
                        t.setTransactionRemarks(remark);
                        t.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                        t.setAccountId(accountId);
                        t.setReason("VALID");
                        transactionService.createTransaction(t);
                        log.info("OPERATION OF CASH_OUT SUCCESSFUL");
                         t2 = new TransactionDTO(t,a);
                        return t2;
                    } else {
                        new BalanceNotSufficientException("BALANCE NOT SUFFICIENT");
                        t.setAmount(amount);
                        t.setDateTime(LocalDateTime.now());
                        t.setTransactionType(TransactionType.DEBIT);
                        t.setTransactionRemarks(remark);
                        t.setTransactionStatus(TransactionStatus.FAILED);
                        t.setAccountId(accountId);
                        t.setReason("BALANCE INSUFFICIENT");
                        transactionService.createTransaction(t);
                        log.info("BALANCE NOT SUFFICIENT");
                         t2 = new TransactionDTO(t,a);
                        return t2;
                    }
                }
            }

        }else      new AccountNotFoundException("Account not Found");
         return t2;
    }

//    public  Transaction doCardlessWithDrawall(String number, double amount){}
//    public  Transaction doDepositToNonCustomer(long senderId, long dapId, double amount){}

//    public Transaction doNoCustomerBankTransfer(){return null}
public List<TransactionDTO> reponse( TransactionDTO t1 , TransactionDTO t2) {
    List<TransactionDTO> t3 = new ArrayList<>();
    t3.add(t1);
    t3.add(t2);
    return t3;
}
    @Override
    public List<TransactionDTO> transfer(long senderAccountId, long reciverAccountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsOperation, BankAccountNotActivatedException, BankAccountSuspendedException {
        TransactionDTO  t1= withdraw(amount,senderAccountId,"transfer to "+reciverAccountId);
        TransactionDTO t2 = deposit(reciverAccountId ,amount,"transfer from "+senderAccountId) ;

       return reponse(t1,t2);
    }
}

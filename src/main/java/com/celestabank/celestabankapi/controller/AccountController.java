package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.exeption.BalanceNotSufficientException;
import com.celestabank.celestabankapi.exeption.BankAccountNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidAccountException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsException;
import com.celestabank.celestabankapi.service.AccountServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    AccountServiceImp   accountServiceImp;

    @PostMapping("/add")
    public List<Account> addAccount(@RequestBody Account account){
        return accountServiceImp.addAccount(account);
    }

    @PostMapping("/savings")
    public List<SavingAccount> addSavingAcc(@RequestBody SavingAccount savingAccount) throws InvalidDetailsException {
        List<SavingAccount> t = null;
        try{
            t = accountServiceImp.addSavingAccount(savingAccount);
        }catch (Exception e){
            throw new InvalidDetailsException("INVALID ENTRY DATA");
        }
        return  t;

    }

    @PostMapping("/current")
    public List<CurrentAccount> addCurrentAcc(@RequestBody CurrentAccount currentAccount) throws InvalidDetailsException {
        List<CurrentAccount> t = null;
        try{
            t = accountServiceImp.addCurrentAccount(currentAccount);
        }catch (Exception e){
            throw new InvalidDetailsException("INVALID ENTRY DATA");
        }
        return  t;
    }

    @DeleteMapping("/closeSavingAcc/{accountId}")
    public boolean closeSavingAcc(@PathVariable long accountId) throws InvalidAccountException {
        if (accountId !=0){
            try{
                accountServiceImp.deleteSavingId(accountId);
                return  true;
            } catch (Exception e) {
                throw new InvalidAccountException("IMPOSSIBLE DE SUPPRIMER CE COMPTE");
            }
        }
        return false;
    }

    @DeleteMapping("/closeCurrentAcc/{accountId}")
    public boolean closeCurrentAcc(@PathVariable long accountId) throws InvalidAccountException {
        if (accountId !=0){
            try{
                accountServiceImp.deleteCurrentId(accountId);
                return true;
            } catch (Exception e) {
                throw new InvalidAccountException("IMPOSSIBLE DE SUPPRIMER CE COMPTE");
            }
        }
        return false;
    }

    @GetMapping("/findAcc/{accountId}")
    public Account findAccount(@PathVariable long accountId) throws BankAccountNotFoundException {
        return  accountServiceImp.getAccountById(accountId);
    }

    @PutMapping("/update/saving")
    public List<SavingAccount> updateSavingsAccount(@RequestBody SavingAccount updS) throws InvalidDetailsException {
        List<SavingAccount> t = null;
        try {
            t = accountServiceImp.updateSavingAccount(updS);
        } catch (Exception e) {
            throw new InvalidDetailsException("RE-VERIFIER LES INFORMATIONS !");
        }
        return t;
    }

    @PutMapping("/update/term")
    public List<CurrentAccount> updateTermAccount(@RequestBody CurrentAccount updT) throws InvalidDetailsException {
        List<CurrentAccount> t = null;
        try {
            t = accountServiceImp.updateCurrentAccount(updT);
        } catch (Exception e) {
            throw new InvalidDetailsException("Invalid details kindly check!");
        }
        return t;
    }

    @PostMapping("/deposit/{accountId}")
    public Transaction deposit(@PathVariable long accountId, @RequestBody double amount ) throws BankAccountNotFoundException {
        return accountServiceImp.deposit(amount, accountId);

    }

    @PostMapping("/withdraw/{accountId}")
    public Transaction withdraw(@PathVariable long accountId, @RequestBody double amount, long customerId,
                                String password) throws BankAccountNotFoundException, BalanceNotSufficientException {
        return accountServiceImp.withdraw(amount, accountId, customerId, password);

    }

    @GetMapping("find/{customerId}")
    public List<Account> viewAccount(@PathVariable long customerId) throws InvalidDetailsException {
        List<Account> a = null;
        try {
            a = accountServiceImp.viewAccounts(customerId);
        } catch (Exception e) {
            throw new InvalidDetailsException("Invalid details kindly check!");
        }

        return a;

    }

    @GetMapping("findSaving/{customerId}")
    public List<Account> viewSavingAccount(@PathVariable long customerId) {

        return accountServiceImp.viewSavingAcc(customerId);

    }

    @GetMapping("findTerm/{customerId}")
    public List<Account> viewTermAccount(@PathVariable long customerId) {

        return accountServiceImp.viewCurrentAcc(customerId);

    }

    @PostMapping("transfer")
    public Transaction transferMoney(@RequestBody long senderAccountId,long reciverAccountId, double amount,long customerId, String password) throws BankAccountNotFoundException, BalanceNotSufficientException {
        return accountServiceImp.transfer(senderAccountId, reciverAccountId, amount, customerId, password);
    }

}

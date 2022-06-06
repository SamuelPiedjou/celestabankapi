package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.dto.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    AccountServiceImp   accountServiceImp;



    @PostMapping("/{customerId}/savings")
    public SavingAccount addSavingAcc(@RequestBody SavingAccountDTO savingAccount) throws InvalidDetailsException {
        SavingAccount t = null;
        try{
            t = accountServiceImp.saveSavingBankAccount(savingAccount.getInitialBalance(), savingAccount.getCustomerId());
        }catch (Exception e){
            throw new InvalidDetailsException("INVALID ENTRY DATA");
        }
        return  t;

    }

    @PostMapping("/{customerId}/current")
    public CurrentAccount addCurrentAcc(@RequestBody CurrentAccountDTO currentAccountDTO) throws InvalidDetailsException {
        CurrentAccount t = null;
        try{
            t = accountServiceImp.saveCurrentBankAccount(currentAccountDTO.getInitialBalance(), currentAccountDTO.getCustomerId());
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
        return  accountServiceImp.getBankAccount(accountId);
    }

    @PutMapping("/update/saving")
    public SavingAccount updateSavingsAccount(@RequestBody SavingAccount updS) throws InvalidDetailsException {
        SavingAccount t = null;
        try {
            t = accountServiceImp.updateSavingAccount(updS);
        } catch (Exception e) {
            throw new InvalidDetailsException("Invalid details kindly check! !");
        }
        return t;
    }

    @PutMapping("/update/term")
    public CurrentAccount updateTermAccount(@RequestBody CurrentAccount updT) throws InvalidDetailsException {
        CurrentAccount t = null;
        try {
            t = accountServiceImp.updateCurrentAccount(updT);
        } catch (Exception e) {
            throw new InvalidDetailsException("Invalid details kindly check!");
        }
        return t;
    }

    @PostMapping("/accounts/deposit")
    public Transaction deposit(@RequestBody DepositDTO depositDTO ) throws BankAccountNotFoundException {
        return accountServiceImp.deposit(depositDTO.getAmount(), depositDTO.getAccountId(),depositDTO.getRemark());

    }

    @PostMapping("/withdraw/{accountId}")
    public boolean withdraw(@RequestBody WithdrawlDTO withdrawlDTO ) throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsException {
        return accountServiceImp.withdraw(withdrawlDTO.getAmount(), withdrawlDTO.getAccountId(), withdrawlDTO.getRemark());

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
    public boolean transferMoney(@RequestBody TransferDTO transferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsException {
        return accountServiceImp.transfer(transferDTO.getSender(), transferDTO.getReceiver(), transferDTO.getAmount());
    }

}

package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
import com.celestabank.celestabankapi.exeption.BankAccountNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidAccountException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsException;
import com.celestabank.celestabankapi.service.AccountServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.ListDataEvent;
import java.util.List;

@Controller
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

    @DeleteMapping("/closeAccount/{accountId}")
    public boolean closeSavingAcc(@PathVariable long accountId) throws InvalidAccountException {
        if (accountId !=0){
            try{
                accountServiceImp.deleteSavingId(accountId);
            } catch (Exception e) {
                throw new InvalidAccountException("IMPOSSIBLE DE SUPPRIMER CE COMPTE");
            }
        }
        return true;
    }
}

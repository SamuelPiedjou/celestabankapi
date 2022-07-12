package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.dto.*;
import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.enums.AccountStatus;
import com.celestabank.celestabankapi.exeption.*;
import com.celestabank.celestabankapi.service.AccountServiceImp;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountServiceImp   accountServiceImp;



    @PostMapping("/savings")
    @ApiOperation(value = "CREER UN COMPTE EPARGNE")
    public SavingAccount addSavingAcc(@RequestBody SavingAccountDTO savingAccount) {
        SavingAccount t = null;
        try{
            t = accountServiceImp.saveSavingBankAccount(savingAccount.getInitialBalance(), savingAccount.getCustomerId());
        }catch (RuntimeException e){
           e.printStackTrace();
        }
        return  t;

    }

    @PostMapping("/current")
    @ApiOperation(value = "CREER COMPTE COURANT")
    public CurrentAccount addCurrentAcc(@RequestBody CurrentAccountDTO currentAccountDTO) throws CustomerNotFoundException, CustomerAlreadyHaveAnAccountException  {
         CurrentAccount t = null;
        try{
            t = accountServiceImp.saveCurrentBankAccount(currentAccountDTO.getInitialBalance(), currentAccountDTO.getCustomerId());
        }catch (RuntimeException e){
             e.printStackTrace();
        }
        return  t;
    }

    @DeleteMapping("/closeSavingAcc/{accountId}")
    @ApiOperation(value = "FERMER UN COMPTE EPARGNE SOUS LA BASE DE SON ID")
    public boolean closeSavingAcc(@PathVariable long accountId)   {
        if (accountId !=0){
            try{
                accountServiceImp.deleteSavingId(accountId);
                return  true;
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @DeleteMapping("/closeCurrentAcc/{accountId}")
    @ApiOperation(value = "FERMER UN COMPTE COURANT SOUS LA BASE DE SON ID")
    public boolean closeCurrentAcc(@PathVariable long accountId)   {
        if (accountId !=0){
            try{
                accountServiceImp.deleteCurrentId(accountId);
                return true;
            } catch (RuntimeException e) {
                 e.printStackTrace();
            }
        }
        return false;
    }

    @GetMapping("/findAcc/{accountId}")
    @ApiOperation(value = "CHERCHER UN COMPTE SOUS LA BASE DE SON NUMERO ")
    public Account findAccount(@PathVariable long accountId) throws BankAccountNotFoundException {
        return  accountServiceImp.getBankAccount(accountId);
    }

    @PutMapping("/update/saving")
    @ApiOperation(value = "MAJ UN COMPTE EPARGNE")
    public SavingAccount updateSavingsAccount(@RequestBody SavingAccount updS) throws InvalidDetailsException {
        SavingAccount t = null;
        try {
            t = accountServiceImp.updateSavingAccount(updS);
        } catch (RuntimeException e) {
            throw new InvalidDetailsException("Invalid details kindly check! !");
        }
        return t;
    }

    @PutMapping("/update/current")
    @ApiOperation(value = "MAJ COMPTE COURANT")
    public CurrentAccount updateCurrentAccount(@RequestBody CurrentAccount updT) throws InvalidDetailsException {
        CurrentAccount t = null;
        try {
            t = accountServiceImp.updateCurrentAccount(updT);
        } catch (Exception e) {
            throw new InvalidDetailsException("Invalid details kindly check!");
        }
        return t;
    }


    @PostMapping("/accounts/deposit")
    @ApiOperation(value = "EFFECTUER DUN DEPOT DANS UN COMPTE")
    public Transaction deposit(@RequestBody DepositDTO depositDTO )   {
        Transaction t = null;
        try{
           return accountServiceImp.deposit( depositDTO.getAccountId(),depositDTO.getAmount(),depositDTO.getRemark());

        }catch(RuntimeException e){
            e.printStackTrace();
        }
        return  t;
    }

    @PostMapping("/withdraw/")
    @ApiOperation(value = "OPERATION DE RETRAIT DANS UN COMPTE")
    public Transaction withdraw(@RequestBody WithdrawlDTO withdrawlDTO )   {
        Transaction t = null;
        try{
            return accountServiceImp.withdraw(withdrawlDTO.getAmount(), withdrawlDTO.getAccountId(), withdrawlDTO.getRemark());
        }catch(RuntimeException e){
            e.printStackTrace();
        }
        return  t;

    }
    
    @PutMapping("/suspend/{accoountId}")
    @ApiOperation(value = "DESACTIVER UN COMPTE ")
    public AccountStatus suspendAcc(@PathVariable long accoountId)   {
        AccountStatus status= AccountStatus.CREATED;
        try{
            status= accountServiceImp.suspendAccount(accoountId);
        }catch (RuntimeException e){
            e.printStackTrace();
            
        }
        return  status;
    }
    @PutMapping("/activeAcc/{accoountId}")
    @ApiOperation(value = "ACTIVER UN COMPTE")
    public AccountStatus activate(@PathVariable long accoountId)   {
        AccountStatus status= AccountStatus.CREATED;
        try{
            status= accountServiceImp.activateAccount(accoountId);
        }catch (RuntimeException e){
            e.printStackTrace();

        }
        return  status;
    }


    @GetMapping("find/{customerId}")
    @ApiOperation(value = "CONSULTER UN COMPTE")
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
    @ApiOperation(value = "CONSULTER UN COMPTE EPARGNE")
    public Account viewSavingAccount(@PathVariable long customerId) {

        try{
            return (Account) accountServiceImp.viewSavingAcc(customerId);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("findCurrent/{customerId}")
    @ApiOperation(value = "CONSULTER UN COMPTE COURANT")
    public Account viewCurrentAccount(@PathVariable long customerId) {

        try{
            return accountServiceImp.viewCurrentAcc(customerId);
        }catch(RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("transfer")
    @ApiOperation(value = "OPERATION DE TRANSFERT D'UN COMPTE A COMPTE")
    public boolean transferMoney(@RequestBody TransferDTO transferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsException {
        return accountServiceImp.transfer(transferDTO.getSender(), transferDTO.getReceiver(), transferDTO.getAmount());
    }

}

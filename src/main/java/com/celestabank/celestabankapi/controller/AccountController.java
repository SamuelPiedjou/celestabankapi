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



    @PostMapping("/savings/{idCust}")
    @ApiOperation(value = "CREER UN COMPTE EPARGNE")
    public AccountDto addSavingAcc(@PathVariable long idCust, @RequestBody SavingAccountDTO savingAccountDTO) {
            return  accountServiceImp.saveSavingBankAccount(savingAccountDTO.getBalance(), idCust);
    }

    @PostMapping("/current/{idCust}")
    @ApiOperation(value = "CREER COMPTE COURANT")
    public AccountDto addCurrentAcc(@PathVariable long idCust, @RequestBody CurrentAccountDTO currentAccountDTO) {
       return accountServiceImp.saveCurrentBankAccount(currentAccountDTO.getBalance(), idCust);
    }

    @DeleteMapping("/closeSavingAcc/{accountId}")
    @ApiOperation(value = "FERMER UN COMPTE EPARGNE SOUS LA BASE DE SON ID")
    public boolean closeSavingAcc(@PathVariable long accountId)   {
                accountServiceImp.deleteSavingId(accountId);
                return true;
    }

    @DeleteMapping("/closeCurrentAcc/{accountId}")
    @ApiOperation(value = "FERMER UN COMPTE COURANT SOUS LA BASE DE SON ID")
    public boolean closeCurrentAcc(@PathVariable long accountId)   {
        accountServiceImp.deleteCurrentId(accountId);
        return true;
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
            t = accountServiceImp.updateSavingAccount(updS);
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
           return accountServiceImp.deposit( depositDTO.getAccountId(),depositDTO.getAmount(),depositDTO.getRemark());
    }

    @PostMapping("/withdraw/")
    @ApiOperation(value = "OPERATION DE RETRAIT DANS UN COMPTE")
    public Transaction withdraw(@RequestBody WithdrawlDTO withdrawlDTO )   {
            return accountServiceImp.withdraw(withdrawlDTO.getAmount(), withdrawlDTO.getAccountId(), withdrawlDTO.getRemark());
    }
    
    @PutMapping("/suspend/{accoountId}")
    @ApiOperation(value = "DESACTIVER UN COMPTE ")
    public AccountDto suspendAcc(@PathVariable long accoountId)   {
         return accountServiceImp.suspendAccount(accoountId);
    }
    @PutMapping("/activeAcc/{accoountId}")
    @ApiOperation(value = "ACTIVER UN COMPTE")
    public AccountDto activate(@PathVariable long accoountId)   {
        return accountServiceImp.activateAccount(accoountId);
    }
    @GetMapping("/listAccount")
    @ApiOperation(value = "LISTE DES COMPTES")
    public List<AccountDto> listAccount(){
        return accountServiceImp.listAccounts();
    }

    @GetMapping("findSaving/{accountId}")
    @ApiOperation(value = "CONSULTER UN COMPTE EPARGNE")
    public AccountDto viewSavingAccount(@PathVariable long accountId) {
            return accountServiceImp.viewSavingAcc(accountId);
    }

    @GetMapping("findCurrent/{accountId}")
    @ApiOperation(value = "CONSULTER UN COMPTE COURANT")
    public AccountDto viewCurrentAccount(@PathVariable long accountId) {
            return accountServiceImp.viewCurrentAcc(accountId);
    }

    @PostMapping("transfer")
    @ApiOperation(value = "OPERATION DE TRANSFERT D'UN COMPTE A COMPTE")
    public boolean transferMoney(@RequestBody TransferDTO transferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsException {
        return accountServiceImp.transfer(transferDTO.getSender(), transferDTO.getReceiver(), transferDTO.getAmount());
    }

}

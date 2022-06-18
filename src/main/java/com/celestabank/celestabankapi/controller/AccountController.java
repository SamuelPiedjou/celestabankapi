package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.dto.*;
import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.enums.AccountStatus;
import com.celestabank.celestabankapi.service.AccountServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountServiceImp accountServiceImp;

	public AccountController(AccountServiceImp accountServiceImp) {
		super();
		this.accountServiceImp = accountServiceImp;
	}

	@PostMapping("/savings")
	public SavingAccount addSavingAcc(@RequestBody SavingAccountDTO savingAccount) {
		return accountServiceImp.saveSavingBankAccount(savingAccount.getInitialBalance(),
				savingAccount.getCustomerId());
	}

	@PostMapping("/current")
	public CurrentAccount addCurrentAcc(@RequestBody CurrentAccountDTO currentAccountDTO) throws Exception {
		return accountServiceImp.saveCurrentBankAccount(currentAccountDTO.getInitialBalance(),
				currentAccountDTO.getCustomerId());
	}

	@DeleteMapping("/closeSavingAcc/{accountId}")
	public boolean closeSavingAcc(@PathVariable long accountId) {
		if (accountId != 0) {
			accountServiceImp.deleteSavingId(accountId);
			return true;
		}
		return false;
	}

	@DeleteMapping("/closeCurrentAcc/{accountId}")
	public boolean closeCurrentAcc(@PathVariable long accountId) {
		if (accountId != 0) {
			accountServiceImp.deleteSavingId(accountId);
			return true;
		}
		return false;
	}

	@GetMapping("/findAcc/{accountId}")
	public Account findAccount(@PathVariable long accountId) throws Exception {
		return accountServiceImp.getBankAccount(accountId);
	}

	@PutMapping("/update/saving")
	public SavingAccount updateSavingsAccount(@RequestBody SavingAccount updS) throws Exception {
		return accountServiceImp.updateSavingAccount(updS);
	}

	@PutMapping("/update/current")
	public CurrentAccount updateCurrentAccount(@RequestBody CurrentAccount updT) throws Exception {
		return accountServiceImp.updateCurrentAccount(updT);
	}

	@PostMapping("/accounts/deposit")
	public Transaction deposit(@RequestBody DepositDTO depositDTO) {
			return accountServiceImp.deposit(depositDTO.getAccountId(), depositDTO.getAmount(), depositDTO.getRemark());
	}

	@PostMapping("/withdraw/")
	public Transaction withdraw(@RequestBody WithdrawlDTO withdrawlDTO) {
		return accountServiceImp.withdraw(withdrawlDTO.getAmount(), withdrawlDTO.getAccountId(),
				withdrawlDTO.getRemark());
	}

	@PutMapping("/suspend/{accoountId}")
	public AccountStatus suspendAcc(@PathVariable long accoountId) {
		return accountServiceImp.suspendAccount(accoountId);
	}

	@PutMapping("/activeAcc/{accoountId}")
	public AccountStatus activate(@PathVariable long accoountId) {
		return accountServiceImp.activateAccount(accoountId);
	}

	@GetMapping("find/{customerId}")
	public List<Account> viewAccount(@PathVariable long customerId) throws Exception {
		return accountServiceImp.viewAccounts(customerId);
	}

	@GetMapping("findSaving/{customerId}")
	public Account viewSavingAccount(@PathVariable long customerId) throws Exception {
		return accountServiceImp.viewSavingAcc(customerId);

	}

	@GetMapping("findCurrent/{customerId}")
	public Account viewCurrentAccount(@PathVariable long customerId) throws Exception {
		return accountServiceImp.viewCurrentAcc(customerId);
	}

	@PostMapping("transfer")
	public boolean transferMoney(@RequestBody TransferDTO transferDTO) throws Exception {
		return accountServiceImp.transfer(transferDTO.getSender(), transferDTO.getReceiver(), transferDTO.getAmount());
	}

}

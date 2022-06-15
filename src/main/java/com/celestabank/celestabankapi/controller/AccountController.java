package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.dto.*;
import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.enums.AccountStatus;
import com.celestabank.celestabankapi.exeption.*;
import com.celestabank.celestabankapi.service.AccountServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

	private final AccountServiceImp accountServiceImp;

	@PostMapping("/savings")
	public SavingAccount addSavingAcc(@RequestBody SavingAccountDTO savingAccount) {
		SavingAccount t = null;

		t = accountServiceImp.saveSavingBankAccount(savingAccount.getInitialBalance(), savingAccount.getCustomerId());
		return t;

	}

	@PostMapping("/current")
	public CurrentAccount addCurrentAcc(@RequestBody CurrentAccountDTO currentAccountDTO)
			throws CustomerNotFoundException, CustomerAlreadyHaveAnAccountException {
		CurrentAccount t = null;
		t = accountServiceImp.saveCurrentBankAccount(currentAccountDTO.getInitialBalance(),
				currentAccountDTO.getCustomerId());
		return t;
	}

	@DeleteMapping("/closeSavingAcc/{accountId}")
	public boolean closeSavingAcc(@PathVariable long accountId) {
		if (accountId != 0) {
			try {
				accountServiceImp.deleteSavingId(accountId);
				return true;
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@DeleteMapping("/closeCurrentAcc/{accountId}")
	public boolean closeCurrentAcc(@PathVariable long accountId) {
		if (accountId != 0) {
			try {
				accountServiceImp.deleteCurrentId(accountId);
				return true;
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@GetMapping("/findAcc/{accountId}")
	public Account findAccount(@PathVariable long accountId) throws BankAccountNotFoundException {
		return accountServiceImp.getBankAccount(accountId);
	}

	@PutMapping("/update/saving")
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
	public Transaction deposit(@RequestBody DepositDTO depositDTO) {
		Transaction t = null;
		try {
			return accountServiceImp.deposit(depositDTO.getAccountId(), depositDTO.getAmount(), depositDTO.getRemark());

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return t;
	}

	@PostMapping("/withdraw/")
	public Transaction withdraw(@RequestBody WithdrawlDTO withdrawlDTO) {
		Transaction t = null;
		try {
			return accountServiceImp.withdraw(withdrawlDTO.getAmount(), withdrawlDTO.getAccountId(),
					withdrawlDTO.getRemark());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return t;

	}

	@PutMapping("/suspend/{accoountId}")
	public AccountStatus suspendAcc(@PathVariable long accoountId) {
		AccountStatus status = AccountStatus.CRT;
		try {
			status = accountServiceImp.suspendAccount(accoountId);
		} catch (RuntimeException e) {
			e.printStackTrace();

		}
		return status;
	}

	@PutMapping("/activeAcc/{accoountId}")
	public AccountStatus activate(@PathVariable long accoountId) {
		AccountStatus status = AccountStatus.CRT;
		try {
			status = accountServiceImp.activateAccount(accoountId);
		} catch (RuntimeException e) {
			e.printStackTrace();

		}
		return status;
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
	public Account viewSavingAccount(@PathVariable long customerId) {

		try {
			return (Account) accountServiceImp.viewSavingAcc(customerId);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("findCurrent/{customerId}")
	public Account viewCurrentAccount(@PathVariable long customerId) {

		try {
			return accountServiceImp.viewCurrentAcc(customerId);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("transfer")
	public boolean transferMoney(@RequestBody TransferDTO transferDTO)
			throws BankAccountNotFoundException, BalanceNotSufficientException, InvalidDetailsException {
		return accountServiceImp.transfer(transferDTO.getSender(), transferDTO.getReceiver(), transferDTO.getAmount());
	}

}

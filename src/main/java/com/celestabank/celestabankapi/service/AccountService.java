package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.CurrentAccount;
import com.celestabank.celestabankapi.entity.SavingAccount;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.enums.AccountStatus;
import java.util.List;

public interface AccountService {

	CurrentAccount saveCurrentBankAccount(double initialBalance, long customerId) throws Exception;

	SavingAccount saveSavingBankAccount(double initialBalance, long customerId) throws Exception;

	boolean deleteSavingId(long accountId) throws Exception;

	boolean deleteCurrentId(long accountId) throws Exception;

	Account getBankAccount(long accountId) throws Exception;

	SavingAccount updateSavingAccount(SavingAccount savingAccount);

	CurrentAccount updateCurrentAccount(CurrentAccount currentAccount);

	double SoldeCompte(long accountId) throws Exception;

	AccountStatus activateAccount(long accountId) throws Exception;

	AccountStatus suspendAccount(long accountId) throws Exception;

	Transaction deposit(long accountId, double amount, String remark) throws Exception;

	Transaction withdraw(double amount, long accountId, String remark) throws Exception;

	List<Account> viewAccounts(long accountId) throws Exception;

	Account viewSavingAcc(long customerId) throws Exception;

	Account viewCurrentAcc(long customerId) throws Exception;

	boolean transfer(long senderAccountId, long reciverAccountId, double amount) throws Exception;
}

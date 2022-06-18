package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private TransactionRepository transactionRepository;

	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository = transactionRepository;
	}

	@Override
	public Transaction createTransaction(Transaction transaction) {
		transactionRepository.saveAndFlush(transaction);
		return transaction;
	}

	@Override
	public Transaction viewTransaction(long transactionId) {
		return transactionRepository.findById(transactionId).orElse(null);
	}

	@Override
	public Transaction findTransactionById(long transactionId) {
		return transactionRepository.findById(transactionId)
				.orElse(null);
	}

	@Override
	public List<Transaction> getAllMyAccTransactions(long accountId) {
		return transactionRepository.getAllMyAccTransactions(accountId);
	}
}

package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/transaction")
@RestController
public class TransactionController {
    private final TransactionService transactionService;

    
    public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}

	@PostMapping("/create")
    public Transaction createTransaction(@RequestBody Transaction transaction) throws Exception {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/find/{transactionId}")
    public Transaction findTransactionByid(@PathVariable long transactionId) {
        return transactionService.findTransactionById(transactionId);
    }

    @GetMapping("/view/{transactionId}")
    public Transaction viewTransactionByid(@PathVariable long transactionId) throws Exception {
        return transactionService.viewTransaction(transactionId);
    }

    @GetMapping("/all/{accountId}")
    public List<Transaction> getAllMyAccTransactions(@PathVariable int accountId) throws Exception {
        return transactionService.getAllMyAccTransactions(accountId);
    }
}

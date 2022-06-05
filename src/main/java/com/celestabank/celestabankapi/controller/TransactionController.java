package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.service.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/transaction")
@RestController
@AllArgsConstructor
public class TransactionController {
    TransactionServiceImpl transactionService;

    @PostMapping("/create")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/find/{transactionId}")
    public Transaction findTransactionByid(@PathVariable long transactionId) {
        return transactionService.findTransactionById(transactionId);
    }

    @GetMapping("/view/{transactionId}")
    public Transaction viewTransactionByid(@PathVariable long transactionId) {
        return transactionService.viewTransaction(transactionId);
    }

    @GetMapping("/all/{accountId}")
    public List<Transaction> getAllMyAccTransactions(@PathVariable int accountId) {
        return transactionService.getAllMyAccTransactions(accountId);
    }
}

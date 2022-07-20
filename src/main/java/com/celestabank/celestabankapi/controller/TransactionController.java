package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.dto.TransactionDTO;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.service.TransactionServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/transaction")
@RestController
@AllArgsConstructor
@CrossOrigin(value = "*")
public class TransactionController {
    private final TransactionServiceImpl transactionService;

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
    @GetMapping("/all")
    @ApiOperation("LISTE TOTALE DES TRANSACTIONS")
    public List<Transaction> listTransaction() {
        return transactionService.listAll();
    }
    @GetMapping("/allByAcc/{accountId}")
    @ApiOperation("LISTE TOTALE DES TRANSACTIONS FAITES A PARTIR D'UN COMPTE")
    public List<Transaction> listTransactionByAcc(@PathVariable long accountId) {
        return transactionService.getAccByNum(accountId);
    }

}

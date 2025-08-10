package com.docker.mstransaction.controller;

import com.docker.mstransaction.model.TransactionModel;
import com.docker.mstransaction.model.TransactionStatus;
import com.docker.mstransaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/internal/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionModel> getTransactionById(@PathVariable UUID id) {
        return ResponseEntity
                .ok(transactionService.getTransactionById(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionModel>> getAllTransactions() {
        List<TransactionModel> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateTransactionStatus(@PathVariable UUID id,
                                        @RequestParam TransactionStatus status,
                                        @RequestParam(required = false) String failureReason) {
        transactionService.updateTransactionStatus(id, status, failureReason);
        return ResponseEntity.noContent().build();
    }

    //    @GetMapping("/filter")
//    public ResponseEntity<List<TransactionModel>> getTransactionsByFilters(
//            @RequestParam(required = false) TransactionStatus status,
//            @RequestParam(required = false) Long senderWalletId,
//            @RequestParam(required = false) Long receiverWalletId
//    ) {
//        if (status == null && senderWalletId == null && receiverWalletId == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        List<TransactionModel> transactions = transactionService
//                .getTransactionsByFilters(status, senderWalletId, receiverWalletId);
//        return ResponseEntity.ok(transactions);
//    }

//    @GetMapping("/wallet/{walletId}")
//    public ResponseEntity<List<TransactionModel>> getTransactionsByWalletId(@PathVariable Long walletId) {
//        List<TransactionModel> transactions = transactionService
//                .getTransactionsByWalletId(walletId);
//        return ResponseEntity.ok(transactions);
//    }
}

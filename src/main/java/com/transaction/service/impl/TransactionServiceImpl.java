package com.transaction.service.impl;

import com.transaction.model.entity.Transaction;
import com.transaction.exception.TransactionNotFoundException;
import com.transaction.model.TransactionStatus;
import com.transaction.model.TransactionModel;
import com.transaction.repo.TransactionRepository;
import com.transaction.service.TransactionService;
import com.transaction.util.TransactionMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    @Transactional
    public TransactionModel createTransaction(TransactionModel transactionModel) {
        Transaction transaction = transactionMapper.toEntity(transactionModel);
        transaction.setStatus(TransactionStatus.PENDING);

        LocalDateTime now = LocalDateTime.now();
        transaction.setCreatedAt(now);
        transaction.setUpdatedAt(now);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toModel(savedTransaction);
    }

    @Override
    @Transactional
    public void updateTransactionStatus(UUID transactionId, TransactionStatus status, String failureReason) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found for ID: " + transactionId));

        transaction.setStatus(status);
        transaction.setFailureReason(failureReason);
        transaction.setUpdatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionModel> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found.");
        }
        return transactions.stream()
                .map((transactionMapper::toModel))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionModel getTransactionById(UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found for ID: " + transactionId));
        return transactionMapper.toModel(transaction);
    }

//    @Override
//    public List<TransactionModel> getTransactionsByFilters(TransactionStatus status, Long senderWalletId, Long receiverWalletId) {
//        List<Transaction> filteredTransactions = transactionRepository
//                .findByFilters(status, senderWalletId, receiverWalletId);
//
//        return filteredTransactions.stream()
//                .map(transactionMapper::toModel)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<TransactionModel> getTransactionsByWalletId(Long walletId) {
//        List<Transaction> transactions = transactionRepository.findByWalletId(walletId);
//        return transactions.stream()
//                .map(transactionMapper::toModel)
//                .collect(Collectors.toList());
//    }

}

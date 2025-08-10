package com.docker.mstransaction.service;

import com.docker.mstransaction.model.TransactionModel;
import com.docker.mstransaction.model.TransactionStatus;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    TransactionModel createTransaction(TransactionModel transactionModel);

    void updateTransactionStatus(UUID transactionId, TransactionStatus status, String failureReason);

    List<TransactionModel> getAllTransactions();

    TransactionModel getTransactionById(UUID transactionId);

//    List<TransactionModel> getTransactionsByFilters(TransactionStatus status, Long senderWalletId, Long receiverWalletId);
//
//    List<TransactionModel> getTransactionsByWalletId(Long walletId);
}

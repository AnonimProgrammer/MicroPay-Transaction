package com.transaction.service.impl;

import com.transaction.dto.InitiateTransactionEvent;
import com.transaction.dto.TransactionFailedEvent;
import com.transaction.dto.TransactionSucceededEvent;
import com.transaction.messaging.MessagePublisher;
import com.transaction.model.TransactionStatus;
import com.transaction.model.TransactionModel;
import com.transaction.service.MessagingService;
import com.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class MessagingServiceImpl implements MessagingService {

    private final TransactionService transactionService;
    private final MessagePublisher messagePublisher;

    public MessagingServiceImpl(TransactionService transactionService,
                                MessagePublisher messagePublisher) {
        this.transactionService = transactionService;
        this.messagePublisher = messagePublisher;
    }

    @Override
    public void handleInitiateTransactionEvent(InitiateTransactionEvent event) {
        TransactionModel transactionModel = new TransactionModel.Builder()
                .amount(event.getAmount())
                .source(event.getSource())
                .sourceType(event.getSourceType())
                .destination(event.getDestination())
                .destinationType(event.getDestinationType())
                .type(event.getType())
                .build();

        TransactionModel createdTransaction = transactionService.createTransaction(transactionModel);
        messagePublisher.publishTransactionCreatedEvent(createdTransaction, event.getPaymentId());
    }

    @Override
    public void handleTransactionSucceededEvent(TransactionSucceededEvent event) {
        transactionService
                .updateTransactionStatus(
                        event.getTransactionId(),
                        TransactionStatus.SUCCEEDED, null);
    }

    @Override
    public void handleTransactionFailedEvent(TransactionFailedEvent event) {
        transactionService
                .updateTransactionStatus(
                        event.getTransactionId(),
                        TransactionStatus.FAILED, event.getFailureReason());
    }
}

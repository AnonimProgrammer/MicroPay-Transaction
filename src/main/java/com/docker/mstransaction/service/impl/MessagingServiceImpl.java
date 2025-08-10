package com.docker.mstransaction.service.impl;

import com.docker.mstransaction.dto.InitiateTransactionEvent;
import com.docker.mstransaction.dto.TransactionFailedEvent;
import com.docker.mstransaction.dto.TransactionSucceededEvent;
import com.docker.mstransaction.messaging.MessagePublisher;
import com.docker.mstransaction.model.TransactionStatus;
import com.docker.mstransaction.model.TransactionModel;
import com.docker.mstransaction.service.MessagingService;
import com.docker.mstransaction.service.TransactionService;
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

package com.transaction.service;

import com.transaction.dto.InitiateTransactionEvent;
import com.transaction.dto.TransactionFailedEvent;
import com.transaction.dto.TransactionSucceededEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface MessagingService {

    final static Logger logger = LoggerFactory.getLogger(MessagingService.class);

    void handleInitiateTransactionEvent(InitiateTransactionEvent event);

    void handleTransactionSucceededEvent(TransactionSucceededEvent event);

    void handleTransactionFailedEvent(TransactionFailedEvent event);
}

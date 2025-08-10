package com.docker.mstransaction.service;

import com.docker.mstransaction.dto.InitiateTransactionEvent;
import com.docker.mstransaction.dto.TransactionFailedEvent;
import com.docker.mstransaction.dto.TransactionSucceededEvent;
import com.docker.mstransaction.service.impl.MessagingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface MessagingService {

    final static Logger logger = LoggerFactory.getLogger(MessagingService.class);

    void handleInitiateTransactionEvent(InitiateTransactionEvent event);

    void handleTransactionSucceededEvent(TransactionSucceededEvent event);

    void handleTransactionFailedEvent(TransactionFailedEvent event);
}

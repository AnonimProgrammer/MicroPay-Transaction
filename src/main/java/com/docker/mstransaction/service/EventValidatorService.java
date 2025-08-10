package com.docker.mstransaction.service;

import com.docker.mstransaction.dto.InitiateTransactionEvent;
import com.docker.mstransaction.dto.TransactionFailedEvent;
import com.docker.mstransaction.dto.TransactionSucceededEvent;

public interface EventValidatorService {

    void validate(InitiateTransactionEvent event);

    void validate(TransactionSucceededEvent event);

    void validate(TransactionFailedEvent event);
}

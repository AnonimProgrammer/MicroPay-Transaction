package com.transaction.service;

import com.transaction.dto.InitiateTransactionEvent;
import com.transaction.dto.TransactionFailedEvent;
import com.transaction.dto.TransactionSucceededEvent;

public interface EventValidatorService {

    void validate(InitiateTransactionEvent event);

    void validate(TransactionSucceededEvent event);

    void validate(TransactionFailedEvent event);
}

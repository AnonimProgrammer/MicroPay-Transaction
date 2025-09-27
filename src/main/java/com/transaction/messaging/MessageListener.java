package com.transaction.messaging;

import com.transaction.config.RabbitConstants;
import com.transaction.dto.InitiateTransactionEvent;
import com.transaction.dto.TransactionFailedEvent;
import com.transaction.dto.TransactionSucceededEvent;
import com.transaction.service.MessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final MessagingService messagingService;
    private final static Logger logger = LoggerFactory.getLogger(MessageListener.class);

    public MessageListener(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @RabbitListener(queues = RabbitConstants.TRANSACTION_INITIATE_QUEUE)
    public void listenToInitiateTransactionEvent(InitiateTransactionEvent initiateTransactionEvent) {
        logger.info("[MessageListener] - Listening to InitiateTransactionEvent: {}", initiateTransactionEvent);
        messagingService.handleInitiateTransactionEvent(initiateTransactionEvent);
    }

    @RabbitListener(queues = RabbitConstants.TRANSACTION_SUCCEEDED_QUEUE)
    public void listenToTransactionSucceededEvent(TransactionSucceededEvent transactionSucceededEvent) {
        logger.info("[MessageListener] - Listening to TransactionSucceededEvent: {}", transactionSucceededEvent);
        messagingService.handleTransactionSucceededEvent(transactionSucceededEvent);
    }

    @RabbitListener(queues = RabbitConstants.TRANSACTION_FAILED_QUEUE)
    public void listenToTransactionFailedEvent(TransactionFailedEvent transactionFailedEvent) {
        logger.info("[MessageListener] - Listening to TransactionFailedEvent: {}", transactionFailedEvent);
        messagingService.handleTransactionFailedEvent(transactionFailedEvent);
    }

}

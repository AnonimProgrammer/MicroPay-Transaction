package com.transaction.messaging;

import com.transaction.config.RabbitConstants;
import com.transaction.dto.TransactionCreatedEvent;
import com.transaction.model.TransactionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final static Logger logger = LoggerFactory.getLogger(MessagePublisher.class);

    public MessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishTransactionCreatedEvent(TransactionModel transactionModel, Long paymentId) {
        TransactionCreatedEvent transactionCreatedEvent =
                new TransactionCreatedEvent(
                        transactionModel.getId(),
                        paymentId
                );

        logger.info("[MessagingPublisher] - Publishing TransactionCreatedEvent: {}", transactionCreatedEvent);
        rabbitTemplate.convertAndSend(
                RabbitConstants.TRANSACTION_EXCHANGE,
                RabbitConstants.TRANSACTION_CREATED_ROUTING_KEY,
                transactionCreatedEvent
        );
    }

//    public void publishTransactionValidationFailedEvent(Long paymentId, String errorMessage) {
//        TransactionValidationFailedEvent transactionValidationFailedEvent = new TransactionValidationFailedEvent(
//                paymentId,
//                "Validation failed: " + errorMessage
//        );
//        logger.info("[MessagingPublisher] - Publishing TransactionValidationFailedEvent: {}", transactionValidationFailedEvent);
//        rabbitTemplate.convertAndSend(
//                RabbitConstants.TRANSACTION_EXCHANGE,
//                RabbitConstants.TRANSACTION_VALIDATION_FAILED_ROUTING_KEY,
//                transactionValidationFailedEvent
//        );
//    }

}

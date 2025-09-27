//package com.docker.mstransaction.service.impl;
//
//import com.docker.mstransaction.dto.InitiateTransactionEvent;
//import com.docker.mstransaction.dto.TransactionFailedEvent;
//import com.docker.mstransaction.dto.TransactionSucceededEvent;
//import com.docker.mstransaction.exception.InvalidTransactionEventException;
//import com.docker.mstransaction.service.EventValidatorService;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validator;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//
//@Service
//public class EventValidatorServiceImpl implements EventValidatorService {
//
//    private final Validator validator;
//
//    public EventValidatorServiceImpl(Validator validator) {
//        this.validator = validator;
//    }
//
//    @Override
//    public void validate(InitiateTransactionEvent event) {
//        Set<ConstraintViolation<InitiateTransactionEvent>> violations = validator.validate(event);
//
//        if (!violations.isEmpty()) {
//            throw new InvalidTransactionEventException(buildViolationMessage("InitiateTransactionEvent", violations));
//        }
//
//        if (event.getSenderWalletId() != null &&
//                event.getSenderWalletId().equals(event.getReceiverWalletId())) {
//            throw new InvalidTransactionEventException("Sender and receiver wallet IDs must not be the same.");
//        }
//    }
//
//    @Override
//    public void validate(TransactionFailedEvent event) {
//        Set<ConstraintViolation<TransactionFailedEvent>> violations = validator.validate(event);
//
//        if (!violations.isEmpty()) {
//            throw new InvalidTransactionEventException(buildViolationMessage("TransactionFailedEvent", violations));
//        }
//    }
//
//    @Override
//    public void validate(TransactionSucceededEvent event) {
//        Set<ConstraintViolation<TransactionSucceededEvent>> violations = validator.validate(event);
//
//        if (!violations.isEmpty()) {
//            throw new InvalidTransactionEventException(buildViolationMessage("TransactionSucceededEvent", violations));
//        }
//    }
//
//    private <T> String buildViolationMessage(String eventType, Set<ConstraintViolation<T>> violations) {
//        StringBuilder sb = new StringBuilder("Validation failed for ").append(eventType).append(":");
//        for (ConstraintViolation<T> violation : violations) {
//            sb.append(" ").append(violation.getPropertyPath())
//                    .append(": ").append(violation.getMessage()).append(";");
//        }
//        return sb.toString();
//    }
//}

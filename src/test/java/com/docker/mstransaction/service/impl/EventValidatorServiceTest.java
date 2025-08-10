//package com.docker.mstransaction.service.impl;
//
//import com.docker.mstransaction.dto.InitiateTransactionEvent;
//import com.docker.mstransaction.dto.TransactionFailedEvent;
//import com.docker.mstransaction.dto.TransactionSucceededEvent;
//import com.docker.mstransaction.exception.InvalidTransactionEventException;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Path;
//import jakarta.validation.Validator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.Set;
//import java.util.UUID;
//
//import static org.assertj.core.api.AssertionsForClassTypes.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SuppressWarnings("unchecked")
//class EventValidatorServiceTest {
//
//    @Mock
//    private Validator validator;
//
//    @InjectMocks
//    private EventValidatorServiceImpl eventValidatorService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    private InitiateTransactionEvent createValidInitiateEvent() {
//        return new InitiateTransactionEvent(
//                1L,
//                2L,
//                3L,
//                BigDecimal.valueOf(100),
//                LocalDateTime.now()
//        );
//    }
//
//    private TransactionSucceededEvent createValidTransactionSucceededEvent() {
//        return new TransactionSucceededEvent(
//                UUID.randomUUID()
//        );
//    }
//
//    private TransactionFailedEvent createValidTransactionFailedEvent() {
//        return new TransactionFailedEvent(
//                UUID.randomUUID(),
//                "Insufficient funds"
//        );
//    }
//
//    @Test
//    void testValidateInitiateTransactionEvent() {
//        InitiateTransactionEvent event = createValidInitiateEvent();
//
//        when(validator.validate(event)).thenReturn(Collections.emptySet());
//        assertDoesNotThrow(() -> eventValidatorService.validate(event));
//    }
//
//    @Test
//    void testValidateInitiateTransactionEventThrowsExceptionIfWalletsAreSame() {
//        InitiateTransactionEvent event = createValidInitiateEvent();
//        event.setReceiverWalletId(event.getSenderWalletId());
//
//        when(validator.validate(event)).thenReturn(Collections.emptySet());
//
//        InvalidTransactionEventException exception = assertThrows(
//                InvalidTransactionEventException.class,
//                () -> eventValidatorService.validate(event)
//        );
//
//        assertTrue(exception.getMessage().contains("Sender and receiver wallet IDs must not be the same"));
//    }
//
//    @Test
//    void testThrowsForInvalidInitiateEvent() {
//        InitiateTransactionEvent event = createValidInitiateEvent();
//        event.setSenderWalletId(null);
//        event.setReceiverWalletId(-2L);
//        event.setInitiatedAt(null);
//
//        Path path1 = mock(Path.class);
//        Path path2 = mock(Path.class);
//        Path path3 = mock(Path.class);
//
//        when(path1.toString()).thenReturn("senderWalletId");
//        when(path2.toString()).thenReturn("receiverWalletId");
//        when(path3.toString()).thenReturn("initiatedAt");
//
//        ConstraintViolation<InitiateTransactionEvent> violation1 = mock(ConstraintViolation.class);
//        when(violation1.getPropertyPath()).thenReturn(path1);
//        when(violation1.getMessage()).thenReturn("Sender wallet ID cannot be null");
//
//        ConstraintViolation<InitiateTransactionEvent> violation2 = mock(ConstraintViolation.class);
//        when(violation2.getPropertyPath()).thenReturn(path2);
//        when(violation2.getMessage()).thenReturn("Receiver wallet ID must be a positive number");
//
//        ConstraintViolation<InitiateTransactionEvent> violation3 = mock(ConstraintViolation.class);
//        when(violation3.getPropertyPath()).thenReturn(path3);
//        when(violation3.getMessage()).thenReturn("Initiation time cannot be null");
//
//        Set<ConstraintViolation<InitiateTransactionEvent>> violations = Set.of(
//                violation1, violation2, violation3
//        );
//
//        when(validator.validate(event)).thenReturn(violations);
//
//        InvalidTransactionEventException exception = assertThrows(
//                InvalidTransactionEventException.class,
//                () -> eventValidatorService.validate(event)
//        );
//
//        assertThat(exception.getMessage()).contains("senderWalletId", "cannot be null");
//        assertThat(exception.getMessage()).contains("receiverWalletId", "must be a positive number");
//        assertThat(exception.getMessage()).contains("initiatedAt", "cannot be null");
//    }
//
//    @Test
//    void testValidateTransactionSucceededEvent() {
//        TransactionSucceededEvent event = createValidTransactionSucceededEvent();
//
//        when(validator.validate(event)).thenReturn(Collections.emptySet());
//        assertDoesNotThrow(() -> eventValidatorService.validate(event));
//    }
//
//    @Test
//    void testThrowsForInvalidSucceededEvent() {
//        TransactionSucceededEvent event = createValidTransactionSucceededEvent();
//        event.setTransactionId(null);
//
//        Path path = mock(Path.class);
//        when(path.toString()).thenReturn("transactionId");
//
//        ConstraintViolation<TransactionSucceededEvent> violation = mock(ConstraintViolation.class);
//        when(violation.getPropertyPath()).thenReturn(path);
//        when(violation.getMessage()).thenReturn("Transaction ID cannot be null");
//
//        when(validator.validate(event)).thenReturn(Set.of(violation));
//
//        InvalidTransactionEventException exception = assertThrows(
//                InvalidTransactionEventException.class,
//                () -> eventValidatorService.validate(event)
//        );
//
//        assertThat(exception.getMessage()).contains("transactionId", "cannot be null");
//    }
//
//    @Test
//    void testValidateTransactionFailedEvent() {
//        TransactionFailedEvent event = createValidTransactionFailedEvent();
//
//        when(validator.validate(event)).thenReturn(Collections.emptySet());
//        assertDoesNotThrow(() -> eventValidatorService.validate(event));
//    }
//
//    @Test
//    void testThrowsForInvalidFailedEvent() {
//        TransactionFailedEvent event = createValidTransactionFailedEvent();
//        event.setTransactionId(null);
//        event.setFailureReason(null);
//
//        Path path1 = mock(Path.class);
//        Path path2 = mock(Path.class);
//
//        when(path1.toString()).thenReturn("transactionId");
//        when(path2.toString()).thenReturn("failureReason");
//
//        ConstraintViolation<TransactionFailedEvent> violation1 = mock(ConstraintViolation.class);
//        when(violation1.getPropertyPath()).thenReturn(path1);
//        when(violation1.getMessage()).thenReturn("Transaction ID cannot be null");
//
//        ConstraintViolation<TransactionFailedEvent> violation2 = mock(ConstraintViolation.class);
//        when(violation2.getPropertyPath()).thenReturn(path2);
//        when(violation2.getMessage()).thenReturn("Failure reason cannot be blank");
//
//        when(validator.validate(event)).thenReturn(Set.of(violation1, violation2));
//
//        InvalidTransactionEventException exception = assertThrows(
//                InvalidTransactionEventException.class,
//                () -> eventValidatorService.validate(event)
//        );
//
//        assertThat(exception.getMessage()).contains("transactionId", "cannot be null");
//        assertThat(exception.getMessage()).contains("failureReason", "cannot be blank");
//    }
//
//}
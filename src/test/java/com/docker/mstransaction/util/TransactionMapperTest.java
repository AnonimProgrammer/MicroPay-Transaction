//package com.docker.mstransaction.util;
//
//import com.docker.mstransaction.model.TransactionStatus;
//import com.docker.mstransaction.model.entity.Transaction;
//import com.docker.mstransaction.model.TransactionModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TransactionMapperTest {
//
//    private TransactionMapper transactionMapper;
//
//    @BeforeEach
//    void setUp() {
//        transactionMapper = new TransactionMapperImpl();
//    }
//
//    @Test
//    public void testMapTransactionToModel() {
//        Transaction transaction = new Transaction.Builder()
//                .senderWalletId(1L)
//                .receiverWalletId(2L)
//                .amount(new BigDecimal("100.0"))
//                .status(TransactionStatus.PENDING)
//                .initiatedAt(LocalDateTime.of(2025, 7, 12, 12, 0))
//                .build();
//        transaction.setId(UUID.randomUUID());
//
//        TransactionModel model = transactionMapper.toModel(transaction);
//        System.out.println("TransactionModel: " + model);
//
//        assertNotNull(model);
//        assertEquals(transaction.getId(), model.getId());
//        assertEquals(transaction.getAmount(), model.getAmount());
//        assertEquals(transaction.getSenderWalletId(), model.getSenderWalletId());
//        assertEquals(transaction.getReceiverWalletId(), model.getReceiverWalletId());
//    }
//
//    @Test
//    public void testMapModelToTransaction() {
//        TransactionModel model = new TransactionModel.Builder()
//                .senderWalletId(1L)
//                .receiverWalletId(2L)
//                .amount(new BigDecimal("100.0"))
//                .initiatedAt(LocalDateTime.now())
//                .build();
//
//        Transaction transaction = transactionMapper.toEntity(model);
//
//        System.out.println("Transaction: " + transaction);
//
//        assertNotNull(transaction);
//
//        assertEquals(model.getAmount(), transaction.getAmount());
//        assertNotNull(transaction.getAmount());
//
//        assertEquals(model.getSenderWalletId(), transaction.getSenderWalletId());
//        assertNotNull(transaction.getSenderWalletId());
//
//        assertEquals(model.getReceiverWalletId(), transaction.getReceiverWalletId());
//        assertNotNull(transaction.getReceiverWalletId());
//    }
//}
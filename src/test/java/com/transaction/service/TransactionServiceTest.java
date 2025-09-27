//package com.docker.mstransaction.service;
//
//import com.docker.mstransaction.exception.TransactionNotFoundException;
//import com.docker.mstransaction.model.TransactionStatus;
//import com.docker.mstransaction.model.TransactionModel;
//import com.docker.mstransaction.model.entity.Transaction;
//import com.docker.mstransaction.repo.TransactionRepository;
//import com.docker.mstransaction.service.impl.TransactionServiceImpl;
//import com.docker.mstransaction.util.TransactionMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//class TransactionServiceTest {
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @Mock
//    private TransactionMapper transactionMapper;
//
//    @InjectMocks
//    private TransactionServiceImpl transactionService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    private TransactionModel createTestTransactionModel() {
//        return new TransactionModel.Builder()
//                .senderWalletId(1L)
//                .receiverWalletId(2L)
//                .amount(BigDecimal.valueOf(100))
//                .initiatedAt(LocalDateTime.now())
//                .build();
//    }
//
//    private Transaction createTestTransaction() {
//        return new Transaction.Builder()
//                .senderWalletId(1L)
//                .receiverWalletId(2L)
//                .amount(BigDecimal.valueOf(100))
//                .build();
//    }
//
//    private UUID generateTestUUID() {
//        return UUID.randomUUID();
//    }
//
//    @Test
//    void testCreateTransaction() {
//        TransactionModel transactionModel = createTestTransactionModel();
//        Transaction transaction = createTestTransaction();
//        transaction.setInitiatedAt(transactionModel.getInitiatedAt());
//
//        UUID testUUID = generateTestUUID();
//        LocalDateTime createdAt = LocalDateTime.now();
//
//        Transaction savedTransaction = createTestTransaction();
//        savedTransaction.setId(testUUID);
//        savedTransaction.setInitiatedAt(transactionModel.getInitiatedAt());
//        savedTransaction.setCreatedAt(createdAt);
//        savedTransaction.setStatus(TransactionStatus.PENDING);
//
//        TransactionModel savedTransactionModel = createTestTransactionModel();
//        savedTransactionModel.setId(testUUID);
//        savedTransactionModel.setInitiatedAt(transactionModel.getInitiatedAt());
//        savedTransactionModel.setCreatedAt(createdAt);
//        savedTransactionModel.setStatus(TransactionStatus.PENDING);
//
//        when(transactionMapper.toEntity(any(TransactionModel.class))).thenReturn(transaction);
//        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);
//        when(transactionMapper.toModel(any(Transaction.class))).thenReturn(savedTransactionModel);
//
//        TransactionModel created = transactionService.createTransaction(transactionModel);
//
//        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
//        verify(transactionRepository).save(captor.capture());
//        Transaction capturedTransaction = captor.getValue();
//
//        assertThat(capturedTransaction.getStatus()).isEqualTo(TransactionStatus.PENDING);
//        assertThat(capturedTransaction.getCreatedAt()).isNotNull();
//
//        assertThat(created.getId()).isEqualTo(testUUID);
//        assertThat(created.getStatus()).isEqualTo(TransactionStatus.PENDING);
//        assertThat(created.getInitiatedAt()).isEqualTo(transactionModel.getInitiatedAt());
//        assertThat(created.getCreatedAt()).isEqualTo(createdAt);
//
//        verify(transactionMapper).toEntity(transactionModel);
//        verify(transactionMapper).toModel(savedTransaction);
//    }
//
//    @Test
//    void testUpdateTransaction() {
//        UUID testUUID = generateTestUUID();
//        Transaction transaction = createTestTransaction();
//        transaction.setId(testUUID);
//        transaction.setInitiatedAt(LocalDateTime.of(2025, 7, 14, 21, 0));
//        transaction.setCreatedAt(LocalDateTime.of(2025, 7, 14, 21, 1));
//
//        when(transactionRepository.findById(testUUID)).thenReturn(Optional.of(transaction));
//        when(transactionRepository.save(transaction)).thenReturn(transaction);
//
//        transactionService.updateTransactionStatus(testUUID, TransactionStatus.FAILED, "Insufficient funds");
//
//        assertThat(transaction.getStatus()).isEqualTo(TransactionStatus.FAILED);
//        assertThat(transaction.getFailureReason()).isEqualTo("Insufficient funds");
//        assertThat(transaction.getUpdatedAt()).isNotNull();
//        verify(transactionRepository).save(transaction);
//    }
//
//    @Test
//    void testUpdateTransactionThrowsException() {
//        UUID testUUID = generateTestUUID();
//        when(transactionRepository.findById(testUUID)).thenReturn(Optional.empty());
//
//        assertThrows(TransactionNotFoundException.class,
//                () -> transactionService.updateTransactionStatus(testUUID, TransactionStatus.FAILED, "Insufficient funds"));
//    }
//
//    @Test
//    void testGetAllTransactions() {
//        Transaction transaction1 = createTestTransaction();
//        Transaction transaction2 = createTestTransaction();
//        TransactionModel model1 = createTestTransactionModel();
//        TransactionModel model2 = createTestTransactionModel();
//
//        when(transactionRepository.findAll()).thenReturn(List.of(transaction1, transaction2));
//        when(transactionMapper.toModel(transaction1)).thenReturn(model1);
//        when(transactionMapper.toModel(transaction2)).thenReturn(model2);
//
//        List<TransactionModel> result = transactionService.getAllTransactions();
//
//        assertThat(result).containsExactly(model1, model2);
//        verify(transactionRepository).findAll();
//        verify(transactionMapper).toModel(transaction1);
//        verify(transactionMapper).toModel(transaction2);
//    }
//
//    @Test
//    void testGetTransactionById() {
//        UUID testUUID = generateTestUUID();
//        Transaction transaction = createTestTransaction();
//        TransactionModel expectedModel = createTestTransactionModel();
//
//        when(transactionRepository.findById(testUUID)).thenReturn(Optional.of(transaction));
//        when(transactionMapper.toModel(transaction)).thenReturn(expectedModel);
//
//        TransactionModel result = transactionService.getTransactionById(testUUID);
//
//        assertThat(result).isEqualTo(expectedModel);
//        verify(transactionRepository).findById(testUUID);
//        verify(transactionMapper).toModel(transaction);
//    }
//
//    @Test
//    void testGetTransactionByIdThrowsException() {
//        UUID testUUID = generateTestUUID();
//        when(transactionRepository.findById(testUUID)).thenReturn(Optional.empty());
//
//        assertThrows(TransactionNotFoundException.class, () -> transactionService.getTransactionById(testUUID));
//        verify(transactionRepository).findById(testUUID);
//    }
//
//    @Test
//    void testGetTransactionsByFilters() {
//        Transaction transaction = createTestTransaction();
//        TransactionModel model = createTestTransactionModel();
//
//        when(transactionRepository.findByFilters(TransactionStatus.PENDING, 1L, 2L))
//                .thenReturn(List.of(transaction));
//        when(transactionMapper.toModel(transaction)).thenReturn(model);
//
//        List<TransactionModel> result = transactionService.getTransactionsByFilters(
//                TransactionStatus.PENDING, 1L, 2L);
//
//        assertThat(result).containsExactly(model);
//        verify(transactionRepository).findByFilters(TransactionStatus.PENDING, 1L, 2L);
//        verify(transactionMapper).toModel(transaction);
//    }
//
//    @Test
//    void testGetTransactionsByWalletId() {
//        Transaction transaction = createTestTransaction();
//        TransactionModel model = createTestTransactionModel();
//
//        when(transactionRepository.findByWalletId(1L)).thenReturn(List.of(transaction));
//        when(transactionMapper.toModel(transaction)).thenReturn(model);
//
//        List<TransactionModel> result = transactionService.getTransactionsByWalletId(1L);
//
//        assertThat(result).containsExactly(model);
//        verify(transactionRepository).findByWalletId(1L);
//        verify(transactionMapper).toModel(transaction);
//    }
//
//}
//

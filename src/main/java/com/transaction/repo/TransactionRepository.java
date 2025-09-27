package com.transaction.repo;

import com.transaction.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

//    @Query("SELECT t FROM Transaction t WHERE " +
//            "(:status IS NULL OR t.status = :status) AND " +
//            "(:senderWalletId IS NULL OR t.senderWalletId = :senderWalletId) AND " +
//            "(:receiverWalletId IS NULL OR t.receiverWalletId = :receiverWalletId)")
//    List<Transaction> findByFilters(@Param("status") TransactionStatus status,
//                                    @Param("senderWalletId") Long senderWalletId,
//                                    @Param("receiverWalletId") Long receiverWalletId);
//
//    @Query("SELECT t FROM Transaction t WHERE " +
//            "t.senderWalletId = :walletId OR t.receiverWalletId = :walletId")
//    List<Transaction> findByWalletId(@Param("walletId") Long walletId);

}

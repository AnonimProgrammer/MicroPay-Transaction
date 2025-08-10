package com.docker.mstransaction.repo;

import com.docker.mstransaction.model.TransactionStatus;
import com.docker.mstransaction.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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

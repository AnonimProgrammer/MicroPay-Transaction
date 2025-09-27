//package com.docker.mstransaction.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//
//public class TransactionValidationFailedEvent {
//
//    @NotNull(message = "Payment ID cannot be null")
//    private Long paymentId;
//
//    @NotBlank(message = "Failure reason cannot be blank")
//    private String failureReason;
//
//    public TransactionValidationFailedEvent() {}
//    public TransactionValidationFailedEvent(Long paymentId, String failureReason) {
//        this.paymentId = paymentId;
//        this.failureReason = failureReason;
//    }
//
//    // Getters and Setters
//    public Long getPaymentId() {
//        return paymentId;
//    }
//
//    public void setPaymentId(Long paymentId) {
//        this.paymentId = paymentId;
//    }
//
//    public String getFailureReason() {
//        return failureReason;
//    }
//
//    public void setFailureReason(String failureReason) {
//        this.failureReason = failureReason;
//    }
//
//    @Override
//    public String toString() {
//        return "TransactionValidationFailedEvent {" +
//                "paymentId = " + paymentId +
//                ", failureReason = '" + failureReason + '\'' +
//                '}';
//    }
//}
//

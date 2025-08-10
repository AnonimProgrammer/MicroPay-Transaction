package com.docker.mstransaction.dto;

import com.docker.mstransaction.model.EndpointType;
import com.docker.mstransaction.model.TransactionType;
import java.math.BigDecimal;

public class InitiateTransactionEvent {

    private Long paymentId;
    private BigDecimal amount;
    private String source;
    private EndpointType sourceType;
    private String destination;
    private EndpointType destinationType;
    private TransactionType type;

    public InitiateTransactionEvent() {}
    public InitiateTransactionEvent(Long paymentId, BigDecimal amount, String source, EndpointType sourceType, String destination, EndpointType destinationType, TransactionType type) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.source = source;
        this.sourceType = sourceType;
        this.destination = destination;
        this.destinationType = destinationType;
        this.type = type;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public EndpointType getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(EndpointType destinationType) {
        this.destinationType = destinationType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public EndpointType getSourceType() {
        return sourceType;
    }

    public void setSourceType(EndpointType sourceType) {
        this.sourceType = sourceType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InitiateTransactionEvent{" +
                "paymentId=" + paymentId +
                ", amount=" + amount +
                ", source='" + source + '\'' +
                ", sourceType=" + sourceType +
                ", destination='" + destination + '\'' +
                ", destinationType=" + destinationType +
                ", type=" + type +
                '}';
    }
}

package com.docker.mstransaction.config;

public class RabbitConstants {

    public static final String TRANSACTION_EXCHANGE = "transaction.event";

    public static final String TRANSACTION_INITIATE_QUEUE = "transaction.initiate.queue";
    public static final String TRANSACTION_CREATED_QUEUE = "transaction.created.queue";
    public static final String TRANSACTION_FAILED_QUEUE = "transaction.failed.queue";
    public static final String TRANSACTION_SUCCEEDED_QUEUE = "transaction.succeeded.queue";
    public static final String TRANSACTION_VALIDATION_FAILED_QUEUE = "transaction.validation.failed.queue";

    public static final String TRANSACTION_INITIATE_ROUTING_KEY = "transaction.initiate";
    public static final String TRANSACTION_CREATED_ROUTING_KEY = "transaction.created";
    public static final String TRANSACTION_FAILED_ROUTING_KEY = "transaction.failed";
    public static final String TRANSACTION_SUCCEEDED_ROUTING_KEY = "transaction.succeeded";
    public static final String TRANSACTION_VALIDATION_FAILED_ROUTING_KEY = "transaction.validation.failed";

}

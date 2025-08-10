package com.docker.mstransaction.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitConstants.TRANSACTION_EXCHANGE);
    }

    @Bean
    public Queue transactionCreatedQueue() {
        return new Queue(RabbitConstants.TRANSACTION_CREATED_QUEUE, true);
    }
    @Bean
    public Queue transactionInitiateQueue() {
        return new Queue(RabbitConstants.TRANSACTION_INITIATE_QUEUE, true);
    }
    @Bean
    public Queue transactionSucceededQueue() {
        return new Queue(RabbitConstants.TRANSACTION_SUCCEEDED_QUEUE, true);
    }
    @Bean
    public Queue transactionFailedQueue() {
        return new Queue(RabbitConstants.TRANSACTION_FAILED_QUEUE, true);
    }
    @Bean
    public Queue transactionValidationFailedQueue() {
        return new Queue(RabbitConstants.TRANSACTION_VALIDATION_FAILED_QUEUE, true);
    }

    @Bean
    public Binding transactionCreatedBinding() {
        return BindingBuilder
                .bind(transactionCreatedQueue())
                .to(topicExchange())
                .with(RabbitConstants.TRANSACTION_CREATED_ROUTING_KEY);
    }
    @Bean
    public Binding transactionInitiateBinding() {
        return BindingBuilder
                .bind(transactionInitiateQueue())
                .to(topicExchange())
                .with(RabbitConstants.TRANSACTION_INITIATE_ROUTING_KEY);
    }
    @Bean
    public Binding transactionSucceededBinding() {
        return BindingBuilder
                .bind(transactionSucceededQueue())
                .to(topicExchange())
                .with(RabbitConstants.TRANSACTION_SUCCEEDED_ROUTING_KEY);
    }
    @Bean
    public Binding transactionFailedBinding() {
        return BindingBuilder
                .bind(transactionFailedQueue())
                .to(topicExchange())
                .with(RabbitConstants.TRANSACTION_FAILED_ROUTING_KEY);
    }
    @Bean
    public Binding transactionValidationFailedBinding() {
        return BindingBuilder
                .bind(transactionValidationFailedQueue())
                .to(topicExchange())
                .with(RabbitConstants.TRANSACTION_VALIDATION_FAILED_ROUTING_KEY);
    }

}


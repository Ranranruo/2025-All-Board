package com.allboard.verification.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue emailQueue() {
        return new Queue("verification.email");
    }
    @Bean Queue phoneQueue() {
        return new Queue("verification.phone");
    }
    @Bean
    public Exchange exchange() {
        return new DirectExchange("verification");
    }
    @Bean
    public Binding emailBinding() {
        return BindingBuilder
                .bind(emailQueue())
                .to(exchange())
                .with("email")
                .noargs();
    }
    @Bean
    public Binding phoneBinding() {
        return BindingBuilder
                .bind(phoneQueue())
                .to(exchange())
                .with("phone")
                .noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

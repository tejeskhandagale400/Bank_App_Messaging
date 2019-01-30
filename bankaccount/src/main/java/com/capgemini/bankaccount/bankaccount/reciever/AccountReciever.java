package com.capgemini.bankaccount.bankaccount.reciever;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.capgemini.bankaccount.bankaccount.resource.AccountResource;
import com.capgemini.transaction.transaction.entity.Transaction;

@Component
public class AccountReciever {

	@Autowired
	private AccountResource accountResource;

	@Bean
	public Queue queue() {
		return new Queue("DepositeQueue", false);
	}

	
	  @Bean public Jackson2JsonMessageConverter converter() { return new
	  Jackson2JsonMessageConverter(); }
	 
	 
	@RabbitListener(queues = "DepositeQueue")
	public void processMessage(Transaction transaction) {
		System.out.println("Inside Reciever");
		accountResource.updateAccountBalance(transaction);
	}

	

}

package com.capgemini.transaction.transaction.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.capgemini.transaction.transaction.entity.Transaction;

@Component
//@Lazy
public class TransactionSender {

	@Autowired
	private RabbitMessagingTemplate template;

	@Bean
	public Queue depositeQueue() {
		return new Queue("DepositeQueue", false);
	}

	public void sendCurrentBalance(Transaction transaction) { //
		System.out.println(transaction);
		template.convertAndSend("DepositeQueue", transaction);

	}

	/*
	 * @Bean public MessageConverter jsonMessageConverter() { return new
	 * Jackson2JsonMessageConverter(); }
	 */

	/*
	 * public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
	 * final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	 * rabbitTemplate.setMessageConverter(jsonMessageConverter()); return
	 * rabbitTemplate; }
	 */
}

/**
 * 
 */
package com.trjoshi.aws.demo;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

/**
 * @author tapas
 *
 */
public class SQSAsyncDemo {

	/**
	 * @param args
	 * @throws JMSException
	 * @throws InterruptedException 
	 */
	public void runAsyncQueueDemo() throws JMSException, InterruptedException {
		
		// Create a new connection factory with all defaults (credentials and region)
		// set automatically
		SQSConnectionFactory connectionFactory = new SQSConnectionFactory(new ProviderConfiguration(),
				AmazonSQSClientBuilder.defaultClient());

		// Create the connection.
		SQSConnection connection = connectionFactory.createConnection();

		// Get the wrapped client
		AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();

		// Create an SQS queue named MyQueue, if it doesn't already exist
		if (!client.queueExists("MyAsyncDemoQueue")) {
			client.createQueue("MyAsyncDemoQueue");
		}
		// Create the nontransacted session with AUTO_ACKNOWLEDGE mode
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// Create a queue identity and specify the queue name to the session
		Queue queue = session.createQueue("MyAsyncDemoQueue");
		 
		// Create a producer for the 'MyAsyncQueue'
		MessageProducer producer = session.createProducer(queue);
		
		// Create the text message
		TextMessage message = session.createTextMessage("Test Message from Async Queue");
		 
		// Send the message
		producer.send(message);
		System.out.println("JMS Message ID" + message.getJMSMessageID());
		
		// Create a consumer for the 'MyQueue'.
		MessageConsumer consumer = session.createConsumer(queue);
		 
		// Instantiate and set the message listener for the consumer.
		consumer.setMessageListener(new MyQueueListener());
		 
		// Start receiving incoming messages.
		connection.start();
		 
		// Wait for 1 second. The listener onMessage() method is invoked when a message is received.
		Thread.sleep(1000);

	}

}

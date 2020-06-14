/**
 * 
 */
package com.trjoshi.aws.demo;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

/**
 * @author tapas
 *
 */
public class AmazonSQSDemo {

	private AmazonSQS sqsClient;
	private CreateQueueResult queueResult;
	{
		sqsClient = AmazonSQSClientBuilder.standard().build();
	}
	
	public void createAndPublishMessageToQueue() {
		queueResult = sqsClient.createQueue("MyDemoQueue");
		GetQueueUrlResult queueURL = sqsClient.getQueueUrl("MyDemoQueue");
		System.out.println("*********Publishing message to the queue***********");
		sqsClient.sendMessage(queueURL.getQueueUrl(), "My Test message from Java SDK");
	}
	
	public void consumeMessageFromQueue() {
		GetQueueUrlResult queueURL = sqsClient.getQueueUrl("MyDemoQueue");
		ReceiveMessageResult message = sqsClient.receiveMessage(queueURL.getQueueUrl());
		message.getMessages().forEach(m -> {
			System.out.println("*********Printing the message from queue*************** : "+m.getBody());
			System.out.println("After processing deleting the message from queue");
			sqsClient.deleteMessage(queueURL.getQueueUrl(), m.getReceiptHandle());
		});
		
	}
	

}

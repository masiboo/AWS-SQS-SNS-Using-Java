package com.trjoshi.aws.demo;

import javax.jms.JMSException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsSnsDemoApplication {

	public static void main(String[] args) throws InterruptedException, JMSException {
		SpringApplication.run(AwsSnsDemoApplication.class, args);
//		AmazonSQSDemo sqsDemo = new AmazonSQSDemo();
//		sqsDemo.createAndPublishMessageToQueue();
//		Thread.sleep(2000);
//		sqsDemo.consumeMessageFromQueue();
		
		//Async Queue using JMS
		SQSAsyncDemo asyncQueueDemo = new SQSAsyncDemo();
		asyncQueueDemo.runAsyncQueueDemo();
	}

}

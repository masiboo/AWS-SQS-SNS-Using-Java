/**
 * 
 */
package com.trjoshi.aws.demo;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;

/**
 * @author tapas
 *
 */
public class SNSPublisher {

	private static final String TOPIC_ARN_EMAIL = "Provide your topic arn here";

	private static final String EMAIL_SUBJECT = "This is a Test SNS Notification Mail";
	private static final String EMAIL_MESSAGE = "We are able to successfully trigger notification from SNS";
	
	private static final String EMAIL_SUBJECT_JAVA = "Test SNS Notification Mail From Java";
	private static final String EMAIL_MESSAGE_JAVA = "We are able to create topic, subscribe to that topic and publish to that topic from java";

	private static AmazonSNSClient snsClient = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// creating SNS client
		snsClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().build();

		// Publish message to the topic
//		snsClient.publish(TOPIC_ARN_EMAIL, EMAIL_MESSAGE, EMAIL_SUBJECT);
		
		// Create an Amazon SNS topic.
//		final CreateTopicRequest createTopicRequest = new CreateTopicRequest("MyEmailTopicFromJava");
//		final CreateTopicResult createTopicResponse = snsClient.createTopic(createTopicRequest);
//		
//		// Print the topic ARN.
//		String TOPIC_ARN_FROM_JAVA = createTopicResponse.getTopicArn();
//		System.out.println("TopicArn:" + createTopicResponse.getTopicArn());
//		// Print the request ID for the CreateTopicRequest action.
//		System.out.println("CreateTopicRequest: " + snsClient.getCachedResponseMetadata(createTopicRequest));
//		
//		final SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN_FROM_JAVA, "email", "xyz@gmail.com");
//		snsClient.subscribe(subscribeRequest);
		
		snsClient.publish("Give your topic arn",EMAIL_MESSAGE_JAVA,EMAIL_SUBJECT_JAVA);
	}

}

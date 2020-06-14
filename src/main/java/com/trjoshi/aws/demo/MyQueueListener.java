/**
 * 
 */
package com.trjoshi.aws.demo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author tapas
 *
 */
public class MyQueueListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
            // Cast the received message as TextMessage and print the text to screen.
			System.out.println("**********Inside the receive message queue listener************");
            System.out.println("Received: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
	}

}

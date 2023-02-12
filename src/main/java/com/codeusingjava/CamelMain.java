package com.codeusingjava;


import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import com.codeusingjava.route.JMSRouteBuilder;


public class CamelMain {

	public static void main(String[] args) throws JMSException {
		CamelContext ctx = new DefaultCamelContext();


		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		Connection connection = connectionFactory.createConnection();
		ActiveMQSession session = (ActiveMQSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		ctx.addComponent("jmsComponent", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		JMSRouteBuilder jmsRouteBuilder = new JMSRouteBuilder();


		try {
			ctx.addRoutes(jmsRouteBuilder);
			ctx.start();

			Thread.sleep(5 * 60 * 1000);
			ctx.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

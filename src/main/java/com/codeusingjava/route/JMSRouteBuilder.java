package com.codeusingjava.route;

import org.apache.camel.builder.RouteBuilder;
import java.io.File;


public class JMSRouteBuilder extends RouteBuilder {

	@Override
	public void configure() {
		from("file:src/data?noop=true").routeId("testRoute").process(msg -> {
			File fileName = msg.getIn().getBody(File.class);
			String fileContent = msg.getIn().getBody(String.class);
			System.out.println(  fileName+ " " + fileContent);
		})
		.to("jmsComponent:queue:codeusingjava-outputqueue");
	}
}

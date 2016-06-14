package at.ac.wmpm.booking.service;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ProducerTemplateProvider {

	private static CamelContext context;
	private static ProducerTemplate template;

	public static ProducerTemplate getTemplate() throws Exception {
		if (template == null) {
			ApplicationContext springcontext = new FileSystemXmlApplicationContext("src/main/resources/camel-config.xml");
			context = springcontext.getBean("myConfig", CamelContext.class);
	    	context.start();
	    	template = context.createProducerTemplate();
		}
		return template;
	}

}

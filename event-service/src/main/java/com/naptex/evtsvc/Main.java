package com.naptex.evtsvc;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.naptex.evtsvc.model.Event;
import com.naptex.evtsvc.model.Level;
import com.naptex.evtsvc.service.EventProcessorService;

public class Main
{
	final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args)
	{
		logger.info("main");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringConfig.class);
		EventProcessorService service = ctx
				.getBean(EventProcessorService.class);

		Event testEvt = new Event();
		testEvt.setLevel(Level.INFO);
		testEvt.setMessage("Test Event");
		testEvt.setTime(new Date());
		service.publishEvent(testEvt);
		((ConfigurableApplicationContext) ctx).close();
	}
}

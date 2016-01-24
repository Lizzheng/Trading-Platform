package com.fdmgroup.gateway;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GatewayListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		Gateway gateway = (Gateway) event.getServletContext().getAttribute(
				"gateway");

		new File(
				"H:\\Java\\Workspace\\TradePlatformAdmin\\WebContent\\AdminLog.log")
				.delete();
		new File(
				"H:\\Java\\Workspace\\TradePlatformAdmin\\WebContent\\UserLog.log")
				.delete();
		gateway.finalize();

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext cx = new ClassPathXmlApplicationContext(
				"SpringAOP.xml");
		ServletContext sc = event.getServletContext();

		AdminGateway gateway = (AdminGateway) cx.getBean("admingateway");

		sc.setAttribute("gateway", gateway);

	}
}

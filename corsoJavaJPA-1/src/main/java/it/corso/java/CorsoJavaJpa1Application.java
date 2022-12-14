package it.corso.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.corso.java.conf.AppConfig;

@SpringBootApplication()
//@Configuration
//@ComponentScan(basePackage=".")
//@AutoConfiguration
public class CorsoJavaJpa1Application {

	public static void main(String[] args) {
		
		//Costruire un oggetto di tipo ApplicationContext
		//avvio della fase di bootstrap
//		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationContext context = SpringApplication.run(CorsoJavaJpa1Application.class, args);
		//termine della fase di bootstrap
		

	}
	

}

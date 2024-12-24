package com.example.XML_demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XMLDemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-XML-based-AOP.xml");

		/* Example 1 */
		System.out.println("================== EXAMPLE 1 ==================");
//		Adder adder = new Adder();
//		Adder adder = (Adder) ctx.getBean("adder");
		Adder adder = ctx.getBean("adder", Adder.class);
		int res = adder.add(1, 2);
		System.out.println("res: " + res);

		/* Example 2 */
		System.out.println("================== EXAMPLE 2 ==================");
		Beverage beverage = ctx.getBean("beverage", Beverage.class);
		// Invoking set* methods will trigger the pointcut of validationAspect
		beverage.setCupSize("large");
		beverage.setSweet(null);	// Setting to null results in an error log
		beverage.setAddIce(true);
		// Invoking get* methods will not trigger the pointcut of ValidationAspect
		beverage.getSweet();


		System.out.println("================== EXAMPLE 3 ==================");
		Covid covid = ctx.getBean("covid", Covid.class);
		covid.someMethod();
		try {
			covid.throwExceptionMethod();
		} catch (Exception e) {
			System.out.println("Try-catch exception");
		}
		covid.testAroundMethod();
	}

}

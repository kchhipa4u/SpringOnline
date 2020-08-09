package com.onlinelearning.day10.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

class Human {

	public void speak() {

		System.out.print("This is Kanhaiya");

	}
}

class HumanDecorator implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {

		System.out.print("Hello ");

		Object retVal = invocation.proceed();

		System.out.println("!");

		return retVal;

	}
}


public class HumanAOPDemo {

	public static void main(String... args) {

		Human target = new Human();

		ProxyFactory pf = new ProxyFactory();

		pf.addAdvice(new HumanDecorator());

		pf.setTarget(target);

		Human proxy = (Human) pf.getProxy();

		target.speak();

		System.out.println("");

		proxy.speak();

	}
}

package com.onlinelearning.day10.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

class Agent {

	public void speak() {

		System.out.print("Bond");

	}
}

class AgentDecorator implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {

		System.out.print("James ");

		Object retVal = invocation.proceed();

		System.out.println("!");

		return retVal;

	}
}


public class AgentAOPDemo {

	public static void main(String... args) {

		Agent target = new Agent();

		ProxyFactory pf = new ProxyFactory();

		pf.addAdvice(new AgentDecorator());

		pf.setTarget(target);

		Agent proxy = (Agent) pf.getProxy();

		target.speak();

		System.out.println("");

		proxy.speak();

	}
}

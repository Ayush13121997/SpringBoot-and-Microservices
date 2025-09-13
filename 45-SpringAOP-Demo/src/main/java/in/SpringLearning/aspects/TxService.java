package in.SpringLearning.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TxService {
	
	@Pointcut("execution(public void in.SpringLearning.dao.EmployeeDao.saveEmployee())")
	public void p1() {
		
	}
	
	@Before("p1()")
	public void beginTx() {
		
		System.out.println("Transaction Started ....");
	}

	@AfterReturning("p1()")
	public void commitTx() {
		
		System.out.println("Transaction Commited ....");
	}
	
	@After("p1()")
	public void closeTx() {
		
		System.out.println("Transaction Closed ....");
	}
	
	@AfterThrowing(value = "p1()" , throwing = "ex")
	public void rollbackTx(Throwable ex) {
		
		System.out.println("Transaction Rollbacked ...." + ex.getMessage());
	}
	
	@Pointcut("execution(public * in.SpringLearning.dao.EmployeeDao.*(..))")
	public void p2() {
		
	}
	
	@AfterReturning(value = "p2()" , returning = "result")
	public void logResult(String result) {
		
		System.out.println("Method executed with result : " + result);
	}
	
	public void aroundTest(ProceedingJoinPoint pjp){
		
		System.out.println("Before Business Mehod Started....");
		
		try {
			Object object = pjp.proceed();
			
			System.out.println("Around Business Method Result : " + object);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("After Business Mehod Ended ....");
		
	}
	
	@Pointcut("@annotation(in.SpringLearning.annotation.MyTx)")
	public void p3() {
		
	}
	
	@Before("p3()")
	public void myTxBegin() {
		
		System.out.println("MyTx - Transaction Started ....");
	}
	
}

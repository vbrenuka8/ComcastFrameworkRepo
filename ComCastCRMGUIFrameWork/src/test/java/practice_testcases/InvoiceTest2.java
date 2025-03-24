package practice_testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InvoiceTest2 {
	
	@Test(retryAnalyzer=Com.ComCast.CRM.ListenerUtility.RetryListenerImp.class)
	public void activateSim() {
		System.out.println("execute createInvoiceTest");
		Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}

}

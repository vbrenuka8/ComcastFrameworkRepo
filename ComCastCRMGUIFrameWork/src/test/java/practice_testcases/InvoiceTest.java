package practice_testcases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Com.ComCast.CRM.Generic.BaseUtility.BaseClass;
//@Listeners(Com.ComCast.CRM.ListenerUtility.ListenerImpClass.class)
public class InvoiceTest extends BaseClass {

	@Test
	public void createInvoice() {
	System.out.println("execute createInvoice");
	String actTitle=driver.getTitle();
	System.out.println(actTitle);
	Assert.assertEquals(actTitle, "Login");
	System.out.println("step-1");
	System.out.println("step-2");
	System.out.println("step-3");
	System.out.println("step-4");
	}
	
	@Test
	public void createInvoiceWithContact() {
		System.out.println("execute createInvoiceWithContact");	
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
}

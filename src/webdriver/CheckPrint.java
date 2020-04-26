package webdriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckPrint {
	
@BeforeClass	
public void Recondition()
	{
	System.out.println("chuan bi data va moi truong test");	
	}	
	
@Test
public void TC01()
{
System.out.println("Run testcase 01");	
}
@Test
public void TC02()
{
System.out.println("Run testcase 02");	
}
@AfterClass
public void afterTest()
{
System.out.println("Dong project");	
}
}

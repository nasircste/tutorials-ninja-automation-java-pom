package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
    
      @Test(groups={"Regression","Master"})
      public void verify_account_registration() 
      {
    	  try {
    	  logger.info("*** Starting TC001 AccountRegistrationTest ***");
    	  HomePage hp=new HomePage(driver);
    	  hp.clickMyAccount();
    	  logger.info("Clicked on MyAccount Link...");
    	  hp.clickRegister();
    	  logger.info("Clicked on Register Link...");
    	  
    	  AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
    	  
    	  logger.info("Providing customer Details....");
    	  regpage.setFirstName(randomString().toUpperCase());
    	  regpage.setLastName(randomString().toUpperCase());
    	  regpage.setEmail(randomString()+"@gmail.com");
    	  regpage.setTelephone(randomNumbers());
    	  String password=randomAlphaNumeric();
    	  regpage.setPassword(password);
    	  regpage.setConfirmPassword(password);
    	  regpage.setPrivacyPolicy();
    	  //Thread.sleep(2000);
    	  regpage.clickContinue();
    	  
    	  logger.info("Validating expected message...");
    	  String confmsg=regpage.getConfirmationMsg();
	    	  if(confmsg.equals("Your Account Has Been Created!"))
	    	  {
	    		  Assert.assertTrue(true);
	    	  }
	    	  else
	    	  {
	    		  logger.error("Test failed...");
	    		  logger.debug("Debug logs...");
	    		  Assert.assertTrue(false);
	    	  }
    	  //Assert.assertEquals(confmsg, "Your Account Has Been Created!");
    	  }
    	  catch(Exception e)
    	  {
    		  
    		  Assert.fail(); 
    	  }
    	  logger.info("*** Finished  TC001 AccountRegistrationTest ***");
      }
      
}

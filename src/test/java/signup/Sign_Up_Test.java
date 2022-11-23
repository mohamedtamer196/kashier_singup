package signup;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Sign_Up_Test extends TestBase {
	
	Sign_Up_Page sign_up_page;
	
	//using Data Driven
	@DataProvider(name="testdata")
	public Object[][] name(){
		return new Object[][] {
			{"mohamed tamer","tamer Store","01094841295","tamer@gmail.com","Test@123","tamer@123"},
			{"mohamed tamer","tamer Store","phone number","tamer@gmail.com","Test@123","tamer@123"},
			{"mohamed tamer","tamer Store","01094841295","tamer","tamer@123","Test@123"},
			{"mohamed tamer elsayed ","tamer Store","01094841295","mohamed.tamer@gmail.com","tamer@123","tamer@123"}
		};
	}
	
	
	@Test(enabled = true , priority = 1 ,dataProvider = "testdata" )
	public void Sign_Test(String name , String store , String mobile ,String email , String pass,String confirm_pass) {
		
		//scrolling down
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,80)", "");
		
		sign_up_page = new Sign_Up_Page(driver);
		sign_up_page.signup(name, store, mobile, email, pass, confirm_pass);
		
		WebElement Msg_Result = driver.findElement(By.xpath("//*[@id=\"m_login\"]/div/div/div[2]/div[1]/h6"));
		String result = Msg_Result.getText();
		System.out.println("negative scenario >> " + result);
		
		if(Msg_Result.getText()=="User already exists with the email provided") {
			Assert.assertEquals(Msg_Result.getText(), "User already exists with the email provided");	
		}
		else if(Msg_Result.getText()=="Your mobile number is not valid") {
			Assert.assertEquals(Msg_Result.getText(), "Your mobile number is not valid");			
		}
		
		else if(Msg_Result.getText()=="Please make sure your email is in valid format") {
			Assert.assertEquals(Msg_Result.getText(), "Please make sure your email is in valid format");
		}
		else {
			String URL = driver.getCurrentUrl();
			Assert.assertEquals(URL, "https://merchant.kashier.io/en/dashboard" );
		}
	}
}

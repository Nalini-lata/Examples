package test_Scenarios;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import object.ForgetPasswordPage;
import object.SelectRolePage;
import object.SignInPage;

public class TestMethods_Login {
	WebDriver driver;
	protected static Logger log;
	
	//Initialize driver and URL Access

	@BeforeTest
	public void beforeTest() {
		log = LogManager.getLogger("NaliniQA");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://app.discoveryeducation.com/learn/signin");
		log.info("URL Accessed Successfully");
	}

	@Test
	public void login() {
		SelectRolePage page = new SelectRolePage(driver);
		page.selectRole("Student");

	}

	@Test

	public void signIn() {
		SignInPage input = new SignInPage(driver);
		input.signIn("Nlata", "Test@123");
		log.info("Tried to Login Discover Education with wrong credentials");
		String errorMessage = input.getErrorMessage();
		Assert.assertEquals(errorMessage, " Incorrect username and/or password. Passwords are case sensitive. ");

		log.info("Verified Message After inserting Wrong UserName&Password");

		String verifyMessage = input.verifyMessage1();
		Assert.assertEquals(verifyMessage, "Not sure which method to use?");
		System.out.println("Verified the message content successfully");

		log.info("Verified Text 'Not sure which method to use?' is present in the LoginPage");
	}

	@Test(dependsOnMethods = "signIn")
	public void clickForgetPasswordLink() throws InterruptedException {

		ForgetPasswordPage input1 = new ForgetPasswordPage(driver);
		input1.forgetPassword("nalinilata6666@gmail.com");
		
		log.info("Clicked on ForgetUName Pwd and provided Email & SentEmail btn");

		String checkMessage = input1.checkMessage();
		Assert.assertEquals(checkMessage, "Please check your email.");
		System.out.println(checkMessage);
		System.out.println("Verified the message content successfully");
		
		log.info("Checked Message'Please Check Your Email'");
	}

	@AfterTest
	public void afterTest() {

		driver.quit();

	}

}

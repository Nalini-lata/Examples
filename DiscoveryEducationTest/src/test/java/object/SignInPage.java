package object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage {

	WebDriver driver;

	public SignInPage(WebDriver driver) {

		this.driver = driver;

	}
	
	//Fields or Component on SignIn page

	By enterUName = By.id("username");
	By clkContinue = By.xpath("//button[contains(text(), 'Continue')]");
	By enterPwd = By.id("password");
	By clkSignIn = By.xpath("//button[contains(text(),'Sign in')]");
	By errorMessageElement = By.xpath("//span[text()=' Incorrect username and/or password. Passwords are case sensitive. ']");
	By useMethod = By.xpath("//*[contains(text(), 'Not sure which method to use?')]");

	public void signIn(String str, String str1) {
		driver.findElement(enterUName).sendKeys(str);
		driver.findElement(clkContinue).click();
		driver.findElement(enterPwd).sendKeys(str1);
		driver.findElement(clkSignIn).click();
		driver.findElement(useMethod);

	}

	public String getErrorMessage() {
		WebElement errorMessageElement1 = driver.findElement(errorMessageElement);
		String errorMessageText = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].textContent;", errorMessageElement1);
		return errorMessageText;
	}

	public String verifyMessage1() {
		WebElement verifyMethod = driver.findElement(useMethod);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verifyMethod);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String messageText = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;",
				verifyMethod);

		return messageText;
	}

}

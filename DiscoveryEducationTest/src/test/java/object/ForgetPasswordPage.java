package object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class ForgetPasswordPage {

	WebDriver driver;
	WebDriverWait wait;

	public ForgetPasswordPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	// Fields on ForgetPassword Page 

	By errorMessageElement = By.xpath("(//span[@class='comet-form__field-error-text'])[1]");
	By forgetUNamePwd = By.xpath("//a[contains(text(),'Forgot your username or password?')]");
	By enterEmailAddrs = By.id("username");
	By sendEmail = By.xpath("//button[contains(text(),'Send email')]");
	By emailMessage = By.xpath("//div[@class='content-success']//p[contains(text(), 'Please check your email')]");
	By returnToSignInBtn = By.xpath("//div[@class='content-success']//a[contains(text(), 'Return to sign-in')]");

	public String getErrorMessage() {
		WebElement errorMessageElement1 = driver.findElement(errorMessageElement);
		String errorMessageText = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].textContent;", errorMessageElement1);
		return errorMessageText;
	}

	public void forgetPassword(String str2) {
		driver.findElement(forgetUNamePwd).click();
		driver.findElement(enterEmailAddrs).sendKeys(str2);
		driver.findElement(sendEmail).click();

		// Switch to the new window
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!handle.equals(mainWindowHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}
	}

	public String checkMessage() {
		WebElement checkMessageContent = driver.findElement(emailMessage);

		String checkMessageText = (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].textContent;", checkMessageContent);
		return checkMessageText;

	}
}

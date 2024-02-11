package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectRolePage {

	WebDriver driver;

	public SelectRolePage(WebDriver driver) {

		this.driver = driver;

	}
	//  Select Role BeforeLoginPage
	
	By selectRole = By.linkText("Student");


	public void selectRole(String role) {
       driver.findElement(selectRole).click();
	}
}

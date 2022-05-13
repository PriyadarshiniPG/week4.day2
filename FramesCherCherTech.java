package week4.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.internal.DefaultMethodSelectorContext;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesCherCherTech {
	private String title2;

	public void nestedframe(ChromeDriver driver) {
		// siwtching to frame1
		WebDriver frame = driver.switchTo().frame("frame1");
		String title = frame.getTitle();
		System.out.println(title);
		// switching to the innerframe
		WebDriver frame2 = driver.switchTo().frame("frame3");

		WebElement checkbox = driver.findElement(By.id("a"));
		checkbox.click();
		String text = driver.findElement(By.xpath("//b[text()='Inner Frame Check box :']")).getText();
		System.out.println("default content" + " " + text);
		// switching back to the parent frame frame1
		WebDriver parentFrame = driver.switchTo().parentFrame();
		// send keys in topic field
		driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("Practice");
		// switching back to default content
		driver.switchTo().defaultContent();
		title2 = driver.getTitle();
		System.out.println("deafultcontent" + " " + title2);
		// switching the frame3 (based on index first check in the DOM which order the
		// frame is selected and then choose the index
		driver.switchTo().frame(1);
		WebElement dropdown = driver.findElement(By.id("animals"));
		Select select = new Select(dropdown);
		select.selectByIndex(2);
		driver.switchTo().defaultContent();

	}

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		FramesCherCherTech obj = new FramesCherCherTech();
		obj.nestedframe(driver);
	}
}

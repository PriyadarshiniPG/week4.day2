package week4.day2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import week4.day1.FramesCherCherTech;

public class TestLeafDropDownHandle {
	
		public static void main(String[] args) {
			
		
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.get("http://www.leafground.com/pages/Dropdown.html");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	
	//select by index
	WebElement index = driver.findElement(By.id("dropdown1"));
	Select select=new Select(index);
	select.selectByIndex(2);
	//select by text
	WebElement text = driver.findElement(By.name("dropdown2"));
	Select select1=new Select(text);
	select1.selectByVisibleText("Appium");
	//select by value
	WebElement value = driver.findElement(By.id("dropdown3"));
	Select select2=new Select(value);
	select2.selectByValue("3");
	
	//get the number of options from dropdown
	List<WebElement> dropdown = driver.findElements(By.xpath("//option[text()='Get the number of dropdown options']/following-sibling::option"));
	int size = dropdown.size();
	System.out.println(size);
	for(int i=0;i<size;i++) {
		System.out.println(dropdown.get(i).getText());
	}
	
	
	//select by using sendkeys
	driver.findElement(By.xpath("(//div[@class='example']//select)[5]")).sendKeys("Appium");
	
	
	//select the option
	WebElement option = driver.findElement(By.xpath("(//div[@class='example']//select)[6]"));
	Select select3=new Select(option);
	select3.selectByIndex(3);
	
	driver.close();

}
}
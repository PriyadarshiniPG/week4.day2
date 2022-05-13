package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("Demosalesmanager");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("crmsfa");
		driver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("Merge Contacts")).click();
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdFrom']/following-sibling::a//img[@alt='Lookup']")).click();
		String defaultwindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String>window=new ArrayList<String>(windowHandles);
		//new window for from
		String newwindow = window.get(1);
		driver.switchTo().window(newwindow);
		
	
		
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a)[1]")).click();
		System.out.println("deto(0)"+driver.switchTo().window(window.get(0)));
		System.out.println("address");
		System.out.println(driver.switchTo().window(defaultwindow));
		
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdTo']/following-sibling::a//img")).click();
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String>window1=new ArrayList<String>(windowHandles1);
		driver.switchTo().window(window1.get(1));
		//new window for to
		driver.findElement(By.xpath("(//div[contains(@class,'x-grid3-body')]//div/a)[1]")).click();
	//	System.out.println(driver.switchTo().window(defaultwindow));
		System.out.println("get(0)"+driver.switchTo().window(window1.get(0)));
        
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		System.out.println(text);
		alert.accept();
		if(driver.getTitle().equals("Merge Contacts | opentaps CRM")){
			System.out.println("Title Match");
		}
		
	}

}

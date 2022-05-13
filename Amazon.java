package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//enter the search element
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro",Keys.ENTER);
		String cost =" ";
		List<WebElement> findElements = driver.findElements(By.xpath("(//div[contains(@class,'a-section a-spacing-none a-spacing-top')]//span[@class='a-price-whole'])[1]"));
		for(int i=0;i<findElements.size();i++) {
			if(i==0) {
				 cost = findElements.get(i).getText();
				System.out.println("Cost "+" "+cost);
			}
				
				
			}
			
		String rating = driver.findElement(By.xpath("//div[@class='a-row a-size-small']/span[1]")).getAttribute("aria-label");
		System.out.println("rating of the product"+" "+rating);
		driver.getWindowHandle();
		//click on the link text
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();
		
		//new window opens
		Set<String> set = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(set);
		for(int i=0;i<window.size();i++) {
			if(i==1) {
		
		//switching to the new window
		driver.switchTo().window(window.get(i));
		
		//taking screenshot of the phone
		File sourceImage = driver.getScreenshotAs(OutputType.FILE);
		File destImage = new File("./screenshot/IMG001.png");
		FileUtils.copyFile(sourceImage, destImage);
		//add tocart
	    driver.findElement(By.id("add-to-cart-button")).click();
	    //cart total(cost)
	    Thread.sleep(2000);
	    
		WebElement findElement = driver.findElement(By.xpath("//span[contains(@class,'a-size-base-plus a-color-price a-text-bold')]/span"));
		//ask why we need to click
		
		String carttotal = findElement.getText();
		System.out.println("Rate in cart"+" "+carttotal);
		System.out.println("Cost of first prod"+" "+cost);
//		String replaceAll = carttotal.replaceAll("[^2-9.]", carttotal);
//		System.out.println(replaceAll);
		if(carttotal.contains(cost)) {
			System.out.println("Rate in cart"+" "+carttotal);
			System.out.println("The cart item matches");
		}
		else {
			System.out.println("The cart item is mismatch");
			driver.switchTo().window(window.get(i-1));
		}
		
		
		
	}

		}}}

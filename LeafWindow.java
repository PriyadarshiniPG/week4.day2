package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class LeafWindow {
	public void waitfor(ChromeDriver driver) throws InterruptedException {
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();
		Thread.sleep(5000);

	}

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// String defaultwindow = driver.getWindowHandle();
		System.out.println("Click button to open home page in New Window");
		driver.findElement(By.id("home")).click();

		System.out.println("Find the number of opened windows");
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> newwindowhandle = new ArrayList<String>(windowHandles);
		System.out.println(newwindowhandle.size());

		System.out.println("Close all except this window");
		driver.findElement(By.xpath("//button[text()='Do not close me ']")).click();
		Set<String> close = driver.getWindowHandles();
		List<String> closeall = new ArrayList<String>(close);
		for (int i = 0; i < closeall.size(); i++) {
			System.out.println(i);
			// System.out.println(closeall.size());
			if (i == 4 || i == 5) {
				driver.switchTo().window(closeall.get(i));
				System.out.println(driver.getCurrentUrl());

			}

			// if(i!=4&&i!=5) {
			// System.out.println("this window is closed ");
			// driver.switchTo().window(closeall.get(i));
			// System.out.println(driver.getCurrentUrl());
			// driver.close();
			//
			// }
			else {
				System.out.println("this is  child window");
				driver.switchTo().window(closeall.get(i));
				System.out.println(driver.getCurrentUrl());
				driver.close();
			}
		}

		LeafWindow obj = new LeafWindow();
		obj.waitfor(driver);

	}
}

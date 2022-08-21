package Project;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//To Launch Amazon.in
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		//To Search for Samsung
		WebElement Search = driver.findElement(By.id("twotabsearchtextbox"));
		Search.sendKeys("Samsung");
		
		//To Click Search Button using Xpath
		WebElement SearchButton = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		SearchButton.click();
		
		//To Print Product Details and Price
		List<WebElement> ProductList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		System.out.println("Total number of Products:"+ ProductList.size());
		
		List<WebElement> PriceList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price-whole']"));
		
		for(int i=0;i<ProductList.size();i++) {
			 
			 System.out.println(ProductList.get(i).getText()+" - "+PriceList.get(i).getText());
		 }
		
		//For Getting Parent Window Handler
		String ParentWin= driver.getWindowHandle();
		String ExpectedValue = ProductList.get(0).getText();

		// To Click on First Product Link
		ProductList.get(0).click();

		//To Switch to 2nd Window
		Set<String> AllWindowHandler =	driver.getWindowHandles();
		for(String win : AllWindowHandler ) {
			System.out.println(win);

			if(!win.equals(ParentWin)) {
				driver.switchTo().window(win);

			}
		}

		WebElement Title = driver.findElement(By.id("productTitle"));

		String Pro1 = Title.getText();

		// To Validation on parent and child windows
		if(Pro1.equals(ExpectedValue)) {
			System.out.println("TC passed : Title is matching");
		}else {
			System.out.println("TC Failed : Title is not matching");
		}
		//To Close All Tabs
		driver.quit();
		
		
		
		
		
		

	}

}

package intro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class YahooSearchMain {
	
	

	public static void main(String[] args) {
		//INICIALIZACION DE SYSTEM.SETPROPERTY()
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe"); //es el path de chrome windows

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS);//maneja los timeout y hace esperar con 30 sec
		driver.get("http://www.yahoo.com");//navega a URL
		WebElement searchBox = driver.findElement(By.id("uh-search-box"));//crea una variable y le asigna un web element
		WebElement searchButton = driver.findElement(By.id("uh-search-button"));//encuentra el boton buscar
		
		searchBox.clear();//limpia caja texto
		searchBox.sendKeys("Selenium");//envia el texto "selenium"
		searchButton.click();//da click on search
		
		WebElement seleniumLink = driver.findElement(By.linkText("Selenium - Web Browser Automation"));//busca el texto "selenium -web browser Automation"
		seleniumLink.click();//da click
		
		ArrayList<String> windowIds = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Number of windows: " + windowIds.size());
		
		for(String windowId: driver.getWindowHandles()) {
			driver.switchTo().window(windowId);
		}
		
		WebElement downloadLink = driver.findElement(By.linkText("Download"));
		downloadLink.click();
		
		driver.close();

	}

}

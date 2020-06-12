package classExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MovieMDB {

    static WebDriver driver;
    static WebDriverWait wait;

    public static void navegarSitio(String url) {

        System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().to(url);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }


    public static void verificarLandingPage() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("home_img_holder")));

    }

    public static void buscaPelicula() {

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q"))); // preguntar!  name: q

        textBox.sendKeys("IT");

        WebElement lupa = driver.findElement(By.id("suggestion-search-button"));
        lupa.click();

    }

    public static void listarPeliculas() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".article")));
        WebElement it = driver.findElement(By.cssSelector("[src='https://m.media-amazon.com/images/M/MV5BZDVkZmI0YzAtNzdjYi00ZjhhLWE1ODEtMWMzMWMzNDA0NmQ4XkEyXkFqcGdeQXVyNzYzODM3Mzg@._V1_UX32_CR0,0,32,44_AL_.jpg']"));
        it.click();

    }

}

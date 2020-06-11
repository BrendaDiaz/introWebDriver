package Tareas;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;

        import java.util.List;
        import java.util.concurrent.TimeUnit;

public class SongsSinatraParent {

    public static WebDriver driver;

    public static void navegar(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe"); //es el path de chrome windows
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS);//maneja los timeout y hace esperar con 30 sec
        driver.get(url);//navega a URL
        }

    public static void realizarLoginCorrecto(String usuario, String password) {
        WebElement linkLogin = driver.findElement(By.partialLinkText("log in "));
        linkLogin.click();//da click on search

//    HomePage:
//    linkLogin: txt="log in "
//
//    LoginPage:
//    usernameTxt: id="username"
//    passwordTxt: id="password"
//    loginBtn: value="Log In"
    }

    public static void validarHomePage() {
//    txtBienvenida:
//    imgSinatra: src="/images/sinatra.jpg"
//    linkLogin: txt="log in "
    }

    public static void validarMensajeBienvenida(String mensajeBienvenida) {
//    HomePage:
//    mensajeBienvenida: id="flash"
//    linkLogout: href="/logout"
    }

    public static void validateSongsPage() {
        WebElement songsTitle = driver.findElement(By.cssSelector("section h1"));
        String currentUrl = driver.getCurrentUrl();
        WebElement songsLink = driver.findElement(By.cssSelector("[href='/songs']"));
        String currentClass = songsLink.getAttribute("class");
        List<WebElement> listaCanciones = driver.findElements(By.cssSelector("#songs li"));

        if(songsTitle.isDisplayed()  &&
                currentUrl.endsWith("songs") &&
                currentClass.equals("current") &&
                listaCanciones.size() > 0) {
            System.out.println("Si estoy en la pagina de songs");
        }
        else {
            System.out.println("No estoy en la pagina de songs.");
            cerrarBrowser();

            System.exit(-1);
        }
    }
    public static void cerrarBrowser() {
    }
}
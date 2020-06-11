package classExercise;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
public class TelcelParent {
    static WebDriver driver;
    static WebDriverWait wait;

    public static void navegarSitio(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe");
        //  driver = new ChromeDriver();
        //Para maximizar pantalla y en modo incógnito
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
        driver.navigate().to(url);
    }

    public static void verificarLandingPage() {
        //verificar que existen estos elementos
        //logoTelcel:  css="[src='/content/dam/htmls/img/icons/logo-telcel.svg']"

        WebElement logoTelcel = driver.findElement(By.cssSelector("[src='/content/dam/htmls/img/icons/logo-telcel.svg']"));
        WebElement tiendaEnLinea = driver.findElement(By.cssSelector("[data-nombreboton='Tienda en linea superior']"));
        WebElement campoBusqueda = driver.findElement(By.cssSelector("#buscador-menu-input"));
        if (logoTelcel.isDisplayed() &&
                tiendaEnLinea.isDisplayed() &&
                campoBusqueda.isDisplayed() && campoBusqueda.isEnabled()) {
            System.out.println("Sí cargó la página principal de telcel");
        } else {
            System.out.println("No cargó la página");
            System.exit(-1);
        }
    }



    public static void listarTelefonos() {
        WebElement tiendaEnLinea = driver.findElement(By.cssSelector("[data-nombreboton='Tienda en linea superior']"));
        tiendaEnLinea.click();
        WebElement linkTelefonosCelulares = driver.findElement(By.cssSelector(".shortcut-container [data-nombreboton='Telefonos y smartphones']"));
        linkTelefonosCelulares.click();
    }

    public static void seleccionarEstado(String nombreEstado) {

        System.out.println("breakpoint instruction.");
        /*
        WebElement seleccionaEstadoDropdown = driver.findElement(By.cssSelector(".modal .chosen-single"));
        if (seleccionaEstadoDropdown.isDisplayed()) {
            seleccionaEstadoDropdown.click();
            WebElement estado = driver.findElement(By.cssSelector(".chosen-search input"));
            estado.sendKeys(nombreEstado);
            estado.sendKeys(Keys.ENTER);
            WebElement entrar = driver.findElement(By.cssSelector("#entrarPerfilador"));
            entrar.click();

        } else {
            System.out.println("Falló el modal");
            System.exit(-1);
        }*/

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal .chosen-single")));
        WebElement seleccionaEstadoDropdown = driver.findElement(By.cssSelector(".modal .chosen-single"));
        seleccionaEstadoDropdown.click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".chosen-search input")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".chosen-search input")));
        WebElement inputEstado = driver.findElement(By.cssSelector(".chosen-search input"));
        inputEstado.sendKeys(nombreEstado);
        //inputEstado.sendKeys(nombreEstado);
        WebElement opcionEstado = driver.findElement(By.cssSelector(".chosen-results li"));
        opcionEstado.click();
        WebElement botonEntrar = driver.findElement(By.id("entrarPerfilador"));
        botonEntrar.click();
        //.chosen-results li[data-option-array-index='15']
}


    public static void verificarPaginaResultados() {
        /*
        List<WebElement> celulares = driver.findElements(By.cssSelector(".comp-telcel-mosaico-equipos li"));
        System.out.println(celulares.size());
        if (celulares.size() > 1) {
            System.out.println("La lista se desplego correctamente.");
        }*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".comp-telcel-mosaico-equipos li")));
    }

    public static Celular capturarDatosCelular(int i) {
        WebElement textoMarcaModelo = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-marca"));
        String mm = textoMarcaModelo.getText();

        WebElement textoNombre = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-nombre-equipo"));
        String nombreEquipo = textoNombre.getText();


        WebElement textoPrecio = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-precio"));
        String precioEquipo = textoPrecio.getText();
        precioEquipo = precioEquipo.replace(",", "");
        precioEquipo = precioEquipo.replace("$", "");
        double pe = Double.parseDouble(precioEquipo);


        WebElement textoCapacidad = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-capacidad-numero"));
        String capacidadEquipo = textoCapacidad.getText();
        String[] datos = capacidadEquipo.split(" ");
        String capacidadString = datos[0];
        int numGigas = Integer.parseInt(capacidadString);


        return new Celular(mm, nombreEquipo, pe, numGigas);
    }

    public static void seleccionarCelular(int numCelular) {
        List<WebElement> celulares = driver.findElements(By.cssSelector(".comp-telcel-mosaico-equipos li"));
        System.out.println(celulares.size());
        WebElement celular = celulares.get(numCelular - 1);
        celular.click();
    }

    public static void validarDatosCelular(Celular primerCelular) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra #ecommerce-ficha-tecnica-modelo")));
        WebElement textoMarcaModelo = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra #ecommerce-ficha-tecnica-modelo"));
        String mm = textoMarcaModelo.getText();

        if(primerCelular.getMarcaModelo().equals(mm))
            System.out.println("La marca y modelo coinciden");
        else
            System.out.println("La marca y modelo no coinciden");

        WebElement textoNombre = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra #ecommerce-ficha-tecnica-nombre"));
        String nombreEquipo = textoNombre.getText();

        if(primerCelular.getNombre().equals(nombreEquipo))
            System.out.println("El nombre coincide");
        else
            System.out.println("El nombre no  coincide");


        WebElement textoPrecio = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra #ecommerce-ficha-tecnica-precio-obj"));
        String precioEquipo = textoPrecio.getText();
        precioEquipo = precioEquipo.replace(",", "");
        precioEquipo = precioEquipo.replace("$", "");
        double pe = Double.parseDouble(precioEquipo);

        if(primerCelular.getPrecio() == pe)
            System.out.println("El precio coincide");
        else
            System.out.println("El precio no coincide");


        WebElement textoCapacidad = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra .ecommerce-ficha-tecnica-opciones-compra-caracteristicas-etiqueta"));
        String capacidadEquipo = textoCapacidad.getText();
        String[] datos = capacidadEquipo.split(" ");
        String capacidadString = datos[0];
        int numGigas = Integer.parseInt(capacidadString);

        if(primerCelular.getCapacidadGB() == numGigas)
            System.out.println("La capacidad coincide");
        else
            System.out.println("La capacidad no coincide");
    }

    public static void cerrarBrowser() {
        driver.quit();
    }
}





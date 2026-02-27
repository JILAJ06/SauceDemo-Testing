import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SauceDemoAutomation {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("title")));
            System.out.println("Login exitoso.");

            List<WebElement> botonesCompra = driver.findElements(By.cssSelector("button.btn_inventory"));

            for (int i = 0; i < 4; i++) {
                botonesCompra.get(i).click();
                System.out.println("Producto " + (i + 1) + " agregado.");
            }

            WebElement cartBadge = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("shopping_cart_badge")));
            String cantidad = cartBadge.getText();

            System.out.println("--------------------------------------------------");
            System.out.println("RESULTADO FINAL:");
            System.out.println("Cantidad en el carrito: " + cantidad);

            if ("4".equals(cantidad)) {
                System.out.println("PRUEBA EXITOSA: Se agregaron 4 artículos correctamente.");
            } else {
                System.out.println("ERROR: Se esperaban 4 artículos, pero hay " + cantidad);
            }
            System.out.println("--------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
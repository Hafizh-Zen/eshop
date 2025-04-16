package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_addsToProductList(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement nameField = driver.findElement(By.name("productName"));
        WebElement quantityField = driver.findElement(By.name("productQuantity"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nameField.sendKeys("FunctionalTest Product");
        quantityField.sendKeys("42");
        submitButton.click();

        // Check the product list contains the new product
        WebElement body = driver.findElement(By.tagName("body"));
        String pageText = body.getText();

        assertTrue(pageText.contains("FunctionalTest Product"));
        assertTrue(pageText.contains("42"));
    }
}

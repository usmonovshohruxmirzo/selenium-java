package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceDemoTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    @Disabled
    public void loginTest() throws InterruptedException {
        driver.get("https://www.saucedemo.com");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys("standard_user");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce");

        Thread.sleep(5000);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        Thread.sleep(5000);

        WebElement inventoryContainer = driver.findElement(By.id("inventory_container"));
        assertTrue(inventoryContainer.isDisplayed());
    }

    @Test
    @Disabled
    public void loginFailureTest() throws InterruptedException {
        driver.get("https://www.saucedemo.com");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        Thread.sleep(3000);

        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));

        String expectedErrorMessage = "Epic sadface: Username is required";

        String actualErrorMessage = errorMessage.getText();

        System.out.println("Actual error message: " + actualErrorMessage);

        assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }

    @Test
    public void sidebarTest() throws InterruptedException {
        driver.get("https://www.saucedemo.com");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys("standard_user");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement menuButton = driver.findElement(By.className("bm-burger-button"));
        menuButton.click();

        WebElement sidebar = driver.findElement(By.className("bm-menu-wrap"));

        Thread.sleep(2000);

        assertTrue(sidebar.isDisplayed());
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

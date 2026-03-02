package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    By checkoutButton = By.id("checkout");
    By firstName = By.id("first-name");
    By postalCode = By.id("postal-code");
    By continueButton = By.id("continue");
    By finishButton = By.id("finish");
    By successMessage = By.className("complete-header");
    By lastName = By.id("last-name");

    public void clickCheckout() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By checkoutBtn = By.id("checkout");

        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));

        driver.findElement(checkoutBtn).click();

        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
    }

    public void fillCheckoutInformation() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("first-name")
        ));

        driver.findElement(By.id("first-name")).sendKeys("Farel");
        driver.findElement(By.id("last-name")).sendKeys("Nouval");
        driver.findElement(By.id("postal-code")).sendKeys("40281");
    }

    public void clickContinue() {

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("continue")
        )).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("finish")
        ));
    }

    public void clickFinish() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("finish")
        ));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("finish")
        )).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("complete-header")
        ));
    }

    public boolean isOrderSuccessful() {

        System.out.println("Checking success page - URL: " + driver.getCurrentUrl());
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("complete-header")
        ));

        return driver.findElement(By.className("complete-header"))
                .getText()
                .equals("Thank you for your order!");
    }
}
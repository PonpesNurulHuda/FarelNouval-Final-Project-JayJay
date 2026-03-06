package web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

        wait.until(ExpectedConditions.urlContains("cart.html"));

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.className("cart_item"), 0
        ));

        WebElement checkoutBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("checkout")
                )
        );

        checkoutBtn.click();

        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
    }

    public void fillCheckoutInformation() {

        wait.until(ExpectedConditions.urlContains("checkout-step-one"));

        WebElement firstNameField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("first-name"))
        );
        firstNameField.clear();
        firstNameField.sendKeys("Farel");

        WebElement lastNameField = driver.findElement(By.id("last-name"));
        lastNameField.clear();
        lastNameField.sendKeys("Nouval");

        WebElement postalField = driver.findElement(By.id("postal-code"));
        postalField.clear();
        postalField.sendKeys("40281");

        wait.until(driver ->
                firstNameField.getAttribute("value").equals("Farel") &&
                        lastNameField.getAttribute("value").equals("Nouval") &&
                        postalField.getAttribute("value").equals("40281")
        );
    }

    public void clickContinue() {

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("continue"))
        );

        continueBtn.click();

        if (driver.findElements(By.cssSelector("[data-test='error']")).size() > 0) {
            throw new RuntimeException("Checkout validation error muncul");
        }

        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
    }

    public void clickFinish() {

        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        WebElement finishBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("finish"))
        );

        finishBtn.click();

        wait.until(ExpectedConditions.urlContains("checkout-complete"));
    }

    public boolean isOrderSuccessful() {

        wait.until(ExpectedConditions.urlContains("checkout-complete"));

        WebElement success = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("complete-header")
                )
        );

        return success.getText().contains("Thank you");
    }
}
package web;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class InventoryPage {

    WebDriver driver;
    WebDriverWait wait;

    By inventoryItems = By.className("inventory_item");
    By cartBadge = By.className("shopping_cart_badge");
    By filterDropdown = By.className("product_sort_container");
    By productPrices = By.className("inventory_item_price");
    By productNames = By.className("inventory_item_name");
    By menuButton = By.id("react-burger-menu-btn");
    By logoutLink = By.id("logout_sidebar_link");
    By cartIcon = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addBackpackToCart() {

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("inventory_container")
        ));

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.className("inventory_item"), 5
        ));

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-backpack")
                )
        );

        button.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("shopping_cart_badge")
        ));
    }

    public String getCartBadgeCount() {
        return driver.findElement(cartBadge).getText();
    }

    public void selectFilter(String filterOption) {
        Select select = new Select(driver.findElement(filterDropdown));
        select.selectByVisibleText(filterOption);
    }

    public List<Double> getProductPrices() {
        return driver.findElements(productPrices)
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void logout() {

        WebElement menu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("react-burger-menu-btn")
                )
        );
        menu.click();

        WebElement logout = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("logout_sidebar_link")
                )
        );
        logout.click();

        wait.until(ExpectedConditions.urlContains("saucedemo.com/"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("login-button")
        ));    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("saucedemo.com");
    }

    public void clickCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By cartContainer = By.id("shopping_cart_container");

        wait.until(ExpectedConditions.elementToBeClickable(cartContainer)).click();

        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

}

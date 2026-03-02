package web;

import io.cucumber.java.en.*;
import io.cucumber.messages.types.Product;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.InventoryPage;
import hooks.WebHooks;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventorySteps {
    private WebDriver driver;
    private InventoryPage inventoryPage;

    public InventorySteps(WebHooks hooks) {
        this.driver = hooks.getDriver();
        this.inventoryPage = new InventoryPage(driver);
    }

    @When("User adds backpack to cart")
    public void user_adds_backpack_to_cart() {
        inventoryPage.addBackpackToCart();
    }

    @Then("Cart badge should show 1 item")
    public void cart_badge_should_show_1_item() {
        Assert.assertEquals("1", inventoryPage.getCartBadgeCount());
    }

    @When("User selects filter {string}")
    public void user_selects_filter(String filter) {
        inventoryPage.selectFilter(filter);
    }

    @Then("Products should be sorted by lowest price first")
    public void verify_sorted_by_price() {
        List<Double> prices = inventoryPage.getProductPrices();
        List<Double> sorted = prices.stream().sorted().toList();
        Assert.assertEquals(sorted, prices);
    }

    @Then("Products should be sorted in descending order")
    public void verify_sorted_by_name_desc() {
        List<String> names = inventoryPage.getProductNames();
        List<String> sorted = names.stream().sorted(Collections.reverseOrder()).toList();
        Assert.assertEquals(sorted, names);
    }

    @When("User logs out from application")
    public void user_logs_out() {
        inventoryPage.logout();
    }

    @Then("User should be redirected to login page")
    public void redirected_to_login_page() {
        Assert.assertTrue(inventoryPage.isOnLoginPage());
    }

}



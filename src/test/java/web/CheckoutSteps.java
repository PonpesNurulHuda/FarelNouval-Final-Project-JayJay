package web;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import web.CheckoutPage;
import web.InventoryPage;
import hooks.WebHooks;

public class CheckoutSteps {

    private WebDriver driver;
    private InventoryPage inventoryPage;
    private CheckoutPage checkoutPage;

    public CheckoutSteps(WebHooks hooks) {
        this.driver = hooks.getDriver();
        this.inventoryPage = new InventoryPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
    }

    @When("User clicks cart icon")
    public void user_clicks_cart_icon() {
        inventoryPage.clickCart();
    }

    @When("User clicks checkout button")
    public void user_clicks_checkout_button() {
        checkoutPage.clickCheckout();
    }

    @When("User fills checkout information")
    public void user_fills_checkout_information() {
        checkoutPage.fillCheckoutInformation();
    }

    @When("User clicks continue")
    public void user_clicks_continue() {
        checkoutPage.clickContinue();
    }

    @When("User clicks finish")
    public void user_clicks_finish() {
        checkoutPage.clickFinish();
    }

    @Then("Order should be completed successfully")
    public void order_should_be_completed_successfully() {
        Assert.assertTrue(checkoutPage.isOrderSuccessful());
    }
}
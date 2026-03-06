package web;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.LoginPage;
import hooks.WebHooks;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    public LoginSteps(WebHooks hooks) {
        this.driver = hooks.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    @Given("User is on login page")
    public void user_is_on_login_page(){
        loginPage.openPage();
    }

    @When("User enters valid username and password")
    public void user_enters_valid_credentials(){
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
    }

    @When("User enters invalid username and password")
    public void user_enters_invalid_credentials(){
        loginPage.inputUsername("invalid_user");
        loginPage.inputPassword("wrong_password");
    }

    @And("User clicks login button")
    public void user_clicks_login_button() {
        loginPage.clickLogin();


    }

    @Then("User should be redirected to homepage")
    public void user_redirected_homepage() {

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("inventory_container")
                ));

        assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Then("Error message should be displayed")
    public void error_message_displayed() {

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test='error']")
                ));

        assertTrue(loginPage.isErrorDisplayed());
    }

    @Given("User is logged in successfully")
    public void user_logged_in_successfully() {
        loginPage.openPage();
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLogin();
    }


}

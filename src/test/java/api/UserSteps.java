package api;

import api.ApiClient;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class UserSteps {

    Response response;
    String userId = "60d0fe4f5311236168a109ca";
    String createdUserId;

    @Given("User has valid user id {string}")
    public void user_has_valid_id(String id) {
        userId = id;
    }

    @When("Send GET request using saved user id")
    public void get_created_user() {
        response = ApiClient.request()
                .get("/user/" + createdUserId);
    }

    @When("Send POST request to create user")
    public void create_user() {

        Map<String, String> body = new HashMap<>();
        body.put("firstName", "Farel");
        body.put("lastName", "Nouval");
        body.put("email", "qa" + System.currentTimeMillis() + "@mail.com");

        response = ApiClient.request()
                .body(body)
                .post("/user/create");

        createdUserId = response.jsonPath().getString("id");
    }

    @When("Send PUT request to update saved user")
    public void update_user() {

        Map<String, String> body = new HashMap<>();
        body.put("firstName", "UpdatedName");

        response = ApiClient.request()
                .body(body)
                .put("/user/" + createdUserId);
    }

    @When("Send DELETE request to delete saved user")
    public void delete_user() {
        response = ApiClient.request()
                .delete("/user/" + createdUserId);
    }

    @When("Send GET request to get list of tags")
    public void send_get_tags() {
        response = ApiClient.request()
                .get("/tag");
    }

    @Then("Response status code should be {int}")
    public void validate_status(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @And("Save created user id")
    public void save_user_id() {
        createdUserId = response.jsonPath().getString("id");
    }

    @When("Send GET request with invalid user id")
    public void get_invalid_user() {
        response = ApiClient.request()
                .get("/user/invalid-id-12345");
    }


}
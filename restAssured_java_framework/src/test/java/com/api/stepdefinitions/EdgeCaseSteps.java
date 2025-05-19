package com.api.stepdefinitions;

import com.api.pojo.Item;
import com.api.utils.APIUtils;
import com.api.utils.ScenarioContext;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class EdgeCaseSteps {

    @When("I request to get the item by ID {string}")
    public void getItemBySpecificId(String id) {
        try {
            Response response = APIUtils.getObject(id);
            ScenarioContext.set("response", response);
        }catch ( Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    @And("the item details are stored correctly")
    public void verifyItemStorage() {
        Response response = ScenarioContext.get("response");
        String actualName = JsonPath.from(response.asString()).getString("name");
        Item item = new Item();
        item.setName(actualName);
        ScenarioContext.set("storedItem", item);

        // Additional verifications
        Assert.assertNotNull(JsonPath.from(response.asString()).getString("id"));
        Assert.assertEquals(JsonPath.from(response.asString()).getString("name"),
                item.getName());
    }

    @When("I send an invalid JSON payload")
    public void sendInvalidJsonPayload() {
        String invalidJson = "{\n" +
                "   \"name\": \"Apple MacBook Pro 16\"11,\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": \"1849.99\",\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";
        Response response = APIUtils.createObject(invalidJson);
        ScenarioContext.set("response", response);
    }
}


package com.api.stepdefinitions;

import com.api.pojo.Item;
import com.api.utils.APIUtils;
import com.api.utils.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectSteps {
    private Item currentItem;
    private List<String> createdItemIds = new ArrayList<>();


    @Given("a {string} item is created")
    public void setItemName(String name) {
        currentItem = new Item(name);
    }

    @And("is a {string} CPU model")
    public void setCpuModel(String cpuModel) {
        currentItem.addData("cpu", cpuModel);
    }

    @And("has a price of {string}")
    public void setPrice(String price) {
        if (price == null || price.trim().isEmpty()) {
            currentItem.addData("price", null+"\n11");
        } else {
            currentItem.addData("price", Double.parseDouble(price));
        }
    }

    @When("the request to add the item is made")
    public void addItem() {
        Response response = APIUtils.createObject(currentItem);
        ScenarioContext.set("response", response);

        if (response.getStatusCode() == 200) {
            String id = JsonPath.from(response.asString()).getString("id");
            ScenarioContext.set("itemId", id);
            createdItemIds.add(id);
        }
    }

    @Then("a {int} response code is returned")
    public void verifyResponseCode(int expectedCode) {
        Response response = ScenarioContext.get("response");
        Assert.assertEquals(response.getStatusCode(), expectedCode);
    }

    @And("a {string} is created")
    public void verifyItemName(String expectedName) {
        Response response = ScenarioContext.get("response");
        String actualName = JsonPath.from(response.asString()).getString("name");
        Assert.assertEquals(actualName, expectedName);
    }

    @Given("an item exists in the system")
    public void createAnItem() {
        // Create a test item
        Item testItem = new Item("Test Item");
        testItem.addData("description", "Test item for verification");
        testItem.addData("price", 999.99);

        Response response = APIUtils.createObject(testItem);
        Assert.assertEquals(response.getStatusCode(), 200);

        String id = JsonPath.from(response.asString()).getString("id");
        ScenarioContext.set("itemId", id);
        createdItemIds.add(id);
    }

    @When("I request to get the item by its ID")
    public void getItemById() {
        String id = ScenarioContext.get("itemId");
        Response response = APIUtils.getObject(id);
        System.out.println("Response Print from by its id steep"+response);
        ScenarioContext.set("response", response);
    }

    @And("the item details match the expected values")
    public void verifyItemDetails() {
        Response response = ScenarioContext.get("response");
        String id = ScenarioContext.get("itemId");
        String actualId = JsonPath.from(response.asString()).getString("id");
        Assert.assertEquals(actualId, id);
    }

    @Given("multiple items exist in the system")
    public void createMultipleItems() {
        // Create several test items
        for (int i = 1; i <= 3; i++) {
            Item testItem = new Item("Test Item " + i);
            testItem.addData("index", i);

            Response response = APIUtils.createObject(testItem);
            Assert.assertEquals(response.getStatusCode(), 200);

            String id = JsonPath.from(response.asString()).getString("id");
            ScenarioContext.set("itemId" + i, id);
            createdItemIds.add(id);
        }
    }

    @When("I request to list all objects by ids")
    public void getMultipleItems() {
            String ids = "id="+createdItemIds.get(0)+"&id="+createdItemIds.get(1)+"&id="+createdItemIds.get(2);
            Response response = APIUtils.getListOfObjectByIDs(ids);
            ScenarioContext.set("response", response);
    }

    @And("the response contains all created items")
    public void verifyAllItems() {
        Response response = ScenarioContext.get("response");
        List<Map<String, Object>> items = JsonPath.from(response.asString()).getList("$");
        Assert.assertTrue(items.size() > 0);

        // Check if our created items are in the list
        for (int i = 1; i <= 3; i++) {
            String expectedId = ScenarioContext.get("itemId" + i);
            boolean found = items.stream()
                    .anyMatch(item -> expectedId.equals(item.get("id")));
            Assert.assertTrue(found, "Item with ID " + expectedId + " not found in the response");
        }
    }

    @When("I request to delete the item by its ID")
    public void deleteItem() {
        String id = ScenarioContext.get("itemId");
        Response response = APIUtils.deleteObject(id);
        ScenarioContext.set("response", response);
        // Remove from cleanup list as we're deleting it now
        createdItemIds.remove(id);
    }

    @And("the item is successfully deleted")
    public void verifyItemDeleted() {
        Response getResponse = APIUtils.getObject(ScenarioContext.get("itemId"));
        Assert.assertEquals(getResponse.getStatusCode(), 404);
    }

    @When("I update the item name to {string}")
    public void updateItemName(String newName) {
        String id = ScenarioContext.get("itemId");
        Item updatedItem = new Item(newName);

        Response response = APIUtils.updateObject(id, updatedItem);
        ScenarioContext.set("response", response);
        ScenarioContext.set("updatedName", newName);
    }

    @And("the item name is updated successfully")
    public void verifyNameUpdated() {
        String updatedName = ScenarioContext.get("updatedName");
        Response response = ScenarioContext.get("response");
        String actualName = JsonPath.from(response.asString()).getString("name");
        Assert.assertEquals(actualName, updatedName);
    }

}


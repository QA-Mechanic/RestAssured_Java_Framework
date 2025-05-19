package com.api.stepdefinitions;

import io.cucumber.java.Scenario;
import com.api.utils.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;


public class Hooks {
    private static final String BASE_URL = "https://api.restful-api.dev";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    @Before
    public void beforeScenario() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        ScenarioContext.clear();

    }

    @After
    public void afterScenario(Scenario scenario) {
        // Optionally clear again to be extra safe
        ScenarioContext.clear();
        if (scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName());
        }
    }
}

package com.api.utils;

import com.api.pojo.Item;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtils {

    public static RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .contentType(ContentType.JSON);
    }

    public static Response createObject(Item item) {
        return getRequestSpec()
                .body(item)
                //.log().headers() // Log the entire request
                .when()
                .post("/objects")
                .then()
                //.log().headers()// Log the entire response
                .extract()
                .response();
    }

    public static Response createObject(String rawJson) {
        return getRequestSpec()
                .body(rawJson)
                //.log().all() // Log the entire request
                .when()
                .post("/objects")
                .then()
                //.log().headers() // Log the entire response
                .extract().response();
    }

    public static Response getObject(String id) {
        Response response = getRequestSpec()
                .when()
                .get("/objects/" + id);
        response.then();//.log().headers();
        return response;
    }

    public static Response getAllObjects() {
        return getRequestSpec()
                .when()
                .get("/objects");
    }

    public static Response getListOfObjectByIDs(String ids) {
        Response response = getRequestSpec()
                .when()
                .get("/objects?"+ ids);
        response.then();//.log().headers();
        return response;
    }

    public static Response deleteObject(String id) {
        return getRequestSpec()
                .when()
                .delete("/objects/" + id);
    }

    // Add to APIUtils.java
    public static Response updateObject(String id, Item item) {
        return getRequestSpec()
                .body(item)
                .when()
                .put("/objects/" + id);

    }
}


package com.bank.service.boot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class TransferControllerRestAssuredTest {
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeAll
    public void initializeRestAssuredMockMvcWebApplicationContext() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        given().get("/transfer").then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void transferMoneySuccess() {
        given().get("/transfer/123456/654321/200").then().statusCode(HttpStatus.OK.value());
        float balanceFrom = given().get("/account/123456").then().assertThat().statusCode(HttpStatus.OK.value()).extract().path("balance");
        float balanceTo = given().get("/account/654321").then().assertThat().statusCode(HttpStatus.OK.value()).extract().path("balance");
        assertEquals(4800.0, balanceFrom);
        assertEquals(5200.0, balanceTo);
    }

    @Test
    public void transferMoneyFailure() {
        //given().get("/transfer").then().statusCode(HttpStatus.OK.value());
        given().get("/transfer/123456/654321/-20").then().statusCode(400);
    }
}

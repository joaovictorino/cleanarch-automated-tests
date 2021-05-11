package com.bank.service.boot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
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
public class TransferControllerRestAssuredMockTest {
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeAll
    public void initializeRestAssuredMockMvcWebApplicationContext() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void testTransferMoneySuccess() {
        given().post("/transfer/987321/123789/200").then().statusCode(HttpStatus.OK.value());
        float balanceFrom = given().get("/account/987321").then().assertThat().statusCode(HttpStatus.OK.value()).extract().path("balance");
        float balanceTo = given().get("/account/123789").then().assertThat().statusCode(HttpStatus.OK.value()).extract().path("balance");
        assertEquals(4800.0, balanceFrom);
        assertEquals(5200.0, balanceTo);
    }

    @Test
    public void testTransferMoneyFailure() {
        given().post("/transfer/987321/123789/-20").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }
}

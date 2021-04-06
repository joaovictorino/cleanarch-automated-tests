package com.bank.service.boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.cucumber.java.en.Then;

@SpringBootTest
@CucumberContextConfiguration
public class TransferControllerCucumberTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Given("conta {string} com saldo {double} e a conta {string} com saldo {double}")
    public void testCreateTwoAccounts(String numberFrom, Double balanceFrom, String numberTo, Double balanceTo) {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        given().post(String.format("/account/%s/%f", numberFrom, balanceFrom)).then().statusCode(HttpStatus.OK.value());
        given().post(String.format("/account/%s/%f", numberTo, balanceTo)).then().statusCode(HttpStatus.OK.value());
    }
    
    @When("a conta {string} transferir {double} para a conta {string}")
    public void testTransferMoney(String from, Double value, String to) {
        given().post(String.format("/transfer/%s/%s/%f", from, to, value)).then().statusCode(HttpStatus.OK.value());
    }

    @Then("o saldo da conta {string} deve ser {double} e a conta {string} {double}")
    public void testAssertBalance(String numberFrom, Double balanceFrom, String numberTo, Double balanceTo) {
        float balanceAccountFrom = given().get(String.format("/account/%s", numberFrom)).then().assertThat().statusCode(HttpStatus.OK.value()).extract().path("balance");
        float balanceAccountTo = given().get(String.format("/account/%s", numberTo)).then().assertThat().statusCode(HttpStatus.OK.value()).extract().path("balance");
        
        assertEquals(balanceFrom, balanceAccountFrom);
        assertEquals(balanceTo, balanceAccountTo);
    }
}

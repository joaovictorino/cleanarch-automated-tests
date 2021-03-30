package com.bank.service.boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bank.account.model.Account;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.path.json.JsonPath;
import io.cucumber.java.en.Then;

@SpringBootTest
@CucumberContextConfiguration
public class TransferControllerCucumberTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Given("account {string} with balance {double} and account {string} with balance {double}")
    public void createTwoAccounts(String numberFrom, Double balanceFrom, String numberTo, Double balanceTo) {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        given().post(String.format("/account/%s/%f", numberFrom, balanceFrom)).then().statusCode(HttpStatus.OK.value());
        given().post(String.format("/account/%s/%f", numberTo, balanceTo)).then().statusCode(HttpStatus.OK.value());
    }
    
    @When("account {string} transfer {double} to account {string}")
    public void transferMoney(String from, Double value, String to) {
        given().post(String.format("/transfer/%s/%s/%f", from, to, value)).then().statusCode(HttpStatus.OK.value());
    }

    @Then("the balance of account {string} should be {double} and account {string} {double}")
    public void assertBalance(String numberFrom, Double balanceFrom, String numberTo, Double balanceTo) {
        float balanceAccountFrom = given().get(String.format("/account/%s", numberFrom)).then().assertThat().statusCode(HttpStatus.OK.value()).extract().path("balance");
        float balanceAccountTo = given().get(String.format("/account/%s", numberTo)).then().assertThat().statusCode(HttpStatus.OK.value()).extract().path("balance");
        
        assertEquals(balanceFrom, balanceAccountFrom);
        assertEquals(balanceTo, balanceAccountTo);
    }
}

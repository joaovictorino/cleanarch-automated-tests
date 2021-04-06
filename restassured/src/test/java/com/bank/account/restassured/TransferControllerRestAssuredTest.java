import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;

import io.restassured.RestAssured;

public class TransferControllerRestAssuredTest {
    
    @BeforeAll
    public static void setBaseURI() {
        RestAssured.baseURI = "http://localhost:8080";
        get("/transfer").then().statusCode(200);
    }

    @Test
    public void testTransferMoneySuccess() {
        post("/transfer/123456/654321/200").then().statusCode(200);
        float balanceFrom = get("/account/123456").then().assertThat().statusCode(200).extract().path("balance");
        float balanceTo = get("/account/654321").then().assertThat().statusCode(200).extract().path("balance");
        assertEquals(4800.0, balanceFrom);
        assertEquals(5200.0, balanceTo);
    }

    @Test
    public void testTransferMoneyFailure() {
        post("/transfer/123456/654321/-20").then().statusCode(400);
    }
}

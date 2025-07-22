package com.example.Cards_Service;

import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CardsServiceApplicationTests extends BaseTest {

	static String cardId;
	static Card card;

	@Test
	@Order(1)
	public void createCardTest() {
		String requestBody = """
			{
				"expiration": "12/30",
				"number": "5254800000058010",
				"name": "Test 24User",
				"cvc": "121"
			}
		""";

		 card = given()
				.contentType("application/json")
				.body(requestBody)
				.queryParam("userId", "user-oo1")
				.when()
				.post(BASE_URL)
				.then()
				.statusCode(200)
				.extract().as(Card.class);

		System.out.println("Card created with ID: " + card.getCardId());
		cardId= card.getCardId();
	}

	@Test
	@Order(2)
	public void createCard400BadResquestTest(){
		String requestBody = """
			{
				"expiration": "12/30",
				"number": "5254800000058010",
				"name": "Test 24User",
				"cvc": "121"
			}
		""";
		String response= given()
				.contentType("application/json")
				.body(requestBody)
				.queryParam("userId", "user-oo1")
				.when()
				.post(BASE_URL)
				.then()
				.statusCode(400)
				.extract().asString();
		System.out.println("Response" + response);
	}


	@Test
	@Order(3)
	public void getCardByIdTest() {
		// exite en la base de datos
		String response = given()
				.pathParam("cardId", cardId)
				.when()
				.get(BASE_URL + "/{cardId}")
				.then()
				.statusCode(200)
				.extract().asString();
		System.out.println("Response: " + response);
	}

	@Test
	@Order(4)
	public void getCardByIdNotFoundTest() {
		cardId = "0cfdbfc3-15b6-4f6f-abcd-4d53a8344fc8"; // no existe en la base de datos
		String message = given()
				.pathParam("cardId", cardId)
				.when()
				.get(BASE_URL + "/{cardId}")
				.then()
				.statusCode(404)
				.extract().asString();
		System.out.println("Message: " + message);
	}

	@Test
	@Order(5)
	public void getCardByUserIdTest() {
		String userId = "user-oo1"; // exite en la base de datos
		Response response = given()
				.queryParam("userId", userId)
				.when()
				.get(BASE_URL)
				.then()
				.statusCode(200)
				.extract().response();

		System.out.println("Response: " + response.asString());
	}

	@Test
	@Order(6)
	public void getCardByUserIdNotFoundTest() {
		String userId = "user-oo2"; // no existe en la base de datos devuelve una lista vaqcia
		String message = given()
				.queryParam("userId", userId)
				.when()
				.get(BASE_URL)
				.then()
				.statusCode(200)
				.extract().asString();
		System.out.println("Message: " + message);
	}

	@Test
	@Order(7)
	public void deleteCardByIdTest() {
		 cardId = card.getCardId();// exite en la base de datos
		String response = given()
				.pathParam("cardId", cardId)
				.when()
				.delete(BASE_URL + "/{cardId}")
				.then()
				.statusCode(200)
				.extract().asString();
		System.out.println("Response: " + response);
	}

}

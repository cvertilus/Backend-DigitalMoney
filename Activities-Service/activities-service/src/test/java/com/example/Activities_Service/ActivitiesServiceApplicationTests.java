package com.example.Activities_Service;

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
class ActivitiesServiceApplicationTests  extends BaseTest{
	static Activity activityTransfer;
	static Activity activityDeposit;
	static String activityId;


	// This test is to ensure that the activity transfer can be created successfully
	@Test
	@Order(1)
	public void createActivityTransferTest() {
		String requestBody = """
					{
					    "amount": 1000,
						"type": "Transfer",
						"description": "Test activity",
						"origin": "user-oo1",
						"destination": "user-oo2",
						"name": "Test User"
					}
				""";

		activityTransfer = given()
				.contentType("application/json")
				.body(requestBody)
				.pathParam("userId", "user-oo1")
				.when()
				.post(BASE_URL + "/{userId}")
				.then()
				.statusCode(200)
				.extract().as(Activity.class);

		System.out.println("Activity created with ID: " + activityTransfer.getActivityId() );
	}


	// This test is to ensure that the activity deposit can be created successfully
	@Test
	@Order(2)
	public void createActivityDepositTest() {
		String requestBody = """
					{
					    "amount": 500,
						"type": "Deposit",
						"description": "Test deposit activity",
						"name": "Test User",
						"origin": "user-oo1",
						"destination": "user-oo2"
					}
				""";

		activityDeposit = given()
				.contentType("application/json")
				.body(requestBody)
				.pathParam("userId", "user-oo1")
				.when()
				.post(BASE_URL + "/{userId}")
				.then()
				.statusCode(200)
				.extract().as(Activity.class);

		System.out.println("Activity created with ID: " + activityDeposit.getActivityId());
	}

	// This test is to ensure that the activity transfer can be retrieved by its ID
	@Test
	@Order(3)
	public void getActivityTransferByIdTest() {
		activityId = activityTransfer.getActivityId();
		Activity activity = given()
				.pathParam("id", activityId)
				.when()
				.get(BASE_URL + "/{id}")
				.then()
				.statusCode(200)
				.extract().as(Activity.class);

		System.out.println("Activity retrieved: " + activity);
	}


	// This test is to ensure that the activity deposit can be retrieved by its ID
	@Test
	@Order(4)
	public void getActivityDepositByIdTest() {
		String activityId = activityDeposit.getActivityId();
		Activity activity = given()
				.pathParam("id", activityId)
				.when()
				.get(BASE_URL + "/{id}")
				.then()
				.statusCode(200)
				.extract().as(Activity.class);

		System.out.println("Activity retrieved: " + activity);
	}


	// This test is to ensure that activities can be retrieved by user ID
	@Test
	@Order(5)
	public void getActivityByUserIdTest() {
		String userId = "user-oo1";
		String response = given()
				.queryParam("userId", userId)
				.when()
				.get(BASE_URL )
				.then()
				.statusCode(200)
				.extract().asString();
		System.out.println("Activities retrieved for user " + response);

	}

	@Test
	@Order(6)
	public void getActivityByUserIdNotFoundTest() {
		String userId = "user-oo2"; //devuelve una lista vacia
		String response = given()
				.queryParam("userId", userId)
				.when()
				.get(BASE_URL )
				.then()
				.statusCode(200)
				.extract().asString();
		System.out.println("Activities retrieved for user " + response);
	}

	@Test
	@Order(7)
	public void getActivityByIdNotFoundTest() {
		String activityId = "0cfdbfc3-15b6-4f6f-abcd-4d53a8344fc8"; // no existe en la base de datos
		String response = given()
				.pathParam("id", activityId)
				.when()
				.get(BASE_URL + "/{id}")
				.then()
				.statusCode(404)
				.extract().asString();
		System.out.println("Activity not found: " + response);
	}

}


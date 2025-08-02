package com.digitalMoney.demo;

import com.nimbusds.jose.shaded.gson.Gson;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("Mitest")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceApplicationTests  extends  BaseTest{

	static Account account;
	static Long accountId;

	@Test
	@Order(1)
	public void CrearCuentaTest() {
		String requestBody = """
				{
					"userId": "user-oo1",
					"name": "Test User"
				}
			""";

		account = given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post(BASE_URL)
				.then()
				.statusCode(200)
				.extract().as(Account.class);

		System.out.println("Account created: " + account);
		accountId = account.getAccountId();
	}

	@Test
	@Order(2)
	public void CrearCuenta400BadRequestTest() {
		String requestBody = """
				{
					"userId": "user-oo1",
					"name": "Test User"
				}
			""";

		given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post(BASE_URL)
				.then()
				.statusCode(400);
	}

	@Test
	@Order(3)
	public void ObtenerCuentaTest() {

		account = given()
				.pathParam("userId", "user-oo1")
				.when()
				.get(BASE_URL + "/{userId}")
				.then()
				.statusCode(200)
				.extract().as(Account.class);
		System.out.println("Account retrieved with ID: " + account.getAccountId());
	}

	@Test
	@Order(4)
	public void ActualizarCuentaTest() {
		account.setAlias("newAlias");
		Gson gson = new Gson();
		String requestBody = gson.toJson(account);

		String account = given()
				.contentType("application/json")
				.body(requestBody)
				.pathParam("accountId", accountId)
				.when()
				.put(BASE_URL + "/{accountId}")
				.then()
				.extract().asString();

		System.out.println("New Account data " + account);
	}

	@Test
	@Order(5)
	public void crearTransferenciaTest() {
		String requestBody = """
				{
					"cantidad": 100,
					"origin":"newAlias",
					"destino": "newAlias"
				}
			""";

		String response = given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post(BASE_URL + "/transfer")
				.then()
				.extract().asString();

		System.out.println("Transferencia creada exitosamente" + response);
	}

	@Test
	@Order(6)
	public void crearTransferencia404EntityNotFoundTest() {
		String requestBody = """
				{
					"cantitad": 100,
					"origin":"newAlias21",
					"destino": "newAlias21"
				}
			""";

		given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post(BASE_URL + "/transfer")
				.then()
				.statusCode(404);

		System.out.println("Transferencia fallida, Revisar los datos de origen y destino");
	}

	@Test
	@Order(7)
	public void CrearCuentaTest2() {
		String requestBody = """
				{
					"userId": "user-oo2",
					"name": "Test User2"
				}
			""";

		account = given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post(BASE_URL)
				.then()
				.statusCode(200)
				.extract().as(Account.class);

		System.out.println("Account created with ID: " + account.getAccountId());
		accountId = account.getAccountId();
	}

	@Test
	@Order(8)
	public void ActualizarCuentaTest2() {
		account.setAlias("newAlias2");
		Gson gson = new Gson();
		String requestBody = gson.toJson(account);

		account = given()
				.contentType("application/json")
				.body(requestBody)
				.pathParam("accountId", accountId)
				.when()
				.put(BASE_URL + "/{accountId}")
				.then()
				.statusCode(200)
				.extract().as(Account.class);

		System.out.println("New Account data " + account);
	}

	@Test
	@Order(9)
	public void crearTransferenciaTest2() {
		String requestBody = """
				{
					"cantitad": 100,
					"origin":"newAlias2",
					"destino": "newAlias"
				}
			""";

		given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post(BASE_URL + "/transfer")
				.then()
				.statusCode(200);

		System.out.println("Transferencia creada exitosamente");
	}

}

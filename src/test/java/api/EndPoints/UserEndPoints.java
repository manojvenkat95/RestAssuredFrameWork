package api.EndPoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.Payload.User;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response postUser(User payload) {
		Response res = given().contentType("application/json").accept("application/json").body(payload).when()
				.post(Routes.post_URL);

		return res;
	}

	public static Response getUser(String id) {
		Response res = given().contentType("application/json").accept("application/json")
				.pathParam("id", id).when().get(Routes.get_URL);

		return res;
	}

	public static Response updateUser(User payload, String id) {
		Response res = given().contentType("application/json").accept("application/json")
				.pathParam("id", id).body(payload).when().put(Routes.update_URL);

		return res;
	}

	public static Response deleteUser(String id) {
		Response res = given().contentType("application/json").accept("application/json")
				.pathParam("id", id).when().delete(Routes.delete_URL);

		return res;
	}
}

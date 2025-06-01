package api.Test;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.EndPoints.UserEndPoints;
import api.Payload.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserTestsUsingPojo {

	Faker faker;
	User userPayload;
	Response response;
	Logger logger;
	
	@BeforeClass
	public void setupData() {
	    faker = new Faker();
	    userPayload = new User();

	    userPayload.setName(faker.name().username());
	    userPayload.setLocation(faker.address().cityName());
	    userPayload.setPhone(faker.phoneNumber().cellPhone());
	    userPayload.setYearsOld(18);

	    String Courses[] = {"API", "PostMan"};
	    userPayload.setCourses(Courses);

	    logger = LogManager.getLogger(UserTestsUsingPojo.class);

	    logger.debug("Setup Data Completed: " + userPayload.toString()); // debug user payload state
	}

	@Test(priority = 1)
	public void createNewUser() {
	    logger.info("******POST USER START********");
	    logger.debug("Payload before POST: " + userPayload.toString());

	    response = UserEndPoints.postUser(userPayload);

	    logger.debug("Response Status Code: " + response.getStatusCode());
	    logger.debug("Response Body: " + response.getBody().asString());

	    response.then().log().all();
	    response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema.json"));

	    Assert.assertEquals(response.getStatusCode(), 201);
	    Assert.assertEquals(response.jsonPath().getString("name"), userPayload.getName());
	    Assert.assertEquals(response.jsonPath().getString("location"), userPayload.getLocation());
	    Assert.assertEquals(response.jsonPath().getString("phone"), userPayload.getPhone());
	    Assert.assertEquals(response.jsonPath().getInt("yearsOld"), userPayload.getYearsOld());

	    List<String> actualCourses = response.jsonPath().getList("courses");
	    List<String> expectedCourses = Arrays.asList(userPayload.getCourses());

	    Assert.assertEquals(actualCourses, expectedCourses);

	    logger.info("******POST USER FINISH********");
	}

	@Test(priority = 2)
	public void getNewUser() {
	    logger.info("******GET USER START********");
	    String userId = response.jsonPath().getString("id");

	    logger.debug("Getting user with ID: " + userId);

	    response = UserEndPoints.getUser(userId);

	    logger.debug("Response Status Code: " + response.getStatusCode());
	    logger.debug("Response Body: " + response.getBody().asString());

	    response.then().log().body();
	    Assert.assertEquals(response.getStatusCode(), 200);

	    logger.info("******GET USER FINISH********");
	}

	@Test(priority = 3)
	public void updateNewUser() {
	    logger.info("******UPDATE USER START********");

	    userPayload.setName(faker.name().username());
	    userPayload.setLocation(faker.address().cityName());

	    logger.debug("Payload before UPDATE: " + userPayload.toString());

	    response = UserEndPoints.updateUser(userPayload, response.jsonPath().getString("id"));

	    logger.debug("Response Status Code: " + response.getStatusCode());
	    logger.debug("Response Body: " + response.getBody().asString());

	    response.then().log().body();
	    Assert.assertEquals(response.getStatusCode(), 200);

	    logger.info("******UPDATE USER FINISH********");
	}

	@Test(priority = 4)
	public void deleteNewUser() {
	    logger.info("******DELETE USER START********");

	    String userId = response.jsonPath().getString("id");
	    logger.debug("Deleting user with ID: " + userId);

	    response = UserEndPoints.deleteUser(userId);

	    logger.debug("Response Status Code: " + response.getStatusCode());

	    Assert.assertEquals(response.getStatusCode(), 200);

	    logger.info("******DELETE USER FINISH********");
	}

}

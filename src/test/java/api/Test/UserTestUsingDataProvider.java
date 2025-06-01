package api.Test;


import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import api.EndPoints.UserEndPoints;
import api.Payload.User;
import api.Utilities.DataProviderData;
import io.restassured.response.Response;

public class UserTestUsingDataProvider {
	User userPayLoad;
	Response res;

	@Test(dataProvider = "Data", dataProviderClass = DataProviderData.class)
	public void createNewUser(String name, String yearsOld, String location, String phone, String courses, ITestContext context) {
		userPayLoad = new User();
		userPayLoad.setName(name);
		userPayLoad.setYearsOld(Integer.parseInt(yearsOld));
		userPayLoad.setLocation(location);
		userPayLoad.setPhone(phone);
		userPayLoad.setCourses(courses.split(","));

		res = UserEndPoints.postUser(userPayLoad);
		Assert.assertEquals(res.getStatusCode(), 201);

		// Extract ID
		String userId = res.jsonPath().getString("id");

		// GET request
		res = UserEndPoints.getUser(userId);
		Assert.assertEquals(res.getStatusCode(), 200);
		res.then().log().body();
		
		// DELETE request
		res=UserEndPoints.deleteUser(userId);
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}

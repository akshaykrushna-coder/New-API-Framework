package StepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resourse.API_Resources;
import resourse.TestDataBuild;
import resourse.Utils;

public class StepDefination extends Utils {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	Response actualResponse;
	static String place_Id;

	@Given("^Add Place Payload with \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
	public void add_Place_Payload_with(String name, String langauge, String address) throws Throwable {

		reqSpec = given().spec(requestSpecification()).body(data.addPlacePayload(name, langauge, address));

	}

	@When("^User call \"([^\"]*)\" API using \"([^\"]*)\" http request$")
	public void user_call_API_using_http_request(String resource, String httpMethod) throws Throwable {

		API_Resources resourceAPI = API_Resources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (httpMethod.equalsIgnoreCase("POST")) {
			response = reqSpec.when().post(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("GET")) {
			response = reqSpec.when().get(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("DELETE")) {
			response = reqSpec.when().delete(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("PUT")) {
			response = reqSpec.when().put(resourceAPI.getResource());

		}

	}

	@Then("^The API call get success$")
	public void the_API_call_get_success() throws Throwable {

		assertEquals(response.getStatusCode(), 200);

	}

	@Then("^\"([^\"]*)\" in response is \"([^\"]*)\"$")
	public void in_response_is(String key, String expectedValue) throws Throwable {

		assertEquals(getJasonPath(response, key), expectedValue);

	}

	@Then("^verify place id creating map to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_id_creating_map_to_using(String expectedName, String resource) throws Throwable {

		place_Id = getJasonPath(response, "place_id");

		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		reqSpec = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_Id);

		user_call_API_using_http_request(resource, "GET");

		String actualName = getJasonPath(response, "name");

		assertEquals(actualName, expectedName);

		// System.out.println(place_Id);
		// reqSpec = given().spec(requestSpecification()).queryParam("place_id",
		// place_Id);
		// user_call_API_using_http_request(resource, "GET");
		// System.out.println(response.asString());
		// String actualName=getJasonPath(response, "name");
		// System.out.println(actualName);
		// System.out.println(expectedName);
		// assertEquals(expectedName, actualName);

	}
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
		reqSpec =given().spec(requestSpecification()).body(data.deletePlacePayload(place_Id));
	}

}

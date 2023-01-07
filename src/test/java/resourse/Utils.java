package resourse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	protected static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalproperties("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).addQueryParam("key", "qaclick1213")
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public String getGlobalproperties(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Aspire\\workspace\\API_FrameWork\\src\\test\\java\\resourse\\global.properties");
		prop.load(file);
		String url = prop.getProperty(key);
		return url;
	}

	public String getJasonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();

	}
	
	

}

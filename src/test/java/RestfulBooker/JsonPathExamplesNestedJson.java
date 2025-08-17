package RestfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JsonPathExamplesNestedJson {
    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
        requestSpecification = RestAssured.given().log().all().baseUri("https://restful-booker.herokuapp.com/").basePath("booking").contentType(ContentType.JSON).body("{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}");
    }

    @Test
    public void createBooking() {
        ResponseSpecification responseSpecification = RestAssured.expect().
                statusCode(200).contentType(ContentType.JSON).time(Matchers.lessThan(5000L));

        Response response = RestAssured.given(requestSpecification, responseSpecification)
                .post();

        System.out.println("response as string --> " + response.asPrettyString());


    }
}

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

public class JsonPathExamples {
    /**
     * A helper class provided by Rest Assured (io.restassured.path.json.JsonPath).
     * <p>
     * It allows you to navigate inside a JSON response and extract values without manually parsing JSON.
     * <p>
     * It supports dot notation and Groovy expressions to filter JSON.
     */
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

        JsonPath jsonPath = response.jsonPath();
        String bookingId = jsonPath.getString("bookingid");
        System.out.println("booking id in response --> " + bookingId);

        Object bookingIdObject = jsonPath.get("bookingid");
        System.out.println("booking id object in response --> " + bookingIdObject);
        System.out.println("booking id object res --> " + String.valueOf(bookingIdObject).equals(bookingId));

        String checkIn = jsonPath.getString("booking.bookingdates.checkin");
        System.out.println("check in in response --> " + checkIn);

        System.out.println("Print whole response using $ symbol");
        System.out.println((Object) jsonPath.get("$"));

        System.out.println("Print whole response using get string $");
        System.out.println(jsonPath.getString("$"));
    }
}

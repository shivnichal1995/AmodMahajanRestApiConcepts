package RestfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class JsonPathExamplesInlineAssertions {
    public static void main(String[] args) {
RestAssured.given().baseUri("https://restful-booker.herokuapp.com/auth").
                        body("{\n" +
                                "    \"username\" : \"admin\",\n" +
                                "    \"password\" : \"password123\"\n" +
                                "}").
                        contentType(ContentType.JSON).
                        when().
                        post().
                        then().log().all().
                      body("token", Matchers.notNullValue()).
                        body("token.length()", Matchers.is(15));



    }
}

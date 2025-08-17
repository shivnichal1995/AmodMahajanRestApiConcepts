package RestfulBooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

public class GetBookingByIdGET {
    public static void main(String[] args) {
        Response res = RestAssured.given().log().all().
                baseUri("https://restful-booker.herokuapp.com/booking/").
                basePath("{id}").
                pathParam("id", "1").
                get();

        System.out.println(res.getTimeIn(TimeUnit.MILLISECONDS));
        System.out.println(res.then().time(Matchers.lessThan(3000L)));
        System.out.println(res.then().time(Matchers.greaterThan(10L)));
        System.out.println(res.then().time(Matchers.both(Matchers.greaterThan(30L))
                .and(Matchers.lessThan(3000L))));
    }
}

package RestfulBooker;

import io.restassured.RestAssured;

public class GetBookingByIdGET {
    public static void main(String[] args) {
        RestAssured.given().log().all().
                baseUri("https://restful-booker.herokuapp.com/booking/").basePath("1").
        get().then().log().all();
    }
}

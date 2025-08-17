package RestfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleHeadersValues {

    /**
     * Headers are metadata associated with the request and response of API
     * It may contain auth token, cookies, content type, proxies
     * Req and Res can both can have assciated headers
     */
    @Test
    public void createUserWithHeaders() {

        // 1) Sample header values ---->
        RestAssured.given()
                .header("Content-Type", "application/json")   // Body type
                .header("Accept", "application/json")        // Expected response type
                .header("Authorization", "Bearer your_token_here") // OAuth2/JWT token
                .header("x-api-key", "12345abcde")                // Custom API key
                .header("Cache-Control", "no-cache");

        // 2) Multiple headers in one --->
        RestAssured.given()
                .headers(
                        "Content-Type", "application/json",
                        "Accept", "application/json",
                        "Authorization", "Bearer abc123"
                );

        // 3)  Using Header class - Header → represents a single header
        Header header = new Header("Content-Type", "application/json");
        RestAssured.given().header(header);


        // 4) Using Headers class - Header →represents a collection of multiple headers
        /* Create multiple Header objects*/
        Header h1 = new Header("Content-Type", "application/json");
        Header h2 = new Header("Accept", "application/json");
        Header h3 = new Header("x-api-key", "12345");

        /* Put them into Headers collection */
        Headers headers = new Headers(h1, h2, h3);
        RestAssured.given().headers(headers);

        // 5) Using map
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("h1", "v1");
        headersMap.put("h2", "v2");
        RestAssured.given().headers(headersMap);

        // 6) list of headers
        List<Header> headerList = new ArrayList<>();
        Header h4 = new Header("Content-Type", "application/json");
        Header h5 = new Header("Accept", "application/json");
        Header h6 = new Header("x-api-key", "12345");
        headerList.add(h4);
        headerList.add(h5);
        headerList.add(h6);

        Headers headers2 = new Headers(headerList);
        RestAssured.given().headers(headers2);
    }
}

package RestfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.List;

public class JsonPathExamplesJsonArray {
    public static void main(String[] args) {

/* ---------------- sample json array response ----->

[
  {
    "id": 1,
    "name": "John",
    "skills": ["Java", "Selenium", "RestAssured"]
  },
  {
    "id": 2,
    "name": "Alice",
    "skills": ["Python", "API Testing"]
  }
]

--------------------------------------------------- */

        // JSON Array as String
        String jsonArrayString = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"John\",\n" +
                "    \"skills\": [\"Java\", \"Selenium\", \"RestAssured\"]\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Alice\",\n" +
                "    \"skills\": [\"Python\", \"API Testing\"]\n" +
                "  }\n" +
                "]";

        JsonPath jsonPath = new JsonPath(jsonArrayString);

        System.out.println("Print first json in array");
        System.out.println(jsonPath.getString("[0]"));

        int secondUserId = jsonPath.getInt("[1].id");
        System.out.println("Second user id -->" + secondUserId);

        List<String> allNames = jsonPath.getList("name");
        System.out.println("All names --> " + allNames);

        System.out.println("Second skill of first user --> " + jsonPath.getString("[0].skills[1]"));

        List<String> skills = jsonPath.getList("skills");
        System.out.println("skills of all users --> " + skills);

        List<String> firstUserSkills = jsonPath.getList("[0].skills");
        System.out.println("skills of first user --> " + firstUserSkills);
    }
}

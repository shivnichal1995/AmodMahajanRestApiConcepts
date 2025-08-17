package RestfulBooker;

import io.restassured.path.json.JsonPath;

import java.io.File;
import java.util.List;
import java.util.logging.Filter;

public class JsonPathExamplesWithFilters {
    public static void main(String[] args) {

        // Read json from external file
        String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\RestfulBooker\\SampleJsonFile.json";
        File jsonArrayFile = new File(filePath);

        JsonPath jsonPath = new JsonPath(jsonArrayFile);
        System.out.println(jsonPath.getString("[0]"));
/*

Key Points
find { ... } → returns the first matching object
findAll { ... } → returns all matching objects as a list
it → refers to the current object in the JSON array
You can apply .name, .id, .skills, etc. on above json after filtering

*/

        System.out.println("Id by name --> ");
        System.out.println(jsonPath.getString("find{it.id==2}.name"));

        System.out.println("Users who has skill as API Testing --> ");
        System.out.println(jsonPath.getString("findAll{it.skills.contains('API Testing')}.name"));

        List<Integer> idsGreaterThan1 = jsonPath.getList("findAll { it.id > 1 }.id");
        System.out.println("IDs > 1: " + idsGreaterThan1);
    }
}

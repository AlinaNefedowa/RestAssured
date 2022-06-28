package guru.qa;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ReqresTests extends TestBase {

    String createUser = "/api/users",
            users2Url = "/api/users/2",
            userNotFound = "/api/users/23";

    String bodyForUserCreation = "{\"name\": \"Rosa\",\"job\": \"artist\"}",
            bodyForUserUpdate = "{\"name\": \"Rosa\",\"job\": \"zion resident\"}";

    @Test
    @DisplayName("Get second user")
    @Tag("getSecondUser")
    void getSecondUser() {
        given()
                .log().uri()
                .when()
                .get(users2Url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2));
    }


    @Test
    @DisplayName("Create user")
    @Tag("createUser")
    void createUser() {
        given()
                .log().uri()
                .log().body()
                .body(bodyForUserCreation)
                .contentType(ContentType.JSON)
                .when()
                .post(createUser)
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Rosa"))
                .body("job", is("artist"));
    }

    @Test
    @DisplayName("Update user")
    @Tag("updateUser")
    void updateUser() {
        given()
                .log().uri()
                .log().body()
                .body(bodyForUserUpdate)
                .contentType(ContentType.JSON)
                .when()
                .put(users2Url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("Rosa"))
                .body("job", is("zion resident"));
    }


    @Test
    @DisplayName("Delete user")
    @Tag("deleteUser")
    void deleteUser() {
        given()
                .when()
                .delete(users2Url)
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    @DisplayName("Single user not found")
    @Tag("singleUserNotFound")
    void singleUserNotFound() {
        given()
                .when()
                .get(userNotFound)
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }
 }

/**
 * This file is part of surmiser-app <https://github.com/StevenJDH/quarkus-surmiser-app>.
 * Copyright (C) 2020-2023 Steven Jenkins De Haro.
 *
 * surmiser-app is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * surmiser-app is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with surmiser-app.  If not, see <http://www.gnu.org/licenses/>.
 */

package stevenjdh.demo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

@QuarkusTest
class PersonResourceTest {
    
    final String BAD_REQUEST_MSG = "Name was invalid or not provided.";

    @Test
    void Should_ReturnOk_ForValidName() {
        given()
            .queryParam("name", "steven")
            .when().get("/api/person")
            .then()
                .statusCode(200)
                .body("name", is("steven"),
                    "genderPrediction.gender", is(oneOf("male", "female")),
                    "agePrediction.age", greaterThan(0),
                    "nationalityPrediction.country", hasSize(greaterThan(3)))
                .log().all();
    }

    // Testing invalid parameters separately because batching passes always.
    @Test
    void Should_ReturnBadRquest_ForEmptyName() {
        given()
            .queryParam("name", "")
            .when().get("/api/person")
            .then()
                .statusCode(400)
                .body(is(BAD_REQUEST_MSG))
                .log().all();
    }

    @Test
    void Should_ReturnBadRquest_ForMissingName() {
        given()
            .when().get("/api/person")
            .then()
                .statusCode(400)
                .body(is(BAD_REQUEST_MSG))
                .log().all();
    }

    @Test
    void Should_ReturnBadRquest_ForNameWithSpaces() {
        given()
            .queryParam("name", "John Doe")
            .when().get("/api/person")
            .then()
                .statusCode(400)
                .body(is(BAD_REQUEST_MSG))
                .log().all();
    }
}
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

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import stevenjdh.demo.repositories.HistoryRepository;

@QuarkusTest
class HistoryResourceTest {

    @Inject
    HistoryRepository history;

    @Test
    void Should_ReturnOk_ForList() {
        given()
            .when().get("/api/history")
            .then()
                .statusCode(200)
                .body("size()", is(oneOf(5, 6)), // Test classes run in a different order under different environments, and PersonResourceTest persists data.
                    "name", hasItems("Moe", "Larry", "Curly", "Shemp", "Joe"))
                .log().all();
    }

    @Test
    void Should_ReturnOk_ForValidName() {
        final String NAME = "curly";

        given()
            .queryParam("name", NAME) // Uses a seeded name.
            .when().get("/api/history")
            .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1),
                        "$.name", everyItem(equalToIgnoringCase(NAME)))
                .log().all();
    }

    @Test
    void Should_ReturnBadRquest_ForNameWithSpaces() {
        given()
            .queryParam("name", "John Doe")
            .when().get("/api/history")
            .then()
                .statusCode(400)
                .body(is("Invalid name provided."))
                .log().all();
    }

    @Test
    void Should_ReturnFiveItems_ForSeededNames() {
        // Test classes run in a different order under different environments, and PersonResourceTest persists data.
        assertThat(history.count(), either(is(5L)).or(is(6L)));
    }

    @Test
    @Transactional
    void Should_SaveNewHistoryEntry_ForName() {
        String expectedName = "John";
        var id = history.saveRecord(expectedName);

        Assertions.assertEquals(expectedName, history.findById(id).name);
        history.deleteById(id);
    }
}

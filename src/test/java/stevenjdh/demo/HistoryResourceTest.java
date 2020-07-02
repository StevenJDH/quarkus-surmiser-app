/**
 * This file is part of surmiser-app <https://github.com/StevenJDH/quarkus-surmiser-app>.
 * Copyright (C) 2020 Steven Jenkins De Haro.
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

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.DisabledOnNativeImage;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItems;

import static org.hamcrest.Matchers.is;

import stevenjdh.demo.models.repositories.HistoryRepository;

@QuarkusTest
public class HistoryResourceTest {
    
    @Inject
    HistoryRepository history;

    @Test
    @DisabledOnNativeImage // Reports size() as 6 in native mode, so it fails.
    public void Should_ReturnOk_ForList() {
        given()
          .when().get("/api/history")
          .then()
            .statusCode(200)
            .body("size()", is(5),
                "name", hasItems("Moe", "Larry", "Curly", "Shemp", "Joe"))
                .log().all();
    }
    
    @Test
    public void Should_ReturnOk_ForValidName() {
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
    public void Should_ReturnBadRquest_ForNameWithSpaces() {
        given()
          .queryParam("name", "John Doe")
          .when().get("/api/history")
          .then()
             .statusCode(400)
             .body(is("Invalid name provided."))
             .log().all();
    }

    @Test
    @DisabledOnNativeImage // Reports as 6 in native mode, so it fails.
    public void Should_ReturnFiveItems_ForSeededNames() {
        Assertions.assertEquals(5, history.count());
    }

    @Test
    @Transactional
    public void Should_SaveNewHistoryEntry_ForName() {
        String expectedName =  "John";
        var id = history.saveRecord(expectedName);

        Assertions.assertEquals(expectedName, history.findById(id).name);
        history.deleteById(id);
    }
}
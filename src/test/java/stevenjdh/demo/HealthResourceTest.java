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

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class HealthResourceTest {
    
    @Test
    public void Should_ReturnOkWithUpStatuses_ForAllHealthChecks() {
		given()
          .when().get("/health")
          .then()
            .statusCode(200)
            .body("status", is("UP"),
                "checks.status", everyItem(equalTo("UP")))
                .log().all();
    }

    @Test
    public void Should_ReturnOkWithUpStatus_ForReadinessCheck() {
		given()
          .when().get("/health/ready")
          .then()
            .statusCode(200)
            .body("status", is("UP"))
                .log().all();
    }

    @Test
    public void Should_ReturnOkWithUpStatus_ForLivenessCheck() {
		given()
          .when().get("/health/live")
          .then()
            .statusCode(200)
            .body("status", is("UP"))
                .log().all();
    }
}
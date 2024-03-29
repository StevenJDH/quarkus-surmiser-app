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
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import stevenjdh.demo.models.Determination;
import stevenjdh.demo.repositories.HistoryRepository;
import stevenjdh.demo.services.AgeService;
import stevenjdh.demo.services.GenderService;
import stevenjdh.demo.services.NationalityService;

@Path("/api/person")
public class PersonResource {

    @Inject
    HistoryRepository history;

    @Inject
    @RestClient
    GenderService genderService;

    @Inject
    @RestClient
    AgeService ageService;

    @Inject
    @RestClient
    NationalityService nationalityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response person(@QueryParam("name") String name) {

        if (name == null || name.isBlank() || name.contains(" ")) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Name was invalid or not provided.")
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .build();
        }

        var dtr = new Determination();

        dtr.name = name;
        dtr.genderPrediction = genderService.getGenderByName(name);
        dtr.agePrediction = ageService.getAgeByName(name);
        dtr.nationalityPrediction = nationalityService.getNationalityByName(name);
        history.saveRecord(name);

        return Response.ok(dtr).build();
    }
}
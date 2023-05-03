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

package stevenjdh.demo.services;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import stevenjdh.demo.models.NationalityPrediction;
import stevenjdh.demo.models.NationalityPrediction.Country;

@Path("/")
@RegisterRestClient
public interface NationalityService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 3, delay = 2000)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.75, delay = 5000)
    @Fallback(NationalityFallback.class)
    public NationalityPrediction getNationalityByName(@QueryParam("name") String name);

    public static class NationalityFallback implements FallbackHandler<NationalityPrediction> {

        private static final NationalityPrediction GENERIC_NATIONALITY = NationalityPrediction.of(List.of(Country.of("XX", 0.0f)));
        
        @Override
        public NationalityPrediction handle(ExecutionContext context) {
            return GENERIC_NATIONALITY;
        }

    }

}